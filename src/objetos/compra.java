/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import DB.conexion;
import DB.param;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
public class compra {
    private final StringProperty id = new SimpleStringProperty("N/A");
    private final IntegerProperty proveedorId = new SimpleIntegerProperty(0);
    private Date fechaCompra = new Date();
    private final IntegerProperty tipoPago = new SimpleIntegerProperty(0);
    private BigDecimal total = new BigDecimal(0);
    private final StringProperty fechaModificacion = new SimpleStringProperty("N/A");
    conexion c = new conexion();
    
    public compra(){}

    
    public String getId() { return id.get(); }
    public void setId(String value) { id.set(value); }
    public StringProperty getIdProperty() { return id; }

    public int getProveedorId() { return proveedorId.get(); }
    public void setProveedorId(int value) { proveedorId.set(value); }
    public IntegerProperty getProveedorIdProperty() { return proveedorId; }
    
    public Date getFechaCompra(){ return fechaCompra; }
    public void setFechaCompra(Date value){ this.fechaCompra = value; }

    public int getTipoPago() { return tipoPago.get(); }
    public void setTipoPago(int value) { tipoPago.set(value); }
    public IntegerProperty getTipoPagoProperty() { return tipoPago; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal value) { this.total=value; }

    public String getFechaModificacion() { return fechaModificacion.get(); }
    public void setFechaModificacion(String value) { fechaModificacion.set(value); }
    public StringProperty getFechaModificacionProperty() { return fechaModificacion; }
    
    
    public boolean Insertar(){
        param[] Parametros = new param[]{
            new param("str", "_ID", getId()),
            new param("int", "_ProveedorID", getProveedorId()),
            new param("date", "_FechaCompra", getFechaCompra()),
            new param("int", "_TipoPago", getTipoPago()),
            new param("decimal", "_Monto", getTotal())
        };

        return c.execute("Insertar_Compra", Parametros);
    }

    public boolean Editar(){
        param[] Parametros = new param[]{
            new param("str", "_ID", getId()),
            new param("int", "_ProveedorID", getProveedorId()),
            new param("date", "_FechaCompra", getFechaCompra()),
            new param("int", "_TipoPago", getTipoPago()),
            new param("decimal", "_Monto", getTotal())
        };

        return c.execute("Actualizar_Compra", Parametros);
    }


    public ObservableList<SearchResult> Buscar(String valor, int clave){
        param[] Parametros = new param[]{
            new param("str", "_valor", valor),
            new param("int", "_clave", clave)
        };
        ObservableList<SearchResult> resultado = FXCollections.observableArrayList();
        try{
            ResultSet rs = c.reader("Busqueda_Compras", Parametros);
            while(rs.next()){
                SearchResult t = new SearchResult();
                t.setId(rs.getString("ID"));
                t.setName(rs.getDate("FechaCompra").toString());
                t.setModificationDate(rs.getString("FechaModificacion"));
                resultado.add(t);
            }
        }catch(SQLException ex){
        }finally{ try{ c.close(); }catch(Exception ex){} }
        
        return resultado;
    }


    public ObservableList<SearchResult> Mostrar(){
        ObservableList<SearchResult> resultado = FXCollections.observableArrayList();
        try{
            ResultSet rs = c.reader("Mostrar_Compras");
            while(rs.next()){
                SearchResult t = new SearchResult();
                t.setId(rs.getString("ID"));
                t.setName(rs.getDate("FechaCompra").toString());
                t.setModificationDate(rs.getString("FechaModificacion"));
                resultado.add(t);
            }
        }catch(SQLException ex){
        }finally{ try{ c.close(); }catch(Exception ex){} }
        
        return resultado;
    }

    public compra Detalle(String idvalue){
        param[] Parametros = new param[]{ new param("str", "_ID", idvalue) };
        compra t = new compra();
        try{
            ResultSet rs = c.reader("CompraDetallada", Parametros);
            while(rs.next()){
                t.setId(rs.getString("ID"));
                t.setProveedorId(rs.getInt("ProveedorID"));
                t.setFechaCompra(rs.getDate("FechaCompra"));
                t.setTipoPago(rs.getInt("TipoPago"));
                t.setTotal(rs.getBigDecimal("Monto"));
                t.setFechaModificacion(rs.getString("FechaModificacion"));
            }
        }catch(SQLException ex){
        }finally{ try{ c.close(); }catch(Exception ex){} }
        
        return t;
    }
}
