package code.run

import code.run.AtmApp.stage

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


object Stages {

  def intro: PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        stage = cardNumber
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

  def cardNumber: PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        stage = pin
      }
    }

    val cancelButton = new Button("Cancelar")
    cancelButton.onMouseClicked = {
      e: MouseEvent => {
        stage = intro
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val cardLabel = new Label("Nro. de tarjeta")

    val cardField = new TextField()

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

  def pin: PrimaryStage = new PrimaryStage {
    val nextButton = new Button("Siguiente")
    nextButton.onMouseClicked = {
      e: MouseEvent => {
        stage = transactionType
      }
    }

    val cancelButton = new Button("Cancelar")
    cancelButton.onMouseClicked = {
      e: MouseEvent => {
        stage = intro
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val pinLabel = new Label("PIN")

    val pinField = new TextField()

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
        """.stripMargin), 0, 0)
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

  def transactionType: PrimaryStage = new PrimaryStage {

    val cancelButton = new Button("Cancelar")
    cancelButton.onMouseClicked = {
      e: MouseEvent => {
        stage = intro
      }
    }

    val url = this.getClass.getResource("/images/logobancounion.png").toExternalForm

    val logo = new ImageView(
      new Image(url, requestedWidth = 300, requestedHeight = 269, preserveRatio = true, smooth = true))

    val withdrawButton = new Button("Retiro")
    withdrawButton.onMouseClicked = {
      e: MouseEvent => {
        stage = intro
      }
    }

    val duiButton = new Button("Impuestos")
    duiButton.onMouseClicked = {
      e: MouseEvent => {
        stage = intro
      }
    }

    val printButton = new Button("Saldo")
    printButton.onMouseClicked = {
      e: MouseEvent => {
        stage = intro
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
          |EL NUMERO DE PIN ASOCIADO A SU TARJETA LE FUE
          |PROPORCIONADO POR SU BANCO, ES UN NUMERO DE 4
          |DIGITOS PARA CUALQUIER CONSULTA COMUNIQUESE CON
          |SU BANCO.
          |
        """.stripMargin), 0, 0)
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
