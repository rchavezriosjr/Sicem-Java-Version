/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view.administrar;

import DB.conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import objetos.departamento;
import sicem.utilities.Load;
import sicem.utilities.Popup;
/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class DetalleDepartamentosController implements Initializable {

	@FXML private TextField txtID;
    @FXML private TextField txtNombre;
    @FXML private TextField txtNombreGrupo;
    @FXML private Button editar;
    @FXML private Label EstadoValue;
    @FXML private Label labelFechaCreacion;
    @FXML private Label labelFechaModificacion;
    
    conexion c = new conexion();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicia();
    }    
    
    
    private void inicia(){
        editar.setVisible(false);
        labelFechaCreacion.setText("");
        labelFechaModificacion.setText("");
        EstadoValue.setText("");
        
        
        //Evt editar button
        editar.setOnAction(e -> {
            try{
                FXMLLoader loader = Load.Loader("/sicem/view/administrar/departamentos.fxml");
                Scene scene= new Scene(loader.load());
                DepartamentosController form = loader.getController();
                form.setDataView(Integer.parseInt(txtID.getText()), this);
                Load.Form(scene).showAndWait();
            }catch(IOException ex){}
        });
    }
    
    
    public void setInfo(int id){
        departamento data = new departamento().Detalle(id);
        if (data != null){
            editar.setVisible(true);
            
            txtID.setText(Integer.toString(data.getId()));
            txtNombre.setText(data.getNombre());
            txtNombreGrupo.setText(data.getNombreGrupo());
            labelFechaCreacion.setText(data.getFechaCreacion());
            labelFechaModificacion.setText(data.getFechaModificacion());
            EstadoValue.setText((data.getEstado()==1) ? "Habilitado" : "Deshabilitado");
        }
        else
            Popup.error(
                "Error con la solicitud", 
                "Ocurrió un error inesperado al tratar de obtener la información solicitada. Por favor, intente nuevamente.");
    }
    
    
    public void show(){
        try{
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sicem/view/administrar/detalleDepartamentos.fxml")));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("/sicem/images/favicon.png"));
            stage.showAndWait();
        }catch(IOException ex){ System.out.println("Error form: "+ex.getMessage()); System.exit(0); }
    }
    
}
