object Logic
{
   def main(args: Array[String]) 
   {
      val x = 12
      if (x % 3 != 0 && x % 2 != 0 && x>10)
      {
      println("x 10-nel nagyobb, 2-vel es 3-al nem oszthato szam.")
      printf("x = %d\n", x)
      }
      else println("x 10-nel kisebb, vagy oszthato 2-vel vagy 3-al.")
      
      val y = 10
      if(y > -5 && y < 5)
      {
        println("y -5 es 5 koze eso szam.")
        printf("y = %d\n", y)
      }
      else println("y -5nel kisebb, vagy 5nel nagyobb.")
   }
}
