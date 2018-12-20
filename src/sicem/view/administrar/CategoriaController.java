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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objetos.categoria;
import sicem.utilities.Dragged;
import sicem.utilities.Popup;
/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class CategoriaController implements Initializable {

	@FXML private TextField txtNombre;
    @FXML private TextField txtId;
    @FXML private TextArea txtDescripcion;
    @FXML private Button cancelar;
    @FXML private Button guardar;
    @FXML private CheckBox estadoValor;
    @FXML private Pane title;
    @FXML private Label titleLbl;
    
    String accionformulario="";
    DetalleCategoriaController detalle;
    conexion c = new conexion();
    
    
    public CategoriaController(){ accionformulario = "crear"; }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicia();
    }    
    
    
    private void inicia(){
        guardar.setText((accionformulario.equals("crear")) ? "Guardar" : "Actualizar");
        if(accionformulario.equals("crear")) txtId.setText(Integer.toString((int) c.readerScalar("select count(*) + 1 from Categoria", Integer.class)));
        Dragged.set(title, titleLbl);
        
        
        //Evt Cancelar button
        cancelar.setOnAction(e -> { close(); });
        
        
        //Evt guardar button
        guardar.setOnAction(e -> {
            if(!txtNombre.getText().isEmpty()){
                boolean transac = false;
                categoria ca = new categoria();
                ca.setId(Integer.parseInt(txtId.getText()));
                ca.setNombre(txtNombre.getText());
                ca.setDescripcion(txtDescripcion.getText());
                ca.setEstado((estadoValor.isSelected()) ? 1 : 0);

                if (accionformulario.equals("crear")) transac = ca.Insertar();
                else transac = ca.Editar();

                if(transac){ 
                    if(accionformulario.equals("editar")) detalle.setInfo(Integer.parseInt(txtId.getText())); 
                    close(); 
                }else Popup.error(
                        "Error innesperado", 
                        "Ocurrió un error al tratar de realizar la acción, por favor, intente nuevamente.");
            }else
                Popup.error(
                    "Error innesperado", 
                    "Se requiere un nombre en la categoría para poder registrarla.");
        });
    }
    
    
    public void setDataView(int id, DetalleCategoriaController dc){
        accionformulario = "editar";
        detalle = dc;
        categoria data = new categoria().Detalle(id);

        if(data != null){
            txtId.setText(Integer.toString(data.getId()));
            txtNombre.setText(data.getNombre());
            txtDescripcion.setText(data.getDescripcion());
            estadoValor.setSelected((data.getEstado() == 1) ? true : false);
        }else{
            Popup.error(
                    "Error con la solicitud", 
                    "Ocurrió un error inesperado al tratar de obtener la información solicitada. Por favor, intente nuevamente.");
            close();
        }

    }
    
    
    public void show(){
        try{
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sicem/view/administrar/categoria.fxml")));
            scene.setFill(Color.TRANSPARENT);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("/sicem/images/favicon.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.showAndWait();
        }catch(IOException ex){ System.out.println("Error form: "+ex.getMessage()); System.exit(0); }
    }
    
    public void close(){
        Stage s = (Stage) cancelar.getScene().getWindow();
        s.close(); 
    }
    
}
