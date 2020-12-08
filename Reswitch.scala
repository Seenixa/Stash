object Reswitch
{
   def main(args: Array[String]) 
   {
    val betu = 'z'
    
    // Az alap program amit at kell irni switch-re
    
    if( betu == 'X' ){         
        val s = 0
        println(s)
    }
    
    else if ( betu == 'Z' ){
        val jelzo = 1
        println(jelzo)
    }
    
    else if( betu == 'A' ){
        val s = 1
        println(s)
    }
    
    else{
        println("Ismeretlen betu --> " + betu);
    }
    
    // Ujrairva 
    
    
    betu match{
         case 'X' =>
             val s = 0
             println("s = " + s)
        
         case 'Z' =>
             val jelzo = 1
             println("jelzo = " + jelzo)
             
         case 'A' =>
             val s = 1
             println("s = " + s)
 
         case _   => 
             println("Ismeretlen betu --> " + betu)
    }

   }
}
