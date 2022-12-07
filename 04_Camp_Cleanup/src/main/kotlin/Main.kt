import java.io.File

fun main(args: Array<String>) {
    val totalReducible = File(args[0]).readLines().map { line ->
        return@map if (ElfPair.parseRawInput(line).isReducibleAssignment())  1 else 0
    }.sum()
    println("Reducible assignements: $totalReducible")
}