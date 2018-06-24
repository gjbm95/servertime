/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertime;

import Red.Recepcion;
import com.Entidades.Estadistica;
import java.util.Scanner;

/**
 *
 * @author Junior
 */
public class ServerTime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Recepcion().start();
        System.out.println("-----------------------------------------------------------");
        System.out.println("UCAB - Trabajo Especial de Grado");
        System.out.println("Autores: Garry Bruno / Carlos Valero");
        System.out.println("Tutor: Wilmer Pereira");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Servidor del Tiempo");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Para generar un informe escriba 'generate' ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Ingrese un comando:");
        Scanner in = new Scanner(System.in);
        while (true) {
            String line ="";
            line = in.nextLine();
            if (line.equals("generate")){
               Estadistica.generarInforme();
            }
            
        }
    }
    
}
