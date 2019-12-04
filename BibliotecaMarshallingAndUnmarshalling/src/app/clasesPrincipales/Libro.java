package app.clasesPrincipales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "libro")
public class Libro{

    @XmlAttribute
    private Integer id;
    @XmlElement
    private String isbn;
    @XmlElement
    private String paginas;
    @XmlElement
    private String editorial;
    @XmlElement
    private String titulo;
    @XmlElement
    private String anioPublicacion;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Libro(Integer id, String isbn, String paginas, String editorial, String titulo, String anioPublicacion) {
        this.id = id;
        this.isbn = isbn;
        this.paginas = paginas;
        this.editorial = editorial;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    public Libro(){}

}