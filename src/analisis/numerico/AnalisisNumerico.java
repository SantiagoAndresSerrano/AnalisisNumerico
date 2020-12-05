/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.numerico;

import org.nfunk.jep.JEP;
import org.nfunk.jep.ParseException;

/**
 *
 * @author santi
 */
public final class AnalisisNumerico {

    JEP objep = new JEP();
    String a, b;
    String fx;
    double valor, a1, b1;
    double valorx;
    double xi[],xit[];
    short n;

    public AnalisisNumerico(String fx, String a, String b, short n) throws ParseException {
        this.fx = fx;
        this.a = a;
        this.b = b;

        this.valor = 0.0;
        this.n = (short) (n); //toma el 0 ademas de las  n iteraciones
        xi = new double[n + 1];
        xit = new double[n+1];

        //System.out.println(medotoDeSimpson()[0]+"\n"+medotoDeTrapecio()[0]);

    }

    public String mostrar() {

        String mensaje = "Funcion: " + this.fx + " Valor: " + valor;

        return mensaje;
    }

    public void inicializar() {

        try {
            objep.addStandardConstants();
            objep.addStandardFunctions();
            objep.addComplex();

            objep.setAllowAssignment(true);
            objep.setAllowUndeclared(true);
            objep.setImplicitMul(true);

            objep.parseExpression(a);
            this.a1 = objep.getValue();

            objep.parseExpression(b);
            this.b1 = objep.getValue();
        } catch (Exception e) {

        }
    }

    public String[] medotoDeTrapecio() {
        String msg = "";
        inicializar();

        double deltax = (b1 - a1) / n;    // b-a/n 
        msg+="*******METODO DEL TRAPECIO*******\n";
        msg += "* Se halla el valor de Δx= b-a/n \n";
        msg += "Δx=" + deltax + "\n\n";
        xit[0]=a1;
        msg += "======================================================================================================\n";
        msg += "* Se calculan los Xi -> Xi = Δx + X(i-1)\n";
        msg += "X0=" + xit[0] + "\n";

        for (short i = 1; i <= n; i++) {

            xit[i] = deltax + xit[i - 1];
            msg += "X" + i + "=" + xit[i] + "\n";

        }
        
                double resultado = 0.0;
        msg += "======================================================================================================\n";
        msg += "\n* Se aplica la formula: (Δx/2)*[f(X0)+2*f(X1)+2*f(x2)+...+f(Xn)]\ndonde n: " + n + "\n";
        msg += "[("+deltax+")" + "/2] * (";

        resultado += fxi(xi[0]);
        msg += fxi(xi[0]) + " + ";
        for (short i = 1; i < n; i++) {

           
                resultado += 2 * fxi(xi[i]);
                msg += "2*" + fxi(xi[i]) + " + ";
             
        }
        msg += ")\n";

        resultado += fxi(xi[n]);
        resultado *= (deltax) / (2);
        msg += "======================================================================================================\n";

        msg += "Resultado=" + resultado;

        String[] respuesta = new String[2];

        respuesta[0] = msg;
        respuesta[1] = "" + resultado;
        
        return respuesta;
    }

    public String[] medotoDeSimpson() throws ParseException {
        String msg = "";
        inicializar();

        double deltax = (b1 - a1) / n;    // b-a/n   
        msg+="*****METODO DE SIMPSON*******\n";
        msg += "* Se halla el valor de Δx= b-a/n \n";
        msg += "Δx=" + deltax + "\n\n";

        xi[0] = a1;
        msg += "======================================================================================================\n";

        msg += "* Se calculan los Xi que es Xi = Δx + X(i-1)\n";
        msg += "X0=" + xi[0] + "\n";

        for (short i = 1; i <= n; i++) {

            xi[i] = deltax + xi[i - 1];
            msg += "X" + i + "=" + xi[i] + "\n";

        }

        double resultado = 0.0;
        msg += "======================================================================================================\n";
        msg += "\n* Se aplica la formula: (b-a/3n)*[f(X0)+4*f(X1)+2*f(x2)+...+f(Xn)]\ndonde n: " + n + "\n";
        msg += "[(" + b1 + "-" + a1 + ")" + "/" + 3 * n + "] * (";

        resultado += fxi(xi[0]);
        msg += fxi(xi[0]) + " + ";
        for (short i = 1; i < n; i++) {

            if (i % 2 == 0) {
                resultado += 2 * fxi(xi[i]);
                msg += "2*" + fxi(xi[i]) + " + ";
            } else {
                resultado += 4 * fxi(xi[i]);
                msg += "4*" + fxi(xi[i]) + " + ";
            }
        }
        msg += ")\n";

        resultado += fxi(xi[n]);
        resultado *= (b1 - a1) / (3 * n);
        msg += "======================================================================================================\n";

        msg += "Resultado=" + resultado;

        String[] respuesta = new String[2];

        respuesta[0] = msg;
        respuesta[1] = "" + resultado;

        return respuesta;
    }

    public Double fxi(Double x) {

        objep.addVariable("x", x);
        objep.parseExpression(fx);

        return objep.getValue();

    }

}
