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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class EmpleadoFormController implements Initializable {

    @FXML private ImageView foto;
    @FXML private RadioButton generoMasculino;
    @FXML private ToggleGroup genero;
    @FXML private RadioButton generoFemenino;
    @FXML private TextField txtID;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TabPane contentTabs;
    @FXML private Tab infoPersonal;
    @FXML private Tab infoLaboral;
    @FXML private CheckBox estadoValue;
    @FXML private Button cancelar;
    @FXML private Button guardar;
    @FXML private AnchorPane contentInfoLaboral;
    @FXML private DatePicker fechaContratacion;
    @FXML private TextField txtDepartamento;
    @FXML private TextField txtTituloLaboral;
    @FXML private TextField txtReportarA;
    @FXML private TextArea txtObservaciones;
    @FXML private AnchorPane contentInfoPersonal;
    @FXML private DatePicker fechaNacimiento;
    @FXML private ComboBox<?> txtCiudad;
    @FXML private RadioButton ecSoltero;
    @FXML private ToggleGroup estadocivil;
    @FXML private RadioButton ecCasado;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTel;
    @FXML private TextField txtCedula;
    @FXML private TextArea txtDireccion;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
