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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class DetalleEmpleadoController implements Initializable {

    @FXML private ImageView foto;
    @FXML private TextField txtID;
    @FXML private TextField txtNombre;
    @FXML private TabPane contentTabs;
    @FXML private Tab infoPersonal;
    @FXML private Tab infoLaboral;
    @FXML private Button editar;
    @FXML private TextField txtGenero;
    @FXML private AnchorPane contentInfoPersonal;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTel;
    @FXML private TextField txtCedula;
    @FXML private TextArea txtDireccion;
    @FXML private TextField txtEstadoCivil;
    @FXML private TextField txtCiudad;
    @FXML private TextField fechaNacimiento;
    @FXML private AnchorPane contentInfoLaboral;
    @FXML private TextField txtDepartamento;
    @FXML private TextField txtTituloLaboral;
    @FXML private TextField txtReportarA;
    @FXML private TextArea txtObservaciones;
    @FXML private TextField txtFechaContratacion;
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
