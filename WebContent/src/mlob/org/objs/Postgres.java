package mlob.org.objs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Postgres {
	
	private String host = "localhost";
	private String user = "postgres";
	private String pass = null;
	private String db = null;
	private int port = 5433;

	private Connection conn = null;

	public Postgres() {
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Postgres(String host, String user, String pass, String db, int port) {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.db = db;
		this.port = port;
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Postgres(String user, String pass, String db, int port) {
		this.user = user;
		this.pass = pass;
		this.db = db;
		this.port = port;
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Postgres(String user, String pass, String db) {
		this.user = user;
		this.pass = pass;
		this.db = db;
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Postgres(String host, String user, String pass, String db) {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.db = db;
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//
	
	public Postgres(String pass, String db, int port) {
		this.pass = pass;
		this.db = db;
		this.port = port;
		try {
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Postgres(String pass, String db) {
		this.pass = pass;
		this.db = db;
		try {
            Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void createConnection() {
		try {
			String connString = "jdbc:postgresql://";
			this.conn = DriverManager.getConnection(connString +
				this.host + ":" + 
				this.port + "/" + 
				this.db, 
				this.user, 
				this.pass
			);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
			
	}
	
	public void simpleQuery( String query ) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void simpleQuery( String query, String data[][] ) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			String valuesType[] = data[0];
			String values[] = data[1];
			
			for( int i = 0; i < values.length; i++ ) {
				String type = valuesType[i];
				switch( type.toLowerCase() ) {
					case "string":
						pstmt.setString(i+1, values[i]);
						break;
						
					case "int":
						pstmt.setInt(i+1, Integer.parseInt(values[i]));
						break;
						
					case "long":
						pstmt.setLong(i+1, Long.parseLong(values[i]));
						break;
						
					case "float":
						pstmt.setFloat(i+1, Float.parseFloat(values[i]));
						break;
				}
			
			} // ~for

			
			pstmt.execute();
			
		} // ~try
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[][] returnQuery( String query ) {
		String table[][] = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(query);
			
			table = RSToTable(rs);
			
		} // ~try
		catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	public String[][] returnQuery( String query, String data[][] ) {
		String table[][] = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			String valuesType[] = data[0];
			String values[] = data[1];
			
			for( int i = 0; i < values.length; i++ ) {
				String type = valuesType[i];
				switch( type.toLowerCase() ) {
					case "string":
						pstmt.setString(i+1, values[i]);
						break;
						
					case "int":
						pstmt.setInt(i+1, Integer.parseInt(values[i]));
						break;
						
					case "long":
						pstmt.setLong(i+1, Long.parseLong(values[i]));
						break;
						
					case "float":
						pstmt.setFloat(i+1, Float.parseFloat(values[i]));
						break;
				}
			
			} // ~for
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			table = RSToTable(rs);
			
		} // ~try
		catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	private String[][] RSToTable( ResultSet rs ) {
		String table[][] = null;
		try {
			int numRows = 0;
			if (rs.last()) {
				numRows = rs.getRow();
				rs.beforeFirst();
			}
			ResultSetMetaData rsmd = rs.getMetaData();
	        int numCols = rsmd.getColumnCount();
				        
	        table = new String[numRows + 1][numCols];
	        
	        for(int i = 0; i < numCols; i++ ) {
	        	table[0][i] = rsmd.getColumnLabel(i + 1);
	        }
	        
			while( rs.next() ) {
				int rowNum = rs.getRow();
				
				for(int i = 0; i < numCols; i++ ) {
					String label = table[0][i];
					String type = rsmd.getColumnTypeName(i+1);
					String value = type.equals("varchar") ? 	  // Si el tipo de dato es varchar
							this.dblQuotes(rs.getString(label)) : // Guardamos el valor con comillas dobles
							rs.getString(label);				  // Si no entonces solo guardamos el valor
					
					table[rowNum][i] = value;
				} // ~for
			} // ~while
		} // ~try 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return table;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setDb(String db) {
		this.db = db;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	private String dblQuotes( String str ) {
		return "\"" + str + "\"";
	}
	
	public String getPrettyJson( String json ) {
		String jsonString = json;
	    int tabCount = 0;
	    StringBuffer prettyPrintJson = new StringBuffer();
	    String lineSeparator = "\r\n";
	    String tab = "  ";
	    boolean ignoreNext = false;
	    boolean inQuote = false;

	    char character;

	    /* Loop through each character to style the output */
	    for (int i = 0; i < jsonString.length(); i++) {

	        character = jsonString.charAt(i);

	        if (inQuote) {

	            if (ignoreNext) {
	                ignoreNext = false;
	            } else if (character == '"') {
	                inQuote = !inQuote;
	            }
	            prettyPrintJson.append(character);
	        } else {

	            if (ignoreNext ? ignoreNext = !ignoreNext : ignoreNext);

	            switch (character) {

	            case '[':
	                ++tabCount;
	                prettyPrintJson.append(character);
	                prettyPrintJson.append(lineSeparator);
	                printIndent(tabCount, prettyPrintJson, tab);
	                break;

	            case ']':
	                --tabCount;
	                prettyPrintJson.append(lineSeparator);
	                printIndent(tabCount, prettyPrintJson, tab);
	                prettyPrintJson.append(character);
	                break;

	            case '{':
	                ++tabCount;
	                prettyPrintJson.append(character);
	                prettyPrintJson.append(lineSeparator);
	                printIndent(tabCount, prettyPrintJson, tab);
	                break;

	            case '}':
	                --tabCount;
	                prettyPrintJson.append(lineSeparator);
	                printIndent(tabCount, prettyPrintJson, tab);
	                prettyPrintJson.append(character);
	                break;

	            case '"':
	                inQuote = !inQuote;
	                prettyPrintJson.append(character);
	                break;

	            case ',':
	                prettyPrintJson.append(character);
	                prettyPrintJson.append(lineSeparator);
	                printIndent(tabCount, prettyPrintJson, tab);
	                break;

	            case ':':
	                prettyPrintJson.append(character + " ");
	                break;

	            case '\\':
	                prettyPrintJson.append(character);
	                ignoreNext = true;
	                break;

	            default:
	                prettyPrintJson.append(character);
	                break;
	            }
	        }
	    }

	    return prettyPrintJson.toString();
	}

	private void printIndent(int count, StringBuffer stringBuffer, String indent) {
	    for (int i = 0; i < count; i++) {
	        stringBuffer.append(indent);
	    }
	}
	
}
