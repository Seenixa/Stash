object Negyzet
{
   def main(args: Array[String]) 
   {
     val Addone = (x: Int) => x + 1
     for(x <- 0 to 19)
     {
        println("A szam:" + Addone(x) + " A negyzete:" + Addone(x)*Addone(x))
     }
   }
