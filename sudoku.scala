object sudoku{
  def main(args: Array[String]) :Unit ={
    
    val r = scala.util.Random
    
    def map(): Array[Array[Int]] ={
      val rows = 9
      val columns = 9
      var numbers = Array.ofDim[Int]( rows, columns)      
      for(i <- 0 to 8; j <- 0 to 8){
        numbers(i)(j) = r.nextInt(9) + 1
        while( checkLine( i, j, numbers) == false || checkColumn( i, j, numbers) == false || checkBox( i, j, numbers) == false)
          numbers(i)(j) = r.nextInt(9) + 1
      }
      
      numbers
    }
    
    def checkLine( row :Int, column: Int, numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      for(j <- 0 until column){
        if( numbers(row)(column) == numbers(row)(j))
          check = false
      }
      check
    }
    
    def checkColumn( row :Int, column: Int, numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      for(i <- 0 until row){
        if( numbers(row)(column) == numbers(i)(column))
          check = false
      }
      check
    }
    
    def checkBox( row :Int, column: Int, numbers: Array[Array[Int]]) :Boolean ={
      true
    }
    
    
    
    def printGame :Unit ={
      val numbers = map()
      for(i <- 0 to 8){
        if( i > 0)
          println("| \n")
        for(j <- 0 to 8){
          if( j == 3 ||j == 6)
            print( "||" + numbers(i)(j) )
          else
            print("|" + numbers(i)(j))
        }
        if( i == 8)
        print("|")
      }
      
        
    }
    
    
    printGame
  }
}
