import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ElfAssignmentTests {
    @Test
    fun testElfAssignmentParsingRange() {
        val assignment = ElfAssignment.parseRange("1-4")
        assertEquals(1, assignment.start)
        assertEquals(4, assignment.end)
    }
    @Test
    fun testElfAssignmentParsingSingleRange() {
        val assignment = ElfAssignment.parseRange("3-3")
        assertEquals(3, assignment.start)
        assertEquals(3, assignment.end)
    }

    @Test
    fun testElfAssignmentParsingInvalidRange() {
        assertThrows<IllegalArgumentException> { ElfAssignment.parseRange("4-3") }
    }

    @Test
    fun testElfAssignmentParsingInvalidSingleDigitRange() {
        assertThrows<IllegalArgumentException> { ElfAssignment.parseRange("4") }
    }

    @Test
    fun testElfAssignmentParsingInvalidMultiRange() {
        assertThrows<IllegalArgumentException> { ElfAssignment.parseRange("4-5-6") }
    }
}