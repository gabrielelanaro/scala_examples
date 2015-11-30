

class NDArray[T](values: Array[T], dims: List[Int]) {
  val ndims: Int = dims.size

  def apply (index: Int*): T = {
    /**
     * Index with row-major order
     */
    require(dims.size == index.size)
    values(column_major(index, dims))
  }

  // def slice(first: Int, last: Int, axis: Int) : NDArray[T] {
  //   /**
  //    * Slice along a certain dimension
  //    */
  //    require(axis < ndims)
  //
  // }
  // 
  // def ix(index: Int, axis: Int) : NDArray[T] {
  //   require(axis < ndims)
  //   val newdims: List[Int] = for ((d, i) <- dims.zipWithIndex if i != axis) yield i
  //
  //   val ixToCombine = for ((i, dim) <- dims.zipWithIndex) {
  //       if (i == axis) {
  //         val indices = List(index)
  //       }
  //       else {
  //         val indices = 0 to dim
  //       }
  //     } yield indices
  //
  //   ixToRetrieve = ixToCombine.foldLeft(_ cross _)
  // }
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
