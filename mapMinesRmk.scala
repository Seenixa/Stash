object mapMines{
  def main(args: Array[String]):Unit ={
    val r = scala.util.Random     
    val mapHeight = 10
    val mapLength = 10
    var numberOfMines = 50
    var minesBlown = 0
    
    class position(
      var x:Int,
      var y:Int
    )
    
    class moves(position:position){
      val up = new position(position.x, position.y + 1)
      val down = new position(position.x, position.y - 1)
      val right = new position(position.x + 1, position.y)
      val left = new position(position.x - 1, position.y)
    }
    
    if( numberOfMines > (mapHeight+1)*(mapLength+1))
      numberOfMines = (mapHeight+1)*(mapLength+1)
    var mines = new Array[position](numberOfMines)
    
    for( i <- 0 until numberOfMines){
      mines(i) = new position (r.nextInt(mapLength+1), r.nextInt(mapHeight+1))
      for( j <- 0 until i){
        while( mines(i).x == mines(j).x && mines(i).y == mines(j).y || swap == true){
          mines(i) = new position (r.nextInt(mapLength+1), r.nextInt(mapHeight+1))
          swap = false
          for( z <- 0 until j)
              if(mines(i).x == mines(z).x && mines(i).y == mines(z).y){
                swap = true
              }
        }
      }
    }
    
    val startingPosition = new position(0, 0)
    var realTimePosition = startingPosition
    
    def afterMoveCheck :Unit ={
      if( realTimePosition.x >= mapLength)
        realTimePosition.x = mapLength
      if( realTimePosition.y >= mapHeight)
        realTimePosition.y = mapHeight
      if( realTimePosition.x <= 0)
        realTimePosition.x = 0
      if( realTimePosition.y <= 0)
        realTimePosition.y = 0
      Thread.sleep(100)
      println( realTimePosition.x + " " + realTimePosition.y)
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
          realTimePosition = new moves(realTimePosition).up
          afterMoveCheck
        }
      if( moveY < 0)
        for(i <- 0 until (moveY).abs){
          realTimePosition = new moves(realTimePosition).down
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
    
    for(mine <- mines)
      println("X: " + mine.x + "Y: " + mine.y)
    
    for(i <- 1 to 10)
      move(1,1)       
    
    println("Mines Blown: " + minesBlown)
    println("Mines still in: " + (numberOfMines - minesBlown))
  }
}
