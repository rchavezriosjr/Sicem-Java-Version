/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view;
import java.io.IOException;
import sicem.utilities.dialogos;
import sicem.view.home.HomeController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import static sicem.utilities.dialogos.tipo.info;
import static sicem.utilities.dialogos.tipo.confir;
import sicem.utilities.rounded;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class LoginController implements Initializable {
    
    @FXML private TextField username = new TextField();
    @FXML private PasswordField password = new PasswordField();
    @FXML private ImageView background = new ImageView();
    @FXML private Button ingresar = new Button();
    @FXML private Button close = new Button();
    @FXML private ImageView avatar = new ImageView();
    @FXML private Button iconfielduser = new Button();
    @FXML private Button iconfieldpass = new Button();
    
    
    @FXML
    private void changeNode(KeyEvent e) throws IOException{
        if(e.getCode() == ENTER){
            if(username.isFocused())
                password.requestFocus();
            else if(password.isFocused())
                IngresarVoid();
        }
    }
    
    
    @FXML private void handleIconfieldUser(ActionEvent e){ username.requestFocus(); }
    @FXML private void handleIconfieldPass(ActionEvent e){ password.requestFocus(); }
    
    
    @FXML
    private void handleIngresar(ActionEvent e) throws IOException{ IngresarVoid(); }
    private void IngresarVoid() throws IOException{
        new dialogos("Inicio de sesión", "Has iniciado sesión correctamente...", info).show();
        
        username.getParent().getScene().getWindow().hide();
        new HomeController().show();
    }
    
    
    @FXML
    private void handleClose(ActionEvent e){
        if(new dialogos("Cerrar sesión", "Cerrar sesión ?", confir).show())
            System.exit(0);
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        background.setImage(new Image("/sicem/images/login-background.png"));
        new rounded().roundImageView(avatar, new Image("/sicem/images/avatar.png"));
    }    
    
}
