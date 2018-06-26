/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entidades;

import com.ControladoresRed.Mensaje;
import com.Utils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Universidad Catolica Andres Bello
 * Facultad de Ingenieria
 * Escuela de Ingenieria Informatica
 * Trabajo Especial de Grado
 * ----------------------------------
 * Tutor:
 * --------------
 * Wilmer Pereira
 *
 * Autores:
 * --------------
 * Garry Bruno
 * Carlos Valero
 */
public class Estadistica {
    private static int nodos_estables;
    private static int nodos_caidos;
    private static int tablas_generadas; 
    private static int tiempo_tablas; 
    private static int descargas_exitosas; 
    private static ArrayList<Registro> registros = new ArrayList<Registro>();
    
    public static void add_nodos(){
      nodos_estables++;
    }
    
    public static void add_caidos(){
       nodos_caidos++;
    }
    
    public static void add_tablas(){
       tablas_generadas++;
    }
    
    public static void add_exitosas(){
       descargas_exitosas++;
    }

    public static int getNodos_estables() {
        return nodos_estables;
    }

    public static void setNodos_estables(int nodos_estables) {
        Estadistica.nodos_estables = nodos_estables;
    }

    public static int getNodos_caidos() {
        return nodos_caidos;
    }

    public static void setNodos_caidos(int nodos_caidos) {
        Estadistica.nodos_caidos = nodos_caidos;
    }

    public static int getTablas_generadas() {
        return tablas_generadas;
    }

    public static void setTablas_generadas(int tablas_generadas) {
        Estadistica.tablas_generadas = tablas_generadas;
    }

    public static int getTiempo_tablas() {
        return tiempo_tablas;
    }

    public static void setTiempo_tablas(int tiempo_tablas) {
        Estadistica.tiempo_tablas = tiempo_tablas;
    }

    public static int getDescargas_exitosas() {
        return descargas_exitosas;
    }

    public static void setDescargas_exitosas(int descargas_exitosas) {
        Estadistica.descargas_exitosas = descargas_exitosas;
    }

    public static ArrayList<Registro> getRegistros() {
        return registros;
    }

    public static void setRegistros(ArrayList<Registro> registros) {
        Estadistica.registros = registros;
    }
    
    public static void agregandoregistro(Mensaje mensaje){
         if (mensaje.getData().equals("inicio")){
            registros.add(new Registro(mensaje.getOrigen().getHash().toString(),
                    mensaje.getFuncion(),Utils.obtenerTiempo(),Long.parseLong("0")));
                         System.out.println("Recibido: "+ mensaje.getFuncion()+" marca: "+mensaje.getData()+" tiempo: "+Utils.obtenerTiempo());
         }
         if (mensaje.getData().equals("final")){
             
              for (Registro registro : registros){
                  if (registro.getNodo().equals(
                          mensaje.getOrigen().getHash().toString())&&
                          registro.getFuncion().equals(mensaje.getFuncion())&&
                          (registro.getTiempo_final()==0)){
                      registro.setTiempo_final(Utils.obtenerTiempo());
                  
                  }
              }
              System.out.println("Recibido: "+ mensaje.getFuncion()+" marca: "+mensaje.getData()+" tiempo: "+Utils.obtenerTiempo());
         }
    }
    
    public static void generarInforme(){
       ArchivoThread generar = new ArchivoThread();
       new Thread(generar).start();     
    }
    
    private static class ArchivoThread implements Runnable{
        public void run() {
            try {
                String ruta = "Informe.txt";
                File archivo = new File(ruta);
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("------------------------------------------------------\n");
                bw.newLine();
                bw.write("Resultados de los registros \n");
                bw.newLine();
                bw.write("------------------------------------------------------\n");
                for (Registro registro : registros){
                  bw.newLine();
                  bw.write("Nodo: "+registro.getNodo()+" Funcion: "+registro.getFuncion()
                          +" Duracion: "+(registro.getTiempo_final()-registro.getTiempo_inicial())+" milisegundos \n");
                  bw.newLine();
                }
                bw.close();
                
                System.out.println("Se ha generado el archivo con exito!");
            } catch (IOException ex) {
                Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
