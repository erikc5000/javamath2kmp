@file:JvmMultifileClass
@file:JvmName("MathKt")

package dev.erikchristensen.javamath2kmp

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

infix fun Int.plusExact(other: Long): Long = this.toLong() plusExact other
infix fun Int.minusExact(other: Long): Long = this.toLong() minusExact other

infix fun Long.plusExact(other: Int): Long = this plusExact other.toLong()
infix fun Long.minusExact(other: Int): Long = this minusExact other.toLong()
