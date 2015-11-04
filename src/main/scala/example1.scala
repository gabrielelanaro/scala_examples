object Example1 {
    // How to reverse a list
    def exec(args: Array[String]) {
    println("Example on how to reverse a list in scala")
    val input: List[Int] = List(0, 1, 2, 3, 4)
    val expected: List[Int] = List(4, 3, 2, 1, 0)
    
    println(reverse(input))
    
    println("Example on how to find if list is palindrome")
    println(isPalindrome(List(0, 1, 2, 1, 0)))

    }
    def reverse[A] (list: List[A]) : List[A] = {
        list match {
            case h :: tail =>
                reverse(tail) ::: List(h)
            case _ =>
                Nil
        }
    }
    
    def isPalindrome(list: List[Any]) : Boolean = {
        list match {
            case Nil => // Zero elements
                true
            case List(a) => // Single element
                true
            case list => 
                list.head == list.last && isPalindrome(list.init.tail)
        }
    }
}
