object Haromszog{
   def main(args: Array[String]) {
       
    val a = 4
    val b = 3
    val c = 3
    
    if(a + b > c && a + c > b && b + c > a){
        print("A haromszog szerkesztheto")
        if(a*a + b*b == c*c || a*a + c*c == b*b || b*b + c*c == a*a){
            print(" es derekszogu.")
        }
        else print(".")
    }
   }
}
