import scala.io.Source
import scala.util.Using
import scala.util.matching.Regex

object Main {
  val dirRegExp: Regex = "dir (.+)".r
  val fileRegExp: Regex = "^([0-9]+) (.+)".r

  def processLines(root: FileDirectory, lines: List[String]): FileDirectory = {
    var currentDir = root
    lines.foreach {
      case "$ cd /" => currentDir = root
      case cdup if cdup == "$ cd .." =>
        currentDir = currentDir.parent.get
      case cd if cd.startsWith("$ cd ") =>
        val dirName = cd.replace("$ cd ", "")
        currentDir.content.find(s => s.name == dirName).foreach(f => {
          currentDir = f.asInstanceOf[FileDirectory]
        })
      case lsDirMatch if dirRegExp.matches(lsDirMatch) =>
        currentDir.add(FileDirectory(dirRegExp.findFirstMatchIn(lsDirMatch).get.group(1), Option(currentDir)))
      case lsFileMatch if fileRegExp.matches(lsFileMatch) =>
        val groups = fileRegExp.findFirstMatchIn(lsFileMatch).get
        currentDir.add(
          VFile(groups.group(2), groups.group(1).toInt, Option(currentDir))
        )
      case "$ ls" =>
      case _ =>
    }

    root
  }

  def getAllDirs(fd: FileDirectory): List[FileDirectory] = {
    List(fd).appendedAll(fd.content.flatMap(f => f match {
      case cfd: FileDirectory => getAllDirs(cfd)
      case _ => List.empty
    }))
  }

  def main(args: Array[String]): Unit = {
    val root = FileDirectory("/")

    Using(Source.fromFile("./input.txt")) { source =>
      processLines(
        root,
        source.getLines.toList
      )

      val allSmall = getAllDirs(root).map(d => d.size).filter(s => s <= 100000).sum
      println("Part one solution size: " + allSmall)

      println("Total size " + root.size)
      val totalDisk = 70000000
      val target = 30000000
      val free = totalDisk - root.size
      val smallestDirSizeToDelete = getAllDirs(root).sortWith((a, b) => a.size < b.size).map {d => d.size}
      print("Smallest dir size: ")
      println(smallestDirSizeToDelete.find(s => (free + s) > target))
    }


  }
}