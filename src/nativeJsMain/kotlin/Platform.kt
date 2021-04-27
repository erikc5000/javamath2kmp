package dev.erikchristensen.javamath2kmp

import kotlin.mod as kotlinMod
import kotlin.floorDiv as kotlinFloorDiv

actual infix fun Long.floorMod(other: Long): Long = kotlinMod(other)
actual infix fun Int.floorMod(other: Int): Int = kotlinMod(other)
actual infix fun Int.floorMod(other: Long): Long = kotlinMod(other)
actual infix fun Long.floorMod(other: Int): Int = kotlinMod(other)

actual infix fun Long.floorDiv(other: Long): Long = kotlinFloorDiv(other)
actual infix fun Int.floorDiv(other: Int): Int = kotlinFloorDiv(other)
actual infix fun Int.floorDiv(other: Long): Long = kotlinFloorDiv(other)
actual infix fun Long.floorDiv(other: Int): Long = kotlinFloorDiv(other)

actual infix fun Long.plusExact(other: Long): Long {
    val result = this + other

    if (this xor result < 0L && this xor other >= 0L) {
        throw ArithmeticException("'$this + $other' overflows a Long")
    }

    return result
}

actual infix fun Int.plusExact(other: Int): Int {
    val result = this + other

    if (this xor result < 0 && this xor other >= 0) {
        throw ArithmeticException("'$this + $other' overflows an Int")
    }

    return result
}

actual infix fun Long.minusExact(other: Long): Long {
    val result = this - other

    if (this xor result < 0L && this xor other < 0L) {
        throw ArithmeticException("'$this - $other' overflows a Long")
    }

    return result
}

actual infix fun Int.minusExact(other: Int): Int {
    val result = this - other

    if (this xor result < 0 && this xor other < 0) {
        throw ArithmeticException("'$this - $other' overflows an Int")
    }

    return result
}

actual infix fun Long.timesExact(other: Long): Long {
    return when {
        other == 1L -> this
        this == 1L -> other
        this == 0L || other == 0L -> 0L
        else -> {
            val total = this * other

            if ((this == Long.MIN_VALUE && other == -1L) ||
                (other == Long.MIN_VALUE && this == -1L) ||
                total / other != this
            ) {
                throw ArithmeticException("'$this * $other' overflows a Long")
            }

            total
        }
    }
}

actual infix fun Int.timesExact(other: Int): Int {
    val total = this.toLong() * other.toLong()

    if (total !in Int.MIN_VALUE..Int.MAX_VALUE) {
        throw ArithmeticException("'$this * $other' overflows an Int")
    }

    return total.toInt()
}

actual fun Int.negateExact(): Int {
    if (this == Int.MIN_VALUE) {
        throw ArithmeticException("'$this' can't be negated without overflow")
    }
    return -this
}

actual fun Long.negateExact(): Long {
    if (this == Long.MIN_VALUE) {
        throw ArithmeticException("'$this' can't be negated without overflow")
    }
    return -this
}

actual fun Long.toIntExact(): Int {
    if (this !in Int.MIN_VALUE..Int.MAX_VALUE) {
        throw ArithmeticException("'$this' can't be converted to Int without overflow")
    }

    return toInt()
}

actual fun Int.incExact(): Int {
    if (this == Int.MAX_VALUE) {
        throw ArithmeticException("'$this' can't be incremented without overflow")
    }

    return inc()
}

actual fun Long.incExact(): Long {
    if (this == Long.MAX_VALUE) {
        throw ArithmeticException("'$this' can't be incremented without overflow")
    }

    return inc()
}

actual fun Int.decExact(): Int {
    if (this == Int.MIN_VALUE) {
        throw ArithmeticException("'$this' can't be decremented without overflow")
    }

    return dec()
}

actual fun Long.decExact(): Long {
    if (this == Long.MIN_VALUE) {
        throw ArithmeticException("'$this' can't be decremented without overflow")
    }

    return dec()
}
