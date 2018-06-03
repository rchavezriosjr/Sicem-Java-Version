/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view.directorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class DetalleClienteController implements Initializable {

    @FXML private TextField txtNombre;
    @FXML private TextField txtID;
    @FXML private TextField txtNombreContacto;
    @FXML private TextField txtTituloContacto;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTel;
    @FXML private TextArea txtDireccion;
    @FXML private Button editar;
    @FXML private TextField txtCiudad;
    @FXML private Label EstadoValue;
    @FXML private Label labelFechaModificacion;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
