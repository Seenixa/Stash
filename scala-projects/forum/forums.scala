object forums extends App {
    
  class comment( 
    val comment :String = "New comment"){
    override def toString = s"      $comment"
  }

  
  class topic( 
    val title :String = "New topic", 
    var comments :Seq[comment]){
    override def toString = {
      var display = ""
      display += s"   $title"
      for( comment <- comments)
        display += s"""
                    |$comment""".stripMargin
      display
    }
  }

  
  class forum( 
    var title :String = "New forum", 
    var topics :Vector[topic]){
    override def toString ={
      var display = ""
      display += s"""$title
                  |""".stripMargin
      for( topic <- topics){
        display += s"""   
                    |   ${topic.title}
                    |""".stripMargin
        for( comment <- topic.comments)
          display += s"""$comment
                      |""".stripMargin
      }
    display
    }                            
  }

  val f = new forum( "Forum cim", topics = Vector[topic]())
  val t = new topic( "topic cim", comments = Seq[comment]())
  val c = new comment("barmi, akarmi")
  val com = new comment("semmi")
  val co = new comment("Esetleg")
  val to = new topic( "uj topic", comments = Seq[comment]() )
  val top = new topic( "new top", comments = Seq[comment]())
  
  t.comments = c +: t.comments
  t.comments = co +: t.comments
  t.comments = com +: t.comments
  to.comments = c +: to.comments
  to.comments = co +: to.comments
  to.comments = com +: to.comments
  top.comments = new comment("legyen egy itt is") +: top.comments
  
  f.topics = f.topics :+ t
  f.topics = f.topics :+ to
  f.topics = f.topics :+ top
  
  println(s"$f")
}
