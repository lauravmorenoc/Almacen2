/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

/**
 *
 * @author Laura Moreno, Hernan Quiroga
 */
public class Pedido {
    private String cliente;
    private int cantidad;
    private Producto producto;

    public Pedido(String cliente, int cantidad, Producto producto) {
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.producto = producto;
    }
    
    public void generarFactura(){
        System.out.println("Factura del pedido");
        System.out.println("Cliente: " + cliente);
        System.out.println("Producto pedido: " + producto.getTipo());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Valor unitario: $" + producto.getPrecio());
        System.out.println("Valor total compra: $" + producto.getPrecio()*cantidad);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
