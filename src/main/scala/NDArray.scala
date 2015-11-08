object Sub

class NDArray[T](values: Array[T], dims: List[Int]) {
  val ndims: Int = dims.size

  def apply (index: Int*): T = {
    /**
     * Index with row-major order
     */
    require(dims.size == index.size)
    values(column_major(index, dims))
  }

  def sub (index: Any*): NDArray[T] = {
    val slices = index map { ix =>
      println(ix.getClass)
      require(ix == Sub || ix.isInstanceOf[Int] )
      ix match {
        case ix: Int =>
          false
        case Sub =>
          true
      }
    }
    this
  }

  private def column_major(index: Seq[Int], dims: Seq[Int]): Int = {
    /**
     * Column major order -- we need to offset by the product of the remaining
     * dimension sizes
     */
    index match {
      case Nil => 0
      case _ => index.head * dims.tail.product + column_major(index.tail, dims.tail)
    }
  }
}
