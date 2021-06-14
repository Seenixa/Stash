object AccountManagement {
  def main(args: Array[String]): Unit = {

    class website {
      var bankAccounts: List[BankAccount] = List()

      def registerAccount(username: String) = {
        val newRegister = new BankAccount(username)
        var alreadyExists = false
        for (existingAccount <- bankAccounts) {
          if (existingAccount.username == newRegister.username) {
            alreadyExists = true
          }
        }
        if (alreadyExists == true)
          throw new Error("That username is already taken.")
        else {
          bankAccounts = newRegister :: bankAccounts
        }
      }

      def login(username: String, password: String): BankAccount = {
        var exists = false
        var returnAccount = new BankAccount("")
        for (existingAccount <- bankAccounts) {
          if (existingAccount.username == username) {
            exists = true
            returnAccount = existingAccount
          }
        }
        if (exists == false)
          throw new Error("Account with that username does not exist.")
        if (returnAccount.password == password)
          returnAccount
        else
          throw new Error("Incorrect Password.")
      }

    }

    class BankAccount(
        val username: String
    ) {

      private var balance = 0
      var password = "19960329"

      def changePassword(newPassword: String) = {
        password = newPassword
      }

      def deposit(amount: Int) = {
        if (amount > 0)
          balance += amount
      }

      def withdraw(amount: Int) = {
        if (0 < amount && amount <= balance)
          balance -= amount
        else
          throw new Error("insufficient funds")
      }

      override def toString = s"""\n|Username: $username
                                  |Balance: $balance
                                  |password: $password\n""".stripMargin
    }

    def driver = {
      val website = new website()

      website.registerAccount("Akarki")
      website.registerAccount("valaki")
      website.registerAccount("Barki")
      website.registerAccount("Senki")
      val user = website.login("Akarki", "19960329")
      user.deposit(1000)
      user.withdraw(100)
      println(user)
    }

    driver

  }
}
