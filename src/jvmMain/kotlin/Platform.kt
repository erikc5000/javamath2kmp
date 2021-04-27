@file:JvmMultifileClass
@file:JvmName("MathKt")

package dev.erikchristensen.javamath2kmp

actual infix fun Long.floorMod(other: Long): Long = Math.floorMod(this, other)
actual infix fun Int.floorMod(other: Int): Int = Math.floorMod(this, other)
actual infix fun Int.floorMod(other: Long): Long = this.toLong() floorMod other
actual infix fun Long.floorMod(other: Int): Int = (this floorMod other.toLong()).toInt()

actual infix fun Long.floorDiv(other: Long): Long = Math.floorDiv(this, other)
actual infix fun Int.floorDiv(other: Int): Int = Math.floorDiv(this, other)
actual infix fun Int.floorDiv(other: Long): Long = this.toLong() floorDiv other
actual infix fun Long.floorDiv(other: Int): Long = this floorDiv other.toLong()

actual infix fun Long.plusExact(other: Long): Long = Math.addExact(this, other)
actual infix fun Int.plusExact(other: Int): Int = Math.addExact(this, other)

actual infix fun Long.minusExact(other: Long): Long = Math.subtractExact(this, other)
actual infix fun Int.minusExact(other: Int): Int = Math.subtractExact(this, other)

actual infix fun Long.timesExact(other: Long): Long = Math.multiplyExact(this, other)
actual infix fun Int.timesExact(other: Int): Int = Math.multiplyExact(this, other)

actual fun Int.negateExact(): Int = Math.negateExact(this)
actual fun Long.negateExact(): Long = Math.negateExact(this)

actual fun Long.toIntExact(): Int = Math.toIntExact(this)

actual fun Int.incExact(): Int = Math.incrementExact(this)
actual fun Long.incExact(): Long = Math.incrementExact(this)

actual fun Int.decExact(): Int = Math.decrementExact(this)
actual fun Long.decExact(): Long = Math.decrementExact(this)
