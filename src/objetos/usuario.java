/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;
import DB.conexion;
import DB.param;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author espinoza
 */
public class usuario {
    private final StringProperty nombre = new SimpleStringProperty("N/A");
    private final StringProperty apellido = new SimpleStringProperty("N/A");
    private final StringProperty username = new SimpleStringProperty("N/A");
    private final StringProperty password = new SimpleStringProperty("N/A");
    private final StringProperty fechamodificacion = new SimpleStringProperty("N/A");
    private final StringProperty fechacreacion = new SimpleStringProperty("N/A");
    private final IntegerProperty estado = new SimpleIntegerProperty(0);
    private Image foto = new Image("/sicem/images/avatar.png");;
    conexion c = new conexion();
    
    
    public usuario(){}

    
    public String getNombre(){ return this.nombre.get(); }
    public void setNombre(String v){ this.nombre.set(v); }
    public StringProperty getNombreProperty(){ return this.nombre; }
    
    public String getApellido(){ return this.apellido.get(); }
    public void setApellido(String v){ this.apellido.set(v); }
    public StringProperty getApellidoProperty(){ return this.apellido; }
    
    public String getUsername(){ return this.username.get(); }
    public void setUsername(String v){ this.username.set(v); }
    public StringProperty getUsernameProperty(){ return this.username; }
    
    public String getPassword(){ return this.password.get(); }
    public void setPassword(String v){ this.password.set(v); }
    public StringProperty getPasswordProperty(){ return this.password; }
    
    public String getFechaCreacion(){ return this.fechacreacion.get(); }
    public void setFechaCreacion(String v){ this.fechacreacion.set(v); }
    public StringProperty getFechaCreacionProperty(){ return this.fechacreacion; }
    
    public String getFechaModificacion(){ return this.fechamodificacion.get(); }
    public void setFechaModificacion(String v){ this.fechamodificacion.set(v); }
    public StringProperty getFechaModificacionProperty(){ return this.fechamodificacion; }
    
    public int getEstado(){ return this.estado.get(); }
    public void setEstado(int v){ this.estado.set(v); }
    public IntegerProperty getEstadoProperty(){ return this.estado; }
    
    public Image getFoto(){ return this.foto; }
    public void setFoto(Image v){ this.foto = v; }
    
    
    public boolean Insertar(){
        param[] Parametros = new param[]{
            new param("str", "_ID", getUsername()),
            new param("str", "_Contraseña", getPassword()),
            new param("str", "_Nombre", getNombre()),
            new param("str", "_Apellido", getApellido()),
            new param("img", "_Foto", getFoto()),
            new param("int", "_Estado", getEstado())
        };

        return c.execute("Insertar_Usuario", Parametros);
    }
    
    public boolean Editar(){
        param[] Parametros = new param[]{
            new param("str", "_ID", getUsername()),
            new param("str", "_Contraseña", getPassword()),
            new param("str", "_Nombre", getNombre()),
            new param("str", "_Apellido", getApellido()),
            new param("img", "_Foto", getFoto()),
            new param("int", "_Estado", getEstado())
        };
        
        return c.execute("Actualizar_Usuario", Parametros);
    }
    
    public ObservableList<SearchResult> Buscar(String valor, int clave){
        param[] Parametros = new param[]{
            new param("str", "_valor", valor),
            new param("int", "_clave", clave)
        };

        ResultSet rs = c.reader("Busqueda_Usuario", Parametros);
        ObservableList<SearchResult> resultado = FXCollections.observableArrayList();
        try{
            while(rs.next()){
                SearchResult t = new SearchResult();
                t.setId(rs.getString("ID"));
                t.setName(rs.getString("Nombre")+" "+rs.getString("Apellido"));
                t.setModificationDate(rs.getString("FechaModificacion"));

                resultado.add(t);
            }
        }catch(SQLException e){}finally{ try{ c.close(); }catch(SQLException e){} }
        
        return resultado;
    }
    
    public ObservableList<SearchResult> Mostrar(){
        ObservableList<SearchResult> resultado = FXCollections.observableArrayList();
        ResultSet rs = c.reader("Mostrar_Usuarios");
        try{
            while(rs.next()){
                SearchResult t = new SearchResult();
                t.setId(rs.getString("ID"));
                t.setName(rs.getString("Nombre")+" "+rs.getString("Apellido"));
                t.setModificationDate(rs.getString("FechaModificacion"));

                resultado.add(t);
            }
        }catch(SQLException e){}finally{ try{ c.close(); }catch(SQLException e){} }
        
        return resultado;
    }
    
    public usuario Detalle(String idvalue){
        param[] Parametros = new param[]{ new param("str", "_ID", idvalue) };
        ResultSet rs = c.reader("Detalle_Usuario", Parametros);
        usuario t = new usuario();
        try{
            while(rs.next()){
                t.setUsername(rs.getString("ID"));
                t.setPassword(rs.getString("Contraseña"));
                t.setNombre(rs.getString("Nombre"));
                t.setApellido(rs.getString("Apellido"));
                t.setEstado(rs.getInt("Estado"));
                t.setFechaCreacion(rs.getString("FechaCreacion"));
                t.setFechaModificacion(rs.getString("FechaModificacion"));
                t.setFoto(c.getImage(rs.getBlob("Foto")));
            }
        }catch(SQLException e){}finally{ try{ c.close(); }catch(SQLException e){} }
        
        return t;
    }
    
    public boolean Verifica(String user, String pass){
        param[] Parametros = new param[]{
            new param("str", "_Usuario", user),
            new param("str", "_Contraseña", pass)
        };
        
        ResultSet rs = c.reader("Validar_Usuario", Parametros);
        int cont = 0;
        try{ while(rs.next()){ cont++; }
        }catch(SQLException e){}finally{ try{ c.close(); }catch(SQLException e){} }

        return (cont == 0) ? false : true;
    }
    
    public Image obtenerFoto(String id){
        Image value = new Image("/sicem/images/avatar.png");
        try{
            ResultSet rs = c.readerSimple("select Foto from Usuario where ID = '" + id + "'");
            while(rs.next()){ value = c.getImage(rs.getBlob("Foto")); }
        }catch(SQLException e){}
        
        return value;
    }
}
