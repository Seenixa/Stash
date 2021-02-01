object forum{
  def main(args: Array[String]) :Unit ={
    
    class comment(
      val com :String
    )
    
    class topic(
      val title :String,
      val comments :Seq[comment]
    )
    
    class forum(
      val title :String,
      val topics :Seq[topic]
    )
    
    val comments = Seq[comment](
      new comment ("valami"),
      new comment ("semmi"),
      new comment ("akarmi")
    )
    
    val top = new topic( "valami", comments )
    val topi = new topic( "akarmi", comments )
    val forrum = Seq[topic](
      top,
      topi
    )
    
    val form = new forum( "semmi", forrum)
    
    for(i <- 0 to 1; j <- 0 to 2)
      println(form.title + " " + form.topics(i).comments(j).com)
  } 
}
