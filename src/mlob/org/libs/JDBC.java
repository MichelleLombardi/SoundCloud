package mlob.org.libs;

import java.sql.*;

public class JDBC {

    private String jdbc = null;
    private String host = null;
    private String user = null;
    private String pass = null;
    private String db = null;
    private int port = 0;

    private Connection conn = null;

    public static final String HOST = "localhost";

    public static final String JDBC_POSTGRESQL = "postgresql";
    public static final String USER_POSTGRESQL = "postgres";
    public static final int PORT_POSTGRESQL = 5432;

    public static final String JDBC_MYSQL = "mysql";
    public static final String USER_MYSQL = "root";
    public static final int PORT_MYSQL = 3306;

    public JDBC(String jdbc, String host, String user, String pass, String db, int port) {
        try {
            String driver;
            switch (this.jdbc = jdbc.toLowerCase()) {
                case "postgresql":
                    driver = "org.postgresql.Driver";
                    break;
                case "mysql":
                    driver = "com.mysql.jdbc.Driver";
                    break;
                default:
                    throw new Error("Can't load jdbc driver");
            }
            this.host = host;
            this.user = user;
            this.pass = pass;
            this.db = db;
            this.port = port;
            Class.forName(driver);
            this.createConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createConnection() {
        try {
            String connString = "jdbc:" + this.jdbc + "://" + this.host + ":" + this.port + "/" + this.db;
            this.conn = DriverManager.getConnection(connString, this.user, this.pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getConnectionStatus() {
        return this.conn != null;
    }

    public void execute( String query, Object... values ) {
        try {
            PreparedStatement pstmt = setValues(query, values);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[][] executeQuery( String query, Object... values ) {
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = this.setValues(query, values);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.RSToTable(rs);
    }

    private PreparedStatement setValues( String query, Object... values ) {
        PreparedStatement pstmt = null;
        try {
            pstmt = this.conn.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            for( int i = 0; i < values.length; i++ ) {
                pstmt.setObject(i+1, values[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    private Object[][] RSToTable( ResultSet rs ) {
        Object table[][] = null;
        try {
            int numRows = 0;
            if (rs.last()) {
                numRows = rs.getRow();
                rs.beforeFirst();
            }

            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();

            table = new Object[numRows + 1][numCols];

            String[] labels = new String[numCols];

            for(int i = 0; i < numCols; i++ ) {
                labels[i] = rsmd.getColumnLabel(i + 1);
            }

            table[0] = labels;

            while( rs.next() ) {
                int rowNum = rs.getRow();
                for(int i = 0; i < numCols; i++ )
                    table[rowNum][i] = rs.getObject(i+1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return table;
    }

}
