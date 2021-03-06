object sudoku{
  def main(args: Array[String]) :Unit ={
    
    val r = scala.util.Random
        
    def map(): Array[Array[Int]] ={
      val rows = 9
      val columns = 9
      var oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
      var numbers = Array.ofDim[Int]( rows, columns)      
      for(i <- 0 to 8){
        oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
        for(j <- 0 to 8){
          numbers(i)(j) = oneToNine( r.nextInt( oneToNine.length))
          oneToNine-= (numbers(i)(j))
          while( checkLine( i, j, numbers) == false || checkColumn( i, j, numbers) == false || checkBox( i, j, numbers) == false)
            numbers(i)(j) = oneToNine( r.nextInt( oneToNine.length))
        }
      }
      numbers
    }
    
    def checkLine( row :Int, column: Int, numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      for(j <- 0 until column){
        if( numbers(row)(column) == numbers(row)(j))
          check = false
      }
      true
    }
    
    def checkColumn( row :Int, column: Int, numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      for(i <- 0 until row){
        if( numbers(row)(column) == numbers(i)(column))
          check = false
      }
      true
    }
    
    def checkBox( row :Int, column: Int, numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      for( i <- ((row / 3) * 3) to row){
        if( row % 3 == i % 3){
          for( j <- ((column / 3) * 3) to column)
            if( numbers(row)(column) == numbers(i)(j))
              if( row != i || column != j)
                check = false
        }
        if( row % 3 == 1 && i % 3 == 0 || row % 3 == 2 && i % 3 < 2){
          for( j <- ( ( column / 3) * 3) to ( ( ( column / 3) * 3) + 2))
            if( numbers(row)(column) == numbers(i)(j))
              if( row != i || column != j)
                check = false
        }
      }
      true  
    }
        
    def printGame :Unit ={
      val numbers = map()
      for(i <- 0 to 8){
        if( i > 0)
          println("| \n")
        if( i == 0 )
          println("-------------------\n")
        if( i == 3 || i == 6 )
          println("════════════════════\n")
        for(j <- 0 to 8){
          if( j == 3 ||j == 6)
            print( "│" + numbers(i)(j) )
          else
            print("|" + numbers(i)(j))
        }
        if( i == 8)
        print("| \n-------------------")
      }        
    }
    
    printGame
  }
}
