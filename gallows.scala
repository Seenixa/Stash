

object gallows {
  def main(args: Array[String]): Unit ={
    var kor = 0
    
    def contained(letter:Char, tips:Array[Char]): Boolean={
      var contain = false
      for( tip <- tips)
         if( letter == tip)
           contain = true
      contain
    }
    
    def display(tips:Array[Char], word:String): String={
      var wordDisplay = new Array[Char](word.length)
      var wordArray = word.toCharArray()
      var wordDisplayString = ""
      for( i <- 0 until word.length)
        wordDisplay(i) = '_'
      for( letterTipped <- tips){
        if( isIn(letterTipped, word) == true)
        for( i <- 0 until wordArray.length){
          if( wordArray(i) == ' ' || wordArray(i) == '.' || wordArray(i) == ',' || wordArray(i) == '!' ||
              wordArray(i) == '?' || wordArray(i) == ':' || wordArray(i) == '-')
            wordDisplay(i) = wordArray(i)
          else if( letterTipped == wordArray(i))
            wordDisplay(i) = letterTipped
        }
      }
       for (i <- 0 until word.length)
         wordDisplayString+= wordDisplay(i)
      wordDisplayString
    }  
    
    def deciphered(tips:Array[Char], word:String): Boolean={
      if( display(tips, word) == word)
        true
      else
        false
    }
    
    def isIn(letter:Char, word:String):Boolean={
      var itIsIn = false
      var wordArray = word.toCharArray()
      for( letterInWord <- wordArray)
        if( letter == letterInWord)
          itIsIn = true
      itIsIn
    }
    
    def badTips(tips:Array[Char], word:String): Int={
      var wrong = 0
      for(i <- 0 until kor)
        if( isIn(tips(i), word) == false ){
          wrong+=1
        }
      wrong     
    }
    
    def lives(maxLife:Int, usedLife:Int): String={
      var currentLives = ""
      var livesLeft = maxLife - usedLife
      for(i <- 0 until livesLeft)
        currentLives = currentLives.concat(":)")
      for(i <- livesLeft until maxLife)
        currentLives = currentLives.concat(":(")
      currentLives  
    }

    
    def gallows(word:String, maxLife:Int): Unit={
      var winOrLose = false
      var tips = new Array[Char](maxLife + word.length)
      var tip = ' '
      while( winOrLose == false || kor == (maxLife + word.length)){
        println(display( tips, word))
        println( "Adja meg a kovetkezo betut: ")
        println("" + lives(maxLife, (badTips(tips, word))))
        tip = scala.io.StdIn.readChar()
        tips(kor) = tip
        kor+= 1
        if( deciphered(tips, word) == true){
          println(display( tips, word))
          println( "Gratulalok, nyertel, es meg " + (maxLife - badTips(tips, word)) + " eleted maradt!")
          winOrLose = true
        }
        if( maxLife == badTips(tips, word)){
          println( "Sajnalom, nem nyertel, ez lett volna a megoldas: " + word)
          winOrLose = true
        }

      } 
    }
    
    println("Mi legyen a kitalalando szo?")
    val word = scala.io.StdIn.readLine()
    println("Hany probalkozassal?")
    val maxLife = scala.io.StdIn.readInt()
    gallows(word, maxLife)
  }
}
