/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;
import becker.robots.*;

/**
 *
 * @author Laura M
 */
public class Empleado {
    private Thing thing;
    private int street;
    private int avenue;
    private int robotQueue;

    public Empleado(Thing thing, int street, int avenue) {
        this.thing = thing;
        this.street = street;
        this.avenue = avenue;
        robotQueue=0;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public int getStreet() {
        return street;
    }

    public void setStreet(int street) {
        this.street = street;
    }

    public int getAvenue() {
        return avenue;
    }

    public void setAvenue(int avenue) {
        this.avenue = avenue;
    }

    public int getRobotQueue() {
        return robotQueue;
    }

    public void setRobotQueue(int robotQueue) {
        this.robotQueue = robotQueue;
    }
    
}
