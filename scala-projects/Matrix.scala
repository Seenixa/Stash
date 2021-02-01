object Matrix {
   def main(args: Array[String]) {
     val r = scala.util.Random
     val lastrow = 6
     val lastcolumn = 10
     var sum = 0
     var maxsum = 0
     var max = new Array[Int](10)
     var matrix = Array.ofDim[Int](lastrow, lastcolumn)
     
     // A matrix feltoltese //
     
     for( i <- 0 until lastrow; j <- 0 until lastcolumn ){
       matrix(i)(j) = r.nextInt(201)
       sum+= matrix(i)(j)
       print( "" + matrix(i) (j) + " " )
       if( j == lastcolumn - 1 )
         println(" ")
     }
     
     // A sorok maximumanak keresese //
     
     for( i <- 0 until lastrow; j <- 0 until lastcolumn ){
       if( matrix(i)(j) > max(i) )
         max(i) = matrix(i)(j)
       if( j == lastcolumn -1 ){
         println( "" + (i+1) + ". sor maximuma: " + max(i) )
         maxsum = maxsum + max(i)
       }
     }

     // A matrix elemeinek, es a sorok maximumanak atlaganak szamitasa //
     
     val maxavg = maxsum / lastrow
     val avg = sum / (lastrow*lastcolumn)
     println("A sorok maximumanak atlaga: " + maxavg)
     println("A matrix elemeinek atlaga: " + avg)
     
     // Hany atlagnal nagyobb, illetve kisebb eleme van a matrixnak //
     
     var smalltavg = 0
     var largetavg = 0
     for( i <- 0 until lastrow; j <- 0 until lastcolumn ){
       if( matrix(i)(j) > avg )
         largetavg+= 1
       if( matrix(i)(j) < avg )
         smalltavg+= 1
     }
     println("Atlagnal nagyobb elemek szama: " + largetavg)
     println("Atlagnal kisebb elemek szama: " + smalltavg)    
}
}
