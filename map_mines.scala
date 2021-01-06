object map_mines{
  def main(args: Array[String]):Unit ={
    val r = scala.util.Random     
    val map_top = 10
    val map_side = 10
    
    class position(
      var x:Int,
      var y:Int
    )
    val mine_pos = new position(r.nextInt(11), 0)
    val start_pos = new position(0, 0)
    var rt_pos = start_pos
    
    def move_up :Unit ={
      if( rt_pos.y < map_top)
        rt_pos.y+= 1
      else
        rt_pos.y = 10
      Thread.sleep(1000)
      println(rt_pos.x + " " + rt_pos.y)
      if ( rt_pos.x == mine_pos.x && rt_pos.y == mine_pos.y)
        println("Booom.")      
    }
    def move_down :Unit ={
      if( rt_pos.y > 0)
        rt_pos.y-= 1
      else
        rt_pos.y = 0
      Thread.sleep(1000)
      println(rt_pos.x + " " + rt_pos.y)
      if ( rt_pos.x == mine_pos.x && rt_pos.y == mine_pos.y)
        println("Booom.")      
    }
    def move_right :Unit ={
      if( rt_pos.x < map_side)
        rt_pos.x+= 1
      else
        rt_pos.x = 10
      Thread.sleep(1000)
      println(rt_pos.x + " " + rt_pos.y)
      if ( rt_pos.x == mine_pos.x && rt_pos.y == mine_pos.y)
        println("Booom.")      
    }
    def move_left :Unit ={
      if( rt_pos.x > 0)
        rt_pos.x-= 1
      else
        rt_pos.x = 0
      Thread.sleep(1000)
      println(rt_pos.x + " " + rt_pos.y)
      if ( rt_pos.x == mine_pos.x && rt_pos.y == mine_pos.y)
        println("Booom.")      
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
      
      if( move_to_x > 10)
        rt_pos.x = 10
      if( move_to_x < 0)
        rt_pos.x = 0
      
      if( move_to_y > 10)
        rt_pos.y = 10
      if( move_to_y < 0)
        rt_pos.y = 0
      println(rt_pos.x + " " + rt_pos.y)
    }
  }
}
