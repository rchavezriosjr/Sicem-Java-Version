/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view.home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class InicioController implements Initializable {

    @FXML private Label clientesContador;
    @FXML private Label productosContador;
    @FXML private Label proveedoresContador;
    @FXML private Label ventasContador;
    @FXML private Button showBaged;
    @FXML private Pane bagedContent;
    @FXML private Button hideBaged;
    @FXML private Button clienteItem;
    @FXML private Button compraItem;
    @FXML private Button proveedorItem;
    @FXML private Button ventaItem;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
