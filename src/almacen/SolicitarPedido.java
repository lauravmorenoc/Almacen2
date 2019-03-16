/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

import becker.robots.Robot;

/**
 *
 * @author Laura M
 */
public class SolicitarPedido extends Thread{

    private Estante[] estantes;
    private int cantidad;
    private Robot[] drive;
    private String cliente;
    private String nombreProducto;
    private Empleado[] empleadosZonaEnvio;
    private int empleado;
    
    
    public SolicitarPedido() {
    }

    public SolicitarPedido(Estante[] estantes, int cantidad, Robot[] drive, String cliente, String nombreProducto, Empleado[] empleadosZonaEnvio, int empleado) {
        this.estantes = estantes;
        this.cantidad = cantidad;
        this.drive = drive;
        this.cliente = cliente;
        this.nombreProducto = nombreProducto;
        this.empleadosZonaEnvio = empleadosZonaEnvio;
        this.empleado = empleado;
    }
    
    
    
    @Override
    public void run(){
            //función para buscar el producto pedido
            Producto productoSolicitado=null;
            int posicionArray=0, productosExistentes=0; //La variable productos existentes mira cuantos productos del que pidieron hay.
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
            
            if(productosExistentes<cantidad){//Significa que no se encontraron productos o se encontraron menos de los requeridos
                System.out.println("No se encontraron productos o se encontraron menos de los requeridos");
            } else
            
            System.out.println("Antes de mover a karel");
          //  System.out.println("Imprimiendo array de estantes: ");
            
            /*for(int w=0; estantes_array[w]!=-1; w++){
                System.out.println(estantes_array[w]);
            }*/
            int productosPorSacar=cantidad;
            
            for(int c=0; ((estantes_array[c]!=-1) && (productosPorSacar>0 && c<10)); c++){//c es el estante del cual se están extrayendo productos
                
                
            int randomRobot= (int)(Math.random()*9);//Escoge un robot random para llevar el pedido
            drive[randomRobot].move();
            
            
           // boolean indicadorPosicion=estantes_array[c]+4<drive[randomRobot].getStreet();
    
            //Desde aqui quitado
            /*if(indicadorPosicion){
                drive[randomRobot].turnLeft();
            } else {
            turnRight(randomRobot); //(c+4) es la "street" del estante c
            }*/
            // Hasta aqui quitado - Ni para arriba ni para abajo
            
            //int movimientosInicialesEnY=Math.abs(((estantes_array[c])+4)-drive[randomRobot].getStreet()); //Street horizontales, avenue verticales
            //moverVariasVeces(randomRobot, movimientosInicialesEnY);
            //Desde aqui quitado
            /*if(indicadorPosicion){
                turnRight(randomRobot);
            } else{
            drive[randomRobot].turnLeft();*/
            //}//Hasta aqui quitado
            //moverVariasVeces(randomRobot, 13-drive[randomRobot].getAvenue());
            //drive[randomRobot].pickThing();
            //turnTwice(randomRobot);
            //moverVariasVeces(randomRobot, 6);
            //drive[randomRobot].turnLeft();
            
            moverVariasVeces(randomRobot, 5);
            turnRight(randomRobot);
            
            int movimientosY=empleadosZonaEnvio[empleado].getStreet()-drive[randomRobot].getStreet()-1;
            
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
            
           // moverVariasVeces(randomRobot, drive[randomRobot].getStreet()-(estantes_array[c]+4));
           
           moverVariasVeces(randomRobot, movimientosY);
           
           //Desde aqui quitado
            /*turnRight(randomRobot);
            
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
            }*/
           //Hasta aqui quitado
            
            drive[randomRobot].turnLeft();
            while(drive[randomRobot].frontIsClear()){
                drive[randomRobot].move();
            }
            turnTwice(randomRobot);
            
            //TESTEO
               // System.out.println(productosPorSacar);
            }
            Pedido pedido=new Pedido(cliente, cantidad, productoSolicitado);
            pedido.generarFactura();     
            System.out.println("Pedido hecho.");
    }
    
    public void turnTwice(int n){
            if((n>=0)&&(n<10))
            drive[n].turnLeft();
            drive[n].turnLeft();
        }
        public void turnRight(int n){
            if((n>=0)&&(n<10))
            drive[n].turnLeft();
            drive[n].turnLeft();
            drive[n].turnLeft();
        }
        public void moverVariasVeces(int n, int m){ //Le ordena al Robot numero n que se mueva m veces
            if((n>=0)&&(n<10)){
            for(int i=0; i<m; i++){
                if(drive[n].frontIsClear()){
                drive[n].move();
                }
            }
            }
        }
    
}
