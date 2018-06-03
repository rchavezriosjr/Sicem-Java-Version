/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view.operaciones;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class OperacionesController implements Initializable {
    
    @FXML private Label labelVentas;
    @FXML private Label labelCompras;
    @FXML private ImageView imageviewSearch;
    @FXML private TextField txtBusqueda;
    @FXML private ChoiceBox<?> metodoBusqueda;
    @FXML private Button btnAdd;
    @FXML private TableView<?> vistaResultados;
    @FXML private TableColumn<?, ?> columnID;
    @FXML private TableColumn<?, ?> columnFecha;
    @FXML private TableColumn<?, ?> columnFechaModificacion;
    @FXML private Pane contentDetails;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
