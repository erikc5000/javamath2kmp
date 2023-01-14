@file:JvmMultifileClass
@file:JvmName("MathKt")

package dev.erikchristensen.javamath2kmp

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Returns the floor modulus of this value and another value.
 */
expect infix fun Long.floorMod(other: Long): Long

/**
 * Returns the floor modulus of this value and another value.
 */
expect infix fun Int.floorMod(other: Int): Int

/**
 * Returns the floor modulus of this value and another value.
 */
expect infix fun Int.floorMod(other: Long): Long

/**
 * Returns the floor modulus of this value and another value.
 */
expect infix fun Long.floorMod(other: Int): Int

/**
 * Returns the floor division of this value and another value.
 */
expect infix fun Long.floorDiv(other: Long): Long

/**
 * Returns the floor division of this value and another value.
 */
expect infix fun Int.floorDiv(other: Int): Int

/**
 * Returns the floor division of this value and another value.
 */
expect infix fun Int.floorDiv(other: Long): Long

/**
 * Returns the floor division of this value and another value.
 */
expect infix fun Long.floorDiv(other: Int): Long

/**
 * Returns the sum of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect infix fun Long.plusExact(other: Long): Long

/**
 * Returns the sum of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect infix fun Int.plusExact(other: Int): Int

/**
 * Returns the difference of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect infix fun Long.minusExact(other: Long): Long

/**
 * Returns the difference of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect infix fun Int.minusExact(other: Int): Int

/**
 * Returns the product of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect infix fun Long.timesExact(other: Long): Long

/**
 * Returns the product of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect infix fun Int.timesExact(other: Int): Int

/**
 * Returns the product of this value and another value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect infix fun Long.timesExact(other: Int): Long

/**
 * Returns the negation of this value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect fun Int.negateExact(): Int

/**
 * Returns the negation of this value, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect fun Long.negateExact(): Long

/**
 * Converts this value to an [Int], throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect fun Long.toIntExact(): Int

/**
 * Returns this value incremented by one, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect fun Int.incExact(): Int

/**
 * Returns this value incremented by one, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect fun Long.incExact(): Long

/**
 * Returns this value decremented by one, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect fun Int.decExact(): Int

/**
 * Returns this value decremented by one, throwing an exception if overflow occurs.
 * @throws ArithmeticException if overflow occurs
 */
expect fun Long.decExact(): Long
