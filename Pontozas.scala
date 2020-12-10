object Pontozas
{
def main(args: Array[String]) {
    
    class student (name: String, points: Int){
        def p = points
        def n = name
    }
    
    def grading(points: Int):Int = {
        if(points <= 100 && points > 90)return 5
        if(points <= 90 && points > 80) return 4
        if(points <= 80 && points > 65) return 3
        if(points <= 65 && points > 49) return 2
        if(points <= 49 && points >= 0) return 1
        return 0
    }
    
    var points = new student("Pistike", 78)
    
    if(grading(points.p) != 0)
        println(points.n + " osztalyzata: " + grading(points.p))
    else println("Hibas pontszam megadas." + points.p + " " + points.n)
    
    points = new student("Janika", 100)
    
    if(grading(points.p) != 0)
        println(points.n + "osztalyzata: " + grading(points.p))
    else println("Hibas pontszam megadas." + points.p + " " + points.n)
    
    points = new student("Hulyegyerek", -15)
    
    if(grading(points.p) != 0)
        println(points.n + "osztalyzata: " + grading(points.p))
    else println("Hibas pontszam megadas." + points.p + " " + points.n)
    
    points = new student("cheater", 135)
    
    if(grading(points.p) != 0)
        println(points.n + "osztalyzata: " + grading(points.p))
    else println("Hibas pontszam megadas." + points.p + " " + points.n)
   }
}
