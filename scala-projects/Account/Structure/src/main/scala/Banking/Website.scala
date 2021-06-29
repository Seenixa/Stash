package Banking

class Website {
  var accountList = List[BankRepository]()
  var accountMap = Map[String, BankRepository]()

  def updateAccountList = {
    accountList = loadToList
    accountMap = convertMap(accountList)
  }

  def refreshList = {
    accountList = List()
    for (account <- accountMap)
      accountList = account._2 :: accountList
  }

  this.register("Guest", "")
  val guest = login("Guest", "")
  guest.Repository.emailAddress = "NULL "

  def cleanSave = {
    refreshList
    accountList(0).resetTxt
    for (account <- accountMap) {
      account._2.save(this)
    }
  }

  def register(username: String, password: String): Unit = {
    if (!accountMap.contains(username)) {
      var account = new BankRepository(username = username, password = password)
      accountList = account :: accountList
      accountMap = convertMap(accountList)
      account.save(this)
    } else throw new Error("Account already exists.")
  }

  def checkExistence(account: BankRepository): Boolean = {
    accountMap.contains(account.username)
  }

  def login(username: String, password: String): ApplicationContext = {
    var account = new BankRepository()
    if (accountMap.contains(username)) {
      account = accountMap(username)
    } else throw new Error("Account does not exist.")
    if (account.password == password) {
      account
    } else throw new Error("Wrong password.")
    var returnAccount = new ApplicationContext(account)
    returnAccount
  }

  def loadToList: List[BankRepository] = {
    import scala.io.Source
    var action = 0
    var tempRepo = new BankRepository("")
    var repoList = List[BankRepository]()
    val filename = "Repositories.txt"
    for (line <- Source.fromFile(filename).getLines) {
      if (action == 0) {
        tempRepo = new BankRepository(username = line)
      }
      if (action == 1) {
        tempRepo.tempBalance = line
      }
      if (action == 2) {
        tempRepo.password = line
      }
      if (action == 3) {
        tempRepo.emailAddress = line
        repoList = tempRepo :: repoList
      }
      action += 1
      if (action == 4)
        action = 0
      if (tempRepo.tempBalance.toInt != tempRepo.getBalance)
        tempRepo.updateBalance
    }
    repoList
  }

  def convertMap(loaded: List[BankRepository]): Map[String, BankRepository] = {
    var usernameList = List[String]()
    for (usernames <- loaded)
      usernameList = usernames.username :: usernameList
    val listToMap = (usernameList.reverse zip loaded).toMap
    listToMap
  }

}