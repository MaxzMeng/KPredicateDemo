package org.example


fun main() {
    val invalidUserContext = mapOf(
        "age" to 20,
        "name" to "Bob",
        "isActive" to true
    )

    val userPredicate = KPredicate(
        "age >= 18 && (name == \"John\" OR name == \"Jane\") && isActive == true",
        context = invalidUserContext
    )
    println("result: ${userPredicate.evaluate()}") // 应该输出 false
} 