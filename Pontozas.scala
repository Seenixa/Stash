object Pontozas{
   def main(args: Array[String]) {
       
    val points = 90
    
    if(points > 100)
        println("100 pont a maximum.")
    if(points <= 100 && points > 90)
        println("jeles")
    if(points <= 90 && points > 80)
        println("jo")
    if(points <= 80 && points > 65)
        println("kozepes")
    if(points <= 65 && points > 49)
        println("elegseges")
    if(points <= 49 && points >= 0)
        println("elegtelen")
    if(points < 0)
        println("0 pont a minimum.")
   }
}
