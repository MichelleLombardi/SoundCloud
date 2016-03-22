package mlob.org.objs;

public class BrutalSon {
	private String body = "";

	public BrutalSon add( String name, String value ) {
		if( this.body.equals("") )
			this.body += "\"" + name + "\":\"" + value + "\"";
		else
			this.body += ",\"" + name + "\":\"" + value + "\"";	
		return this;
	}

	public BrutalSon add( String name, String value[] ) {
		int length = value.length;
		if( this.body.equals("") )
			this.body += "\"" + name + "\":[";
		else
			this.body += ",\"" + name + "\":[";

		for( int i = 0; i < length; i++ ) {
			this.body += "\"" + value[i] + "\"";
			if( i+1 != length )
				this.body += ",";
		}

		this.body += "]";
		
		return this;
	}

	public BrutalSon add( String name, int value ) {
		if( this.body.equals("") ) {
			this.body += "\"" + name + "\":" + value + "";
		}
		else {
			this.body += ",\"" + name + "\":" + value + "";	
		}
		return this;
	}

	public BrutalSon add( String name, int value[] ) {
		int length = value.length;
		if( this.body.equals("") )
			this.body += "\"" + name + "\":[";
		else
			this.body += ",\"" + name + "\":[";

		for( int i = 0; i < length; i++ ) {
			this.body += value[i];
			if( i+1 != length )
				this.body += ",";
		}

		this.body += "]";
		
		return this;
	}

	public BrutalSon add( String name, float value ) {
		if( this.body.equals("") ) {
			this.body += "\"" + name + "\":" + value + "";
		}
		else {
			this.body += ",\"" + name + "\":" + value + "";	
		}
		return this;
	}

	public BrutalSon add( String name, float value[] ) {
		int length = value.length;
		if( this.body.equals("") )
			this.body += "\"" + name + "\":[";
		else
			this.body += ",\"" + name + "\":[";

		for( int i = 0; i < length; i++ ) {
			this.body += value[i];
			if( i+1 != length )
				this.body += ",";
		}

		this.body += "]";
		
		return this;
	}

	public BrutalSon add( String name, Double value ) {
		if( this.body.equals("") ) {
			this.body += "\"" + name + "\":" + value + "";
		}
		else {
			this.body += ",\"" + name + "\":" + value + "";	
		}
		return this;
	}

	public BrutalSon add( String name, Double value[] ) {
		int length = value.length;
		if( this.body.equals("") )
			this.body += "\"" + name + "\":[";
		else
			this.body += ",\"" + name + "\":[";

		for( int i = 0; i < length; i++ ) {
			this.body += value[i];
			if( i+1 != length )
				this.body += ",";
		}

		this.body += "]";
		
		return this;
	}

	public BrutalSon add( String name, long value ) {
		if( this.body.equals("") ) {
			this.body += "\"" + name + "\":" + value + "";
		}
		else {
			this.body += ",\"" + name + "\":" + value + "";	
		}
		return this;
	}

	public BrutalSon add( String name, long value[] ) {
		int length = value.length;
		if( this.body.equals("") )
			this.body += "\"" + name + "\":[";
		else
			this.body += ",\"" + name + "\":[";

		for( int i = 0; i < length; i++ ) {
			this.body += value[i];
			if( i+1 != length )
				this.body += ",";
		}

		this.body += "]";
		
		return this;
	}

	public BrutalSon add( String name, BrutalSon value ) {
		if( this.body.equals("") ) {
			this.body += "\"" + name + "\":" + value.getJson();
		}
		else {
			this.body += ",\"" + name + "\":" + value.getJson();	
		}
		return this;
	}

	public BrutalSon add( String name, BrutalSon value[] ) {
		int length = value.length;
		if( this.body.equals("") )
			this.body += "\"" + name + "\":[";
		else
			this.body += ",\"" + name + "\":[";

		for( int i = 0; i < length; i++ ) {
			this.body += value[i].getJson();
			if( i+1 != length )
				this.body += ",";
		}

		this.body += "]";
		
		return this;
	}

	public String getJson() {
		return "{" + this.body + "}";
	}

}