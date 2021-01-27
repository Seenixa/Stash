object sudoku{
  def main(args: Array[String]) :Unit ={
    
    val r = scala.util.Random
        
    def map: Array[Array[Int]] ={
      val rows = 3
      val columns = 9
      var helperNumber = 0
      var i = -1
      var j = -1
      var retry = false
      var oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
      var numbers = Array.ofDim[Int]( rows, columns)      
      while(i < 2){
        retry = false
        i+= 1
        j = -1
        oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
        while(j < 8 && retry == false){
          j+= 1
          numbers(i)(j) = oneToNine( r.nextInt( oneToNine.length))
          oneToNine-= (numbers(i)(j))
          helperNumber = 0
          while( checkBox(numbers) == false && retry == false){
            oneToNine+= numbers(i)(j)
            numbers(i)(j) = oneToNine( r.nextInt( oneToNine.length))
            helperNumber+= 1
            if( helperNumber == 100){
              retry = true
              i = -1
              j = -1
            }
          }
        }
      }
      numbers
    }
    
    def checkRow( row: Int, numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      var timesNumberInRow = 0
      for(i <- 1 to 9){
        timesNumberInRow = 0
        for(j <- 0 until 9){
          if (numbers(row)(j) == i)
            timesNumberInRow+= 1
          if( timesNumberInRow == 2)
            check = false
        }
      }
      check
    }
    
    def checkColumn( column :Int, numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      var timesNumberInColumn = 0
      for(i <- 1 to 9){
        timesNumberInColumn = 0
        for(j <- 0 until 3){
          if( numbers(j)(column) == i)
            timesNumberInColumn+= 1
          if( timesNumberInColumn == 2)
            check = false
        }
      }
      check
    }
    
    def checkBox( numbers: Array[Array[Int]]) :Boolean ={
      var check = true
      var timesNumberInBox = 0
      for( i <- 1 to 9 ; j <- 0 until 3){
        timesNumberInBox = 0
        for(k <- 0 until 3; l <- 0 until 3){
          if( numbers(k)(l + (j * 3)) == i)
            timesNumberInBox+= 1
          if( timesNumberInBox == 2)
            check = false
        }
      }
      check
    }
    def printGame :Unit ={
      val numbers = map
      for(i <- 0 to 2){
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
