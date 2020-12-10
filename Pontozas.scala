object HelloWorld
{
def main(args: Array[String]) {
    
    var points = 0
    
    def grading(points: Int):Int = {
        if(points <= 100 && points > 90)return 5
        if(points <= 90 && points > 80) return 4
        if(points <= 80 && points > 65) return 3
        if(points <= 65 && points > 49) return 2
        if(points <= 49 && points >= 0) return 1
        return 0
    }
    
    points = 67
    if(grading(points) != 0)
        println("A tanulo osztalyzata: " + grading(points))
    else println("Hibas pontszam megadas.")
    points = -21
    if(grading(points) != 0)
        println("A tanulo osztalyzata: " + grading(points))
    else println("Hibas pontszam megadas.")
    points = 110
    if(grading(points) != 0)
        println("A tanulo osztalyzata: " + grading(points)) 
    else println("Hibas pontszam megadas.")
    points = 30
    if(grading(points) != 0)
        println("A tanulo osztalyzata: " + grading(points)) 
    else println("Hibas pontszam megadas.")
   }
}
