package dev.erikchristensen.javamath2kmp

actual infix fun Long.floorMod(other: Long): Long = (this % other + other) % other
actual infix fun Int.floorMod(other: Int): Int = (this % other + other) % other
actual infix fun Long.floorMod(other: Int): Int = (this floorMod other.toLong()).toInt()

actual infix fun Long.floorDiv(other: Long): Long {
    val result = this / other

    return if (this xor other < 0 && result * other != this) {
        result - 1
    } else {
        result
    }
}

actual infix fun Int.floorDiv(other: Int): Int {
    val result = this / other

    return if (this xor other < 0 && result * other != this) {
        result - 1
    } else {
        result
    }
}

actual infix fun Long.floorDiv(other: Int): Long = this floorDiv other.toLong()

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

actual infix fun Long.timesExact(other: Int): Long = this timesExact other.toLong()

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

actual fun Int.incExact(): Int = this plusExact 1
actual fun Long.incExact(): Long = this plusExact 1

actual fun Int.decExact(): Int = this minusExact 1
actual fun Long.decExact(): Long = this minusExact 1
