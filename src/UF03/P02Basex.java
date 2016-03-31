package UF03;

import org.basex.api.client.ClientSession;

import java.io.IOException;
import java.util.Scanner;
/**
 * Created by 47257165p on 10/03/16.
 */
public class P02Basex {

    private static Scanner in = new Scanner(System.in);

    private static String doc1 = "doc(\"Factbook.xml\")";
    private static String doc2 = "doc(\"mondial.xml\")";

    public static void main(String[] args) {

        menu();
    }

    public static void menu()
    {
        String option = "";

        while (!option.equals("8")) {
            System.out.println("ELIJE UNA OPCIÓN");
            System.out.println("1. Quins països hi ha en el fitxer «factbook.xml»?");
            System.out.println("2. Quants països hi ha?");
            System.out.println("3. Quina és la informació sobre Alemanya?");
            System.out.println("4. Quanta gent viu a Uganda segons aquest fitxer?");
            System.out.println("5. Quines són les ciutats de Perú que recull aquest fitxer?");
            System.out.println("6. Quanta gent viu a Shanghai?");
            System.out.println("7. Quin és el codi de matricula de cotxe de Xipre?");
            System.out.println("8. Salir");
            option = in.next();

            switch (option)
            {
                case "1":
                    paises();
                    break;
                case "2":
                    numeroPaises();
                    break;
                case "3":
                    infoAlemania();
                    break;
                case "4":
                    poblacionUganda();
                    break;
                case "5":
                    ciudadesPeru();
                    break;
                case "6":
                    poblacionShanghai();
                    break;
                case "7":
                    matriculaXipre();
                    break;
                case "8":
                    System.out.println("Gracias por utilizar el programa.");
                    break;
                default:
                    break;
            }
        }
    }

    public static void paises()
    {
        try {
            ClientSession clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println(clientSession.query(doc1+"/factbook/record/country").execute()+"\n");
            clientSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void numeroPaises()
    {
        try {
            ClientSession clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println(clientSession.query(doc2+"/count(/mondial/country)").execute()+"\n");
            clientSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void infoAlemania()
    {
        try {
            ClientSession clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println(clientSession.query(doc2+"/mondial/country[name=\"Germany\"]").execute()+"\n");
            clientSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void poblacionUganda()
    {
        try {
            ClientSession clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println(clientSession.query(doc2+"/mondial/country[name='Uganda']/population").execute()+"\n");
            clientSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void ciudadesPeru()
    {
        try {
            ClientSession clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println(clientSession.query(doc2+"/mondial/country[name='Peru']/province/city").execute()+"\n");
            clientSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void poblacionShanghai()
    {
        try {
            ClientSession clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println(clientSession.query(doc2+"/mondial/country/province[name=\"Shanghai\"]/population").execute()+"\n");
            clientSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void matriculaXipre()
    {
        try {
            ClientSession clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println(clientSession.query(doc2+"/mondial/country[name=\"Cyprus\"]/@car_code").execute()+"\n");
            clientSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
