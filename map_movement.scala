object map_movement{
  def main(args: Array[String]):Unit ={
    val r = scala.util.Random
    var position = new Array[Int](2)
    position = Array(r.nextInt(11), r.nextInt(11))
    val map_top = 10
    val map_side = 10
    
    
    def move( x:Int, y:Int) ={
      if( position(0) < map_side)
        position(0)+= x
      if( position(1) < map_top)
        position(1)+= y
      
      if( position(0) > map_side)
        position(0) = map_side
      if( position(1) > map_top)
        position(1) = map_top
      
      if( position(0) < 0)
        position(0) = 0
      if( position(1) < 0)
        position(1) = 0
    }
    
    def move_to( x:Int, y:Int) ={
      if( x > map_side || x < 0 || y > map_top || y < 0)
        println("Nem teleportalhatsz ki.")
      else{
        position(0) = x
        position(1) = y
      }   
    }
    
    def move_right: Unit ={
      if( position(1) < map_side)
        position(1)+=1
    }
    def move_left: Unit ={
      if( position(1) > 0)
        position(1)-=1
    }
    def move_up: Unit ={
      if( position(0) < map_top)
        position(0)+=1
    }
    def move_down: Unit ={
      if( position(0) > 0)
        position(0)-=1
    }
    
  }
}
