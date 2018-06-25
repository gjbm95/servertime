/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ControladoresRed;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Junior
 */
public class Recepcion extends Thread {
    
    public Recepcion(){
    }
    
    public void run (){
        try {
                ServerSocket recepcion = null;
                recepcion = new ServerSocket(1500);
                System.out.println("Servidor de tiempo habilitado y en espera...");
                while (true) {
                    
                    Socket recibo = recepcion.accept();
                    ObjectInputStream ois = new ObjectInputStream(recibo.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(recibo.getOutputStream());
                    //Mensaje que llega:
                    Object mensaje = ois.readObject();
                    //Falta ejecutar acciones dependiendo del mensaje
                    Mensaje data = (Mensaje) mensaje;
                    //Se ejecuta un hilo con el proceso
                    new Ejecucion(data,oos).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}
