package code.data

import code.model.{ATMBalance, Card}


object BaseData {

  import java.text.SimpleDateFormat

  val formatter = new SimpleDateFormat("dd-MM-yyyy")

  val cards: List[Card] = List(
    Card(1, 1111, "Banco Union", 2000, true, 1, formatter.parse("31-09-2020")),
    Card(2, 2222, "Banco Union", 0.0, true, 2, formatter.parse("31-09-2020")),
    Card(3, 3333, "Banco Union", 1000, true, 0, formatter.parse("31-09-2020")),
    Card(4, 4444, "Banco Ganadero", 200.20, false, 3, formatter.parse("31-09-2020")),
    Card(5, 5555, "Banco Nacional de Bolivia", 200.20, true, 0, formatter.parse("31-01-2016")),
    Card(6, 6666, "Banco Sol", 1200.23, true, 0, formatter.parse("31-09-2020")),
    Card(7, 7777, "Banco Mercantil Santa Cruz", 8000, true, 0, formatter.parse("31-09-2020")),
    Card(8, 8888, "Banco Mercantil Santa Cruz", 10000, true, 0, formatter.parse("31-09-2020")),
    Card(9, 9999, "Banco Nacional de Bolivia", 0.20, true, 0, formatter.parse("31-09-2020")),
    Card(10, 1010, "Banco Sol", 100.20, true, 0, formatter.parse("31-09-2020"))
  )

  val aTMBalance = ATMBalance(1000)
}
