package org.example

import org.example.antlr.PredicateParser
import org.example.antlr.PredicateParserBaseVisitor

internal class PredicateEvaluator(private val context: Map<String, Any>) : PredicateParserBaseVisitor<Boolean>() {

    companion object {
        private const val TAG = "PredicateEvaluator"
    }

    override fun visitPredicate(ctx: PredicateParser.PredicateContext): Boolean {
        Log.d(TAG, "Visiting predicate: ${ctx.text}")
        return ctx.expr()?.let { visit(it) } ?: false
    }

    override fun visitExpr(ctx: PredicateParser.ExprContext): Boolean {
        Log.d(TAG, "Visiting expr: ${ctx.text}")
        val comparisonSize = ctx.comparison().size
        if (comparisonSize == 0) {
            return false
        }
        val firstComparison = ctx.comparison(0)
        var result = visit(firstComparison)
        if (comparisonSize == 1) {
            return result
        }
        for (i in 1 until ctx.comparison().size) {
            val nextComparison = ctx.comparison(i)
            val nextValue = visit(nextComparison)
            val logicOp = ctx.logicOp(i - 1)
            result = when {
                logicOp.AND() != null -> result && nextValue
                logicOp.OR() != null -> result || nextValue
                else -> false
            }
        }
        return result
    }

    override fun visitComparison(ctx: PredicateParser.ComparisonContext): Boolean {
        Log.d(TAG, "Visiting comparison: ${ctx.text}")
        val inParen = ctx.LPAREN() != null
        if (inParen) {
            return ctx.expr()?.let { visit(it) } ?: false
        }

        val identifier = ctx.IDENTIFIER()?.text ?: return false
        val operator = ctx.op() ?: return false
        val comparisonValue = ctx.value() ?: return false
        val value = when {
            comparisonValue.STRING() != null -> {
                val text = comparisonValue.STRING().text
                text.trim('\'', '"')
            }

            comparisonValue.NUMBER() != null -> {
                comparisonValue.NUMBER().text.toDouble()
            }

            comparisonValue.BOOLEAN() != null -> {
                val boolText = ctx.value().BOOLEAN().text
                boolText.equals("true", ignoreCase = true)
            }

            else -> return false
        }
        val contextValue = context[identifier]
        Log.d(TAG, "Comparing: $identifier ${operator.text} $value with context value: $contextValue")

        when {
            operator.EQUALS() != null -> return value == contextValue
            operator.NOT_EQUALS() != null -> return value != contextValue
        }
        val contextValueNum = (contextValue as? Number)?.toDouble() ?: 0.0
        val valueNum = (value as? Number)?.toDouble() ?: 0.0
        return when {
            operator.GREATER() != null -> contextValueNum > valueNum
            operator.LESS() != null -> contextValueNum < valueNum
            operator.GREATER_EQUALS() != null -> contextValueNum >= valueNum
            operator.LESS_EQUALS() != null -> contextValueNum <= valueNum
            else -> false
        }
    }
} 