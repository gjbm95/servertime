/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladoresRed;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class Ejecucion  extends Thread{
    
    private Mensaje mensaje; 
    private ObjectOutputStream oos;
    
    public Ejecucion(Mensaje mensaje,ObjectOutputStream oos){
       this.mensaje = mensaje; 
       this.oos = oos; 
    }
    
    public void run (){
        try {
            
            
            
            oos.writeObject(null);
        } catch (IOException ex) {
            Logger.getLogger(Ejecucion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
