/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class HomeController implements Initializable {

    boolean activenav = false;
    @FXML private Button menu = new Button();
    @FXML private ImageView avatar = new ImageView();
    @FXML private Pane content = new Pane();
    @FXML public Pane navbar = new Pane();
    
    @FXML
    private Pane header;

    @FXML
    private ImageView avatarHeader;

    @FXML
    private Pane infoUser;

    @FXML
    private ImageView avatarNavbar;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblID;

    @FXML
    private Pane contentTabs;

    @FXML
    private Button inicioTab;

    @FXML
    private Button directorioTab;

    @FXML
    private Button operacionesTab;

    @FXML
    private Button productosTab;

    @FXML
    private Button administrarTab;

    
    
    public void show() throws IOException{
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sicem/view/home/home.fxml")));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    private void handleMenu() throws InterruptedException{
        activenav = (navbar.getWidth() == 240)? true : false;
        
        Timer ani = new Timer();
        ani.scheduleAtFixedRate(new TimerTask(){
            int i = 1;
            
            @Override
            public void run(){
                if(i<=40){
                    if(activenav)
                        navbar.setPrefWidth(navbar.getWidth() - 5);
                    else
                        navbar.setPrefWidth(navbar.getWidth() + 5);
                }else
                    this.cancel();
                
                i++;
            }
        }, 750, 25);
        
        ani.purge();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        avatar.setImage(new Image("sicem/images/perfil.jpg"));
    }    
    
}
