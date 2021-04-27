package dev.erikchristensen.javamath2kmp

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.todo

class MathTest {
    @Test
    fun floorMod_IntInt() {
        assertEquals(1, 4 floorMod 3)
        assertEquals(2, -4 floorMod 3)
        assertEquals(-1, -4 floorMod -3)
    }

    @Test
    fun floorMod_LongLong() {
        assertEquals(1L, 4L floorMod 3L)
        assertEquals(2L, -4L floorMod 3L)
        assertEquals(-1L, -4L floorMod -3L)
    }

    @Test
    fun floorMod_LongInt() {
        assertEquals(1, 4L floorMod 3)
        assertEquals(2, -4L floorMod 3)
        assertEquals(-1, -4L floorMod -3)
    }

    @Test
    fun floorDiv_IntInt() {
        todo { assertEquals(Int.MIN_VALUE, Int.MIN_VALUE floorDiv -1) } // Hangs on mingw
        assertEquals(1, 4 floorDiv 3)
        assertEquals(-2, -4 floorDiv 3)
    }

    @Test
    fun floorDiv_LongLong() {
        todo { assertEquals(Long.MIN_VALUE, Long.MIN_VALUE floorDiv -1L) } // Hangs on mingw
        assertEquals(1L, 4L floorDiv 3L)
        assertEquals(-2L, -4L floorDiv 3L)
    }

    @Test
    fun floorDiv_LongInt() {
        todo { assertEquals(Long.MIN_VALUE, Long.MIN_VALUE floorDiv -1) } // Hangs on mingw
        assertEquals(1L, 4L floorDiv 3)
        assertEquals(-2L, -4L floorDiv 3)
    }

