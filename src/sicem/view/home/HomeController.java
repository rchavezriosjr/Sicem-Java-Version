/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicem.view.home;

import DB.sqlite;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objetos.permisos;
import objetos.usuario;
import sicem.utilities.Dragged;
import sicem.utilities.Load;
import sicem.utilities.Popup;
import sicem.utilities.dialogos;
import static sicem.utilities.dialogos.tipo.confir;
import sicem.utilities.rounded;
import sicem.utilities.validator;
import sicem.view.LoginController;
import sicem.view.administrar.AdministrarController;
import sicem.view.administrar.UsuarioFormController;
import sicem.view.directorio.DirectorioController;
import sicem.view.operaciones.OperacionesController;
import sicem.view.productos.ProductosController;
import sicem.view.reportes.ReportesController;

/**
 * FXML Controller class
 *
 * @author espinoza
 */
public class HomeController implements Initializable {

    boolean activenav = false;
    @FXML private BorderPane content;
    @FXML private Pane header;
    @FXML private Button menu;
    @FXML private ImageView avatar;
    @FXML private Label lblUsername;
    @FXML private Label lblID;
    @FXML private Pane navbar;
    @FXML private Button inicioTab;
    @FXML private Button directorioTab;
    @FXML private Button operacionesTab;
    @FXML private Button productosTab;
    @FXML private Button reportesTab;
    @FXML private Button administrarTab;
    @FXML private VBox vbox;
    
    Pane inicio;
    Pane directorio;
    Pane operaciones;
    Pane productos;
    Pane reportes;
    Pane administrar;
    
    InicioController inicioController;
    DirectorioController directorioController;
    OperacionesController operacionesController;
    ProductosController productosController;
    ReportesController reportesController;
    AdministrarController administrarController;
    
