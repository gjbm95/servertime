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
    private static long total_addnode=0;
    private static long total_finger=0; 
    private static long total_busquedas=0;
    private static long total_combinacion=0;
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
        Long tiempo = Utils.obtenerTiempo();
         if (mensaje.getData().equals("inicio")){
            
            registros.add(new Registro(mensaje.getOrigen().getHash().toString(),
            mensaje.getFuncion(),tiempo,Long.parseLong("0")));
            System.out.println("Recibido: "+ mensaje.getFuncion()+" marca: "+mensaje.getData()+" tiempo: "+tiempo);
            if (mensaje.getFuncion().equals("addnode"))
            registros.add(new Registro(mensaje.getOrigen().getHash().toString(),"combinacion",tiempo,Long.parseLong("0")));
         }
         if (mensaje.getData().equals("final")){
             
              for (Registro registro : registros){
                  if (registro.getNodo().equals(
                          mensaje.getOrigen().getHash().toString())&&
                          registro.getFuncion().equals(mensaje.getFuncion())&&
                          (registro.getTiempo_final()==0)){
                      registro.setTiempo_final(tiempo);
                      for (Registro registro2 : registros){
                           if (registro2.getNodo().equals(
                          mensaje.getOrigen().getHash().toString())&&
                          registro2.getFuncion().equals("combinacion")&&
                          (registro2.getTiempo_final()==0)&&(mensaje.getFuncion().equals("generarFinger")))
                             registro2.setTiempo_final(tiempo);
                      }
                          
                  }
              }
              System.out.println("Recibido: "+ mensaje.getFuncion()+" marca: "+mensaje.getData()+" tiempo: "+Utils.obtenerTiempo());
         }
    }
    
    public static void realizarOperaciones(){
       int total_nodos =0; 
       for (Registro registro : registros){
          if (registro.getFuncion().equals("addnode")){
            total_addnode = total_addnode + (registro.getTiempo_final()-registro.getTiempo_inicial());
            total_nodos++;
            add_nodos();
          }  
          if (registro.getFuncion().equals("combinacion")){
            total_combinacion = total_combinacion + (registro.getTiempo_final()-registro.getTiempo_inicial());
          }  
       }
       
       int i=registros.size()-1;
       while (i>=((registros.size()-1)-total_nodos)){
        if (registros.get(i).getFuncion().equals("generarFinger")){
            total_finger = total_finger + (registros.get(i).getTiempo_final()-registros.get(i).getTiempo_inicial());
        }  
        i--;
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
                realizarOperaciones();
                bw.write("------------------------------------------------------\n");
                bw.newLine();
                bw.write("Resultados Finales \n");
                bw.newLine();
                bw.write("------------------------------------------------------\n");
                bw.newLine();
                bw.write("Numero de nodos: "+nodos_estables+" \n");
                bw.newLine();
                bw.write("Tiempo en agregar nodos: "+total_addnode+" milisegundos \n");
                bw.newLine();
                bw.write("Tiempo en generar fingers: "+total_finger+" milisegundos \n");
                bw.newLine();
                bw.write("Tiempo en agregar nodos + generar fingers: "+total_combinacion+" milisegundos \n");
                bw.newLine();
                bw.close();
                
                System.out.println("Se ha generado el archivo con exito!");
            } catch (IOException ex) {
                Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
