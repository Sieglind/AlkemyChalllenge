# Alkemy Challenge - Java Backend – Julio 2022

---

### Autor: Cristian David Moraleda

---

#### Tecnologías
* JDK 15
* Maven 3
* Git
* Spring Boot
* JPA
* Hibernate
* H2
* Spring Security
* JWT
* HTML5
* CSS
* JavaScript
### Funcionalidades:
- **Seguridad**
    - **Registro:**
        - Validación de datos mediante uso de expresiones regulares
        - Verificación de correo y nombre de usuario existente
    - **Autenticación**
    - **Autorización:**
        - Validación de sesión mediante JWT Token
- REST API
    - **Operaciones CRUD para películas y personajes:**
        - Validación de campos ingresados
        - Asociación /personajes y películas/géneros asociadas mediante una relaciona "Many to many"
        - Películas y géneros asociados mediante una relacion "Many to many"
        - Asociación / disociación de películas con personajes
    - Creación de géneros

### Ejecuccion del Proyecto

Para poder ejecutar este proyecto desde su equipo, debera tener instalados Maven y Java 15.0.2 y seguir los siguientes pasos

1. Clonar el proyecto:

<code> $ ht<span>tps://</span>github.com/Sieglind/AlkemyChalllenge.git </code>

2. Ir al directorio del proyecto:

<code> $ cd Alkemy Challenge/ </code>

3. Compilar el proyecto:

<code> $ mvn clean && mvn install -DskipTests -Dspring.profiles.active=local </code>

4. Ejecutar el proyecto:

<code> $ mvn spring-boot:run </code>

---
### Utilidades:
Una vez que el proyecto se encuentre en ejecución, podrá acceder a la base de datos en el siguiente link:
<a href=http://localhost:8080/h2/ terget=_blank >H2 Database</a> (**username**: *testing*, **password**: *challenge*).
Para pruebas y documentación se recomienda usar <a href=https://swagger.io/ target=_blank>Swagger</a>,
accediendo al siguiente link:
<a href=http://localhost:8080/swagger-ui/index.html target=_blank >Swagger-UI</a>.

También tiene a su disposición un rudimentario frontend, que fue creado para testear algunos endpoints, aquí:
<a href=http://localhost:8080/> Alkemy Challenge API - Disney </a>

Los requerimientos técnicos para el desarrollo de este proyecto se pueden consultar
<a href="/Challenge Backend - Java Spring Boot (API).pdf">aqui</a>
