object shorts{
  def main(args: Array[String]){
    
    // Irjunk fuggvenyt, mely parameterkent egy karaktert es egy egesz erteket kap, es ennyi darabot ir ki egymas utan az atadott karakterbol! //

  val number = 10
  val letter = 'a'
  def writechar(number:Int, letter:Char) = {
    for( i <- 0 until number)
      print(letter + " ")
  }
  writechar(number, letter)
    
    // Irjunk fuggvenyt mely parameterkent egy valos(a) és egy pozitiv(n) egesz szamot kap. Visszateresi erteke pedig: an //

    val a:Int = 10 
    val n:Int = 2
    
    def power(number:Int, to_power:Int) ={
      var a_power = 1
      if ( n > 0){
        a_power = number
        for( i <- 1 until to_power)
          a_power*= number
      }
      if ( n < 0){
        println("Hibas hatvany megadas.")
        ("")
      }
      else
        a_power
    }
      println("")
      println("" + power(a,n))
    
    // Irjunk fuggvenyt, mely harom atadott egesz szamot nagysag szerint sorba rendez, a valtozas a visszaadott ertekekben latszik! //
    
    val numbers:Array[Int] = Array[Int](11, 4, -1)
    def sort( numbers:Array[Int]) ={
      def swap( numbers:Array[Int], j:Int) ={
        var tmp = numbers(j)
        numbers(j) = numbers(j-1)
        numbers(j-1) = tmp
      }
      for( i <- 1 until numbers.length; j <- 1 until numbers.length){
        if( numbers(j) < numbers(j-1)){
          swap(numbers, j)
        }
      }
      numbers
    }
    sort(numbers)
    for( i <- numbers)
      println( i)
  }
}
