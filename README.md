**El ejercicio consistió en crear una API utilizando el proyecto de Spring, Spring-Boot, del lenguaje Java.**

**La API esta basada en contener Libros con sus respectivos datos (Título, Autor, Precio, Fecha de lanzamiento). Es un CRUD, con el cual podremos crear, leer, modificar y eliminar libros.**

# Información del proyecto

## Se utilizaron las siguientes herramientas

-   Lenguaje **Java** para el desarrollo
-   **Spring-Boot Data JPA** como ORM para la persistencia
-   **Maven** para gestión de dependencias del proyecto y configuración del mismo
-   Base de datos en memoria **H2**
-   **H2 Console** en el navegador para la administración de la base de datos
-   Librería **Lombok** para reducir porciones de código
-   IDE **Intellij IDEA Community Edition** 2023.1.23
-   **Git** para el versionado
-   **Postman** para probar los endpoints
<br>
# Como probar el proyecto en su equipo
<br>
Es recomendable contar con el IDE **Intellij IDEA Community Edition** 2023.1.23 para abrir el proyecto, sin embargo puede utilizar el de su preferencia (**VSC, Netbeans**.

Es ideal también que tenga conocimiento sobre las líneas del archivo **application.properties**, ya que ahí es donde se realiza la conexión a la base de datos.

Es deseable que ya tenga todo el entorno para desarrollar en JAVA configurado.
<br>
## Pasos a seguir
<br>

-   Descargar el proyecto en su equipo
-   Si está utilizando **Intellij IDEA**, vaya hasta el archivo BooksAPIApplication.java, haga click derecho -> run BooksAPIApplication main()
-   Si el paso anterior no funciona abrir una terminal en la carpeta del proyecto y escriba el siguiente comando:
    
`mvn.cmd spring-boot:run`

-   Si no está utilizando ningún IDE o Editor de Texto, sitúese en la carpeta del proyecto, abra una terminal y escriba el siguiente comando:

`mvn.cmd spring-boot:run`

-   Una vez hecho eso, ya podrá manipular la base de datos en memoria a través del H2 Console: http://localhost:8080/h2-console/ 
Ingresando con el usuario y contraseña especificadas en el archivo **application.properties** del proyecto


-   Para probar los endpoints con los distintos métodos HTTP, puede utilizar **Postman**