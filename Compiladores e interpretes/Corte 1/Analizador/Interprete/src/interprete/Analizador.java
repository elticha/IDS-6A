/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import java.util.LinkedList;
import javafx.scene.control.TextArea;

/**
 *
 * @author Fernando
 */
public class Analizador {

    private String textoDeEntrada;
    private LinkedList<Token> tokens;
    private TextArea area;
    //valores: x=0;
    //private LinkedList<String> tokenID;
    //private LinkedList<String> tokenID;
    //private LinkedList<String> tokenID;

    public Analizador(String entrada, TextArea ta) {
        tokens = new LinkedList<>();
        textoDeEntrada = entrada;
        area = ta;
        loadDiccionario();
        analizar();
    }

    private void loadDiccionario() {
        tokens.add(new Token("VALORES", "valores"));
        tokens.add(new Token("FUNCIONES", "funcion"));
        tokens.add(new Token("PLUS", "+"));
        tokens.add(new Token("MULT", "*"));
        tokens.add(new Token("EQ", "="));
        tokens.add(new Token("NOT", "-"));
        tokens.add(new Token("PAR_OPEN", "("));
        tokens.add(new Token("PAR_CLOSE", ")"));
        tokens.add(new Token("DOS_PUNTOS", ":"));
        tokens.add(new Token("SEMICOLON", ";"));
        tokens.add(new Token("COMA", ","));
        tokens.add(new Token("ID", "a"));
        tokens.add(new Token("ID", "b"));
        tokens.add(new Token("ID", "c"));
        tokens.add(new Token("ID", "d"));
        tokens.add(new Token("ID", "e"));
        tokens.add(new Token("ID", "f"));
        tokens.add(new Token("ID", "g"));
        tokens.add(new Token("ID", "h"));
        tokens.add(new Token("ID", "i"));
        tokens.add(new Token("ID", "j"));
        tokens.add(new Token("ID", "k"));
        tokens.add(new Token("ID", "l"));
        tokens.add(new Token("ID", "m"));
        tokens.add(new Token("ID", "n"));
        tokens.add(new Token("ID", "o"));
        tokens.add(new Token("ID", "p"));
        tokens.add(new Token("ID", "q"));
        tokens.add(new Token("ID", "r"));
        tokens.add(new Token("ID", "s"));
        tokens.add(new Token("ID", "t"));
        tokens.add(new Token("ID", "u"));
        tokens.add(new Token("ID", "v"));
        tokens.add(new Token("ID", "w"));
        tokens.add(new Token("ID", "x"));
        tokens.add(new Token("ID", "y"));
        tokens.add(new Token("ID", "z"));
        tokens.add(new Token("SIGNAL", "0"));
        tokens.add(new Token("SIGNAL", "1"));
        tokens.add(new Token("SIGNAL", "0"));
    }

    public void analizar() {
        area.setText("");
        //System.out.println(txt.replace(" ", ""));
        //System.out.println(txt.replace("\n", ""));

        //valores: x=0,y=0;
        //funcion: xy;
        //valores:x=0,y=0;funcion:xy;
        //split por ;
        //valores:x=0,y=0;
        //funcion:xy;
        textoDeEntrada = textoDeEntrada.replace(" ", "");
        textoDeEntrada = textoDeEntrada.replace("\n", "");

        //Ahora que la cadena ya está unificada, se hará split por ;
        String cadenas[] = textoDeEntrada.split(";");

        //Trabajndo con cadenas[0]
        int indiceDosPuntos = cadenas[0].indexOf(":");

        String primera_reservada = cadenas[0].substring(0, indiceDosPuntos);
        String dos_puntos = cadenas[0].substring(indiceDosPuntos, indiceDosPuntos + 1);
        indiceDosPuntos += 1;
        //A partir del ultimo índice dosPuntos, se encuentran valores
        cadenas[0] += ";";
        String resto = cadenas[0].substring(indiceDosPuntos, cadenas[0].length());
        iterar(primera_reservada);
        iterar(dos_puntos);
        
        for(int i = 0; i < resto.length(); i++){
            iterar(String.valueOf(resto.charAt(i)));
        }

//        valores: x=0, y =1;
//        funcion: x*y;
    }
    
    private void iterar(String s){
        boolean ok = false;
        for(Token t : tokens){
            String strToken = t.getLexema();
            if(strToken.equals(s)){
                area.setText(area.getText() + "\n" + t.toString());
                ok = true;
            }
        }
        if(!ok)
            area.setText(area.getText() + "\n" + "[ERROR] Palabra " + s + "no reconocida.\n");
    }
}

class Token {

    private String token, lexema;

    public Token(String t, String l) {
        this.token = t;
        this.lexema = l;
    }

    public String getToken() {
        return token;
    }

    public String getLexema() {
        return lexema;
    }

    public String toString() {
        return "TOKEN -> " + token + " Lexema -> " + lexema + "\n";
    }
}
