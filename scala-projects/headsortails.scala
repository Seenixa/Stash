object headsortails{
  def main(args: Array[String]):Unit ={
    val r = scala.util.Random

    
    // 50-200 kozotti tagu sorozat letrehozasa, es feltoltese "fej vagy iras(0/1)" ertekekkel", leghosszabb fej illetve iras sorozat keresese. //
    
  def toss_up:Unit ={
    val len = (r.nextInt(201-50)) + 50
    val arr = new Array[Int](len)
    var times_heads = 0
    var heads_streak = 0
    var heads_streak_max = 0
    var times_tails = 0
    var tails_streak = 0
    var tails_streak_max = 0
    for(i <- 0 until len){
      arr(i) = r.nextInt(2)
      if( arr(i) == 0){
        times_heads+= 1
        heads_streak+= 1
        tails_streak = 0
        if( heads_streak > heads_streak_max)
          heads_streak_max = heads_streak
      }
      if( arr(i) == 1){
        times_tails+= 1
        tails_streak+= 1
        heads_streak = 0
        if( tails_streak > tails_streak_max)
          tails_streak_max = tails_streak
      }  
    }
    
    // Fej <-> iras aranya. Leghosszabb fej <-> iras sorozat aranya az egeszhez. //
    
    var percent_heads_streak = (heads_streak_max.toFloat / len) * 100
    var percent_tails_streak = (tails_streak_max.toFloat / len) * 100
    var percent_heads = (times_heads.toFloat / len.toFloat) * 100
    var percent_tails = (times_tails.toFloat / len.toFloat) * 100

    println(times_heads + " darabszor dobtunk fejet. " + f"$percent_heads%.2f" + "%")
    println(times_tails + " darabszor dobtunk irast. " + f"$percent_tails%.2f" + "%")
    println(heads_streak_max + " volt a leghosszabb fej sorozat. " + f"$percent_heads_streak%.2f" + "%")
    println(tails_streak_max + " volt a leghosszabb iras sorozat. " + f"$percent_tails_streak%.2f" + "%")
    println(" ")
  }  
    // 10 sorozat. //
    
    for(i <- 0 to 9)
      toss_up
  }
}
