object AccountManagement {
  def main(args: Array[String]): Unit = {

    class website {
      var bankAccounts: List[BankAccount] = List()

      def registerAccount(username: String): Unit = {
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
          throw new Error("Incorrect password.")
      }

    }

    class BankAccount(
        val username: String
    ) {

      private var balance = 0
      var password = "19960329"
      var history = ""

      def changePassword(oldPassword: String, newPassword: String, newPasswordAgain: String): Unit = {
        if (newPassword == newPasswordAgain && oldPassword == password)
          password = newPassword
        else if (newPassword != newPasswordAgain)
          throw new Error ("You have to write the same new password twice.")
        else if (oldPassword != password)
          throw new Error ("Incorrect password.")
          
      }

      def checkBalance: String = {
        s"Your current balance is : $balance,-"
      }

      def deposit(amount: Int): Unit = {
        if (amount > 0) {
          balance += amount
          history += s"\n$amount has been deposited."
        }

      }

      def withdraw(amount: Int): Unit = {
        if (0 < amount && amount <= balance) {
          balance -= amount
          history += s"\n$amount has been withdrawn."
        } 
        else
          throw new Error("insufficient funds")
      }

      override def toString = s"""\n|Username: $username
                                  |Balance: $balance""".stripMargin
    }

    def driver: Unit = {
      val website = new website()

      website.registerAccount("Akarki")
      website.registerAccount("valaki")
      website.registerAccount("Barki")
      website.registerAccount("Senki")

      val user = website.login("Akarki", "19960329")
      user.changePassword("19960329", "Whatever", "Whatever")
      val newuser = website.login("Akarki", "Whatever")
      user.deposit(1000)
      user.withdraw(100)
      user.withdraw(100)
      user.withdraw(100)
      user.withdraw(100)
      println(user.history)

      println(user.checkBalance)
      println(user)
    }

    driver

  }
}
