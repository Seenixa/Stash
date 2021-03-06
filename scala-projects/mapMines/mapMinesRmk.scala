object mapMines{
  def main(args: Array[String]):Unit ={
    val r = scala.util.Random     
    val mapHeight = 3
    val mapLength = 3
    var numberOfMines = 30
    var swap = false
    var minesBlown = 0
    var isThereMine = false
    
    class position(
      var x:Int,
      var y:Int
    )
    
    class moves(position:position){
      val down = new position(position.x, position.y + 1)
      val up = new position(position.x, position.y - 1)
      val right = new position(position.x + 1, position.y)
      val left = new position(position.x - 1, position.y)
    }
    
    val startingPosition = new position(0, 0)
    var realTimePosition = startingPosition
    
    if( numberOfMines > (mapHeight+1)*(mapLength+1))
      numberOfMines = ((mapHeight+1)*(mapLength+1) - 1)
    var mines = new Array[position](numberOfMines)
    
    for( i <- 0 until numberOfMines){
      mines(i) = new position (r.nextInt(mapLength+1), r.nextInt(mapHeight+1))
      while ( mines(i).x == 0 && mines(i).y == 0)
          mines(i) = new position (r.nextInt(mapLength+1), r.nextInt(mapHeight+1))
      for( j <- 0 until i){
        if( mines(i).x == 0 && mines(i).y == 0)
          mines(i) = new position (r.nextInt(mapLength+1), r.nextInt(mapHeight+1))
        while( mines(i).x == mines(j).x && mines(i).y == mines(j).y || swap == true){
          mines(i) = new position (r.nextInt(mapLength+1), r.nextInt(mapHeight+1))
          swap = false
          for( z <- 0 until j){
              if(( mines(i).x == mines(z).x && mines(i).y == mines(z).y)||( mines(i).x == 0 && mines(i).y == 0))
                swap = true

              }
        }
      }
    }
    
    def display( mines :Array[position], realTimePosition:position) :Unit ={
      println("C = Character, O = Safe Place, M = Mine")
      for( i <- 0 to mapHeight; j <- 0 to mapLength){
        for(z <- 0 until numberOfMines){
          if( mines(z).x == j && mines(z).y == i){
            print("M ")
            isThereMine = true
          }
        }
        if( realTimePosition.x == j && realTimePosition.y == i)
          print("C ")
        else if ( isThereMine == false)
          print("O ")
        if( j == mapLength)
          println("\n")
        isThereMine = false
      }
      println(" \n \n \n")
      println(" \n \n ")
    }
    
    def afterMoveCheck :Unit ={
      if( realTimePosition.x >= mapLength)
        realTimePosition.x = mapLength
      if( realTimePosition.y >= mapHeight)
        realTimePosition.y = mapHeight
      if( realTimePosition.x <= 0)
        realTimePosition.x = 0
      if( realTimePosition.y <= 0)
        realTimePosition.y = 0
      Thread.sleep(1000)
      
      for ( mine <- mines)
        if ( realTimePosition.x == mine.x && realTimePosition.y == mine.y){
          println("Booom.")
          mine.x = -1
          minesBlown+= 1
      }
    }

    def move( moveX:Int, moveY:Int): Unit ={
      if( moveX > 0)
        for(i <- 0 until moveX){
          realTimePosition = new moves(realTimePosition).right
          afterMoveCheck
        }
      if( moveX < 0)
        for(i <- 0 until (moveX).abs){
          realTimePosition = new moves(realTimePosition).left
          afterMoveCheck
    }
      if( moveY > 0)
        for(i <- 0 until moveY){
          realTimePosition = new moves(realTimePosition).down
          afterMoveCheck
        }
      if( moveY < 0)
        for(i <- 0 until (moveY).abs){
          realTimePosition = new moves(realTimePosition).up
          afterMoveCheck
        }
    }
    
    def move_to( moveToX:Int, moveToY:Int): Unit ={
      Thread.sleep(1000)
      if( moveToX < mapLength && moveToX > 0)
        realTimePosition.x = moveToX
      if( moveToY < mapHeight && moveToY > 0)
        realTimePosition.y = moveToY
      
      if( moveToX > mapLength)
        realTimePosition.x = mapLength
      if( moveToX < 0)
        realTimePosition.x = 0
      
      if( moveToY > mapHeight)
        realTimePosition.y = mapHeight
      if( moveToY < 0)
        realTimePosition.y = 0
      println(realTimePosition.x + " " + realTimePosition.y)
    }
    
    def letsGo() :Unit = {
      var step = 0
      var steps = 0
      var invalidsteps = 0
      while ( steps < 10 || invalidSteps >= 3){
        display( mines, realTimePosition)
        println("1 = up, 2 = down, 3 = right, 4 = left")
        println("Make your move.")
        step = scala.io.StdIn.readInt()
        step match{
          case 1 => move(0,1)
            steps+= 1
          case 2 =>  move(0,-1)
            steps+= 1
          case 3 => move(1,0)
            steps+= 1
          case 4 => move(-1,0)
            steps+= 1
          case _ => println("invalid move")
            invalidsteps+= 1
        } 
      }
    }
    
    letsGo()
       
    println("Mines Blown: " + minesBlown)
    println("Mines still in: " + (numberOfMines - minesBlown))
  }
}
