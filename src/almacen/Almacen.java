/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;
import becker.robots.*;
//import java.lang.Object;
import java.awt.Color;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Gloria
 */
public class Almacen {

    public static City Bogota;
    private Robot drive[];
    private Estante estantes[];
    private Empleado empleadosAlmacen[];
    private Empleado empleadosZonaEnvio[];
    public Almacen(Robot drive[], Estante estantes[], Empleado empleadosAlmacen[], Empleado empleadosZonaEnvio[]){
        this.drive=drive;
        this.estantes=estantes;
        this.empleadosAlmacen=empleadosAlmacen;
        this.empleadosZonaEnvio=empleadosZonaEnvio;
    }
    
    public boolean almacenarProductos(Producto productos[], int empleado){
        int productosNoAlmacenados=productos.length;
        for(int i=0; (i<7)&&(productosNoAlmacenados>0); i++){
        if (estantes[i].getEspaciosDisponibles()>0){
            moverVariasVeces(i, 13-drive[i].getAvenue());
            drive[i].pickThing();
            turnTwice(i);
            moverVariasVeces(i, drive[i].getAvenue()-empleadosAlmacen[empleado].getAvenue());
            turnRight(i);
            int movimientosVerticales=drive[i].getStreet()-empleadosAlmacen[empleado].getStreet()-1;
            moverVariasVeces(i, movimientosVerticales);
            productosNoAlmacenados=estantes[i].llenarCajas(productosNoAlmacenados, productos.length-productosNoAlmacenados, productos);
            turnRight(i);
            drive[i].move();
            turnRight(i);
            moverVariasVeces(i, movimientosVerticales);
            drive[i].turnLeft();
            moverVariasVeces(i, 13-drive[i].getAvenue());
            drive[i].putThing();
            turnTwice(i);
            while(drive[i].frontIsClear()){
                drive[i].move();
            }
            turnTwice(i);
        }
    }
        
        if (productosNoAlmacenados>0){
         for(int i=7; (i<10)&&(productosNoAlmacenados>0); i++){
          if (estantes[i].getEspaciosDisponibles()>0){
            drive[i-7].move();
            turnRight(i-7);
            int movimientosInicialesEnY=(i+4)-drive[i-7].getStreet();
            moverVariasVeces(i-7, movimientosInicialesEnY);
            drive[i-7].turnLeft();
            moverVariasVeces(i-7, 13-drive[i-7].getAvenue());
            drive[i-7].pickThing();
            turnTwice(i-7);
            moverVariasVeces(i-7, drive[i-7].getAvenue()-empleadosAlmacen[empleado].getAvenue());
            turnRight(i-7);
            int movimientosVerticales=drive[i-7].getStreet()-empleadosAlmacen[empleado].getStreet()-1;
            moverVariasVeces(i-7, movimientosVerticales);
            productosNoAlmacenados=estantes[i].llenarCajas(productosNoAlmacenados, productos.length-productosNoAlmacenados, productos);
            turnRight(i-7);
            drive[i-7].move();
            turnRight(i-7);
            moverVariasVeces(i-7, movimientosVerticales);
            drive[i-7].turnLeft();
            moverVariasVeces(i-7, 13-drive[i-7].getAvenue());
            drive[i-7].putThing();
            turnTwice(i-7);
            drive[i-7].move();
            turnRight(i-7);
            moverVariasVeces(i-7, movimientosInicialesEnY);
            drive[i-7].turnLeft();
            while(drive[i-7].frontIsClear()){
                drive[i-7].move();
            }
            turnTwice(i-7);
        }
    }
        }
        if(productosNoAlmacenados>0){
        return false;
    } else return true;
    }
    
