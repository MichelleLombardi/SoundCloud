package mlob.org.objs;

import mlob.org.libs.JSonG;

public class SessionUser {
    private String token = "";
    private int id = 0;
    private String name = "";
    private String lastname = "";

    private JSonG json = new JSonG();

    public SessionUser(String token, Object id, Object name, Object lastname) {
        this.token = token;
        this.id = (int) id;
        this.name = (String) name;
        this.lastname = (String) lastname;

        this.json
            .add("token", this.token)
            .add("id", this.id)
            .add("nombre", this.name)
            .add("apellido", this.lastname);
    }

    public String getToken() { return this.token; }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public String getLastname() { return this.lastname; }

    public JSonG getJson() { return this.json; }

}
