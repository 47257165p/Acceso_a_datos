package UF03;
/**
 * Created by 47257165p on 17/03/16.
 */

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.*;

public class ConsultaDB {

    private static final String IP = "172.31.101.225";
    private static final String PORT = "8080";

    public static void main(String[] args) throws XQException {

        //Definimos la consulta a realizar
        String defineQuery = "collection('AlejandroData')/CATALOG/PLANT/COMMON";
        System.out.println(query(defineQuery));

        /*String [] name = latitude.replaceAll("</COMMON>","").split("<COMMON>");
        System.out.println("Hi han "+(name.length -1) +" planetes");
        for (int i = 0; i < name.length; i++) {
            System.out.println(name[i]);
        }*/
    }

    public static String query(String query) throws XQException {

        //Creamos el String que devolveremos con el resultado a impirimir
        String result = "";

        //Creamos la conexión
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", IP);
        xqs.setProperty("port", PORT);
        XQConnection connection = xqs.getConnection();
        XQPreparedExpression xqpe =
                connection.prepareExpression(query);
        XQResultSequence rs = xqpe.executeQuery();

        while (rs.next())
        {
            result += rs.getItemAsString(null)+"\n";
        }
        connection.close();
        return result;
    }
}
