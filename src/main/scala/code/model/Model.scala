package code.model

case class Transaction(
  valid_card: Boolean,
  expired: Boolean,
  pin_correct: Boolean,
  attempts: Int,
  balance_after: Double,
  amount: Double,
  fee: Double,
  transaction_type: String,
  confirmed: Boolean,
  bank: String
)

case class ATMBalance(var balance: Double)

case class Commission(amount: Double)

case class Card(
  number: Int,
  ping:Int,
  bank: String,
  var balance: Double,
  var valid: Boolean,
  var attempts: Int,
  date: java.util.Date
)

case class Approval( message : String, approved : Boolean )