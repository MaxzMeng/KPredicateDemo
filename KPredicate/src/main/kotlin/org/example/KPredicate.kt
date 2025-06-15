package org.example

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.example.antlr.PredicateLexer
import org.example.antlr.PredicateParser

class KPredicate(
    predicateString: String,
    private val context: Map<String, Any>
) {
    private val parser: PredicateParser

    init {
        val input = CharStreams.fromString(predicateString)
        val lexer = PredicateLexer(input)
        val tokens = CommonTokenStream(lexer)
        parser = PredicateParser(tokens)
    }

    fun evaluate(): Boolean {
        val tree = parser.predicate()
        return PredicateEvaluator(context).visit(tree)
    }
}