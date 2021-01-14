object calendar{
  def main(args: Array[String]) :Unit ={
    
    class month( id:Int, name: String, lengthInDay: Int)
    
    val monthsName = Array[String]("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val days = Array[String]("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    
    def leapYear(currentYear: Int): Boolean={
      currentYear % 4 == 0
    }
    
    def firstDayOfYear( year:Int): String ={
      var day = ""
      if( year == 2018)
        day = days(0)
      if( year > 2018){
        val numberOfLeapYears = ( year - 2016) / 4 
        day = days( ( year % 2018 + numberOfLeapYears) % 7)  
      }
      if( year < 2018){
        val numberOfLeapYears = ( ( year - 2020) / 4).abs
        day = days( ( ( year - 2) - numberOfLeapYears) % 7) 
      }
      day
    }
    
    def firstDayOfMonth( firstDayOfYear: String, monthsId: Int): String ={
      
      "asd"
    }
    
    def monthLengthByName( monthsName: String, leapYear: Boolean) :Int ={
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
    
    def monthLengthById( id: Int, leapYear: Boolean) :Int ={
      var monthLength = 30
      if( id == 2){
        monthLength = 28
        if( leapYear == true)
          monthLength = 29
      }
      if( ((id < 8) && (id % 2 == 1)) || ((id >= 8) && (id % 2 == 0) ))
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
    
    def displayMonth( monthName: String, firstDay: String): Array[String] ={
      var display = new Array[String](49)
      display
    }
    
    for(i <- 0 to 1)
      println(displayHeadline( monthsName(1), i))
  }
}
