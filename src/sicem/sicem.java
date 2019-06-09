/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem;

import DB.conexion;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import sicem.view.LoginController;
import sicem.view.configuracion.ConexionWizardController;
import sicem.view.directorio.ClienteFormController;

/**
 *
 * @author espinoza
 */
public class sicem extends Application {
    
    private void inicia(){
        try{
            conexion c = new conexion();
            c.open();
            new ClienteFormController().show();
                    
            c.close();
        }catch(Exception ex){
            new ConexionWizardController().show();
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        inicia();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
PseudoClass errorClass = PseudoClass.getPseudoClass("error");
textfield.pesudoClassStateChanged(errorClass, true) // or false to unset it
*/
