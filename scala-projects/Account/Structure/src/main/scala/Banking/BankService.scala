package Banking

class BankService(
  val BankAccountRepo: BankRepository) {

  def withdraw(amount: Int): Unit = {
    val balance = BankAccountRepo.getBalance
    if (amount > 0 && balance >= amount) {
      BankAccountRepo.changeBalance(-amount)
      BankAccountRepo.writeHistory(s"\n$amount has been withdrawn.")
    } else throw new Error("Not enough minerals.")
  }

  def deposit(amount: Int): Unit = {
    if (amount > 0) {
      BankAccountRepo.changeBalance(amount)
      BankAccountRepo.writeHistory(s"\n$amount has been deposited.")
    }
  }

  def transfer(amount: Int, to: BankRepository): Unit = {
    if (amount > 0 && BankAccountRepo.getBalance > amount) {
      withdraw(amount)
      try {
      to.changeBalance(amount)
      } catch {
        case _: Throwable => deposit(amount)
      }
      
      to.writeHistory(s"\n$amount has been transferred from ${BankAccountRepo.username}.")
    }
  }

}