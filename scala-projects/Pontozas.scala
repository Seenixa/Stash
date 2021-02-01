object Pontozas{
def main(args: Array[String]) {
    def grading(points: Int):Int = {
        if(points <= 100 && points > 90) return 5
        if(points <= 90 && points > 80) return 4
        if(points <= 80 && points > 65) return 3
        if(points <= 65 && points > 49) return 2
        if(points <= 49 && points >= 0) return 1
        return 0
    }
    
    var name = Array("Jani", "Pisti", "Valaki", "Barki", "Akarki", "Senki",
                     "Mindenki", "Fekete", "Feher", "Igen", "Nem", "Luther",
                     "Alison", "Diego", "Ben", "Reginald", "Claus", "Pogo",
                     "Knuckledrager", "Boombewm")
    var points = Array(100, 32, 56, 73, 47, 59, 37, 45, 93, 74, 9, -32,
                       123, 54, 12, 57, 69, 73, 98, 65)
                  
    for ( i <- 0 to 19 ){
        if (grading(points(i)) == 0)
            println("Hibas a megadott pontszam " + name(i) + "-nél.")
        else
            println(name(i) + " erdemjegye " + grading(points(i)) + ".")
    }
}
}
