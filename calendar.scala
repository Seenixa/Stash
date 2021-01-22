object calendar{
  def main(args: Array[String]) :Unit ={
    
    val year = 2021
    
    class month( id:Int, name: String, lengthInDay: Int)
    
    val monthsName = Array[String]( "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val monthsIds = Array[Int]( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val days = Array[String]( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val daysInt = Array[Int]( 1, 2, 3, 4, 5, 6, 7)
    
    def leapYear(currentYear: Int): Boolean={
      currentYear % 4 == 0
    }
    
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
      for( previousMonths <- 1 to monthsId){
        day = ( monthLengthById(previousMonths - 1, leapYear(year)) - 28 + day) % 7
      }
      day
    }
    
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
    
    def displayMonth: String ={
      var display = ""
      val skip = "   "
      var previousDay = 0
      for( month <- monthsIds){
        previousDay = 0
        for( i <- 0 to 1){
          display+= displayHeadline( monthsName( month), i) + "\n"
        }
        for( i <- 0 to 6){
          if( (firstDayOfMonth( firstDayOfYearInt( year), month)) > i)
            display+= skip          
        }
        for( i <- 0 until monthLengthById( month, leapYear( year))){
          if( (firstDayOfMonth( firstDayOfYearInt( year), month) + i) % 7 == 0)
            display+= "\n"
          if( i < 9){
            display+= (previousDay + 1) + "  "
            previousDay+= 1
          }
          if( i >= 9){
              display+= (previousDay + 1) + " "
            previousDay+= 1
          if( i == monthLengthById( month, leapYear( year)) - 1)
            display+= "\n \n"
          }
        }   
      }
      display
    }
    
    def printCalendar(year: Int): Unit ={
      println( displayMonth + " \n" + " \n")
    }
    

    printCalendar(2019)


  }
}
