object forums extends App {
    
  class comment( 
    val comment :String = "New comment"){
    override def toString = s"$comment"
  }
  
  class topic( 
    val title :String = "New topic", 
    var comments :Seq[comment]){
    override def toString = s"""$title
                            |${comments.head}""".stripMargin
  }
    
  class forum( 
    var title :String = "New forum", 
    var topics :Vector[topic]){
    override def toString = s"""$title
                            |${topics(0).title}
                            |${topics(0).comments.head}""".stripMargin
  }

  val f = new forum( "Forum cim", topics = Vector[topic]())
  val t = new topic( "topic cim", comments = Seq[comment]())
  val c = new comment("barmi, akarmi")
  
  t.comments = c +: t.comments
  f.topics = f.topics :+ t
  
  println(s"$t")
}
