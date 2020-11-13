import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DBConnector dbc = new DBConnector("localhost", "root", "12345", "OLTP", 3306);
        ConnexioLocal cl = new ConnexioLocal(dbc.getInstance());

        System.out.println("Escriu quina base de dades vols utilitzar: ");
        System.out.println(" - flight_db_00\n - flight_db_01\n - flight_db_02");
        System.out.println("Base de dades: ");
        Scanner scanner = new Scanner(System.in);
        String bbdd = scanner.next();

        DBConnector dbc2 =
                    new DBConnector("puigpedros.salleurl.edu", "lsair_user", "lsair_bbdd", bbdd, 3306);
        ConnexioPuigpedros cp = new ConnexioPuigpedros(dbc2.getInstance());

        cl.reset();
        cl.insert("airline", cp.getAirline());
        cl.insert("airport", cp.getAirport());
        cl.insert("country", cp.getCountry());
        cl.insert("city", cp.getCity());
        cl.insert("plane", cp.getPlane());
        cl.insert("route", cp.getRoute());
        cl.insert("timezone", cp.getTimeZone());
        cl.insert("timezone_city", cp.getTimezoneCity());
    }
}
