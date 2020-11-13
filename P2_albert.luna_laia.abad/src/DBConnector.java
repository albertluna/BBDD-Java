
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
    private String user;
    private String password;
    private String db;
    private int port;
    private String url;
    private String host;
    private Connection conn;
    private Statement s;
    private DBConnector instance;

    public DBConnector(String host, String usr, String password, String db, int port) {
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + db;
        this.url += "?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";
        this.user = usr;
        this.password = password;
        this.db = db;
        this.port = port;
        this.host = host;
        this.instance = null;
    }

    public  DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector(host, user, password, db, port);
            instance.connect();
        }
        return instance;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connexió a base de dades " + url + " ... Ok");
            }
        } catch (SQLException ex) {
            System.out.println("Problema al connecta-nos a la BBDD --> " + url);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void insertQuery(String query) {
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Inserir --> " + ex.getSQLState());
            ex.printStackTrace();
        }
    }

    public void updateQuery(String query) {
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Modificar --> " + ex.getSQLState());
            ex.printStackTrace();
        }
    }

    public void deleteQuery(String query) {
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Eliminar --> " + ex.getSQLState());
            ex.printStackTrace();
        }
    }

    public ResultSet selectQuery(String query) {
        ResultSet rs = null;
        try {
            s = (Statement) conn.createStatement();
            rs = s.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
            ex.printStackTrace();
        }
        return rs;
    }

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }
}
