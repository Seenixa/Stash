object calendar{
  def main(args: Array[String]) :Unit ={
    
    val year = 2018
    
    class month( id:Int, name: String, lengthInDay: Int)
    
    val monthsName = Array[String]( "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val days = Array[String]( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val daysInt = Array[Int]( 1, 2, 3, 4, 5, 6, 7)
    
    def leapYear(currentYear: Int): Boolean={
      currentYear % 4 == 0
    }
    
  /*  def firstDayOfYear( year:Int): String ={
      var day = ""
      if( year == 2018)
        day = days(0)
      if( year > 2018){
        val numberOfLeapYears = ( year - 2016) / 4 
        day = days( ( year % 2018 + numberOfLeapYears) % 7)  
      }
      if( year < 2018){
        val numberOfLeapYears = ( ( year - 2020) / 4).abs
        day = days( (( ( year - 2) - numberOfLeapYears) % 7).abs) 
      }
      day
    } 
  */
    
    def firstDayOfYearInt( year:Int): Int ={
      var day = 0
      if( year == 2018)
        day = daysInt(0)
      if( year > 2018){
        val numberOfLeapYears = ( year - 2016) / 4 
        day = ( ( year % 2018 + numberOfLeapYears) % 7)  
      }
      if( year < 2018){
        val numberOfLeapYears = ( ( year - 2020) / 4).abs
        day = ( ( ( ( year - 2) - numberOfLeapYears) % 7).abs) 
      }
      day
    }
    
    def firstDayOfMonth( firstDayOfYear: Int, monthsId: Int): Int ={
      var day = firstDayOfYear
      for( previousMonths <- 0 to monthsId){
        day = ( monthLengthById(previousMonths, leapYear(year)) - 28 + day) % 7
      }
      day
    }
    
 /*   def monthLengthByName( monthsName: String, leapYear: Boolean) :Int ={
      var monthLength = 30
      if( monthsName == "January" || monthsName == "March" || monthsName == "May" || monthsName == "July"
         || monthsName == "August" || monthsName == "October" || monthsName == "December" )
        monthLength = 31
      if( monthsName == "February"){
        monthLength = 28
        if( leapYear == true)
          monthLength = 29
      }
      monthLength     
    }
 */
    
    def monthLengthById( id: Int, leapYear: Boolean) :Int ={
      var monthLength = 30
      if( id == 1){
        monthLength = 28
        if( leapYear == true)
          monthLength = 29
      }
      else if( ((id < 7) && (id % 2 == 0)) || ((id >= 7) && (id % 2 == 1) ))
        monthLength = 31
      monthLength
    }
    
    def displayHeadline( monthName: String, numberOfLine: Int): String ={
      var headline = ""
      if( numberOfLine == 0)
        headline = "     " + monthName  
      if( numberOfLine == 1)
        headline = "Mo Tu We Th Fr Sa Su"
      headline
    }
    
    def displayMonth( monthId: Int, firstDay: Int): String ={
      var display = ""
      display = "   1  2  3  4  5  6 \n7  8  9  10 11 12 13 \n"
      display+= "14 15 16 17 18 19 20 \n21 22 23 24 25 26 27\n28 29 30"
      display
    }
    
    def calendar(year: Int): Unit ={
      for(i <- monthsName)
        for(j <- 0 to 1){
          println( displayHeadline( i, j))
          if( j == 1)
            println( displayMonth( 1, firstDayOfMonth( firstDayOfYearInt( year), 1)) + " \n" + " \n")
        }
    }
    
    calendar(2020)

  }
}
