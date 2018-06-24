package com.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class RespuestaUtils {

    /**
     * Aplica el algoritmo de compendio SHA-1 sobre la entrada y retorna su representacion numerica
     * @param entrada Caracteres alfanumericos
     * @return Numero de maximo 60 digitos
     */
    public static BigInteger generarHash (String entrada) throws NoSuchAlgorithmException
    {
        MessageDigest algoritmoCompendio = MessageDigest.getInstance("SHA1");
        algoritmoCompendio.update(StandardCharsets.UTF_8.encode(entrada));

        BigInteger hash = new BigInteger (1, algoritmoCompendio.digest() );
        return hash.mod(BigInteger.valueOf(20));
    }
    
        public static int obtenerTiempo(){
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos,milisegundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        milisegundos = calendario.get(Calendar.MILLISECOND);
        return milisegundos;
    }
}
