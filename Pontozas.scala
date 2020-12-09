object Pontozas
{
def main(args: Array[String]) {
    
    new student("Pistike", 100)
    new student("Moriczka", 45)
    new student("Tomika", 90)
    new student("Nixie", 80)
    new student("Seenixa", 65)
    new student("Doma", 49)
    new student("Davidka", -2)
    new student("Kutyus", 112)

    class student (name: String, points: Int){
        var osztalyzat = ""

        if(points <= 100 && points > 90)
            osztalyzat = "jeles"
        if(points <= 90 && points > 80)
            osztalyzat = "jo"
        if(points <= 80 && points > 65)
            osztalyzat = "kozepes"
        if(points <= 65 && points > 49)
            osztalyzat = "elegseges"
        if(points <= 49 && points >= 0)
            osztalyzat = "elegtelen"

        if(!(points>100 || points<0))
            println(name + " a pontszamai alapjan " + osztalyzat + " eredmenyt ert el. (" + points + ")")
        else if(points>100)
            println(name + " vagy nagyon okos, vagy rossz a pontszam. (" + points + ")")
        else if(points<0)
            println(name + " vagy nagyon... buta, vagy rossz a pontszam.(" + points + ")")
    }
   }
}
