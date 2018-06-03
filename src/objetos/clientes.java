/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;
import DB.conexion;
import DB.param;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author espinoza
 */
public class clientes {
    private final StringProperty id;
    private final StringProperty nombre;
    private final StringProperty nombrecontacto;
    private final StringProperty titulocontacto;
    private final StringProperty domicilio;
    private final StringProperty ciudad; 
    private final StringProperty telefono;
    private final StringProperty email;
    private final IntegerProperty estado;
    private final StringProperty fechamodificacion;
    conexion c;
    
    public clientes() throws ClassNotFoundException, SQLException{ 
        this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", 0); }
    
    public clientes(String _id, String _nombre, String _nombreContacto, String _tituloContacto, String _domicilio, String _ciudad, String _telefono, String _email, int _estado) throws ClassNotFoundException, SQLException{
        this.id = new SimpleStringProperty(_id);
        this.nombre = new SimpleStringProperty(_nombre);
        this.nombrecontacto = new SimpleStringProperty(_nombreContacto);
        this.titulocontacto = new SimpleStringProperty(_tituloContacto);
        this.domicilio = new SimpleStringProperty(_domicilio);
        this.ciudad = new SimpleStringProperty(_ciudad);
        this.telefono = new SimpleStringProperty(_telefono);
        this.email = new SimpleStringProperty(_email);
        this.estado = new SimpleIntegerProperty(_estado);
        this.fechamodificacion = new SimpleStringProperty("N/A");
        this.c = new conexion();
    }
    
    /*  Setter's y Getter's  */
    
    public String getId(){ return this.id.get(); }
    public void setId(String v){ this.id.set(v); }
    public StringProperty getIdProperty(){ return this.id; }
    
    
    public String getNombre(){ return this.nombre.get(); }
    public void setNombre(String v){ this.nombre.set(v); }
    public StringProperty getNombreProperty(){ return this.nombre; }
    
    
    public String getNombreContacto(){ return this.nombrecontacto.get(); }
    public void setNombreContacto(String v){ this.nombrecontacto.set(v); }
    public StringProperty getNombreContactoProperty(){ return this.nombrecontacto; }
    
    
    public String getTituloContacto(){ return this.titulocontacto.get(); }
    public void setTituloContacto(String v){ this.titulocontacto.set(v); }
    public StringProperty getTituloContactoProperty(){ return this.titulocontacto; }
    
    
    public String getDomicilio(){ return this.domicilio.get(); }
    public void setDomicilio(String v){ this.domicilio.set(v); }
    public StringProperty getDomicilioProperty(){ return this.domicilio; }
    
    
    public String getCiudad(){ return this.ciudad.get(); }
    public void setCiudad(String v){ this.ciudad.set(v); }
    public StringProperty getCiudadProperty(){ return this.ciudad; }
    
    
    public String getTelefono(){ return this.telefono.get(); }
    public void setTelefono(String v){ this.telefono.set(v); }
    public StringProperty getTelefonoProperty(){ return this.telefono; }
    
    
    public String getEmail(){ return this.email.get(); }
    public void setEmail(String v){ this.email.set(v); }
    public StringProperty getEmailProperty(){ return this.email; }
    
    
    public int getEstado(){ return this.estado.get(); }
    public void setEstado(int v){ this.estado.set(v); }
    public IntegerProperty getEstadoProperty(){ return this.estado; }
    
    
    public String getFechaModificacion(){ return this.fechamodificacion.get(); }
    public void setFechaModificacion(String v){ this.fechamodificacion.set(v); }
    public StringProperty getFechaModificacionProperty(){ return this.fechamodificacion; }
    
    
    /*
    *       Métodos
    */
    public boolean Insertar() throws SQLException, IOException
    {
        param[] Parametros = new param[]{
            new param("str", "@ID", getId()),
            new param("str", "@NombreCliente", getNombre()),
            new param("str", "@NombreContacto", getNombreContacto()),
            new param("str", "@TituloContacto", getTituloContacto()),
            new param("str", "@Domicilio", getDomicilio()),
            new param("str", "@Ciudad", getCiudad()),
            new param("str", "@Telefono", getTelefono()),
            new param("str", "@Email", getEmail()),
            new param("int", "@Estado", getEstado())
        };

        return c.execute("Insertar_Cliente", Parametros);
    }
    
    
    public boolean Editar() throws SQLException, IOException
    {
        param[] Parametros = new param[]{
            new param("str", "@ID", getId()),
            new param("str", "@NombreCliente", getNombre()),
            new param("str", "@NombreContacto", getNombreContacto()),
            new param("str", "@TituloContacto", getTituloContacto()),
            new param("str", "@Domicilio", getDomicilio()),
            new param("str", "@Ciudad", getCiudad()),
            new param("str", "@Telefono", getTelefono()),
            new param("str", "@Email", getEmail()),
            new param("int", "@Estado", getEstado())
        };

        return c.execute("Actualizar_Cliente", Parametros);
    }
    
    
    public ObservableList<clientes> Buscar(String valor, int clave) throws SQLException, IOException, ClassNotFoundException{
        param[] Parametros = new param[]{
            new param("str", "@valor", valor),
            new param("int", "@clave", clave)
        };

        ResultSet rs = c.reader("Busqueda_Cliente", Parametros);
        ObservableList<clientes> resultado = FXCollections.observableArrayList();
        while(rs.next()){
            clientes t = new clientes();
            t.setId(rs.getString("ID"));
            t.setNombre(rs.getString("NombreCliente"));
            t.setNombreContacto(rs.getString("NombreContacto"));
            t.setTituloContacto(rs.getString("TituloContacto"));
            t.setDomicilio(rs.getString("Domicilio"));
            t.setCiudad(rs.getString("Ciudad"));
            t.setTelefono(rs.getString("Telefono"));
            t.setEmail(rs.getString("Email"));
            t.setEstado(rs.getInt("Estado"));
            t.setFechaModificacion(rs.getString("FechaModificacion"));
            
            resultado.add(t);
        }
        c.close();
        
        return resultado;
    }
    
    
    public ObservableList<clientes> Mostrar() throws SQLException, ClassNotFoundException{
        ObservableList<clientes> resultado = FXCollections.observableArrayList();
        ResultSet rs = c.reader("Mostrar_Clientes");
        while(rs.next()){
            clientes t = new clientes();
            t.setId(rs.getString("ID"));
            t.setNombre(rs.getString("NombreCliente"));
            t.setNombreContacto(rs.getString("NombreContacto"));
            t.setTituloContacto(rs.getString("TituloContacto"));
            t.setDomicilio(rs.getString("Domicilio"));
            t.setCiudad(rs.getString("Ciudad"));
            t.setTelefono(rs.getString("Telefono"));
            t.setEmail(rs.getString("Email"));
            t.setEstado(rs.getInt("Estado"));
            t.setFechaModificacion(rs.getString("FechaModificacion"));
            
            resultado.add(t);
        }
        c.close();
        
        return resultado;
    }
    
    
    public clientes Detalle(String idvalue) throws SQLException, IOException, ClassNotFoundException{
        param[] Parametros = new param[]{ new param("str", "@ID", idvalue) };
        ResultSet rs = c.reader("Detalle_Cliente", Parametros);
        clientes t = new clientes();
        while(rs.next()){
            t.setId(rs.getString("ID"));
            t.setNombre(rs.getString("NombreCliente"));
            t.setNombreContacto(rs.getString("NombreContacto"));
            t.setTituloContacto(rs.getString("TituloContacto"));
            t.setDomicilio(rs.getString("Domicilio"));
            t.setCiudad(rs.getString("Ciudad"));
            t.setTelefono(rs.getString("Telefono"));
            t.setEmail(rs.getString("Email"));
            t.setEstado(rs.getInt("Estado"));
            t.setFechaModificacion(rs.getString("FechaModificacion"));
        }
        c.close();
        
        return t;
    }
}
