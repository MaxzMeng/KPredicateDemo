package org.example

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class KPredicateTest {
    @Test
    fun testSimpleComparison() {
        val context = mapOf("age" to 20)
        val predicate = KPredicate(
            "age > 18",
            context = context
        )
        assertTrue(predicate.evaluate())
    }

    @Test
    fun testComplexExpression() {
        val context = mapOf(
            "age" to 20,
            "name" to "John"
        )
        val predicate = KPredicate("age > 18 AND name == \"John\"", context)
        assertTrue(predicate.evaluate())
    }

    @Test
    fun testNestedExpression() {
        val context = mapOf(
            "age" to 15,
            "name" to "John"
        )
        val predicate = KPredicate("(age > 18 OR age < 10) AND name == \"John\"", context)
        assertFalse(predicate.evaluate())
    }
} 