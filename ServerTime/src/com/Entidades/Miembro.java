package com.Entidades;

import com.Utils.RespuestaUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
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
public abstract class Miembro implements Serializable {
    private int puertopeticion;
    private String direccion;
    private BigInteger hash;

    public Miembro(){

    }

    public Miembro(String direccion,int puertopeticion,BigInteger hash){
        this.direccion = direccion;
        this.puertopeticion = puertopeticion;
        this.hash = hash;
    }

    public int getPuertopeticion() {
        return puertopeticion;
    }

    public void setPuertopeticion(int puertopeticion) {
        this.puertopeticion = puertopeticion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        try {
            this.direccion = direccion;
            this.hash = RespuestaUtils.generarHash(direccion);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Miembro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BigInteger getHash() {
        return hash;
    }

    public void setHash(BigInteger hash) {
        this.hash = hash;
    }
}

