@file:JvmMultifileClass
@file:JvmName("MathKt")

package dev.erikchristensen.javamath2kmp

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Returns the sum of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
infix fun Int.plusExact(other: Long): Long = this.toLong() plusExact other

/**
 * Returns the sum of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
infix fun Long.plusExact(other: Int): Long = this plusExact other.toLong()

/**
 * Returns the difference of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
infix fun Int.minusExact(other: Long): Long = this.toLong() minusExact other

/**
 * Returns the difference of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
infix fun Long.minusExact(other: Int): Long = this minusExact other.toLong()

/**
 * Returns the product of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
infix fun Long.timesExact(other: Int): Long = this timesExact other.toLong()

/**
 * Returns the product of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
infix fun Int.timesExact(other: Long): Long = this.toLong() timesExact other
