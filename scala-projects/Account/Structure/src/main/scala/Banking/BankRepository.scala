package Banking

class BankRepository(
  val username:     String,
  var password:     String = "",
  var emailAddress: String = "") {

  private var balance = 0
  var tempBalance = "0"
  private var history = ""

  def getBalance: Int = {
    balance
  }

  def updateBalance: Unit = {
    balance = tempBalance.toInt
  }

  def changeBalance(amount: Int): Unit = {
    balance += amount
  }

  def checkBalance: String = {
    s"Your current balance is $balance"
  }

  def writeHistory(transaction: String): Unit = {
    history += transaction
  }

  def checkHistory: String = {
    s"$history"
  }

  def save(to: Website): Unit = {
    import java.io._
    if (!to.checkExistence(this)) {
      to.accountList = this :: to.accountList
      to.accountMap = to.convertMap(to.accountList)
      val fw = new FileWriter("Repositories.txt", true)
      try {
        fw.write(s"$username\n$balance\n$password\n$emailAddress\n")
      } catch {
        case e: FileNotFoundException => {
          val pw = new PrintWriter(new File("Repositories.txt"))
          pw.write(s"$username\n$balance\n$password\n$emailAddress\n")
          pw.close
        }
      } finally fw.close()
    }
    to.update
  }

  override def toString = s"""
    |username: $username
    |balance: $tempBalance
    |password: $password
    |emailAddress: $emailAddress""".stripMargin

}



