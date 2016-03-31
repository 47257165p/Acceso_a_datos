package UF03.ExamenUF03;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.*;
import java.util.ArrayList;

/**
 * Created by 47257165p on 17/03/16.
 */
public class Part01 {

    private static final String IP = "172.31.101.225";
    private static final String PORT = "8080";

    public static void main(String[] args) throws XQException {

        //Definimos la consulta a realizar (el nombre del string es cada ejercicio)
        String queryEx01 = "//CATALOG/PLANT[AVAILABILITY = max(//CATALOG/PLANT/AVAILABILITY)]/COMMON";
        String queryEx02 = "sum(//CATALOG/PLANT/AVAILABILITY)";

        System.out.println("LA PLANTA CON MÁS STOCK ES = "+limpiarTag(singleQueryResult(queryEx01))); //sout del ejercicio 1
        System.out.println("CANTIDAD TOTAL DE PLANTAS EN STOCK = "+ singleQueryResult(queryEx02)); //sout del ejercicio 2
        ex03();
    }

    public static String singleQueryResult(String query) throws XQException {

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
            result = rs.getItemAsString(null);
            connection.close();
            return result;
        }
        return result;
    }

    public static String multipleQueryResult(String query) throws XQException {

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
            result += rs.getItemAsString(null);
        }
        connection.close();
        return result;
    }

    public static String limpiarTag (String trashString)
    {
        String [] cleanString = trashString.split(">");
        cleanString = cleanString[1].split("</");
        return cleanString[0];
    }

    public static void ex03() throws XQException {
        ArrayList<String> nombres;
        ArrayList<String> stock;
        ArrayList<String> precios;

        String queryEx031 = "//CATALOG/PLANT/COMMON";
        String queryEx032 = "//CATALOG/PLANT/AVAILABLE";
        String queryEx033 = "//CATALOG/PLANT/PRICE";

        nombres = queryEx03(queryEx031);
        stock = queryEx03(queryEx032);
        precios = queryEx03(queryEx033);
        precios = limpiarDollar(precios);

        for (int i = 0; i < nombres.size(); i++)
        {
            System.out.println("De la planta "+nombres.get(i)+
                    " tenemos "+stock.get(i)+
                    " ejemplares. Cada ejemplar tiene un precio de "+precios.get(i)+
                    "$. Por lo tanto por la venta de todo el stock ingresaremos "+
                    (Double.parseDouble(stock.get(i))*Double.parseDouble(precios.get(i))));
        }

    }

    public static ArrayList<String> queryEx03(String query) throws XQException {
        //Creamos la conexión
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", IP);
        xqs.setProperty("port", PORT);
        XQConnection connection = xqs.getConnection();
        XQPreparedExpression xqpe =
                connection.prepareExpression(query);
        XQResultSequence rs = xqpe.executeQuery();

        ArrayList<String> array = new ArrayList<>();

        while (rs.next())
        {
            array.add(limpiarTag(rs.getItemAsString(null)));
        }
        connection.close();
        return array;
    }

    public static ArrayList<String> limpiarDollar(ArrayList<String> arraySucio)
    {
        ArrayList<String> limpiar = arraySucio;
        for (int i = 0; i < limpiar.size(); i++) {
            limpiar.get(i).replaceAll("$", "");
        }
        return limpiar;
    }
}