    public HomeController(){}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicia();
    }
    
    
    public void inicia(){
        Dragged.set(header);
        rounded.roundImageView(avatar, new Image("sicem/images/perfil.jpg"));
        menu.setTooltip(new Tooltip("Menú"));
        inicioTab.setTooltip(new Tooltip("Inicio"));
        directorioTab.setTooltip(new Tooltip("Directorio"));
        operacionesTab.setTooltip(new Tooltip("Operaciones"));
        productosTab.setTooltip(new Tooltip("Productos"));
        reportesTab.setTooltip(new Tooltip("Reportes"));
        administrarTab.setTooltip(new Tooltip("Administrar"));
        contextmenu();
        components();
        setInfo();
        
        
        //Evt inicioTab click
        inicioTab.setOnAction(e -> {
            clearIndicador();
            validator.setActive(inicioTab);
            content.setCenter(inicio);
            inicioController.cargar();
        });
        
        
        //Evt directorioTab click
        directorioTab.setOnAction(e -> {
            clearIndicador();
            validator.setActive(directorioTab);
            content.setCenter(directorio);
            directorioController.cargar(false);
        });
        
        
        //Evt operacionesTab click
        operacionesTab.setOnAction(e -> {
            clearIndicador();
            validator.setActive(operacionesTab);
            content.setCenter(operaciones);
            operacionesController.cargar(false);
        });
        
        
        //Evt productosTab click
        productosTab.setOnAction(e -> {
            clearIndicador();
            validator.setActive(productosTab);
            content.setCenter(productos);
            productosController.cargar(false);
        });
        
        
        //Evt reportesTab click
        reportesTab.setOnAction(e -> {
            clearIndicador();
            validator.setActive(reportesTab);
            content.setCenter(reportes);
            reportesController.cargar();
        });
        
        
        //Evt administrarTab click
        administrarTab.setOnAction(e -> {
            clearIndicador();
            validator.setActive(administrarTab);
            content.setCenter(administrar);
            administrarController.cargar(false);
        });
    }
    
    
    public void setInfo(){
        usuario data = new usuario().Detalle(sqlite.getUser());

        if(data != null){
            lblID.setText(data.getUsername());
            lblUsername.setText(data.getNombre()+" "+data.getApellido());
            rounded.roundImageView(avatar, data.getFoto());
            verificaPermisos(data.getUsername());
        }
        else
            Popup.error(
                "Error con la solicitud", 
                "Ocurrió un error al tratar de obtener la información solicitada. \nPor favor, intente nuevamente.");
    }
    
    
    private void components(){
        try{
            FXMLLoader loader = Load.Loader("/sicem/view/home/inicio.fxml");
            inicio = loader.load();
            inicioController = loader.getController();
            
            loader = Load.Loader("/sicem/view/directorio/directorio.fxml");
            directorio = loader.load();
            directorioController = loader.getController();
            
            loader = Load.Loader("/sicem/view/operaciones/operaciones.fxml");
            operaciones = loader.load();
            operacionesController = loader.getController();
            
            loader = Load.Loader("/sicem/view/productos/productos.fxml");
            productos = loader.load();
            productosController = loader.getController();
            
            loader = Load.Loader("/sicem/view/reportes/reportes.fxml");
            reportes = loader.load();
            reportesController = loader.getController();
            
            loader = Load.Loader("/sicem/view/administrar/administrar.fxml");
            administrar = loader.load();
            administrarController = loader.getController();
            
            
            validator.setActive(inicioTab);
            content.setCenter(inicio);
        }catch(IOException ex){ System.out.println("Error carga components"); }
    }
    
    
    private void contextmenu(){
        ContextMenu contextmenu = new ContextMenu();
        MenuItem salir = new MenuItem("Salir");
        MenuItem cerrarsesion = new MenuItem("Cerrar Sesión");
        MenuItem configurarperfil = new MenuItem("Configurar perfil");
        contextmenu.getItems().addAll(configurarperfil, cerrarsesion, salir);
        
        menu.setContextMenu(contextmenu);
        menu.setOnAction(e -> { 
            double x = menu.getScene().getWindow().getX() + 10;
            double y = menu.getScene().getWindow().getY() + 40;
            contextmenu.show(menu, x, y); 
        });
        
        
        salir.setOnAction(e -> { 
            if(new dialogos("Salir", "¿Salir del sistema?", confir).show())
                System.exit(0); 
        });
        
        cerrarsesion.setOnAction(e -> { 
            if(new dialogos("Salir", "¿Cerrar sesión?", confir).show()){
                new LoginController().show();
                close();
            }
        });
        
        configurarperfil.setOnAction(e -> {
            try{
                FXMLLoader loader = Load.Loader("/sicem/view/administrar/usuarioForm.fxml");
                Scene scene= new Scene(loader.load());
                UsuarioFormController form = loader.getController();
                form.setDataView(lblID.getText(), this);
                form.ocultaCampos();
                Load.Form(scene).showAndWait();
            }catch(IOException ex){ System.out.println("Error carga components"); }
        });
    }
    
    
    private void clearIndicador(){
        validator.removeActive(inicioTab);
        validator.removeActive(directorioTab);
        validator.removeActive(operacionesTab);
        validator.removeActive(productosTab);
        validator.removeActive(reportesTab);
        validator.removeActive(administrarTab);
    }
    
    
    private void verificaPermisos(String id){
        vbox.getChildren().clear();
        vbox.getChildren().addAll(inicioTab, directorioTab, operacionesTab, productosTab, reportesTab, administrarTab);
        permisos p = new permisos().get(id);

        if((p.getDconsultar()==0 && p.getDeditar()==0) && p.getDcrear()==0)
            vbox.getChildren().remove(directorioTab);

        if((p.getOconsultar()==0 && p.getOeditar()==0) && p.getOcrear()==0)
            vbox.getChildren().remove(operacionesTab);

        if((p.getPconsultar()==0 && p.getPeditar()==0) && p.getPcrear()==0)
            vbox.getChildren().remove(productosTab);

        if(p.getReportes()==0)
            vbox.getChildren().remove(reportesTab);

        if(p.getAccesototal()==0)
            vbox.getChildren().remove(administrarTab);
        
        directorioController.verificaPermisos();
        productosController.verificaPermisos();
        operacionesController.verificaPermisos();
    }
    
    
    public void show() throws IOException{
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sicem/view/home/home.fxml")));
        scene.setFill(Color.TRANSPARENT);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("/sicem/images/favicon.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    
    public void close(){
        Stage s = (Stage) menu.getScene().getWindow();
        s.close();
    }
    
}
