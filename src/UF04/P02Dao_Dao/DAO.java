package UF04.P02Dao_Dao;

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
 * Created by 45722053p on 29/03/16.
 */

public class DAO {

    public String ipMaquina;
    public String puerto;
    public String URI = "xmldb:exist://" + ipMaquina + ":"+puerto+"/exist/xmlrpc/db/";
    public String user;
    public String contra;

    public DAO(String ip,String puerto,String user,String contra) {

        this.ipMaquina = ip;
        this.puerto=puerto;
        this.URI = "xmldb:exist://" + ip + ":"+puerto+"/exist/xmlrpc/db/";
        this.user = user;
        this.contra = contra;

    }


    //GETTERS

    public  String getIpMaquina() {
        return ipMaquina;
    }

    public String getPuerto() {
        return puerto;
    }

    public  String getURI() {
        return URI;
    }


    public  String getUser() {
        return user;
    }

    public  String getContra() {
        return contra;
    }


    //SETTERS

    public void setIpMaquina(String ipMaquina) {
        this.ipMaquina = ipMaquina;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }


    public void setUser(String user) {
        this.user = user;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }





    /**
     * Con este metodo registramos la Base de Datos.
     *
     * @throws XMLDBException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public  void baseDatosRegistrar() throws XMLDBException, ClassNotFoundException, IllegalAccessException, InstantiationException{

        String driver = "org.exist.xmldb.DatabaseImpl";

        Class cl = Class.forName(driver);
        System.out.println();
        Database database = (Database) cl.newInstance();
        System.out.println();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

    }


    /**
     * En este metodo creamos una coleccion en nuestra base de datos donde le introducimos el nombre de la coleccion.
     * @param colleccion
     * @throws XMLDBException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public  void crearColeccion(String colleccion)throws XMLDBException, ClassNotFoundException, IllegalAccessException, InstantiationException{

        baseDatosRegistrar();

        //crear la collecion
        Collection parent = DatabaseManager.getCollection(URI,user,contra);
        CollectionManagementService c = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");

        c.createCollection(colleccion);
        System.out.println("La coleccion esta creada.");
    }



    /**
     * En este otro metodo creamos un recurso en nuestra coleccion creada anteriormente y con el contenido de un xml especifico que le digamos.
     *
     * @param nombreArchivo
     * @param nombreRecursoPath
     * @param nombreColeccion
     * @throws XMLDBException
     */
    public  void crearRecurso(String nombreArchivo,String nombreRecursoPath,String nombreColeccion) throws XMLDBException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        baseDatosRegistrar();


        File file = new File(nombreRecursoPath);

        Collection col = DatabaseManager.getCollection(URI + nombreColeccion,user,contra);
        Resource res = col.createResource(nombreArchivo,"XMLResource");
        res.setContent(file);
        col.storeResource(res);
        System.out.println("El recurso esta creado.");

    }

    /**
     *Con este metodo borramos el recurso que coloquemos por parametro.
     *
     * @param coleccion
     * @param nombreRecurso
     * @throws XMLDBException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public  void borrarRecurso(String coleccion,String nombreRecurso) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        baseDatosRegistrar();

        Collection col = DatabaseManager.getCollection(URI + coleccion,user,contra);
        Resource res = col.createResource(nombreRecurso,"XMLResource");
        col.removeResource(res);
        System.out.println("El recurso esta borrado.");

    }

    /**
     *El metodo de consulta que nos deja hacer? podremos hacer consultas sobre el recurso que deseemos.
     *
     * @param Path
     * @return
     * @throws XQException
     */
    public  String consulta(String Path) throws XQException {

        String resultado = " ";
        String linea = "";
        System.out.println();

        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", ipMaquina);
        xqs.setProperty("port",puerto);

        XQConnection conn = xqs.getConnection();

        XQPreparedExpression xqpe = conn.prepareExpression(Path);

        XQResultSequence rs = xqpe.executeQuery();

        while (rs.next()){

            linea = rs.getItemAsString(null);
            resultado += linea;
        }
        conn.close();
        return resultado;
    }




}
