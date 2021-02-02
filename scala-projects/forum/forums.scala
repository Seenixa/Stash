object forums {
  def main(args: Array[String]) :Unit ={
    
    class topic(
      val title :String,
      var comments :Seq[String]
    )
    
    class forum(
      val title :String,
      var topics :Seq[topic]
    )
    
    def createForum( title: String) :forum ={
        val newForum = new forum( title, List[topic]())
        newForum
    }
    
    def createTopic( Forum: forum, title: String) :forum ={
        val newTopic = new topic( title, List[String]())
        val newList = Forum.topics:+ newTopic
        val newForum = new forum( Forum.title, newList)
        newForum
    }
    
    def createComment( Forum: forum, Topic: topic, Comment: String) :forum ={
        val newList = Topic.comments:+ Comment
        val newTopic = new topic ( Topic.title, newList)
        val newListTopic = Forum.topics:+ newTopic
        val newForum = new forum ( Forum.title, newListTopic)
        newForum
    }
  } 
}
