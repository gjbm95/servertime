/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Entidades;

/**
 *
 * @author Junior
 */
public class Registro {
    private String nodo; 
    private String funcion; 
    private Long tiempo_inicial;
    private Long tiempo_final;

    public Registro(String nodo, String funcion, Long tiempo_inicial, Long tiempo_final) {
        this.nodo = nodo;
        this.funcion = funcion;
        this.tiempo_inicial = tiempo_inicial;
        this.tiempo_final = tiempo_final;
    }

    public String getNodo() {
        return nodo;
    }

    public void setNodo(String nodo) {
        this.nodo = nodo;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Long getTiempo_inicial() {
        return tiempo_inicial;
    }

    public void setTiempo_inicial(Long tiempo_inicial){
        this.tiempo_inicial = tiempo_inicial;
    }

    public Long getTiempo_final() {
        return tiempo_final;
    }

    public void setTiempo_final(Long tiempo_final) {
        this.tiempo_final = tiempo_final;
    }
    
    
}
