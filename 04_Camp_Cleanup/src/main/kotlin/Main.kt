import java.io.File

fun main(args: Array<String>) {
    val elfPairs =  File(args[0]).readLines().map {
        ElfPair.parseRawInput(it)
    }
    val totalReducible = elfPairs.sumOf { pair ->
        if (pair.isReducibleAssignment())  1 else 0 as Int
    }
    val totalOverlaps = elfPairs.sumOf {
        if (it.hasOverlap()) 1 else 0 as Int
    }
    println("Reducible assignments: $totalReducible")
    println("Overlapping assignments: $totalOverlaps")
}