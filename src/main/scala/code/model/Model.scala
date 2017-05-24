package code.model

case class Transaction(
  valid_card: Boolean,
  expired: Boolean,
  pin_correct: Boolean,
  attempts: Int,
  balance_after: Int,
  amount: Int,
  transaction_type: String,
  dui: Boolean,
  confirmed: Boolean,
  print: Boolean,
  bank: String
)

case class ATMBalance(balance: Int)

case class InactivityTime(time: Long)

case class RemoveCard(yes: Boolean)

case class Reset(yes: Boolean)

case class GoToStart(yes: Boolean)

case class TryAnotherTransaction(yes: Boolean)

case class RunOutOfAttempts(yes: Boolean)

case class Commission(amount: Int)

case class Print(yes: Boolean)