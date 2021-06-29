package Banking
class ApplicationContext(var Repository: BankRepository) {
  var Service = new BankService(Repository)

  def updateService = {
    this.Service = new BankService(Repository)
  }

}