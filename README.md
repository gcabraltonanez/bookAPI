Books API – Spring Boot 3 / Java 21
====================================

API REST para gestionar libros (CRUD) con Spring Boot 3.2, Java 21 y base en memoria H2.

Stack y características
-----------------------
- Java 21
- Spring Boot 3.2.5 (Web, Data JPA, Validation)
- H2 en memoria (con consola habilitada en dev)
- Lombok para reducir boilerplate
- Maven para build y dependencias

Modelo
------
- `Book`: `id`, `title`, `author`, `price` (`BigDecimal`), `releaseDate` (`LocalDate dd-MM-yyyy`)
- Validaciones: título/autores obligatorios, autor con letras/espacios/apóstrofes/guiones, precio >= 0.01, fecha obligatoria.

Endpoints principales
---------------------
- `GET /books/all` – Lista todos los libros.
- `POST /books/create` – Crea un libro.
- `PUT /books/update` – Actualiza un libro existente por `id`.
- `GET /books/find?title=...` – Busca por título.
- `GET /books/findOne/{id}` – Busca por id.
- `DELETE /books/delete/{bookId}` – Elimina por id.
- `DELETE /books/deleteAll` – Borra todos (si la lista no está vacía).

Manejo de errores
-----------------
- Validaciones retornan 400 con detalles por campo.
- No encontrado retorna 404 con mensaje claro.
- Título duplicado retorna 409.
- Respuestas de mensaje usan DTO `ApiMessage`; errores usan `ApiError`.

Cómo ejecutar
-------------
1) Requisitos: JDK 21, Maven (o el wrapper `mvnw` incluido).
2) Instalar dependencias y correr:
```
./mvnw spring-boot:run
```
3) Consola H2 (dev): `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:librosdb`
   - Usuario/clave: definidos en `src/main/resources/application.properties`

Pruebas
-------
- Ejecutar suite:
```
./mvnw test
```
- Incluye prueba básica de repositorio (`BookRepositoryTest`) para guardar y buscar por título.

Notas
-----
- Endpoints devuelven entidades directamente; si quieres DTOs de salida, se pueden agregar fácilmente.
- Configuración sensible (credenciales) debe gestionarse por perfiles; la consola H2 está pensada solo para desarrollo.
