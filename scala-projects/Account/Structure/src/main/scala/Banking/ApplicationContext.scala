package Banking
class ApplicationContext (var Repository:BankRepository)
    {
  val Service = new BankService(Repository)
  
}