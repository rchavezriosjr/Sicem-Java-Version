/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author espinoza
 */
public class conexion {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/alumno";
    private Connection c;
    private Properties p = new Properties();
    
    public conexion() throws ClassNotFoundException, SQLException{
        Class.forName(dbClassName);
        this.p.put("user","root");
        this.p.put("password","harold97");
        this.p.put("useSSL", "false");
        this.p.put("autoReconnect", "true");
    }
    
    public void open() throws SQLException{ c = DriverManager.getConnection(CONNECTION,p); }
    public void close() throws SQLException{ c.close(); }
    
    /*
    *           Escritura de datos
    */
    
    public boolean execute(String cmd) throws SQLException{
        open();
        
        CallableStatement cs = c.prepareCall("call "+cmd);
        boolean estado = (cs.executeUpdate() != 0) ? true : false;
        close();
        
        return estado;
    }
    
    public boolean execute(String cmd, param[] p) throws SQLException, IOException{
        open();
        
        CallableStatement cs = c.prepareCall(procedure(cmd, p.length));
        setParams(cs, p);
        boolean estado = (cs.executeUpdate() != 0) ? true : false;
        close();
        
        return estado;
    }
    
    
    /*
    *           Lectura de datos
    */
    
    public ResultSet reader(String cmd) throws SQLException{ // procedimiento sin parámetros
        open();
        
        CallableStatement cs = c.prepareCall("call "+cmd);
        
        return cs.executeQuery();
    }
    
    public ResultSet reader(String cmd, param[] p) throws SQLException, IOException{ // Procedimiento con parámetros
        open();
        CallableStatement cs = c.prepareCall(procedure(cmd, p.length));
        setParams(cs, p);
        
        return cs.executeQuery();
    }
    
    public ResultSet readerSimple(String cmd) throws SQLException{
        open();
        Statement s = c.createStatement();
        return s.executeQuery(cmd);
    }
    
    public Object readerScalar(String cmd) throws SQLException{
        open();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(cmd);
        Object o = rs.getObject(1);
        close();
        
        return 1;
    }
    
    
    /*
    *       Métodos complementarios
    */
            public String procedure(String command, int cont){
                String cmd = "call "+command+"(";
                for(int i=0; i<cont-1; i++) cmd+="?,";

                return cmd+="?)";
            }

            public void setParams(CallableStatement cs, param[] param) throws SQLException, IOException{
                for(param p : param){
                    switch(p.type){
                        case "str":
                            cs.setString(p.name, (String)p.value);
                            break;

                        case "int":
                            cs.setInt(p.name, (int)p.value);
                            break;
                            
                        case "img":
                            cs.setBlob(p.name, getBlob((Image)p.value));
                            break;
                    }
                }
            }
            
            public Blob getBlob(Image img) throws IOException, SQLException{
                ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", byteOutput);
                Blob blob =  new SerialBlob(byteOutput.toByteArray());
                
                return blob;
            }
}
/*
while(result.next()){
    byte byteImage[] = null;
    Blob blob = result.getBlob("imagen");
    byteImage = blob.getBytes(1, (int) blob.length());

    Image img = new Image(new ByteArrayInputStream(byteImage));
    imageView = new ImageView(img);
}
*/
