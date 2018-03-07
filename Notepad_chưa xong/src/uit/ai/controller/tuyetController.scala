package uit.ai.controller

import main.scala.tuyet
import javafx.scene.control.MenuItem
import javafx.fxml.FXML
import scalafxml.core.macros.sfxml
import scala.reflect.io.File
import scalafx.stage.FileChooser
import scala.collection.Set
import scalafx.event.ActionEvent
import scalafx.scene.control.TextArea
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXDialogLayout
import scalafx.scene.Scene
import scalafx.application.JFXApp.PrimaryStage
import scala.io.Source
import java.io.PrintWriter
import scalafx.application.Platform
import com.jfoenix.controls.JFXButton
import java.util.List
import java.util.ArrayList
import javafx.scene.Node
import javafx.scene.layout.StackPane
import java.awt.datatransfer.StringSelection
import scalafx.scene.text.Text
import java.awt.Toolkit
import scalafx.scene.text.Font
import scalafx.scene.control.CheckMenuItem
import org.controlsfx.dialog.FontSelectorDialog
import scalafx.event.EventHandler


//import java.io.FileNotFoundException
//import java.awt.Dialog
//import org.controlsfx.dialog.FontSelectorDialog

@sfxml
class tuyetController(
  @FXML private val mFile: MenuItem,
  @FXML private val miNew: MenuItem,
  @FXML private val miOpen: MenuItem,
  @FXML private val miSave: MenuItem,
  @FXML private val miExit: MenuItem,

  @FXML private val mEdit: MenuItem,
  @FXML private val miCut: MenuItem,
  @FXML private val miCopy: MenuItem,
  @FXML private val miPaste: MenuItem,
  @FXML private val miDelete: MenuItem,

  @FXML private val mFormat: MenuItem,
  @FXML private var cmiWordWrap: CheckMenuItem,
  @FXML private val miFont: MenuItem,

  @FXML private val mView: MenuItem,
  @FXML private val cmStatusBar: MenuItem,

  @FXML private val mHelp: MenuItem,

  @FXML private var miViewHelp: MenuItem,
  @FXML private var miAboutNotepad: MenuItem,
  @FXML private var tArea: TextArea) {
  //@FXML private var dialog: JFXDialog){

  def newAction(event: ActionEvent) {

    if (tArea.text != null) {
      val confirmForm = new JFXDialog // dialog
      val dialogLayout = new JFXDialogLayout // thiết kế layout
      dialogLayout.setHeading(new Text("Notepad")) // tiêu đề
      dialogLayout.setBody(new Text("Do you want to save changes to Untitled?")) //nội dung

      //3 nút lựa chọn
      val btnSave = new JFXButton("Save")
      btnSave.setMinWidth(100)
      btnSave.setMinHeight(40)
      btnSave.setStyle("-fx-background-color: #00ffff") //màu nền trắng
      //btnSave.setTextFill(Paint : 00fff)

      val btnCancel = new JFXButton("Cancel")
      btnCancel.setMinWidth(100)
      btnCancel.setMinHeight(40)
      btnCancel.setStyle("-fx-background-color:#00ffff") //màu nền trắng
      // btnSave.setStyle("fx-textFill-color: #00ffff")

      val btnDSave = new JFXButton("Don't Save")
      btnDSave.setMinWidth(100)
      btnDSave.setMinHeight(40)
      btnDSave.setStyle("-fx-background-color: #00ffff")
      // btnSave.setStyle("fx-textFill-color: #00ffff")
      //màu nền trắng

      btnSave.setOnAction(e => {
        SaveAction(event: ActionEvent)
        confirmForm.close
        tArea.text = ""
      })
      btnCancel.setOnAction(e => {
        confirmForm.close // đóng dialog, không thoát nữa
      })

      btnDSave.setOnAction(e => {
        confirmForm.close // thoát chương trình
        tArea.text = ""
        //khong thoat chuong trinh ma lam moi lai no
      })
      //them 3 nut vao danh sach
      val arrayBtn = new ArrayList[Node]() //
      arrayBtn.add(btnSave)
      arrayBtn.add(btnDSave)
      arrayBtn.add(btnCancel)

      // thêm danh sách ba nút vào layout
      dialogLayout.setActions(arrayBtn)
      // set nội dung của dialog là layout
      confirmForm.setContent(dialogLayout)
      confirmForm.setTransitionType(JFXDialog.DialogTransition.BOTTOM)
      confirmForm.show(tuyet.stage.getScene.getRoot.asInstanceOf[StackPane])

    }

  }

  def startOpen(event: ActionEvent) {
    val fileChooser = new FileChooser()
    fileChooser.setTitle("Open File")
    val stage = new PrimaryStage
    val selectFile = fileChooser.showOpenDialog(stage)
    if (selectFile != null) {
      val source = Source.fromFile(selectFile)
      var text: String = ""
      tArea.text = ""
      for (line <- source.getLines) {
        println(line)
        // text = text + line + "\n"
        tArea.appendText(line + "\n")
      }
      //tArea.text = text
    }
  }

  def SaveAction(event: ActionEvent) {
    val fileChooser = new FileChooser()
    fileChooser.setTitle("Save File")
    val stage = new PrimaryStage
    val selectFile = fileChooser.showSaveDialog(stage)
    val saveFile = new PrintWriter(selectFile) {
      for (line <- tArea.getText.split("\n")) {
        println(line)
      }
      close
    }
  }
  //exit
  def ExitAction(event: ActionEvent) {
    if (tArea.text != null) {
      val confirmForm = new JFXDialog // dialog
      val dialogLayout = new JFXDialogLayout // thiết kế layout
      dialogLayout.setHeading(new Text("Notepad")) // tiêu đề
      dialogLayout.setBody(new Text("Do you want to save changes to Untitled?")) //nội dung

      //3 nút lựa chọn
      val btnSave = new JFXButton("Save")
      btnSave.setMinWidth(100)
      btnSave.setMinHeight(40)
      btnSave.setStyle("-fx-background-color: #00ffff") //màu nền trắng
      //btnSave.setTextFill(Paint : 00fff)

      val btnCancel = new JFXButton("Cancel")
      btnCancel.setMinWidth(100)
      btnCancel.setMinHeight(40)
      btnCancel.setStyle("-fx-background-color:#00ffff") //màu nền trắng
      // btnSave.setStyle("fx-textFill-color: #00ffff")

      val btnDSave = new JFXButton("Don't Save")
      btnDSave.setMinWidth(100)
      btnDSave.setMinHeight(40)
      btnDSave.setStyle("-fx-background-color: #00ffff")
      // btnSave.setStyle("fx-textFill-color: #00ffff")
      //màu nền trắng

      btnSave.setOnAction(e => {
        SaveAction(event: ActionEvent)
        confirmForm.close
      })
      btnCancel.setOnAction(e => {
        confirmForm.close // đóng dialog, không thoát nữa
      })

      btnDSave.setOnAction(e => {
        confirmForm.close // thoát chương trình

        //khong thoat chuong trinh ma lam moi lai no
      })
      //them 3 nut vao danh sach
      val arrayBtn = new ArrayList[Node]() //
      arrayBtn.add(btnSave)
      arrayBtn.add(btnDSave)
      arrayBtn.add(btnCancel)

      // thêm danh sách ba nút vào layout
      dialogLayout.setActions(arrayBtn)
      // set nội dung của dialog là layout
      confirmForm.setContent(dialogLayout)
      confirmForm.setTransitionType(JFXDialog.DialogTransition.BOTTOM)
      confirmForm.show(tuyet.stage.getScene.getRoot.asInstanceOf[StackPane])
    }
    Platform.exit()
    //why?
  }
  //copy : code có trên mạng

  def Copy(event: ActionEvent) {
    val myString = "This text will be copied into clipboard when running this code!";
    val stringSelection = new StringSelection(myString); //chọn vùng bôi đen
    val clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null); //ghi giá tri đã chọn vào clipboard
    tArea.copy()
  }
  def Cut(event: ActionEvent) {
    val myString = "This text will be copied into clipboard when running this code!";
    val stringSelection = new StringSelection(myString); //chọn vùng bôi đen
    val clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null); //ghi giá tri đã chọn vào clipboard
    tArea.cut()
    // tArea.replaceSelection("")

  }

  //paste đã có sẵn chỉ cần paste
  def Paste(event: ActionEvent) {
    tArea.paste()
  }
  //set selection = "" --> thay thế lại bằng văn bản trống
  def Delete(event: ActionEvent) {
    tArea.replaceSelection("")
  }
  //WordWrap lưu ý phải ở on Menu Validation
  def startWordWrap(event: ActionEvent => println("true") )
  def ChooseFont(event: ActionEvent) {
    val confirmForm = new JFXDialog()
    val dialogFont = new FontSelectorDialog(null)
    dialogFont.setTitle("Font")
    dialogFont.show()
  }

}









