import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class ElfPairTests {
    @Test
    fun `parse range pair 2-4,3-6 and should succeed`() {
        val pair = ElfPair.parseRawInput("2-4,3-6")
        assertEquals(2, pair.first.start)
        assertEquals(4, pair.first.end)
        assertEquals(3, pair.second.start)
        assertEquals(6, pair.second.end)
    }

    @Test
    fun `parse range pair 2-4 and should fail`() {
        assertThrows<IllegalArgumentException> { ElfPair.parseRawInput("2-4") }
    }

    @Test
    fun `parse range pair 2-4,3-6,4-7 and should fail`() {
        assertThrows<IllegalArgumentException> { ElfPair.parseRawInput("2-4,3-6,4-7") }
    }

    companion object {
        @JvmStatic
        fun getData(): List<Arguments> {
            return listOf(
                Arguments.of(ElfPair(ElfAssignment(2, 4), ElfAssignment(5, 8)), false),
                Arguments.of(ElfPair(ElfAssignment(4, 6), ElfAssignment(5, 8)), false),
                Arguments.of(ElfPair(ElfAssignment(5, 6), ElfAssignment(5, 8)), true),
                Arguments.of(ElfPair(ElfAssignment(6, 7), ElfAssignment(5, 8)), true),
                Arguments.of(ElfPair(ElfAssignment(7, 8), ElfAssignment(5, 8)), true),
                Arguments.of(ElfPair(ElfAssignment(7, 9), ElfAssignment(5, 8)), false),
                Arguments.of(ElfPair(ElfAssignment(8, 9), ElfAssignment(5, 8)), false),
                Arguments.of(ElfPair(ElfAssignment(9, 10), ElfAssignment(5, 8)), false),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(2, 4)), false),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(4, 6)), false),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(5, 6)), true),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(6, 7)), true),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(7, 8)), true),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(7, 9)), false),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(8, 9)), false),
                Arguments.of(ElfPair(ElfAssignment(5, 8), ElfAssignment(9, 10)), false),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun `test assignment combinations`(pair: ElfPair, expectedResult: Boolean) {
        assertEquals(expectedResult, pair.isReducibleAssignment())
    }
}