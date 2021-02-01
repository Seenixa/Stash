object struct{
  def main(args: Array[String]){
    
    var agesum = 0
    var salarysum = 0
    
    class person(
        val firstname: String,        
        val lastname: String, 
        val birthyear: Int, 
        val salary: Int,
    )
    
    val people = Array[person](
      new person( "Neparaczki", "Tamas", 1996, 60000 ),
      new person( "Sipos", "Sandor", 1995, 95000 ),
      new person( "Kis", "Pista", 1985, 64000 ),
      new person( "Nagy", "Janos", 1988, 76000 ),
      new person( "Valaki", "Akarki", 1980, 91000)
  ) 
  
      // Ki szuletett 1980 es 1990 kozott // 
      // Atlagfizetes es atlageletkor szamitas //
  
    for(i <- 0 until people.length){
      if( people(i).birthyear >= 1980 && people(i).birthyear <= 1990)
        println(people(i).firstname + " " + people(i).lastname + " 1980 es 1990 kozott szuletett. (" + people(i).birthyear + ")")
        
      agesum+= 2020 - people(i).birthyear
      salarysum+= people(i).salary

      if( i == people.length - 1){
        var ageavg = agesum / people.length
        println("Az atlag eletkor: " + ageavg)
        var salaryavg = salarysum / people.length
        println("Az atlag fizetes: " + salaryavg)
      }
    }   
  }
}
