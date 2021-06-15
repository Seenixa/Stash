object AccountManagement {
  def main(args: Array[String]): Unit = {

    class website {
      private var bankAccounts: List[BankAccount] = List()
      var guest = new BankAccount("Guest", "")
      guest :: bankAccounts
      
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
          newRegister.Id = (bankAccounts.length + 2)
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
      
      def logout: BankAccount ={
        guest
      }
      
      def locateAccountById(Id :Int): BankAccount = {
        var exists = false
        var returnAccount = new BankAccount("")
        for (existingAccount <- bankAccounts){
          if (existingAccount.Id == Id){
            exists = true
            returnAccount = existingAccount
          }
        }
        if (exists == false)
          throw new Error("Account with that ID does not exist.")
        returnAccount
      }

    }

    class BankAccount(
        val username: String,
        var password: String = "19960329"
    ) {

      private var balance = 0
      var email = ""
      var history = ""
      var Id = 0

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
      
      def transfer (amount: Int, to: BankAccount) :Unit = {
        this.withdraw(amount)
        to.deposit(amount)
        
      }

      override def toString = s"""\n|Username: $username
                                  |${if (email != "")  "E-mail: " + s"$email"; else ("")}
                                  |${if (balance != 0)  "Balance: " + s"$balance"; else ("")}""".stripMargin
    }

    def driver: Unit = {
      val website = new website()

      website.registerAccount("Akarki")
      website.registerAccount("valaki")
      website.registerAccount("Barki")
      website.registerAccount("Senki")

      var user = website.login("Akarki", "19960329")
      user.email = "asdfas@gmail.com"
      user.changePassword("19960329", "Whatever", "Whatever")
      user.deposit(1000)
      user.withdraw(100)
      user.withdraw(100)
      user.withdraw(100)
      user.withdraw(100)
      println(user.history)
      user.transfer(100, website.locateAccountById(3))
      println(website.locateAccountById(3))
      println(s"\n${user.checkBalance}")
      println(user)
      user = website.logout
      println(user)
    }

    driver

  }
}
