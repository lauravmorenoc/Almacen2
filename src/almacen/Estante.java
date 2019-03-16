/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;
import becker.robots.*;
/**
 *
 * @author Laura
 */
public class Estante {
    private Thing thing;
    private Caja[] cajas;

    public Estante(Thing thing, Caja[] cajas) {
        this.thing = thing;
        this.cajas = cajas;
    }
    
    public int getEspaciosDisponibles(){
        return cajas[0].getEspaciosVacios()+cajas[1].getEspaciosVacios()+cajas[2].getEspaciosVacios();
    }
    
    public int llenarCajas(int numeroProductos, int numeroLista, Producto producto[]){ //Retorna el numero de productos que no se añadieron
        int Nproducto=numeroLista; //dice que numero de producto es el siguiente a almacenar

        if(cajas[0].getEspaciosVacios()>0){
            for(int i=0; i<7; i++){
                if((cajas[0].getProductos()[i]==null)&&(numeroProductos>0)){
                    cajas[0].getProductos()[i]=producto[Nproducto];
                    numeroProductos--;
                    Nproducto++;
                }
            }
        } if(numeroProductos>0){
            if(cajas[1].getEspaciosVacios()>0){
            for(int i=0; i<7; i++){
                if((cajas[1].getProductos()[i]==null)&&(numeroProductos>0)){
                    cajas[1].getProductos()[i]=producto[Nproducto];
                    numeroProductos--;
                    Nproducto++;
                }
            }
        }
        } if(numeroProductos>0){
            if(cajas[2].getEspaciosVacios()>0){
            for(int i=0; i<7; i++){
                if((cajas[2].getProductos()[i]==null)&&(numeroProductos>0)){
                    cajas[2].getProductos()[i]=producto[Nproducto];
                    numeroProductos--;
                    Nproducto++;
                }
            }
        }
        }
        return numeroProductos;
    }
    
    public int extraerProductos(int numeroProductos, String nombreProducto){ //Retorna el numero de productos que no fueron extraídos
        for(int i=0; i<3; i++){
        for(int j=0; numeroProductos>0 && j<7; j++){
            if(cajas[i].getProductos()[j]!=null&&cajas[i].getProductos()[j].getTipo().equals(nombreProducto)){
                this.cajas[i].getProductos()[j]=null;
                numeroProductos--;
            }
        }
        }
        System.out.println(numeroProductos);
        return numeroProductos;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public Caja[] getCajas() {
        return cajas;
    }

    public void setCajas(Caja[] cajas) {
        this.cajas = cajas;
    }
}
