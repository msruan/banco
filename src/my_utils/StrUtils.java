package my_utils;

import java.util.ArrayList;
public class StrUtils {
    public static String[] split(String label, char separador){
        ArrayList<String> saida = new ArrayList<>();
        StringBuilder substring = new StringBuilder();
        for(int i = 0; i < label.length(); i++){
            if(label.charAt(i) != separador )
                substring.append(label.charAt(i));
            else if(!substring.isEmpty()){
                saida.add(substring.toString());
                substring.setLength(0);
            }
        }if(!substring.isEmpty())
            saida.add(substring.toString());
        return saida.toArray(new String[0]);
    }

    public static String trim(String label){
        //StringBuilder saida = new StringBuilder(label);
        if(label == null || label.isEmpty())
            return null;
        StringBuilder saida = new StringBuilder(label);
        while (saida.charAt(0) == ' ') {
            saida.deleteCharAt(0);
        }for(int len = saida.length(); len>0; len--){
            if(saida.charAt(len-1) != ' ')
                return saida.toString();
            saida.deleteCharAt(len-1);
        }return saida.toString();//nunca chega aqui
    }
}