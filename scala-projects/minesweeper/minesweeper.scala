object minesweeper extends App{
  import scala.collection.mutable.ArrayBuffer
  val r = scala.util.Random
  
  class table(
    var Xlength :Int = 10,
    var Ylength :Int = 10,
    var numberOfMines :Int = 90,
  ){    
    var tableSize = Xlength * Ylength
    if( tableSize <= numberOfMines)
      numberOfMines = tableSize
    
    def getMinesPosition :Array[Int] ={
      var numberList = scala.collection.mutable.ListBuffer[Int]()

      for( i <- 1 until tableSize)
        numberList += i
      var minesPosition = new Array[Int](numberOfMines)
      for( i <- 0 until numberOfMines - 1){
        minesPosition(i) = numberList(r.nextInt( numberList.length))
        numberList -= minesPosition(i)    
      }
      minesPosition
    }
    
    def getNumbersPosition( minesPosition: Array[Int]) :Array[Int] ={
      var numberList = scala.collection.mutable.ListBuffer[Int]()
      var numbersPosition = new Array[Int](tableSize - numberOfMines)
      for( i <- 1 until tableSize)
        numberList += i
      for( minePosition <- minesPosition)
        numberList -= minePosition
      for(i <- 0 until tableSize - numberOfMines)
        numbersPosition(i) = numberList(i)
      numbersPosition
    }
    
    def convertMinesPosition( minesPosition: Array[Int]) :Array[mines] ={
      var realMinesPosition = new Array[mines](numberOfMines)
      var realMine = new mines()
      for( i <- 0 until minesPosition.length){
        realMine.Yposition = (minesPosition(i) / Xlength) + 1
        realMine.Xposition = (minesPosition(i) % Xlength) + 1
        realMinesPosition(i) = realMine
        realMine = new mines()
      }
      realMinesPosition
    }
    
    def convertNumbersPosition( numbersPosition: Array[Int]) :Array[numbers] ={
      var realNumbersPosition = new Array[numbers](tableSize - numberOfMines)
      var realNumber = new numbers()
      for( i <- 0 until numbersPosition.length){
        realNumber.Yposition = (numbersPosition(i) / Xlength) + 1
        realNumber.Xposition = (numbersPosition(i) % Xlength) + 1
        realNumbersPosition(i) = realNumber
        realNumber = new numbers()
      }
      realNumbersPosition
    }
    
    def displayTable( minesArray: Array[Int], numbersArray: Array[Int]): String ={
      var stringLine = ""
      var number = 0
      var minesPosition = minesArray.toSeq
      var numbersPosition = numbersArray.toSeq
      for( columnTile <- 0 until Ylength){
        stringLine += "\n "
        for( lineTile <- 0 until Xlength){
          if( minesPosition.contains(lineTile + (columnTile * Xlength))){
            stringLine += "X "
          }
          if( numbersPosition.contains(lineTile + (columnTile * Xlength))){
            if( minesPosition.contains(lineTile + (columnTile * Xlength) - 1))                  // Eggyel balra néz            39 41 40 46 / 47 / 48 54 53 55             -3 -1 -2 2 / 3 / 4 8 7 9 
              if( lineTile != 0)                                                                //                             21 19 20 26 / 27 / 28 34 33 35     
                number += 1
            
            if( minesPosition.contains(lineTile + (columnTile * Xlength) + 1))                  // Eggyel jobbra néz
              if( lineTile != Xlength - 1)
                number += 1
            
            
            if( minesPosition.contains(lineTile + (columnTile * Xlength) - Xlength))            // Felfele néz
              if( columnTile != 0)
                number += 1
            
            
            if( minesPosition.contains(lineTile + (columnTile * Xlength) + Xlength))            // Lefelé néz
              if( columnTile != Ylength - 1)
                number += 1
            
            
            if( minesPosition.contains(lineTile + (columnTile * Xlength) - Xlength - 1))        // Balra felfelé néz
              if( lineTile != 0 && columnTile != 0)
                number += 1
            
            
            if( minesPosition.contains(lineTile + (columnTile * Xlength) - Xlength + 1))        // Jobbra felfelé néz
              if( lineTile != Xlength - 1 && columnTile != 0)
                number += 1
            
            
            if( minesPosition.contains(lineTile + (columnTile * Xlength) + Xlength - 1))        // Balra lefelé néz
              if( lineTile != 0 && columnTile != Ylength - 1)
                number += 1
            
            
            if( minesPosition.contains(lineTile + (columnTile * Xlength) + Xlength + 1))        // Jobbra lefelé néz
              if( lineTile != Xlength - 1 && columnTile != Ylength - 1)
                number += 1
            
            
            stringLine += s"$number "
            number = 0
          }
        }
      }
     stringLine
   }
    
  }
  
  class mines(
    var Xposition :Int = 0,
    var Yposition :Int = 0,
  ) extends table{
    
    override def toString ={
      if( Xposition == 10) 
        s"x: $Xposition    y: $Yposition"
      else
        s"x: $Xposition     y: $Yposition"
    }
  } 
  
  
  class numbers(
    var Xposition :Int = 0,
    var Yposition :Int = 0,
  ) extends table{
    
    override def toString ={
      if( Xposition == 10) 
        s"x: $Xposition    y: $Yposition"
      else
        s"x: $Xposition     y: $Yposition"
    }   
  }
  
  var mine = new table()
  var minepos = mine.getMinesPosition
  var realMines = mine.convertMinesPosition(minepos)
  var numberpos = mine.getNumbersPosition(minepos)
  var realNumbers = mine.convertNumbersPosition(numberpos)
  for(i <- minepos)
  println(i)
  println(mine.displayTable(minepos, numberpos))

}
