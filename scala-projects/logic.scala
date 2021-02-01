object logic
{
   def main(args: Array[String]) 
   {
      val x: Int = 12
      if (x % 3 != 0 && x % 2 != 0 && x>10)
      {
        println("x 10-nel nagyobb, 2-vel es 3-al nem oszthato szam.")
        println("x = " + x)
      }
      else println("x 10-nel kisebb, vagy oszthato 2-vel vagy 3-al.")
      
      val y: Int = 10
      if(y > -5 && y < 5)
      {
        println("y -5 es 5 koze eso szam.")
        println("y = " + y)
      }
      else println("y -5nel kisebb, vagy 5nel nagyobb.")
   }
}