    @Test
    fun plusExact_IntInt_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { Int.MAX_VALUE plusExact 1 }
        assertFailsWith<ArithmeticException> { Int.MAX_VALUE plusExact Int.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE plusExact -1 }
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE plusExact Int.MIN_VALUE }
    }

    @Test
    fun plusExact_IntInt_addsWhenResultFits() {
        assertEquals(Int.MAX_VALUE, Int.MAX_VALUE - 1 plusExact 1)
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE + 1 plusExact -1)
    }

    @Test
    fun plusExact_IntLong_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { 1 plusExact Long.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Int.MAX_VALUE plusExact Long.MAX_VALUE }
        assertFailsWith<ArithmeticException> { -1 plusExact Long.MIN_VALUE }
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE plusExact Long.MIN_VALUE }
    }

    @Test
    fun plusExact_IntLong_addsWhenResultFits() {
        assertEquals(Int.MAX_VALUE.toLong(), Int.MAX_VALUE - 1 plusExact 1L)
        assertEquals(Int.MIN_VALUE.toLong(), Int.MIN_VALUE + 1 plusExact -1L)
    }

    @Test
    fun plusExact_LongInt_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE plusExact 1 }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE plusExact Int.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE plusExact -1 }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE plusExact Int.MIN_VALUE }
    }

    @Test
    fun plusExact_LongInt_addsWhenResultFits() {
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE - 1 plusExact 1)
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE + 1 plusExact -1)
    }

    @Test
    fun plusExact_LongLong_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE plusExact 1L }
        assertFailsWith<ArithmeticException> { 1L plusExact Long.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE plusExact -1L }
        assertFailsWith<ArithmeticException> { -1L plusExact Long.MIN_VALUE }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE plusExact Long.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE plusExact Long.MIN_VALUE }
    }

    @Test
    fun plusExact_LongLong_addsWhenResultFits() {
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE - 1 plusExact 1L)
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE + 1 plusExact -1L)
    }

    @Test
    fun minusExact_IntInt_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE minusExact 1 }
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE minusExact Int.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Int.MAX_VALUE minusExact -1 }
        assertFailsWith<ArithmeticException> { Int.MAX_VALUE minusExact Int.MIN_VALUE }
    }

    @Test
    fun minusExact_IntInt_subtractsWhenResultFits() {
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE + 1 minusExact 1)
        assertEquals(Int.MAX_VALUE, Int.MAX_VALUE - 1 minusExact -1)
    }

    @Test
    fun minusExact_IntLong_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { 0 minusExact Long.MIN_VALUE }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE minusExact Int.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE minusExact -1 }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE minusExact Int.MIN_VALUE }
    }

    @Test
    fun minusExact_IntLong_subtractsWhenResultFits() {
        assertEquals(Long.MIN_VALUE, -1 minusExact Long.MAX_VALUE)
    }

    @Test
    fun minusExact_LongInt_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE minusExact 1 }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE minusExact Int.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE minusExact -1 }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE minusExact Int.MIN_VALUE }
    }

    @Test
    fun minusExact_LongInt_subtractsWhenResultFits() {
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE + 1 minusExact 1)
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE - 1 minusExact -1)
    }

    @Test
    fun minusExact_LongLong_throwsOnOverflow() {
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE minusExact 1L }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE minusExact Long.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE minusExact -1L }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE minusExact Long.MIN_VALUE }
    }

    @Test
    fun minusExact_LongLong_subtractsWhenResultFits() {
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE + 1 minusExact 1L)
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE - 1 minusExact -1L)
    }

    @Test
    fun timesExact_throwsWhenMultiplyingLongsCausesOverflow() {
        assertFailsWith<ArithmeticException> { (Long.MAX_VALUE / 2 + 1) timesExact 2 }
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE timesExact Long.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE timesExact Long.MIN_VALUE }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE timesExact Long.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE timesExact -1 }
    }

    @Test
    fun timesExact_multiplesLongsWhenResultFits() {
        assertEquals(Long.MAX_VALUE, Long.MAX_VALUE timesExact 1)
        assertEquals(Long.MAX_VALUE - 1, Long.MAX_VALUE / 2 timesExact 2)
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE timesExact 1)
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE / 2 timesExact 2)
    }

    @Test
    fun timesExact_throwsWhenMultiplyingIntsCausesOverflow() {
        assertFailsWith<ArithmeticException> { (Int.MAX_VALUE / 2 + 1) timesExact 2 }
        assertFailsWith<ArithmeticException> { Int.MAX_VALUE timesExact Int.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE timesExact Int.MIN_VALUE }
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE timesExact Int.MAX_VALUE }
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE timesExact -1 }
    }

    @Test
    fun timesExact_multiplesIntsWhenResultFits() {
        assertEquals(Int.MAX_VALUE, Int.MAX_VALUE timesExact 1)
        assertEquals(Int.MAX_VALUE - 1, Int.MAX_VALUE / 2 timesExact 2)
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE timesExact 1)
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE / 2 timesExact 2)
    }

    @Test
    fun toIntExact_throwsWhenResultCausesOverflow() {
        assertFailsWith<ArithmeticException> { (Int.MAX_VALUE + 1L).toIntExact() }
        assertFailsWith<ArithmeticException> { (Int.MIN_VALUE - 1L).toIntExact() }
    }

    @Test
    fun toIntExact_convertsToIntWhenResultFits() {
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE.toLong()).toIntExact())
        assertEquals(Int.MIN_VALUE, (Int.MIN_VALUE.toLong()).toIntExact())
    }

    @Test
    fun negateExact_Int_throwsWhenResultCausesOverflow() {
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE.negateExact() }
    }

    @Test
    fun negateExact_Int_negatesWhenResultFits() {
        assertEquals(-Int.MAX_VALUE, Int.MAX_VALUE.negateExact())
        assertEquals(Int.MAX_VALUE, (Int.MIN_VALUE + 1).negateExact())
        assertEquals(0, 0.negateExact())
    }

    @Test
    fun negateExact_Long_throwsWhenResultCausesOverflow() {
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE.negateExact() }
    }

    @Test
    fun negateExact_Long_negatesWhenResultFits() {
        assertEquals(-Long.MAX_VALUE, Long.MAX_VALUE.negateExact())
        assertEquals(Long.MAX_VALUE, (Long.MIN_VALUE + 1).negateExact())
        assertEquals(0, 0.negateExact())
    }

    @Test
    fun incExact_Int_throwsWhenResultCausesOverflow() {
        assertFailsWith<ArithmeticException> { Int.MAX_VALUE.incExact() }
    }

    @Test
    fun incExact_Int_incrementsValueWhenResultFits() {
        assertEquals(Int.MAX_VALUE, (Int.MAX_VALUE - 1).incExact())
        assertEquals(1, 0.incExact())
    }

    @Test
    fun incExact_Long_throwsWhenResultCausesOverflow() {
        assertFailsWith<ArithmeticException> { Long.MAX_VALUE.incExact() }
    }

    @Test
    fun incExact_Long_incrementsValueWhenResultFits() {
        assertEquals(Long.MAX_VALUE, (Long.MAX_VALUE - 1).incExact())
        assertEquals(1L, 0L.incExact())
    }

    @Test
    fun decExact_Int_throwsWhenResultCausesOverflow() {
        assertFailsWith<ArithmeticException> { Int.MIN_VALUE.decExact() }
    }

    @Test
    fun decExact_Int_incrementsValueWhenResultFits() {
        assertEquals(Int.MIN_VALUE, (Int.MIN_VALUE + 1).decExact())
        assertEquals(-1, 0.decExact())
    }

    @Test
    fun decExact_Long_throwsWhenResultCausesOverflow() {
        assertFailsWith<ArithmeticException> { Long.MIN_VALUE.decExact() }
    }

    @Test
    fun decExact_Long_incrementsValueWhenResultFits() {
        assertEquals(Long.MIN_VALUE, (Long.MIN_VALUE + 1).decExact())
        assertEquals(-1L, 0L.decExact())
    }
}
