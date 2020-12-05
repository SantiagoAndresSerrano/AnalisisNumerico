/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.numerico;

import org.nfunk.jep.ParseException;

/**
 *
 * @author santi
 */
public class MainAnalisis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
       AnalisisNumerico a = new AnalisisNumerico("sqrt(1+x^2+8x^8)+e^x+ln(x^2)", "e", "pi",(short)60);
       
    }

}
