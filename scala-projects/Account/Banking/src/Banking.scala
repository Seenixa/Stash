

object Banking {
  def main(args: Array[String]) = {

    def BankingUnitTest: Unit = {
      val site = new Website
      
      var Repo = new BankRepository(username = "")
      var stAccount = new BankRepository(
          username = "Valaki",
          password = "asddsa",
          emailAddress = "asd@gmail.com")
      var ndAccount = new BankRepository(
          username = "Akarki",
          password = "asddsa",
          emailAddress = "wasasd@gmail.com")
      var rdAccount = new BankRepository(
          username = "Akarkiislehet",
          password = "asddsa",
          emailAddress = "@gmail.com")      
      val services = new BankService(Repo)
      Repo.changeBalance(300)
      stAccount.save(site)
      ndAccount.save(site)
      rdAccount.save(site)
      
      var user = site.login("Valaki", "asddsa")
      
      services.withdraw(100)
      println(Repo.checkBalance)
      println(Repo.checkHistory)
      println(site.accountMap)

      Repo.save(site)
      
      

  


    }

    BankingUnitTest

  }
}