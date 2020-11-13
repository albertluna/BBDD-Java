public class ConnexioLocal {

    private DBConnector dbc;

    public ConnexioLocal(DBConnector dbc) { this.dbc = dbc; }

    public void insert (String table, String values) {
        dbc.insertQuery("INSERT INTO " + table + " VALUES " + values);
    }

    public void reset() {
        dbc.deleteQuery("DELETE FROM timezone_city;");
        dbc.deleteQuery("DELETE FROM timezone;");
        dbc.deleteQuery("DELETE FROM route;");
        dbc.deleteQuery("DELETE FROM plane;");
        dbc.deleteQuery("DELETE FROM city;");
        dbc.deleteQuery("DELETE FROM country;");
        dbc.deleteQuery("DELETE FROM airport;");
        dbc.deleteQuery("DELETE FROM airline;");

    }
}
