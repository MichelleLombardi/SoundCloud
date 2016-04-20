package mlob.org.libs;

import java.sql.Timestamp;

public class JSonG {
    private String body = "";

    public JSonG add( Object name, Object value ) {
        if( value instanceof String || value instanceof Timestamp)
            value = "\"" + value +"\"";
        else if( value instanceof JSonG )
            value = ((JSonG) value).toString();

        if( this.body.equals("") ) {
            this.body += "\"" + name + "\":" + value;
        }
        else {
            this.body += ",\"" + name + "\":" + value;
        }
        return this;
    }

    public JSonG add( Object name, Object... value ) {
        int length = value.length;
        if( this.body.equals("") )
            this.body += "\"" + name + "\":[";
        else
            this.body += ",\"" + name + "\":[";

        for( int i = 0; i < length; i++ ) {
            Object val = value[i];

            if( val instanceof String )
                val = "\"" + val +"\"";
            else if( val instanceof JSonG )
                val = ((JSonG) val).toString();

            this.body += val;
            if( i+1 != length )
                this.body += ",";
        }

        this.body += "]";

        return this;
    }

    public JSonG add( String name, Object table[][] ) {
        int numRows = table.length;
        int numCols = table[0].length;
        Object[] labels = table[0];

        if( this.body.equals("") )
            this.body += "\"" + name + "\":[";
        else
            this.body += ",\"" + name + "\":[";

        for( int i = 1; i < numRows; i++ ) {
            JSonG tmp = new JSonG();

            for( int j = 0; j < numCols; j++ ) {
                tmp.add(labels[j], table[i][j]);
            }

            this.body += tmp.toString();

            if( i < numRows - 1)
                this.body += ",";
        }

        this.body += "]";

        return this;
    }

    @Override
    public String toString() {
        return "{" + this.body + "}";
    }

    public String getPrettyJson() {
        String jsonString = this.toString();
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
                        prettyPrintJson.append(character).append(" ");
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