package UF03;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import java.io.File;

/**
 * Created by 47257165p on 17/03/16.
 */
public class XMLDBCreates {
    private static final String IP = "172.31.101.225";
    private static final String PORT = "8080";
    private static String URI = "xmldb:exist://"+IP+":"+PORT+"/exist/xmlrpc";
    private static final String driver = "org.exist.xmldb.DatabaseImpl";
    private static final String XMLFile = "plantes_Alejandro.xml";
    private static final String user = "admin";
    private static final String password = "dionis";

    public static void main(String args[]) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
            createCollectionAndResource(XMLFile, "ASoriano", XMLFile);
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
        Collection parent = DatabaseManager.getCollection(URI+"/db", user, password);
        CollectionManagementService c = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
        c.createCollection(newCollectionName);

        //Recuperamos la collection creada
        Collection collection = DatabaseManager.getCollection(URI + "/db/"+newCollectionName, user, password);

        //creamos el resource y lo guardamos
        Resource resource = collection.createResource(newResourceName,"XMLResource");
        resource.setContent(file);
        collection.storeResource(resource);

    }
}