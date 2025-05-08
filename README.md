# Proyecto de Gestión de Biblioteca

## Descripción
Este proyecto es una aplicación de gestión de biblioteca desarrollada con Spring Boot. Permite administrar libros, autores, editoriales, préstamos de libros y clientes.

## Estructura del Proyecto
El proyecto sigue una arquitectura de capas típica de Spring Boot:

- `controller`: Maneja las solicitudes HTTP y define los endpoints de la API.
- `model`: Define las entidades JPA que representan las tablas de la base de datos.
- `repository`: Contiene interfaces que extienden JpaRepository para operaciones CRUD.
- `service`: Implementa la lógica de negocio y actúa como intermediario entre los controladores y los repositorios.

## Entidades y Relaciones

### Libro
- Atributos: id, titulo, isbn, añoPublicacion, autor, fechaPrestamo
- Relaciones:
  - Muchos a Uno con Autor
  - Muchos a Uno con Editorial
  - Uno a Muchos con Prestamo

### Autor
- Atributos: id, nombre, nacionalidad, libros
- Relaciones:
  - Uno a Muchos con Libro
  - Muchos a muchos con editorial

### Editorial
- Atributos: id, nombre, alta
- Relaciones:
  - Uno a Muchos con Libro

### Prestamo
- Atributos: id, fechaPrestamo, fechaDevolucion, alta
- Relaciones:
  - Muchos a Uno con Libro
  - Muchos a Uno con Cliente


### Cliente
- Atributos: id, nombre, apellido, email, prestamos
- Relaciones:
  - Uno a Muchos con Prestamo

## Endpoints de la API

### LibroController
- GET `/api/libros`: Obtiene todos los libros
- GET `/api/libros/{id}`: Obtiene un libro por ID
- POST `/api/libros`: Crea un nuevo libro
- PUT `/api/libros/{id}`: Actualiza un libro existente
- DELETE `/api/libros/{id}`: Elimina un libro

### AutorController
- GET `/api/autores`: Obtiene todos los autores
- GET `/api/autores/{id}`: Obtiene un autor por ID
- POST `/api/autores`: Crea un nuevo autor
- PUT `/api/autores/{id}`: Actualiza un autor existente
- DELETE `/api/autores/{id}`: Elimina un autor

### EditorialController
- GET `/api/editoriales`: Obtiene todas las editoriales
- GET `/api/editoriales/{id}`: Obtiene una editorial por ID
- POST `/api/editoriales`: Crea una nueva editorial
- PUT `/api/editoriales/{id}`: Actualiza una editorial existente
- DELETE `/api/editoriales/{id}`: Elimina una editorial

### PrestamoController
- GET `/api/prestamos`: Obtiene todos los préstamos
- GET `/api/prestamos/{id}`: Obtiene un préstamo por ID
- POST `/api/prestamos`: Crea un nuevo préstamo
- PUT `/api/prestamos/{id}`: Actualiza un préstamo existente
- DELETE `/api/prestamos/{id}`: Elimina un préstamo

### ClienteController
- GET `/api/clientes`: Obtiene todos los clientes
- GET `/api/clientes/{id}`: Obtiene un cliente por ID
- POST `/api/clientes`: Crea un nuevo cliente
- PUT `/api/clientes/{id}`: Actualiza un cliente existente
- DELETE `/api/clientes/{id}`: Elimina un cliente

## Tecnologías Utilizadas
- Java 24
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Configuración
La configuración de la base de datos se encuentra en el archivo `application.properties`. Asegúrate de configurar correctamente los siguientes parámetros:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```