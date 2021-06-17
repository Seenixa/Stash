object AccountManagement {
  def main(args: Array[String]): Unit = {
    
    sealed trait Bank
    case object otp extends Bank
    case object raiffeisen extends Bank
    case object kandh extends Bank
    case object default extends Bank

    class website {
      private var bankAccounts: List[BankAccount] = List()
      var guest = new BankAccount("Guest", "", default)
      bankAccounts = guest :: bankAccounts
      
      def registerAccount(username: String, bank: Bank): Unit = {
        val newRegister = new BankAccount(username = username, bank = bank)
        var alreadyExists = false
        for (existingAccount <- bankAccounts) {
          if (existingAccount.username == newRegister.username) {
            alreadyExists = true
          }
        }
        if (alreadyExists == true)
          throw new Error("That username is already taken.")
        else {
          newRegister.bank.toString match {
            case "otp" => newRegister.Id += "0000-9834-"
            case "raiffeisen" => newRegister.Id += "1231-6723-"
            case "kandh" => newRegister.Id += "8721-1542-"
            case _ => println("WTF?")
          }
          newRegister.Id += (bankAccounts.length + 999).toString
          bankAccounts = newRegister :: bankAccounts
        }
      }

      def login(username: String, password: String): BankAccount = {
        var exists = false
        var returnAccount = new BankAccount("", "", default)
        for (existingAccount <- bankAccounts) {
          if (existingAccount.username == username) {
            exists = true
            returnAccount = existingAccount
          }
        }
        if (exists == false){
          throw new Error("Account with that username does not exist.")
        }
        if (returnAccount.password == password)
          returnAccount
        else
          throw new Error("Incorrect password.")
      }
      
      def logout: BankAccount ={
        guest
      }
      
      def locateAccountById(Id :String): BankAccount = {
        var exists = false
        var returnAccount = new BankAccount("", "", default)
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
      var password: String = "19960329",
      val bank: Bank  
    ) {

      private var balance = 0
      var email = ""
      var history = s"\n   *#* $username *#*"
      var Id = ""

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
        if (this == to)
          throw new Error("Why would you want to transfer to yourself?")
        this.withdraw(amount)
        this.history += s"\n$amount has be transferred to ${to.username}."
        to.deposit(amount)
        to.history += s"\n$amount has been transferred from ${this.username}."
        
      }

      override def toString = s"""\n|Username: $username
                                  |${if (email != "")  "E-mail: " + s"$email"; else ("")}
                                  |${if (balance != 0)  "Balance: " + s"$balance"; else ("")}
                                  |Bank: $bank
                                  |ID: $Id""".stripMargin
    }
    

    def driver: Unit = {
      val website = new website()
      var user = website.login("Guest", "")
      
      website.registerAccount("Akarki", kandh)
      website.registerAccount("valaki", kandh)
      website.registerAccount("Barki", otp)
      website.registerAccount("Senki", otp)

      user = website.login("Akarki", "19960329")
      user.email = "asdfas@gmail.com"
      user.changePassword("19960329", "Whatever", "Whatever")
      
      user.deposit(1000)
      user.withdraw(100)
      user.withdraw(100)
      user.withdraw(100)
      user.withdraw(100)
      println(user.history)
      user.transfer(100, website.locateAccountById("8721-1542-1001"))
      
      println(website.locateAccountById("8721-1542-1001"))
      println(s"\n${user.checkBalance}")
      println(user)
      println(user.history)
      println(user)
      
      if (user.Id.take(4) == "8721")
        println("\nkandh")
      
      user = website.logout
    }

    driver

  }
}
