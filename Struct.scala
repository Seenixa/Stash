object struct{
  def main(args: Array[String]){
    class person( var firstname: String, var lastname: String, var birthyear: Int, var salary: Int ){
      def fn = firstname
      def ln = lastname
      def by = birthyear
      def sal = salary
    }
    val people = Array[person]{
      new person( "Neparaczki", "Tamas", 1996, 60000 )
      new person( "Sipos", "Sandor", 1995, 95000 )
      new person( "Kis", "Pista", 1985, 64000 )
      new person( "Nagy", "Janos", 1988, 76000 )
    }

      println(" " + people(0).by) 
  }
}
