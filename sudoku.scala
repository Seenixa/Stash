object sudoku{
  def main(args: Array[String]) :Unit ={
    
    val r = scala.util.Random
      
    def mapSegmentOne: Array[Array[Int]] ={
      val rows = 3
      val columns = 9
      var helperNumber = 0
      var i = -1
      var j = -1
      var retry = false
      var oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
      var numbers = Array.ofDim[Int]( rows, columns)      
      while( i < (rows - 1)){
        retry = false
        i+= 1
        j = -1
        oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
        while( j < (columns - 1) && retry == false){
          j+= 1
          numbers(i)(j) = oneToNine( r.nextInt( oneToNine.length))
          oneToNine-= numbers(i)(j)
          helperNumber = 0
          while( checkBox(numbers) == false && retry == false){
            oneToNine+= numbers(i)(j)
            numbers(i)(j) = oneToNine( r.nextInt( oneToNine.length))
            oneToNine-= numbers(i)(j)
            helperNumber+= 1
            if( helperNumber == 50){
              
              retry = true
              i = -1
              numbers = Array.ofDim[Int]( rows, columns)
            }
          }
        }
      }
      numbers
    }
      
    def mapSegmentTwo( numbersFirst: Array[Array[Int]]) :Array[Array[Int]] ={
      val rows = 3
      val columns = 9
      var helperNumber = 0
      var i = -1
      var j = -1
      var retry = false
      var oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
      val checkOnList = oneToNine
      var numbersSecond = Array.ofDim[Int]( rows, columns)
      while( j < ( columns - 1)){
        retry = false
        j+= 1
        i = -1
        oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
        if( oneToNine == checkOnList){
          while( i < (rows - 1) && retry == false){
            i+= 1
            oneToNine-= numbersFirst(i)(j)
          }
          i = -1
        }
        while( i < ( rows - 1) && retry == false){
          i+= 1
          numbersSecond(i)(j) = oneToNine( r.nextInt( oneToNine.length))
          oneToNine-= numbersSecond(i)(j)
          helperNumber = 0
          while( checkBox(numbersSecond) == false && retry == false){
            oneToNine+= numbersSecond(i)(j)
            numbersSecond(i)(j) = oneToNine( r.nextInt( oneToNine.length))
            oneToNine-= numbersSecond(i)(j)
            helperNumber+= 1
            if( helperNumber == 50){
              retry = true
              j = -1
              numbersSecond = Array.ofDim[Int]( rows, columns)
            }
          }
        }
      } 
      numbersSecond
    }
    
    def mapSegmentThree( numbersFirst: Array[Array[Int]], numbersSecond: Array[Array[Int]]) :Array[Array[Int]] ={
      val rows = 3
      val columns = 9
      var helperNumber = 0
      var i = -1
      var j = -1
      var retry = false
      var oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
      val checkOnList = oneToNine
      var numbersThird = Array.ofDim[Int]( rows, columns)
      while( j < ( columns - 1)){
        retry = false
        j+= 1
        i = -1
        oneToNine = scala.collection.mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
        if( oneToNine == checkOnList){
          while( i < (rows - 1) && retry == false){
            i+= 1
            oneToNine-= numbersFirst(i)(j)
            oneToNine-= numbersSecond(i)(j)
          }
          i = -1
        }
        while( i < ( rows - 1) && retry == false){
          i+= 1
          numbersThird(i)(j) = oneToNine( r.nextInt( oneToNine.length))
          oneToNine-= numbersThird(i)(j)
          helperNumber = 0
          while( checkBox(numbersSecond) == false && retry == false){
            oneToNine+= numbersThird(i)(j)
            numbersSecond(i)(j) = oneToNine( r.nextInt( oneToNine.length))
            oneToNine-= numbersThird(i)(j)
            helperNumber+= 1
            if( helperNumber == 50){
              retry = true
              j = -1
              numbersThird = Array.ofDim[Int]( rows, columns)
            }
          }
        }
      } 
      numbersThird
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
    
    val firstSegment = mapSegmentOne
    val secondSegment = mapSegmentTwo( firstSegment)
    val thirdSegment = mapSegmentThree( firstSegment, secondSegment)
    
    def printGameOne :Unit ={
      val numbers = firstSegment
      for(i <- 0 to 2){
        if( i > 0)
          println("| \n")
        if( i == 0 )
          println("═══════════════════\n")
        for(j <- 0 to 8){
          if( j == 3 ||j == 6)
            print( "│" + numbers(i)(j) )
          else
            print("|" + numbers(i)(j))
        }
      }        
    }
    
    def printGameTwo :Unit ={
      val numbers = secondSegment
      for(i <- 0 to 2){
        if( i > 0)
          println("| \n")
        if( i == 0 )
          println("|\n═══════════════════\n")
        for(j <- 0 to 8){
          if( j == 3 ||j == 6)
            print( "│" + numbers(i)(j) )
          else
            print("|" + numbers(i)(j))
        }
      }        
    }
    def printGameThree :Unit ={
      val numbers = thirdSegment
      for(i <- 0 to 2){
        if( i > 0)
          println("| \n")
        if( i == 0 )
          println("|\n═══════════════════\n")
        for(j <- 0 to 8){
          if( j == 3 ||j == 6)
            print( "│" + numbers(i)(j) )
          else
            print("|" + numbers(i)(j))
        }
        if( i == 2)
         println("|\n═══════════════════\n")
      }        
    }
    
    printGameOne
    printGameTwo
    printGameThree
  }  
}
