package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

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

    public static void somenteNumeros(java.awt.event.KeyEvent evt) {

        String caracteres = "0987654321. '@#$%*()_+-=`'{[}]?/:;.,<>|";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    public static String getLinhaTable(JTable jTable, int coluna) {
        return jTable.getValueAt(jTable.getSelectedRow(), coluna).toString();

    }
}
