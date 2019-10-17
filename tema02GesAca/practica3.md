# Práctica 3

## Configurar los parámetros de conexión vía JNDI

### Creamos el recurso en META-INF/context.xml

```xml
<Context>
    <Resource name="jdbc/gestionAcademica" global="jdbc/gestionAcademica" 
    auth="Container" type="javax.sql.DataSource"
    maxTotal="100" maxIdle="30" maxWaitMillis="10000"
    username="damuser23" password="damuser" driverClassName="com.mysql.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/gestion_academica"/>
</Context>
```

### Añadimos el recurso en WEB-INF/web.xml

Para que el recurso esté localizable desde el contexto de la aplicación, hay 
que referenciarlo desde el fichero **web.xml**.

```xml
<web-app>
 .
 .
 .
    <resource-ref>
        <description>DataBase Connection</description>
        <res-ref-name>jdbc/gestionAcademica</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
    </resource-ref>
</web-app>

``` 


