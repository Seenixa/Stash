object minesweeper extends App{
  import scala.collection.mutable.ArrayBuffer
  val r = scala.util.Random
  
  class table(
    var Xlength :Int = 2,
    var Ylength :Int = 2,
    var numberOfMines :Int = 2,
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
    
    def convertMinesPosition(minesPosition: Array[Int]) :Array[mines] ={
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
    
    def convertNumbersPosition(numbersPosition: Array[Int]) :Array[numbers] ={
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
  for( i <- minepos)
    println(i)
  for( i <- realMines)
    println(i)
  println(s"""
              |""".stripMargin)
  var numberpos = mine.getNumbersPosition(minepos)
  var realNumbers = mine.convertNumbersPosition(numberpos)

  for(i <- numberpos)
    println(i)
  for( i <- realNumbers)
    println(i)
  
 
  println("it runs")
}
