import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexioPuigpedros {

    private DBConnector dbc;

    public ConnexioPuigpedros(DBConnector dbc) { this.dbc = dbc; }

    public String getAirline() {

        String query = "SELECT * FROM airline;";

        ResultSet rs = dbc.selectQuery(query);
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "(" + rs.getInt("airline_id");
                resultat += ", '" + rs.getString("name").replace("'", " ");
                resultat += "', '" + rs.getString("alias");
                resultat += "', '" + rs.getString("iata");
                resultat += "', '" + rs.getString("icao");
                resultat += "', '" + rs.getString("country").replace("'", " ");
                if (!rs.isLast()) resultat += "'),";
                else resultat += "');";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public  String getAirport() {
        ResultSet rs = dbc.selectQuery("SELECT * FROM airport;");
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "(" + rs.getInt("airport_id");
                resultat += ", '" + rs.getString("name").replace("'", " ");
                resultat += "', '" + rs.getString("city").replace("'", " ");
                resultat += "', '" + rs.getString("country").replace("'", " ");
                resultat += "', '" + rs.getString("iata");
                resultat += "', '" + rs.getString("icao");
                resultat += "', " + rs.getDouble("latitude");
                resultat += ", " + rs.getDouble("longitude");
                resultat += ", " + rs.getDouble("altitude");
                if (!rs.isLast()) resultat += "),";
                else resultat += ");";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public String getCountry() {
        ResultSet rs = dbc.selectQuery("SELECT * FROM country;");
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "('" + rs.getString("country").replace("'", " ");
                resultat += "', '" + rs.getString("code");
                resultat += "', '" + rs.getString("dst");
                if (!rs.isLast()) resultat += "'),";
                else resultat += "');";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public String getCity() {
        ResultSet rs = dbc.selectQuery("SELECT * FROM city;");
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "('" + rs.getString("country").replace("'", " ");
                resultat += "', '" + rs.getString("city").replace("'", " ");
                if (!rs.isLast()) resultat += "'),";
                else resultat += "');";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public String getPlane() {
        ResultSet rs = dbc.selectQuery("SELECT * FROM plane;");
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "(" + rs.getInt("plane_id");
                resultat += ", '" + rs.getString("name").replace("'", " ");
                resultat += "', '" + rs.getString("iata_code");
                resultat += "', '" + rs.getString("icao_code");
                if (!rs.isLast()) resultat += "'),";
                else resultat += "');";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public String getRoute() {
        ResultSet rs = dbc.selectQuery("SELECT * FROM route;");
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "(" + rs.getInt("route_id");
                resultat += ", " + rs.getInt("airline_id");
                resultat += ", " + rs.getInt("src_airport_id");
                resultat += ", " + rs.getInt("dst_airport_id");
                resultat += ", '" + rs.getString("codeshare");
                resultat += "', " + rs.getInt("stops");
                resultat += ", " + rs.getInt("plane");
                if (!rs.isLast()) resultat += "),";
                else resultat += ");";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public String getTimeZone() {
        ResultSet rs = dbc.selectQuery("SELECT * FROM timezone;");
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "(" + rs.getInt("timezone_id");
                resultat += ", '" + rs.getString("timezone_olson");
                resultat += "', " + rs.getDouble("timezone_utc");
                resultat += ", '" + rs.getString("daylight_saving_time");
                if (!rs.isLast()) resultat += "'),";
                else resultat += "');";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public String getTimezoneCity() {
        ResultSet rs = dbc.selectQuery("SELECT * FROM timezone_city;");
        String resultat = "";

        try {
            while (rs.next()) {
                resultat += "('" + rs.getString("country").replace("'", " ");
                resultat += "', '" + rs.getString("city").replace("'", " ");
                resultat += "', " + rs.getInt("timezone_id");
                if (!rs.isLast()) resultat += "),\n";
                else resultat += ");";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultat;
    }
}
