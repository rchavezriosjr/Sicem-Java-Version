/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view;
import DB.sqlite;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.io.IOException;
import sicem.utilities.dialogos;
import sicem.view.home.HomeController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objetos.SearchResult;
import objetos.usuario;
import sicem.utilities.Load;
import sicem.utilities.dialogos.tipo;
import static sicem.utilities.dialogos.tipo.confir;
import sicem.utilities.rounded;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class LoginController implements Initializable {
    
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private ImageView background;
    @FXML private Button ingresar;
    @FXML private Button close;
    @FXML private ImageView avatar;
    @FXML private Button iconfielduser;
    @FXML private Button iconfieldpass;
    HomeController home = null;
    Scene scene = null;
    
    public LoginController(){}
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicia();
    } 
    
    
    private void inicia(){
        background.setImage(new Image("/sicem/images/login-background.png"));
        rounded.roundImageView(avatar, new Image("/sicem/images/avatar.png"));
        close.requestFocus();
        
        ingresar.disableProperty().bind(username.textProperty().isEmpty().or(password.textProperty().isEmpty()));
        close.setTooltip(new Tooltip("Salir"));
        username.setTooltip(new Tooltip("Nombre de usuario"));
        password.setTooltip(new Tooltip("Contraseña"));
        
        
        // evento de escucha para las sugerencias
        username.textProperty().addListener((arg, oldValue, newValue) -> {
            if(username.getText().length() > 0)
                new AutoCompletionTextFieldBinding<>(username, SuggestionProvider.create(sugerencia(newValue)));

        });
        username.setOnKeyPressed(e -> {
            if(e.getCode() == ENTER)
                password.requestFocus();
        });
        iconfielduser.setOnAction(e -> { username.requestFocus(); });
        
        
        // evento propiedad onFocus de password
        password.focusedProperty().addListener((arg, oldValue, newValue) -> {
            if(newValue && username.getText().length() > 0)
                rounded.roundImageView(avatar, new usuario().obtenerFoto(username.getText().trim()));
        });
        password.setOnKeyPressed(e -> {
            if(e.getCode() == ENTER)
                    IngresarVoid();
        });
        iconfieldpass.setOnAction(e -> { password.requestFocus(); });
        
        
        //Evtv close
        close.setOnAction(e -> {
            if(new dialogos("Cerrar sesión", "¿Salir del sistema?", confir).show())
                System.exit(0);
        });
        
        
        //Evt ingresar
        ingresar.setOnAction(e -> { IngresarVoid(); });
    }
    
    
    private ObservableList<String> sugerencia(String value){
        ObservableList<String> items = FXCollections.observableArrayList();
        ObservableList<SearchResult> sr = new usuario().Buscar(value, 0);
        for(SearchResult tem : sr){ items.add(tem.getId()); }
        
        return items;
    }
    
    
    private void IngresarVoid(){
        if(true){//new usuario().Verifica(username.getText().trim(), password.getText().trim())){
            ingresar.setVisible(false);
            try{
                sqlite.setUser(username.getText());
                FXMLLoader loader = Load.Loader("/sicem/view/home/home.fxml");
                scene = new Scene(loader.load());
                home = loader.getController();
                Load.Form(scene).show();
                close();
            }catch(IOException ex){ 
                System.out.println("Error carga components"); 
                ingresar.setVisible(true);
            }
        }else
            new dialogos(
                    "Usuario o contraseña incorrectos",
                    "Error al iniciar sesión. El usuario o contraseña son incorrectos.",
                    tipo.error).show();
    }
    
    
    public void show(){
        try{
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sicem/view/login.fxml")));
            scene.setFill(Color.TRANSPARENT);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("/sicem/images/favicon.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        }catch(Exception ex){}
    }
    
    public void close(){
        Stage s = (Stage) username.getScene().getWindow();
        s.close();
    }
}
