# KPredicate Demo

[English](#English) | [中文](#Chinese)

## English

### Project Description
This is a Kotlin implementation of NSPredicate for the JVM platform. It allows users to dynamically evaluate logical expressions at runtime, similar to Apple's NSPredicate. The library provides a flexible way to define and evaluate complex conditions using a simple string-based syntax.

### API Usage

#### Basic Usage
```kotlin
// Create a context map with your variables
val context = mapOf(
    "age" to 20,
    "name" to "John",
    "isActive" to true
)

// Create a predicate
val predicate = KPredicate(
    "age > 18 AND name == \"John\" AND isActive == true",
    context = context
)

// Evaluate the predicate
val result = predicate.evaluate() // returns true
```

#### Supported Operators
- Comparison: `==`, `!=`, `>`, `<`, `>=`, `<=`
- Logical: `AND` (or `&&`), `OR` (or `||`)
- Parentheses: `(`, `)` for grouping expressions

#### Value Types
- Numbers: `age > 18`
- Strings: `name == "John"`
- Booleans: `isActive == true`

## Chinese

### 项目描述
这是一个使用 Kotlin 实现的 JVM 平台的仿 NSPredicate 实现。它允许用户在运行时动态地评估逻辑表达式，类似于 Apple 的 NSPredicate。该库提供了一种灵活的方式来定义和评估复杂条件，使用简单的基于字符串的语法。

### API 使用说明

#### 基本用法
```kotlin
// 创建包含变量的上下文映射
val context = mapOf(
    "age" to 20,
    "name" to "John",
    "isActive" to true
)

// 创建语句
val predicate = KPredicate(
    "age > 18 AND name == \"John\" AND isActive == true",
    context = context
)

// 执行语句
val result = predicate.evaluate() // 返回 true
```

#### 支持的运算符
- 比较运算符：`==`, `!=`, `>`, `<`, `>=`, `<=`
- 逻辑运算符：`AND`（或 `&&`）, `OR`（或 `||`）
- 括号：`(`, `)` 用于表达式分组

#### 值类型
- 数字：`age > 18`
- 字符串：`name == "John"`
- 布尔值：`isActive == true`
