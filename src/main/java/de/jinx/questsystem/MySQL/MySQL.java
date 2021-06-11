package de.jinx.questsystem.MySQL;

import java.sql.*;

public class MySQL {

    private String HOST = "";

    private String DATABASE = "";

    private String USER = "";

    private String PASSWORD = "";

    private Connection con;

    public MySQL(String host, String user, String password , String database) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        connect();
    }

    public void connect() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true", this.USER, this.PASSWORD);
            System.out.print("[MySQL] Verbindung hergestellt!");
        } catch (SQLException e) {
            System.out.print("[MySQL] Fehlgeschlagen!" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (this.con != null) {
                this.con.close();
                System.out.print("[MySQL] Die Verbindung wurde erfolgreich unterbrochen");
            }
        } catch (SQLException e) {
            System.out.print("[MySQL] Fehler bei der unterbrechung der Verbindung" + e.getMessage());
        }
    }

    public void update(String qry) {
        try {
            Statement st = this.con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = this.con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

}
