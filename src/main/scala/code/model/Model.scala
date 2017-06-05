package code.model

case class Transaction(
  card: Int,
  valid_card: Boolean,
  expired: Boolean = false,
  pin_correct: Boolean = false,
  attempts: Int = 0,
  balance_after: Double = 0,
  amount: Double = 0,
  fee: Double = 0,
  transaction_type: String,
  confirmed: Boolean = false,
  bank: String = "Banco Union",
  gestion_dui: Option[Int] = None,
  aduana: Option[String] = None,
  agencia: Option[String] = None,
  dui: Option[String] = None
) {
  override def toString: String =
    """
      |Transaction:
      |  * valid_card: %s
      |  * expired: %s
      |  * pin_correct: %s
      |  * attempts: %s
      |  * balance_after: %s
      |  * amount: %s
      |  * fee: %s
      |  * transaction_type: %s
      |  * confirmed: %s
      |  * bank: %s,
      |  * gestion_dui: %s,
      |  * aduana: %s,
      |  * agencia: %s,
      |  * dui: %s
    """.stripMargin.format(
      valid_card,
      expired,
      pin_correct,
      attempts,
      balance_after,
      amount,
      fee,
      transaction_type,
      confirmed,
      bank,
      gestion_dui getOrElse "",
      aduana getOrElse "",
      agencia getOrElse "",
      dui getOrElse ""
    )
}

case class ATMBalance(var balance: Double) {
  override def toString: String = s"ATM Balance: $balance"
}

case class Commission(amount: Double)

case class Card(
  number: Int,
  pin:Int,
  bank: String,
  var balance: Double,
  var valid: Boolean,
  var attempts: Int,
  date: java.util.Date
)

case class Approval( message : String, approved : Boolean )

case class CardNumber(number: Int) {
  override def toString = s"Tarjeta: $number"
}

case class TransactionType(transaction_type: String) {
  override def toString = s"Operacion: $transaction_type"
}

case class Pin(pin: Int) {
  override def toString = s"Pin: $pin"
}