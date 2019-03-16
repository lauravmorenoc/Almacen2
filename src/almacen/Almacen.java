/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;
import becker.robots.*;
//import java.lang.Object;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
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
    private ArrayList<Almacenar> almacenamiento;
    private SolicitarPedido pedido;
    
    public Almacen(Robot drive[], Estante estantes[], Empleado empleadosAlmacen[], Empleado empleadosZonaEnvio[]){
        this.drive=drive;
        this.estantes=estantes;
        this.empleadosAlmacen=empleadosAlmacen;
        this.empleadosZonaEnvio=empleadosZonaEnvio;
        this.almacenamiento=new ArrayList<>();
    }
        
        
	public static void main (String[] args){
            Bogota = new City("Field.txt");
	    Bogota.showThingCounts(true);

           Robot drive[] = new Robot[10];
           drive[0] = new Robot(Bogota,4, 1, Direction.EAST);
           drive[1] = new Robot(Bogota,5, 1, Direction.EAST);
           drive[2] = new Robot(Bogota,6, 1, Direction.EAST);
           drive[3] = new Robot(Bogota,7, 1, Direction.EAST);
           drive[4] = new Robot(Bogota,8, 1, Direction.EAST);
           drive[5] = new Robot(Bogota,9, 1, Direction.EAST);
           drive[6] = new Robot(Bogota,10, 1, Direction.EAST);
           drive[7] = new Robot(Bogota,11, 1, Direction.EAST);
           drive[8] = new Robot(Bogota,12, 1, Direction.EAST);
           drive[9] = new Robot(Bogota,13, 1, Direction.EAST);
           
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
         
         Producto computador[]=new Producto[20];
         computador[0]=new Producto("hp", 789);
         computador[1]=new Producto("lg", 789);
         computador[2]=new Producto("samsung", 789);
         
         Producto televisor[]=new Producto[6];
         televisor[0]=new Producto("samsung", 554);
         televisor[1]=new Producto("challenger", 554);
         televisor[2]=new Producto("tvShow", 554);
         
         
         /*if(a1.almacenarProductos(computador, 3)){ //almacena los productos del arreglo computador por el empleado 3
             System.out.println("Productos almacenados: " + computador.length);
         } else{
             System.out.println("Se ha presentado un error al almacenar los productos. ");
         }*/
         //Almacenar alm1=new Almacenar(drive, computador, estantes, empleadosAlmacen, 3);
         //alm1.start();
         
         //Almacenar alm2=new Almacenar(drive, televisor, estantes, empleadosAlmacen, 3);
         //alm2.start(); //Cuando se realiza esto, la funcion ya no sirve
         
         Producto[] producto;
         boolean open=true;
         
         while(open){
         
         int a=Integer.parseInt(JOptionPane.showInputDialog("¿Qué desea hacer?: "
                    + "1 => Almacenar productos, "
                    + "2 => Solicitar pedido, "
                    + "3 => Terminar"));
            switch (a){
                case 1: 
                    int b=Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos productos desea almacenar?: "));
                    
                    if(b>21){
                        JOptionPane.showMessageDialog(null,"Intente con un numero menor de productos");
                    } else{
                        producto=new Producto[b];
                        
                        
                        for(int z=0; z<b; z++){
                            producto[z]=new Producto();
                            String marca=JOptionPane.showInputDialog(null, "Introduzca la marca del producto no. " + z+1);
                            producto[z].setTipo(marca);
                            double precio=Integer.parseInt(JOptionPane.showInputDialog("Igrese precio del producto no. " + z));
                            producto[z].setPrecio(precio);
                        }
                        
                        int randomEmpleado=(int)(Math.random()*4);
                        
                        Almacenar alm1=new Almacenar(drive, producto, estantes, empleadosAlmacen, randomEmpleado);
                        alm1.start();
                    }
                    
                    break;
                case 2:
                    
                    int cantidad=Integer.parseInt(JOptionPane.showInputDialog("Igrese cantidad de productos a adquirir"));
                    String marca=JOptionPane.showInputDialog(null, "Introduzca el tipo de producto");
                    String cliente=JOptionPane.showInputDialog(null, "Introduzca el nombre del cliente");
                    int randomEmpleado=(int)(Math.random()*4);
                    
                    if(cantidad>30){
                        JOptionPane.showMessageDialog(null,"Intente con un numero menor de productos");
                    }else{
                    
                    SolicitarPedido ped1=new SolicitarPedido(estantes, cantidad, drive, cliente, marca, empleadosZonaEnvio, 4);
                    ped1.start();
                    }
                    
                    break;
                    
                case 3:
                    JOptionPane.showMessageDialog(null,"Ha terminado la operacion. ");
                    open=false;
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null,"Intente de nuevo mas tarde");
            }
         }

         
         //SolicitarPedido ped1=new SolicitarPedido(estantes, 28, drive, "Marielito", "celular", empleadosZonaEnvio, 4);
         //ped1.start();
         
         /*if(a1.solicitarPedido("celular", 28, 4, "Marielito")){
             
             System.out.println("Pedido solicitado.");
         } else {
             System.out.println("Se ha presentado un error al solicitar el pedido.");
         }*/
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
                drive[n].move();
            }
            }
        }
    
}
