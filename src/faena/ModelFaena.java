/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faena;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 309Control
 */
public class ModelFaena {
    
    private static final String usuario = "sql10232301";//Usuario x defecto Xampp
    private static final String contrasena = "g5SPvuGWye";
    private static final String enlace = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10232301";
    private Connection conexion;
    private Statement smt = null;
    
    public ModelFaena(){
        /* boolean cerrar = Conexion();
         if (!cerrar){
            System.exit(0);
         
         }*/
         try{
           this.conexion = DriverManager.getConnection(enlace, usuario, contrasena);
           System.out.println("Conexión Exitosa");
           this.smt = (Statement) this.conexion.createStatement();
           //return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            //return false;
        }
        
    }
    
    public final boolean Conexion(){
            try{
           this.conexion = DriverManager.getConnection(enlace, usuario, contrasena);
           //System.out.println("Conexión Exitosa");
           this.smt = (Statement) this.conexion.createStatement();
                System.out.println("Conexion exitosa");
           return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la conexión");
           return false;
        }
    }
    
    
    
      public ArrayList<ControllerFaena> getProductos(){
        ArrayList<ControllerFaena> productos= new ArrayList<>();
        try{
            String consulta = "SELECT *FROM faena";
            ResultSet resultado = this.smt.executeQuery(consulta);
            while(resultado.next()){
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio_compra");
                double cantidad = resultado.getDouble("cantidad");
                double stock = resultado.getDouble("stock");
                String proveedor = resultado.getString("proveedor");
                String comentario = resultado.getString("comentario");
                ControllerFaena producto = new ControllerFaena(id, nombre, precio, cantidad, 
                    stock, proveedor, comentario);
                productos.add(producto);
                //System.out.println("Carga hecha");
            }
        this.smt.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);;
            
        }
        return productos;
    }
      
         public boolean guardarProducto (int id, String nombre, double precio, double cantidad,
            double stock, String proveedor, String comentario){
        try{
            String consulta = "INSERT INTO faena (`id`, `nombre`, `precio_compra`, "
                    + "`cantidad`,`stock`,`proveedor`,`comentario`) VALUES ('"+id+"','"+nombre+"','"+precio+"','"+cantidad+"','"+stock+"','"+proveedor+"','"+comentario+"')";
            this.smt.executeUpdate(consulta);
                       this.smt.close();
            return true;
        }catch(SQLException e){
            System.err.println();
            return false;
        }
        
    }
         
          public boolean eliminarProducto(int id_producto){
        try{
            String consulta = "DELETE FROM faena WHERE id="+id_producto;
            this.smt.executeUpdate(consulta);
            this.smt.close();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
          
          public ControllerFaena getProducto (int id_producto){
        try{
            String consulta = "SELECT *FROM faena WHERE id="+id_producto;
            ResultSet resultado = this.smt.executeQuery(consulta);
            if(resultado.first()){
         
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio_compra");
                double cantidad = resultado.getDouble("cantidad");
                double stock = resultado.getDouble("stock");
                String proveedor = resultado.getString("proveedor");
                String comentario = resultado.getString("comentario");
                ControllerFaena producto = new ControllerFaena( id, nombre, precio, cantidad, 
                    stock, proveedor, comentario);
                this.smt.close();
                return producto;
                
            }
        }catch(SQLException e){
            return null;
            
        }
        return null;
    }
    
}    
