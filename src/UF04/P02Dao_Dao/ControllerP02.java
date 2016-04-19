package UF04.P02Dao_Dao;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.File;

/**
 * Created by 47257165p on 19/04/16.
 */
public class ControllerP02 {

    private static DataType database;
    private static JAXBContext context;
    private static File file = new File("UF04/P02Dao_Dao/data.xml");

    public static void main(String[] args) throws JAXBException {

        DAO dao = new DAO("localhost", "8080", "admin", "admin");

        Marshaller MS = context.createMarshaller();
        MS.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        MS.marshal(database, file);
        System.out.println("\nProducto añadido");
    }
}
