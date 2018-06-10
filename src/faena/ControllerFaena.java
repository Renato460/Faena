/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faena;

import java.util.ArrayList;

/**
 *
 * @author 309Control
 */
public class ControllerFaena {
    
    private int id;
    private String nombre, proveedor, comentario;
    private double precio, cantidad, stock;
    
    public ControllerFaena(){}
    
    public ControllerFaena(int id,String nombre, double precio, double cantidad, double stock, String proveedor,String comentario){
            
            
            this.id=id;
            this.nombre=nombre;
            this.precio=precio;
            this.cantidad=cantidad;
            this.stock=stock;
            this.proveedor=proveedor;
            this.comentario=comentario;
        }
    
    public ArrayList<ControllerFaena> ListaProducto (){
        ModelFaena productos = new ModelFaena();
        ArrayList<ControllerFaena> listaProductos = productos.getProductos();
        return listaProductos;
    }
    
    public boolean Insertar_Producto(String id, String nombre, String precio,
            String cantidad, String stock, String proveedor, String comentario){
        try{
            int id_numero = Integer.parseInt(id);
            double precio_compra = Double.parseDouble(precio);
            double cantidad_numero = Double.parseDouble(cantidad);
            double stock_numero = Double.parseDouble(stock);
            //Enviamos los datos al modelo y recibimos verdadero o falso
    //        boolean resultado;
            ModelFaena nuevo_producto = new ModelFaena();
            nuevo_producto.guardarProducto(id_numero, nombre, precio_compra, cantidad_numero, stock_numero,
                    proveedor, comentario);
            return true;
        }catch(Exception e){
            return false;
            
        }
    }
    
    public boolean Eliminar_Producto(String Id_producto){
        try{
            int id_producto = Integer.parseInt(Id_producto);
            //enviar id almmodelo para eliminar de bd
            ModelFaena modelo= new ModelFaena();
           
            return modelo.eliminarProducto(id_producto);
        }catch(Exception e){
            return false;
        }
    }
    
    public ControllerFaena Mostrar_Producto(int Id_producto){
        ControllerFaena producto = new ControllerFaena();
        //Pedir al modelo ArrayList de personas para devolverlo a la vista
        ModelFaena modelo_productos = new ModelFaena();
        //Recibimos los datos y luego los enviamos al a vista
        producto = modelo_productos.getProducto(Id_producto);
        return producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
