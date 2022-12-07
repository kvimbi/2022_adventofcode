data class ElfPair(
    val first: ElfAssignment,
    val second: ElfAssignment,
) {
    fun isReducibleAssignment(): Boolean {
        return first.fullyContains(second) || second.fullyContains(first)
    }

    fun hasOverlap():Boolean {
        return first.hasOverlapWith(second)
    }

    companion object {
        fun parseRawInput(line: String): ElfPair {
            if (!line.contains(",")) {
                throw IllegalArgumentException("Range pair string must contain ','")
            }
            val elves = line.split(",")
            if (elves.size != 2) {
                throw IllegalArgumentException("Range must contain exactly two elves separated by '-'")
            }
            return ElfPair(
                ElfAssignment.parseRange(elves[0]),
                ElfAssignment.parseRange(elves[1]),
            )
        }
    }
}