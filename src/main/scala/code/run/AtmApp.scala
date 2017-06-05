package code.run

import code.run.Stages

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.effect.DropShadow
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{GridPane, HBox}
import scalafx.scene.paint.Color.{DarkGray, White}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.scene.image.{Image, ImageView}

object AtmApp extends JFXApp {

  stage = Stages.intro

}
