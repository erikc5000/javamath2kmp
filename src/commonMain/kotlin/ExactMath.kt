@file:JvmMultifileClass
@file:JvmName("MathKt")

package dev.erikchristensen.javamath2kmp

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.math.abs

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
infix fun Int.timesExact(other: Long): Long = other timesExact this

/**
 * Returns the absolute value of a number, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
fun absExact(n: Int): Int {
    if (n == Int.MIN_VALUE) {
        throw ArithmeticException("The absolute value of '$n' overflows an Int")
    }
    return abs(n)
}

/**
 * Returns the absolute value of a number, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
fun absExact(n: Long): Long {
    if (n == Long.MIN_VALUE) {
        throw ArithmeticException("The absolute value of '$n' overflows a Long")
    }
    return abs(n)
}