        public boolean solicitarPedido(String nombreProducto, int cantidad, int empleado, String cliente){
            //función para buscar el producto pedido
            Producto productoSolicitado=null;
            int posicionArray=0, productosExistentes=0;
            int estantes_array[]= new int[210]; //210 es la cantidad máxima de productos
            for(int a=0; a<210; a++){
                estantes_array[a]=-1; //permite saber qué espacios del arreglo no se llenaron
            }
            
            for(int i=0; i<10; i++){ //recorre los 10 estantes
                for(int j=0; j<3; j++){ //recorre las 3 cajas
                    for(int k=0; k<7; k++){ //recorre los 7 productos
                        if(estantes[i].getCajas()[j].getProductos()[k]!=null){
                       // System.out.println(estantes[i].getCajas()[j].getProductos()[k].getTipo());
                         //   System.out.println("Estante No: " + i);
                        if(estantes[i].getCajas()[j].getProductos()[k].getTipo().equals(nombreProducto)){
                            productoSolicitado=estantes[i].getCajas()[j].getProductos()[k];
                            
                            if((posicionArray==0)|| i!=estantes_array[posicionArray-1]){
                            estantes_array[posicionArray]=i; //guarda el número de estante en una posicion del array para saber en qué
                                                             // estante se encuentra el producto;  
                            //System.out.println(i);
                            
                           posicionArray++; //Avanza en el array 
                            }
                           //posicionArray++;
                           productosExistentes++; //guarda la cantidad de productos disponibles en el almacen
                        }
                      }
                    }
                }
            }
            
            System.out.println("Hasta aqui llega la funcion");
            //System.out.println(productosExistentes);
            
            if(productosExistentes<cantidad){//Significa que no se encontraron productos o se encontraron muy pocos
                return false;
            } else
            
            System.out.println("Antes de mover a karel");
          //  System.out.println("Imprimiendo array de estantes: ");
            
            /*for(int w=0; estantes_array[w]!=-1; w++){
                System.out.println(estantes_array[w]);
            }*/
            int productosPorSacar=cantidad;
            for(int c=0; ((estantes_array[c]!=-1) && (productosPorSacar>0 && c<10)); c++){//c es el estante del cual se están extrayendo productos
                
                
            int randomRobot= (int)(Math.random()*6);//Escoge un robot random para llevar el pedido
            drive[randomRobot].move();
            boolean indicadorPosicion=estantes_array[c]+4<drive[randomRobot].getStreet();
            if(indicadorPosicion){
                drive[randomRobot].turnLeft();
            } else {
            turnRight(randomRobot); //(c+4) es la "street" del estante c
            }
            int movimientosInicialesEnY=Math.abs(((estantes_array[c])+4)-drive[randomRobot].getStreet()); //Street horizontales, avenue verticales
            moverVariasVeces(randomRobot, movimientosInicialesEnY);
            if(indicadorPosicion){
                turnRight(randomRobot);
            } else{
            drive[randomRobot].turnLeft();
            }
            moverVariasVeces(randomRobot, 13-drive[randomRobot].getAvenue());
            drive[randomRobot].pickThing();
            turnTwice(randomRobot);
            moverVariasVeces(randomRobot, 6);
            drive[randomRobot].turnLeft();
            moverVariasVeces(randomRobot, empleadosZonaEnvio[empleado].getStreet()-drive[randomRobot].getStreet()-1);
            if(empleadosZonaEnvio[empleado].getAvenue()>drive[randomRobot].getAvenue()){
                drive[randomRobot].turnLeft();
            } else {
                turnRight(randomRobot);
            }
            int movimientosHorizontales=empleadosZonaEnvio[empleado].getAvenue()-drive[randomRobot].getAvenue();
            moverVariasVeces(randomRobot, Math.abs(movimientosHorizontales)); //debe ser valor absoluto (positivo)
            
            //System.out.println(productosPorSacar);
            productosPorSacar=estantes[estantes_array[c]].extraerProductos(productosPorSacar, nombreProducto);
                             //Le asigna a productosPorSacar los productos que no se sacaron del estante
                             //System.out.println(productosPorSacar);
            
            turnTwice(randomRobot);
            moverVariasVeces(randomRobot, Math.abs(movimientosHorizontales));
            if(empleadosZonaEnvio[empleado].getAvenue()>drive[randomRobot].getAvenue()){
                turnRight(randomRobot);
            } else {
                drive[randomRobot].turnLeft();
            }
            
            moverVariasVeces(randomRobot, drive[randomRobot].getStreet()-(estantes_array[c]+4));
            turnRight(randomRobot);
            
            while(drive[randomRobot].frontIsClear()){
                drive[randomRobot].move();
            }
            drive[randomRobot].putThing();
            turnTwice(randomRobot);
            drive[randomRobot].move();
            if(indicadorPosicion){
                drive[randomRobot].turnLeft();
            } else {
            turnRight(randomRobot);
            }
            moverVariasVeces(randomRobot, movimientosInicialesEnY);
            if(indicadorPosicion){
                turnRight(randomRobot);
            } else{
            drive[randomRobot].turnLeft();
            }
            while(drive[randomRobot].frontIsClear()){
                drive[randomRobot].move();
            }
            turnTwice(randomRobot);
            
            //TESTEO
               // System.out.println(productosPorSacar);
            }
            Pedido pedido=new Pedido(cliente, cantidad, productoSolicitado);
            pedido.generarFactura();            
            return true;
        }
        
        
	public static void main (String[] args){
            Bogota = new City("Field.txt");
	    Bogota.showThingCounts(true);

           Robot drive[] = new Robot[7];
           drive[0] = new Robot(Bogota,4, 1, Direction.EAST);
           drive[1] = new Robot(Bogota,5, 1, Direction.EAST);
           drive[2] = new Robot(Bogota,6, 1, Direction.EAST);
           drive[3] = new Robot(Bogota,7, 1, Direction.EAST);
           drive[4] = new Robot(Bogota,8, 1, Direction.EAST);
           drive[5] = new Robot(Bogota,9, 1, Direction.EAST);
           drive[6] = new Robot(Bogota,10, 1, Direction.EAST);
           
           Producto productos[][]=new Producto[30][7];
           
           Caja cajas[][]=new Caja[10][3];
           for(int i=0, k=0; i<10; i++){
               for(int j=0; j<3; j++, k++){
                   cajas[i][j]=new Caja(productos[k]);
               }
           }
           
           //SIMULACION para robots distanciados de los estantes
           Producto varios[][]=new Producto[30][7];
         for(int i=0; i<30; i++){
             for(int j=0; j<7; j++){
             varios[i][j]=new Producto("celular", 1234567);
         }
         }
         
         for(int i=0, k=0; i<10; i++){
               for(int j=0; j<1; j++, k++){
                   cajas[i][j]=new Caja(varios[k]);
               }
           }
         //TERMINA SIMULACION
           
           Thing _estantes[]=new Thing[10];
           for(int i=0; i<10; i++){
               _estantes[i]=new Thing(Bogota, i+4, 13);
           }
           
           Estante estantes[]=new Estante[10];
           for(int i=0; i<10; i++){
               estantes[i]=new Estante(_estantes[i], cajas[i]);
           }
           
           Color BLUE=new Color(52, 152, 219);
           Color PURPLE=new Color(142, 68, 173);
           
           Thing _empleadosAlmacen[]=new Thing[5];
           Thing _empleadosZonaEnvio[]=new Thing[5];
           for(int i=0; i<5; i++){
               _empleadosAlmacen[i]=new Thing(Bogota, 2, 2*i+3);
               _empleadosAlmacen[i].setColor(BLUE);
               _empleadosZonaEnvio[i]=new Thing(Bogota, 15, 2*i+3);
               _empleadosZonaEnvio[i].setColor(PURPLE);
           }
           
           Empleado empleadosAlmacen[]=new Empleado[5];
           Empleado empleadosZonaEnvio[]=new Empleado[5];
           for(int i=0; i<5; i++){
               empleadosAlmacen[i]=new Empleado(_empleadosAlmacen[i], 2, 2*i+3);
               empleadosZonaEnvio[i]=new Empleado(_empleadosZonaEnvio[i], 15, 2*i+3);
           }
        
         Almacen a1=new Almacen(drive, estantes, empleadosAlmacen, empleadosZonaEnvio);
         
         Producto computador[]=new Producto[3];
         computador[0]=new Producto("hp", 789);
         computador[1]=new Producto("lg", 789);
         computador[2]=new Producto("samsung", 789);
         
         
         
         if(a1.almacenarProductos(computador, 3)){ //almacena los productos del arreglo computador por el empleado 3
             System.out.println("Productos almacenados: " + computador.length);
         } else{
             System.out.println("Se ha presentado un error al almacenar los productos. ");
         }

         if(a1.solicitarPedido("celular", 28, 4, "Marielito")){
             System.out.println("Pedido solicitado.");
         } else {
             System.out.println("Se ha presentado un error al solicitar el pedido.");
         }
        }
        
        
        public void turnTwice(int n){
            if((n>=0)&&(n<7))
            drive[n].turnLeft();
            drive[n].turnLeft();
        }
        public void turnRight(int n){
            if((n>=0)&&(n<7))
            drive[n].turnLeft();
            drive[n].turnLeft();
            drive[n].turnLeft();
        }
        public void moverVariasVeces(int n, int m){
            if((n>=0)&&(n<7)){
            for(int i=0; i<m; i++){
                drive[n].move();
            }
            }
        }
    
}
