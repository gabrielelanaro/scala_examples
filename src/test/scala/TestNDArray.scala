import org.scalatest.FlatSpec

class FirstSpec extends FlatSpec {

  "A NDArray" should "return his elements column-wise order" in {
      val array = new NDArray[Double](Array(0, 1, 2,
                                            3, 4, 5), List(2, 3))
      assert(array(0, 0) == 0.0)
      assert(array(0, 1) == 1.0)
      assert(array(1, 0) == 3.0)

      val array3D = new NDArray[Double](Array(0, 1, 2,
                                              3, 4, 5, //

                                              6, 7, 8,
                                              9, 10, 11), List(2, 2, 3))

      assert(array3D(0, 0, 0) == 0.0)
      assert(array3D(0, 1, 0) == 3.0)
      assert(array3D(1, 0, 0) == 6.0)
  }

  it should "return sub arrays" in {
      val array = new NDArray[Double](Array(0, 1, 2,
                                            3, 4, 5), List(2, 3))

      // array.slice(0, array.size, 0)
  }
}
