object map_mines{
  def main(args: Array[String]):Unit ={
    val r = scala.util.Random     
    val map_top = 10
    val map_side = 10
    var numberOfMines = 50
    var swap:Boolean = false
    var minesBlown = 0
    
    class position(
      var x:Int,
      var y:Int
    )
    
    if( numberOfMines > (map_top+1)*(map_side+1))
      numberOfMines = (map_top+1)*(map_side+1)
    var mines = new Array[position](numberOfMines)
    
    for( i <- 0 until numberOfMines){
      mines(i) = new position (r.nextInt(map_side+1), r.nextInt(map_top+1))
      for( j <- 0 until i){
        while( mines(i).x == mines(j).x && mines(i).y == mines(j).y || swap == true){
          mines(i) = new position (r.nextInt(map_side+1), r.nextInt(map_top+1))
          swap = false
          for( z <- 0 until j)
              if(mines(i).x == mines(z).x && mines(i).y == mines(z).y){
                swap = true
              }
        }
      }
    }
    
    val start_pos = new position(0, 0)
    var rt_pos = start_pos
    
    def move_up :Unit ={
      if( rt_pos.y < map_top)
        rt_pos.y+= 1
      else
        rt_pos.y = map_top
      Thread.sleep(100)
      println( rt_pos.x + " " + rt_pos.y)
      for ( mine <- mines)
        if ( rt_pos.x == mine.x && rt_pos.y == mine.y){
          println("Booom.")
          mine.x = -1
          minesBlown+= 1
        }      
    }
    
    def move_down :Unit ={
      if( rt_pos.y > 0)
        rt_pos.y-= 1
      else
        rt_pos.y = 0
      Thread.sleep(100)
      println(rt_pos.x + " " + rt_pos.y)
      for ( mine <- mines)
        if ( rt_pos.x == mine.x && rt_pos.y == mine.y){
          println("Booom.")
          mine.x = -1
          minesBlown+= 1
        }
    }
    
    def move_right :Unit ={
      if( rt_pos.x < map_side)
        rt_pos.x+= 1
      else
        rt_pos.x = map_side
      Thread.sleep(100)
      println(rt_pos.x + " " + rt_pos.y)
      for ( mine <- mines)
        if ( rt_pos.x == mine.x && rt_pos.y == mine.y){
          println("Booom.")
          mine.x = -1
          minesBlown+= 1
        }     
    }
    
    def move_left :Unit ={
      if( rt_pos.x > 0)
        rt_pos.x-= 1
      else
        rt_pos.x = 0
      Thread.sleep(100)
      println(rt_pos.x + " " + rt_pos.y)
      for ( mine <- mines)
        if ( rt_pos.x == mine.x && rt_pos.y == mine.y){
          println("Booom.")
          mine.x = -1
          minesBlown+= 1
        }      
    }

    def move( move_x:Int, move_y:Int): Unit ={
      if( move_x > 0)
        for(i <- 0 until move_x)
          move_right
      if( move_x < 0)
        for(i <- 0 until (move_x).abs)
          move_left
      if( move_y > 0)
        for(i <- 0 until move_y)
          move_up
      if( move_y < 0)
        for(i <- 0 until (move_y).abs)
          move_down
    }
    
    def move_to( move_to_x:Int, move_to_y:Int): Unit ={
      Thread.sleep(1000)
      if( move_to_x < 10 && move_to_x > 0)
        rt_pos.x = move_to_x
      if( move_to_y < 10 && move_to_y > 0)
        rt_pos.y = move_to_y
      
      if( move_to_x > map_side)
        rt_pos.x = map_side
      if( move_to_x < 0)
        rt_pos.x = 0
      
      if( move_to_y > map_top)
        rt_pos.y = map_top
      if( move_to_y < 0)
        rt_pos.y = 0
      println(rt_pos.x + " " + rt_pos.y)
    }
      for(mine <- mines)
        println("X: " + mine.x + "Y: " + mine.y)
    
      for(i <- 1 to 15)
        move(1,1)       
    
    println("Mines Blown: " + minesBlown)
    println("Mines still in: " + (numberOfMines - minesBlown))
  }
}
