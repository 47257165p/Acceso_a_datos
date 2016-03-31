package UF03;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.File;

/**
 * Created by dremon on 10/03/16.
 */
public class XMLDBIntro {
    private static final String URI = "xmldb:exist://172.31.101.228:8080/exist/xmlrpc";
    private static final String driver = "org.exist.xmldb.DatabaseImpl";
    private static final String XMLFile = "Pokemons.xml";
    private static final String user = "admin";
    private static final String password = "dionis";

    public static void main(String args[]) throws XMLDBException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

        afegirFitxer(XMLFile);

    }

    private static void afegirFitxer(String XMLFile) throws XMLDBException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{
        File f = new File(XMLFile);

        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // crear el manegador
        DatabaseManager.registerDatabase(database);

        // adquirir la col·lecció que volem tractar
        Collection col = DatabaseManager.getCollection(URI+"/db", user, password);

        //afegir el recurs que farem servir
        Resource res = col.createResource("pokemons.xml","XMLResource");
        res.setContent(f);
        col.storeResource(res);

    }
}
