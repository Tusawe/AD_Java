# Práctica 1

## Inicializar y cerrar la conexión desde los métodos init y destroy del servlet


### Abrir la conexión desde el método init()

```java
public class Alumno extends HttpServlet {

    Connection conn;

    @Override 
    public void init() throws ServletException {
        Conexion conexion = new Conexion(); 
        this.conn = conexion.getConnection();
    }
```

### Cerrar la conexión desde el método destroy()

```java
@Override
    public void destroy() {
        try {
            this.conn.close();
        } catch (SQLException ex) {

        }
    }
```

### Modificar los DAO para que el servlet les pase la conexión

```java
public class AlumnoDAO {
    // CRUD, findAll, finById, count
    Connection conn;
    public AlumnoDAO(Connection conexion){
        this.conn=conexion;
    }
```

### Modificar el código en el servlet

```java
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<AlumnoPOJO> li_al;
        AlumnoDAO dal;
        
        dal = new AlumnoDAO(this.conn);
        li_al = dal.findAll();
    
```