package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 
 */
public class Texto {

    public static String formataData(Date data) {

        if (data == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE   dd / MMMM / yyyy");
        return sdf.format(data);
    }
    
    public static String formataDataPraTabela(Date data) {

        if (data == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd / MMMM / yyyy");
        return sdf.format(data);
    }
    
    public static void somenteNumeros(java.awt.event.KeyEvent evt){
        
        String caracteres = "0987654321.";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }
}
