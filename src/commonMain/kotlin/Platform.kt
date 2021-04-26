@file:JvmMultifileClass
@file:JvmName("MathKt")

package dev.erikchristensen.javamath2kmp

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

expect infix fun Long.floorMod(other: Long): Long
expect infix fun Int.floorMod(other: Int): Int
expect infix fun Long.floorMod(other: Int): Int

expect infix fun Long.floorDiv(other: Long): Long
expect infix fun Int.floorDiv(other: Int): Int
expect infix fun Long.floorDiv(other: Int): Long

expect infix fun Long.plusExact(other: Long): Long
expect infix fun Int.plusExact(other: Int): Int

expect infix fun Long.minusExact(other: Long): Long
expect infix fun Int.minusExact(other: Int): Int

expect infix fun Long.timesExact(other: Long): Long
expect infix fun Int.timesExact(other: Int): Int
expect infix fun Long.timesExact(other: Int): Long

expect fun Int.negateExact(): Int
expect fun Long.negateExact(): Long

expect fun Long.toIntExact(): Int

expect fun Int.incExact(): Int
expect fun Long.incExact(): Long

expect fun Int.decExact(): Int
expect fun Long.decExact(): Long
