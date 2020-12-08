object HelloWorld
{
   def main(args: Array[String]) 
   {
     for(x <- 0 to 10)
     {
       println("")
      
       for(y <- 0 to 10)
       {
          print("" + x*y + " ") 
       }
        
     }
     
     
     println("\n")
     
     
     var x: Int = 0
     while(x < 11)
     {
        println("")
        var y: Int = 0
        
        while(y < 11)
        {
           print("" + x*y + " ")
           y = y + 1
           
        }
      x = x + 1
     }
     
     
     x = 0
     println("")
     

     do
     {
       println("")
       var y: Int = 0
       
       do
       {
          print("" + x*y + " ")
          y = y + 1
       }
       while(y < 11)
       
       x = x + 1
     }
     while(x < 11)
   }
}
