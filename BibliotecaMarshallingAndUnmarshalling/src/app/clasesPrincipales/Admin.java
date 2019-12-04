package app.clasesPrincipales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "admin")
public class Admin extends Usuario{

    public Admin(String id, String username, String password) {
        super(id, username, password);
    }

    public Admin() {
    }

    

}