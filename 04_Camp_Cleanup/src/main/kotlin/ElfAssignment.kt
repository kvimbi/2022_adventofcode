data class ElfAssignment(
    val start: Int,
    val end: Int
) {
    fun fullyContains(other: ElfAssignment): Boolean {
        return other.start >= start && other.end <= end
    }

    fun hasOverlapWith(other: ElfAssignment): Boolean {
        return (start <= other.start && end >= other.end) ||
                (start >= other.start && start <= other.end) ||
                (end >= other.start && end <= other.end)
    }

    companion object {
        fun parseRange(rangeString: String): ElfAssignment {
            if (!rangeString.contains("-")) {
                throw IllegalArgumentException("Range string must contain '-'")
            }
            val parts = rangeString.split("-")
            if (parts.size != 2) {
                throw IllegalArgumentException("Range must contain exactly one '-'")
            }
            val start = parts[0].toInt()
            val end = parts[1].toInt()
            if (end < start) {
                throw IllegalArgumentException("End can not be less than start")
            }
            return ElfAssignment(start, end)
        }
    }
}

