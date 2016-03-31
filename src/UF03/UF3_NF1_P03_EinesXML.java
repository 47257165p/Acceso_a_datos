package UF03;

import net.xqj.exist.ExistXQDataSource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import javax.xml.xquery.*;
import java.io.File;

/**
 * Created by 47257165p on 18/03/16.
 */
public class UF3_NF1_P03_EinesXML {

    private static final String IP = "localhost";
    private static final String PORT = "8080";
    private static String URI = "xmldb:exist://"+IP+":"+PORT+"/exist/xmlrpc/db";
    private static final String driver = "org.exist.xmldb.DatabaseImpl";
    private static final String XMLFile = "simple.xml";
    private static final String user = "admin";
    private static final String password = "admin";

    public static void main(String args[]) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        createCollectionAndResource(XMLFile, "ASoriano", XMLFile); //como input tenemos el nombre del XML, el nombre de la colección que crearemos y el nombre del recurso que crearemos
        someQuerys();
    }

    private static void someQuerys() {
        String query01 = "//breakfast_menu/food[calories < 500]/name";
        String query02 = "//breakfast_menu/food/name";
    }

    private static void createCollectionAndResource(String XMLFile, String newCollectionName, String newResourceName) throws XMLDBException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        //Creamos el archivo que pasaremos como resource más adelante
        File file = new File(XMLFile);

        // Inicializamos el database Driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        // crear el manager
        DatabaseManager.registerDatabase(database);

        //crear la collection
        Collection parent = DatabaseManager.getCollection(URI, user, password);
        CollectionManagementService c = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
        c.createCollection(newCollectionName);

        //Recuperamos la collection creada
        Collection collection = DatabaseManager.getCollection(URI + "/db/"+newCollectionName, user, password);

        //creamos el resource y lo guardamos
        Resource resource = collection.createResource(newResourceName,"XMLResource");
        resource.setContent(file);
        collection.storeResource(resource);
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
}

