object Szorzotabla
{
   def main(args: Array[String]) 
   {
     for(y <- 1 to 10)
     {
      if(y>1) println("\n")
       for(x <- 1 to 10)
       {
          print("" + x*y + " ")
       }
     }
   }
}
