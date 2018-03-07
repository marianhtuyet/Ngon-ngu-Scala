package main.scala

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafxml.core.FXMLView
import scalafxml.core.NoDependencyResolver
import scalafx.scene.layout.StackPane
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.event.ActionEvent
import scalafx.stage.FileChooser
import scalafx.scene.image.Image

object tuyet extends JFXApp {
  val resource = getClass.getResource("/uit/ai/view/Notepad.fxml")
  val root = FXMLView(resource, NoDependencyResolver)
  val stackPane = new StackPane()
  stackPane.getChildren.add(root)

  stage = new PrimaryStage {
    title = "Notepad"
   icons += new Image("/uit/ai/view/Notepad.jpg")
    scene = new Scene(stackPane)
  }
  

}

/*object tuyet {
  def main(args: Array[String]): Unit = {
    println("Hello")
  }
}*/