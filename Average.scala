object Average{
def main(args: Array[String]) {
    val grade = Array(1, 2, 3, 2, 5, 3, 2, 1, 2, 1)
    var sum = 0
    var five = 0
    var four = 0
    var three = 0
    var two = 0
    var one = 0
    
    for(i <- 0 to 9){
        if(grade(i) < 0 || grade(i) > 5){
            println("\n1 es 5 kozotti erdemjegyek leteznek.")
            }
        else{
            sum+= grade(i)
        grade(i) match{
            case 5 =>
            five+= 1
            
            case 4 =>
            four+= 1
            
            case 3 =>
            three+= 1
            
            case 2 =>
            two+= 1
            
            case 1 =>
            one+= 1
        }
        }
    }
    if(one+two+three+four+five == 10){
        val average = sum / 10
        if(one>0)
            println("\nA tanulo megbukott.\n")
    
        println("A tanulo atlaga: " + average)
        println("Ebbol " + five + " db 5-os")
        println("Ebbol " + four + " db 4-es")
        println("Ebbol " + three + " db 3-as")
        println("Ebbol " + two + " db 2-es")
        println("Ebbol " + one + " db 1-es")
    }
}   
}
