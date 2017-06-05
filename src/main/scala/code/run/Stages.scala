package code.run

import code.data.BaseData
import code.model._
import code.run.AtmApp.stage
import org.kie.api.KieServices
import org.kie.api.runtime.{ClassObjectFilter, KieSession}

import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.{Scene, control, layout}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.scene.layout.StackPane
import control._
import layout._
import scalafx.event._
import scalafx.Includes._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.stage.Stage
import scala.collection.JavaConverters._
import scala.util.Try


object Stages {

  private val logText = "Working Memory\n\n"

  var session: KieSession = _

  def introStage: PrimaryStage = new PrimaryStage {
    Try {
      session.dispose()
    }
    session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    session.insert(BaseData.aTMBalance)
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        stage = cardNumberStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(nextButton, 1, 1)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |ESTE PROGRAMA SIMULA EL COMPORTAMIENTO DE
          |UN CAJERO (ATM) DEL BANCO UNION, PERMITE:
          |
          | * EL PAGO DE IMPUESTOS ADUANEROS (DIU)
          | * EL RETIRO DE DINERO
          | * LA IMPRESION DE SALDO
          |
          |PRESIONE "SIGUIENTE" PARA CONTINUAR.
        """.stripMargin), 0, 0)

      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

  def cardNumberStage: PrimaryStage = new PrimaryStage {

    val cardLabel = new Label("Nro. de tarjeta")

    val cardField = new TextField()

    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        session.insert(CardNumber(cardField.getText.toInt))
        stage = pinStage
      }
    }

    val cancelButton = new Button("Cancelar")
    cancelButton.onMouseClicked = {
      e: MouseEvent => {
        stage = introStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(cardLabel, 0, 1)
      add(cardField, 1, 1)
      add(cancelButton, 0, 2)
      add(nextButton, 1, 2)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |LA TARJETA LE FUE PROPORCIONADA POR SU BANCO
          |PARA CUALQUIER CONSULTA COMUNIQUESE CON SU BANCO
          |
          |
        """.stripMargin), 0, 0)
      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

  def pinStage: PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        session.insert(Pin(pinField.getText.toInt))
        stage = transactionTypeStage
      }
    }

    val cancelButton = new Button("Cancelar")
    cancelButton.onMouseClicked = {
      e: MouseEvent => {
        stage = introStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val pinLabel = new Label("PIN")

    val pinField = new PasswordField()

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(pinLabel, 0, 1)
      add(pinField, 1, 1)
      add(cancelButton, 0, 2)
      add(nextButton, 1, 2)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |EL NUMERO DE PIN ASOCIADO A SU TARJETA LE FUE
          |PROPORCIONADO POR SU BANCO, ES UN NUMERO DE 4
          |DIGITOS PARA CUALQUIER CONSULTA COMUNIQUESE CON
          |SU BANCO.
          |
          |IMPORTANTE: SI DURANTE EL DIA COMETE 3
          |EQUIVOCACIONES LA TARJETA QUEDARA ANULADA.
          |
        """.stripMargin), 0, 0)
      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

  def transactionTypeStage: PrimaryStage = new PrimaryStage {

    val cancelButton = new Button("Cancelar")
    cancelButton.onMouseClicked = {
      e: MouseEvent => {
        stage = introStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val withdrawButton = new Button("Retiro")
    withdrawButton.onMouseClicked = {
      e: MouseEvent => {
        session.insert(TransactionType("withdraw"))
        stage = withdrawStage
      }
    }

    val duiButton = new Button("Impuestos")
    duiButton.onMouseClicked = {
      e: MouseEvent => {
        session.insert(TransactionType("dui"))
        stage = introStage
      }
    }

    val printButton = new Button("Saldo")
    printButton.onMouseClicked = {
      e: MouseEvent => {
        session.insert(TransactionType("print"))
        val cardNumber = session.getObjects(new ClassObjectFilter(classOf[CardNumber])).toArray.toList(0).asInstanceOf[CardNumber]
        val card = BaseData.cards.find(_.number == cardNumber.number)
        val pin = session.getObjects(new ClassObjectFilter(classOf[Pin])).toArray.toList(0).asInstanceOf[Pin]
        val now = new java.util.Date
        val transaction = card match {
          case None => Transaction(
            card = cardNumber.number,
            valid_card = false,
            transaction_type = "print"
          )
          case Some(c) => Transaction(
            card = cardNumber.number,
            valid_card = true,
            expired = c.date.before(now),
            pin_correct = c.pin == pin.pin,
            attempts = if (c.pin == pin.pin) c.attempts else c.attempts + 1,
            balance_after = c.balance ,
            amount = 0.0,
            fee = 0.0, //ToDo,
            transaction_type = "print",
            confirmed = true,
            bank = c.bank
          )
        }
        session.insert(transaction)
        session.fireAllRules()
        val result = session.getObjects(new ClassObjectFilter(classOf[Approval])).toArray.toList(0).asInstanceOf[Approval]
        if (result.approved) {
          stage = printStage(transaction.card, transaction.bank, transaction.balance_after)
        } else {
          BaseData.cards.find(_.number == transaction.card).foreach(c => {
            if (transaction.attempts >= 3) {
              c.attempts = transaction.attempts
              c.valid = false
            }
          })
          val reason: String = if (transaction.attempts >= 3) {
            "Datos erroneos: Numero de intentos diarios excedidos, su tarjeta sera retenida"
          } else if (transaction.expired) {
            "Tarjeta expirada"
          } else if (transaction.balance_after < 0) {
            "Monto excede el saldo disponible"
          } else {
            "Datos erroneos"
          }
          stage = errorStage(reason)
        }
      }
    }

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(withdrawButton, 1, 1)
      add(duiButton, 1, 2)
      add(printButton, 1, 4)
      add(cancelButton, 0, 4)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |* RETIRO: MONTO MAXIMO 500 BS.
          |
          |* PAGO DE IMPUESTOS: ADUANEROS DUI, DEBE
          |  TENER LOS DATOS PROPORCIONADOS POR ADUANAS
          |
          |* SALDO: IMPRIME UN COMPROBANTE CON EL SALDO
          |
        """.stripMargin), 0, 0)
      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

  def withdrawStage: PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        val amount = amountField.getText.toDouble
        val cardNumber = session.getObjects(new ClassObjectFilter(classOf[CardNumber])).toArray.toList(0).asInstanceOf[CardNumber]
        val card = BaseData.cards.find(_.number == cardNumber.number)
        val pin = session.getObjects(new ClassObjectFilter(classOf[Pin])).toArray.toList(0).asInstanceOf[Pin]
        val now = new java.util.Date
        val transaction = card match {
          case None => Transaction(
            card = cardNumber.number,
            valid_card = false,
            transaction_type = "withdraw"
          )
          case Some(c) => Transaction(
            card = cardNumber.number,
            valid_card = true,
            expired = c.date.before(now),
            pin_correct = c.pin == pin.pin,
            attempts = if (c.pin == pin.pin) c.attempts else c.attempts + 1,
            balance_after = c.balance - amount,
            amount = amount,
            fee = 0.0, //ToDo,
            transaction_type = "withdraw",
            confirmed = true,
            bank = c.bank
          )
        }
        session.insert(transaction)
        session.fireAllRules()
        val result = session.getObjects(new ClassObjectFilter(classOf[Approval])).toArray.toList(0).asInstanceOf[Approval]
        if (result.approved) {
          BaseData.cards.find(_.number == transaction.card).foreach(c => {
            c.balance = c.balance - amount
          })
          BaseData.aTMBalance.balance = BaseData.aTMBalance.balance - amount
          stage = successStage
        } else {
          BaseData.cards.find(_.number == transaction.card).foreach(c => {
            if (transaction.attempts >= 3) {
              c.attempts = transaction.attempts
              c.valid = false
            }
          })
          val reason: String = if (transaction.attempts >=3) {
            "Datos erroneos: Numero de intentos diarios excedidos, su tarjeta sera retenida"
          } else if (transaction.expired) {
            "Tarjeta expirada"
          } else if (transaction.balance_after < 0) {
            "Monto excede el saldo disponible"
          } else {
            "Datos erroneos"
          }
          stage = errorStage(reason)
        }
      }
    }

    val cancelButton = new Button("Cancelar")
    cancelButton.onMouseClicked = {
      e: MouseEvent => {
        stage = introStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val amountLabel = new Label("MONTO")

    val amountField = new TextField()

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(amountLabel, 0, 1)
      add(amountField, 1, 1)
      add(cancelButton, 0, 2)
      add(nextButton, 1, 2)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |EL MONTO DEBE SER EN MULTIPLOS DE 10,
          |SIN DECIMALES.
          |
        """.stripMargin), 0, 0)
      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

  def successStage: PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        stage = introStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(new Text("TRANSACCION COMPLETADA"), 0, 1)
      add(nextButton, 1, 2)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |SI USTED HA LLEGADO HASTA ESTA PANTALLA
          |SU TRANSACCION HA SIDO COMPLETADA.
          |
          |PRESIONE "SIGUIENTE" PARA CONTINUAR.
        """.stripMargin), 0, 0)

      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

  def errorStage(reason: String): PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        stage = introStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(new Text(s"NO SE PUDO COMPLETAR LA TRANSACCION: $reason"), 0, 1)
      add(nextButton, 1, 2)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |SI USTED HA LLEGADO HASTA ESTA PANTALLA
          |SU TRANSACCION  NO HA SIDO COMPLETADA.
          |
          |PRESIONE "SIGUIENTE" PARA CONTINUAR.
        """.stripMargin), 0, 0)

      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

  def printStage(card: Int, bank: String, balance: Double): PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        stage = introStage
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val grid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(logo, 0, 0)
      add(new Text(
        """
          |SALDO
          |------
          |
          | * TARJETA: %s
          | * BANCO: %s
          | * SALDO: %s
        """.stripMargin.format(card, bank, balance)), 0, 1)
      add(nextButton, 1, 2)
    }

    val baseGrid = new GridPane() {
      hgap = 10
      vgap = 10
      padding = Insets(20, 100, 10, 10)

      add(new Text(
        """
          |INFORMACION
          |
          |SI USTED HA LLEGADO HASTA ESTA PANTALLA
          |SU TRANSACCION  NO HA SIDO COMPLETADA Y
          |PUEDE VER LOS DATOS Y SALDO DE SU TARJETA.
          |
          |PRESIONE "SIGUIENTE" PARA CONTINUAR.
        """.stripMargin), 0, 0)

      add(new Text(logText + session.getObjects().toArray.mkString("\n")), 0, 1)
      add(grid, 1, 0)
    }

    title = "ATM - Banco Union"
    scene = new Scene {
      fill = Color.White
      content = baseGrid
    }
    minHeight = 800

    minWidth = 600

  }

}
