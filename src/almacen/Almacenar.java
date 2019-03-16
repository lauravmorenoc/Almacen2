/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

import becker.robots.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laura M
 */
public class Almacenar extends Thread{
    private Robot[] drive;
    private Producto[] productos;
    private Estante[] estantes;
    private Empleado[] empleadosAlmacen;
    private int empleado;
    private int robotsInUse[];
    private int calledTimes;

    public Almacenar() {
        empleado=0;
    }

    
    public Almacenar(Robot[] drive, Producto[] productos, Estante[] estantes, Empleado[] empleadosAlmacen, int empleado) {
        this.drive = drive;
        this.productos = productos;
        this.estantes = estantes;
        this.empleadosAlmacen=empleadosAlmacen;
        this.empleado = empleado;
        robotsInUse=new int[10];
        calledTimes=0;
        for(int n=0; n<10; n++){
            robotsInUse[n]=-1;
        }
    }

    
    
    
    @Override
    public void run (){ //
        calledTimes++;
        
        int productosNoAlmacenados=productos.length; //Se mira cuantos productos no han sido almacenados, que es la cantidad de productos que se pasn
        
        for(int i=0; (i<10)&&(productosNoAlmacenados>0); i++){ //cada estante tiene 3 cajas con 7 espacios para almacenar productos
                                                     //Es decir, cada estante tiene 21 espacios
        
        
        int n=calledTimes-1; //Se usa para saber qué robot usar  
        
        
        if (estantes[i].getEspaciosDisponibles()>0){
          //  moverVariasVeces(i, 13-drive[i].getAvenue());
           // drive[i].pickThing();
           // turnTwice(i);
           // moverVariasVeces(i, drive[i].getAvenue()-empleadosAlmacen[empleado].getAvenue());
           // turnRight(i);
           
           boolean canGo=false;
           
           while(!canGo){
               if(empleadosAlmacen[empleado].getRobotQueue()<(drive[n].getStreet()-2)){
                   canGo=true;
               }
           }
               
           //}
           
           //if()
            if(canGo){
            moverVariasVeces(i, empleadosAlmacen[empleado].getAvenue()-drive[n].getAvenue());
            }
            
            drive[n].turnLeft();
            System.out.println("Se voltea");
            
            int movimientosVerticales=drive[n].getStreet()-empleadosAlmacen[empleado].getStreet()-1;
            int auxiliarMov=movimientosVerticales;
            
            while(auxiliarMov>0){
                if((drive[n].getStreet()-3)==empleadosAlmacen[empleado].getRobotQueue()){
                    
                } else{
                    drive[n].move();
                    auxiliarMov--;
                }
            }
            
          //  moverVariasVeces(i, movimientosVerticales); //ESTA SI VA
            
            
            empleadosAlmacen[empleado].setRobotQueue(empleadosAlmacen[empleado].getRobotQueue()+1);
            productosNoAlmacenados=estantes[i].llenarCajas(productosNoAlmacenados, productos.length-productosNoAlmacenados, productos);
            turnRight(n);
            drive[n].move();
            
            empleadosAlmacen[empleado].setRobotQueue(empleadosAlmacen[empleado].getRobotQueue()-1);
            
            turnRight(n);
            moverVariasVeces(n, movimientosVerticales);
            //drive[i].turnLeft();
            
            turnRight(n);
            //moverVariasVeces(i, 13-drive[i].getAvenue());
            //drive[i].putThing();
            //turnTwice(i);
            while(drive[n].frontIsClear()){
                drive[n].move();
            }
            turnTwice(n);
        }
    }
        
        if (productosNoAlmacenados>0){
         for(int i=7; (i<10)&&(productosNoAlmacenados>0); i++){
          if (estantes[i].getEspaciosDisponibles()>0){
              
              System.out.println("Esto esta pasando");
              
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
