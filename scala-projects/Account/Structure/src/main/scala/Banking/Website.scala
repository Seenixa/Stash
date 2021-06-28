package Banking

class Website {
  var accountList = List[BankRepository]()
  var accountMap = Map[String, BankRepository]()

  def update = {
    accountList = loadToList
    accountMap = convertMap(accountList)
  }

  def checkExistence(account: BankRepository): Boolean = {
    accountMap.contains(account.username)
  }

  def login(username: String, password: String): BankRepository = {
    var account = new BankRepository("")

    if (accountMap.contains(username)) {
      account = accountMap(username)
    } else throw new Error("Account does not exist.")

    if (account.password == password) {
      account
    } else throw new Error("Wrong password.")
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
      if (tempRepo.getBalance != tempRepo.tempBalance.toInt)
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