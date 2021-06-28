package Banking

class UnitTest {
  val site = new Website
  val appCon = new ApplicationContext(new BankRepository)
  appCon.Repository.username = "wasd"
  appCon.Repository.password = "dsaW"
  appCon.Repository.emailAddress = "appcon@gmail.com"
  appCon.Service.deposit(1000)
  
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
  stAccount.save(site)
  rdAccount.save(site)
  site.resetTxt
  ndAccount.save(site)
  
  appCon.Repository.save(site)
  site.update
  println(appCon.Repository)

  var user = site.login("wasd", "dsaW")

  println(appCon.Repository.checkBalance)
  println(site.accountMap)
  println(site.accountList)


}
