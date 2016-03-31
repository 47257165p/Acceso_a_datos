package UF04.P01;

import org.xmldb.api.base.XMLDBException;

import java.io.File;

/**
 * Created by 47257165p on 29/03/16.
 */
public class P01Controller {

    private static final String IP = "172.31.101.225";
    private static final String PORT = "8080";
    private static String URI = "xmldb:exist://"+IP+":"+PORT+"/exist/xmlrpc/db";
    private static final String driver = "org.exist.xmldb.DatabaseImpl";
    private static final String XMLFile = "plantes_Alejandro.xml";
    private static final String user = "admin";
    private static final String password = "dionis";

    public static void main(String[] args) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        File file = new File(XMLFile);
        DAO dao = new DAO(driver, URI, user, password);
        dao.createCollection("AlejandroTesting");
        dao.createResource("AlejandroTesting", "plantitas", file);
        dao.createResource("AlejandroTesting", "plantitasborrable", file);
        dao.deleteResource("AlejandroTesting", "plantitasborrable");

    }

}
