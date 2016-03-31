package UF03; /**
 * Created by 47419119l on 17/03/16.
 */
import net.xqj.exist.ExistXQDataSource;
import javax.xml.xquery.*;
public class consultaExistDB {
    public static void main(String[] args) throws XQException {


       // String xquery100 = "(collection('47419119l')/mondial/country[name='China']/population[@year='2013'])div(count(collection('xmls')/mondial/country[name='China']/province))";


        String xquery2 = "collection('47419119l')/SOLAR_SYSTEM/PLANETS/PLANET/NAME";
        String latitude = consulta(xquery2);
        String [] name = latitude.replaceAll("</NAME>","").split("<NAME>");
        System.out.println("Hi han "+(name.length -1) +" planetes");
        for (int i = 0; i < name.length; i++) {
            System.out.println(name[i]);
        }

    }

    public static String consulta (String xQuery) throws XQException {
        String resultado = "";
        String linea = "";
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");

        XQConnection conn = xqs.getConnection();

        XQPreparedExpression xqpe =
                conn.prepareExpression(xQuery);



        XQResultSequence rs = xqpe.executeQuery();

        while (rs.next()){

            linea = rs.getItemAsString(null);
            resultado += linea;
        }
        conn.close();
        return resultado;
    }
}
