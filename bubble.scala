object Bubble {
   def main(args: Array[String]) {
     
     val r = scala.util.Random
     var vector = new Array[Int](100)
     var sum = 0
     var max = 0
     var min = 1000
     
     for( i <- 0 to 99 ){
       vector(i) = r.nextInt(1001)
       print( "" + vector(i) + " " )
       
       if( (i+1) % 10 == 0 )
         print( "\n" )
       
       sum+= vector(i)
       
       if( vector(i) > max ){
         max = vector(i)
       }
       
       if( vector(i) < min ){
         min = vector(i)
       }
     }
     val avg: Float = sum.toFloat / 100
     println( "\nA szamok osszege: " + sum )
     println( "A szamok atlaga: " + avg )
     
     def sortasc(vector: Array[Int]):Array[Int] ={
       var vectorasc = vector
       for(i <- 1 to 99; j <- 1 to 99){
          if(vectorasc(j-1) > vectorasc(j)){
            var tmp = vectorasc(j-1)
            vectorasc(j-1) = vectorasc(j)
            vectorasc(j) = tmp
         }
       }
       vectorasc
     }
     
     def sortdec(vector: Array[Int]):Array[Int] ={
       var vectordec = vector
       for(i <- 1 to 99; j <- 1 to 99){
          if(vectordec(j-1) < vectordec(j)){
            var tmp = vectordec(j-1)
            vectordec(j-1) = vectordec(j)
            vectordec(j) = tmp
         }
       }
       vectordec
     }

     println(" ")
     for(i <- 0 to 99){
       print("" + sortasc(vector)(i) + " ")
       if( (i+1) % 10 == 0)
        print("\n") 
     }
     
     println(" ")
     for(i <- 0 to 99){
       print("" + sortdec(vector)(i) + " ")
       if( (i+1) % 10 == 0)
        print( "\n") 
     }
}
}
