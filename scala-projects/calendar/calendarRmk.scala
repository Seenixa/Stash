object calendarRmk{
  def main(args: Array[String]) :Unit ={
    
    val currentYear = 2020
     
    class DayOfMonth(
      val day: Int
    )
    
    class Week(
      val days: Seq[DayOfMonth]
    )

    class Month(
      var weeks: Seq[Week]
    )

    class Year(
      val months: Seq[Month] 
    )
    
    def leapYear(currentYear: Int): Boolean={
      currentYear % 4 == 0
    }
    
    def monthLengthById(monthId: Int) :Int = {
      var monthLength = 30
      if( monthId >= 1 && monthId <= 12){
        if( monthId == 2){
          monthLength = 28
          if( leapYear( currentYear) == true)
            monthLength = 29
        }
        else if( ((monthId < 8) && (monthId % 2 == 1)) || ((monthId >= 8) && (monthId % 2 == 0) ))
          monthLength = 31
        monthLength            
      }
      else{
        monthLength = 0
        monthLength
      }
    }
    
    def displayHeadline( monthId: Int) :String = {
      var headLine = ""      
      if( monthId >= 1 && monthId <= 12){
        val nameOfMonths = Array[String]("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val shortNameOfDays = Array[String]("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
        headLine = "     "
        headLine+= nameOfMonths( monthId - 1) + " \n"
        for( day <- shortNameOfDays)
          headLine+= day + " "
        headLine
      }
      else{
        headLine+= "Error in headline display!"
        headLine
      }
    }
    
    println(displayHeadline(1))
    for (i <- 1 to 14)
    println(monthLengthById(i))
  }
}
