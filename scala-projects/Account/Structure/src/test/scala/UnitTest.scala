package Banking

class UnitTest {
  val site = new Website
  val appCon = new ApplicationContext(new BankRepository())
  appCon.Repository.username = "wasd"
  appCon.Repository.password = "dsaW"
  appCon.Repository.emailAddress = "appcon@gmail.com"
  appCon.Service.deposit(1000)

  site.updateAccountList
  /*site.register("Queen", "Gambit")
  site.register("Danish", "Defense")
  site.register("Kotal", "Kahn") 0*/
  val danish = site.login("Danish", "Defense")
  val queen = site.login("Queen", "Gambit")
  val kotal = site.login("Kotal", "Kahn")
  danish.Service.deposit(1000)
  danish.Service.withdraw(300)
  queen.Service.deposit(300)
  queen.Service.withdraw(100)
  danish.Service.transfer(700, queen.Repository)

  appCon.Repository.save(site) // Should not do anything as it's not created via Website.register

  println(site.accountMap)
  site.cleanSave
  println(site.accountList)

}
