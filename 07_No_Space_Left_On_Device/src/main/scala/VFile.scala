sealed trait BaseFile {
  def name: String

  def size: Int

  def parent: Option[FileDirectory]
}

case class VFile(name: String, size: Int, parent: Option[FileDirectory]) extends BaseFile

case class FileDirectory(name: String, parent: Option[FileDirectory] = Option.empty, var content: List[BaseFile] = List.empty) extends BaseFile {
  def add(e: BaseFile): Unit = {
    content = content.appended(e)
  }
  override def size: Int = content.map {
    case VFile(_, size, _) => size
    case FileDirectory(_, _, content) => content.map(c => c.size).sum
  }.sum
}
