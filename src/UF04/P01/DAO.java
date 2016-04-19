package UF04.P01;

import net.xqj.exist.ExistXQDataSource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import javax.xml.xquery.*;
import java.io.File;
import java.io.Serializable;

/**
 * Created by 47257165p on 29/03/16.
 */
public class DAO implements Serializable{

    private static String driver;
    private static String uri;
    private static String user;
    private static String password;

    /**
     * @param driver driver utilizado para poder utilizar la database
     * @param uri url para conectarnos
     * @param user usuario con el que nos conectaremos
     * @param password password del user con el que nos conectaremos
     */
    public DAO (String driver, String uri, String user, String password)
    {
        DAO.driver = driver;
        DAO.uri = uri;
        DAO.user = user;
        DAO.password = password;
    }

    public DAO (){}

    /**
     * registra la base de datos para poder modificar datos sobre la base de datos
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws XMLDBException
     */
    private void registerDatabase() throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    /**
     * crea una colección sobre nuestra base de datos
     * @param collectionName nombre de la colección que queremos crear
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public void createCollection(String collectionName) throws XMLDBException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        registerDatabase();
        Collection parent = DatabaseManager.getCollection(uri, user, password);
        CollectionManagementService c = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
        c.createCollection(collectionName);
    }

    /**
     * crea un recurso sobre la base de datos en la colección que le hemos indicado
     * @param collectionName nombre de la colección sobre la que crearemos el resource
     * @param resourceName nombre que queremos para nuestro resource
     * @param file archivo XML con los datos del recurso
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public void createResource(String collectionName, String resourceName, File file) throws XMLDBException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        registerDatabase();
        Collection collection = DatabaseManager.getCollection(uri + "/" + collectionName, user, password);
        Resource resource = collection.createResource(resourceName,"XMLResource");
        resource.setContent(file);
        collection.storeResource(resource);
    }

    /**
     * elimina un recurso sobre la base de datos en la colección que le hemos indicado
     * @param collectionName nombre de la colección en la que se encuentra el recurso a borrar
     * @param resourceName nombre del recurso que queremos borrar
     * @throws XMLDBException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public void deleteResource(String collectionName, String resourceName) throws XMLDBException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        registerDatabase();
        Collection collection = DatabaseManager.getCollection(uri + "/" + collectionName, user, password);
        Resource resource = collection.createResource(resourceName,"XMLResource");
        collection.removeResource(resource);
    }

    /**
     * @param ip la ip a la que nos queremos conectar
     * @param port puerto de la ip en la que se encuentra la base de datos
     * @param query query a realizar
     * @return
     * @throws XQException
     */
    public String query(String ip, String port, String query) throws XQException {

        //Creamos el String que devolveremos con el resultado a impirimir
        String result = "";

        XQConnection connection = openDatabase(ip, port);
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

    /**
     * Abre la conexión sobre la base de datos para poder realizar query's
     * @param ip la ip a la que nos queremos conectar
     * @param port puerto de la ip en la que se encuentra la base de datos
     * @return
     * @throws XQException
     */
    private static XQConnection openDatabase(String ip, String port) throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", ip);
        xqs.setProperty("port", port);
        return xqs.getConnection();
    }

    /**
     * @return
     */
    public static String getDriver() {
        return driver;
    }

    /**
     * @param driver
     */
    public static void setDriver(String driver) {
        DAO.driver = driver;
    }

    /**
     * @return
     */
    public static String getUri() {
        return uri;
    }

    /**
     * @param uri
     */
    public static void setUri(String uri) {
        DAO.uri = uri;
    }

    /**
     * @return
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param user
     */
    public static void setUser(String user) {
        DAO.user = user;
    }

    /**
     * @return
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public static void setPassword(String password) {
        DAO.password = password;
    }
}