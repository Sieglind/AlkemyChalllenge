> # Alkemy Challenge - Java Backend - Julio 2022
>> ### Autor: Cristian David Moraleda
---
#### Tecnologias
* JDK 15
* Maven 3
* Git
* Spring Boot
* JPA
* Hibernate
* H2
* Spring Security
* JWT
### Funcionalidades:
- **Seguridad**
    - **Registro:**
      - Validacion de datos mediante uso de expresiones regulares 
      - Verificacion de correo y nombre de usuario existente
    - **Autenticacion**
    - **Autorizacion:**
      - Validacion de sesion mediante JWT Token
- REST API
  - **Operaciones CRUD para peliculas y personajes:**
    - Validacion de campos ingresados 
    - Peliculas/personajes y peliculas/generos asociadas mediante una relaciona "Many to many"
    - Peliculas y generos asociados mediante una relacion "Many to many"
    - Asociacion / disociasion de peliculas con personajes
  - Creacion de generos

---
### Utilidades:
Una vez que el proyecto se encuentre en ejecucion, podra acceder a la base de datos en el siguiente link:
<a href=http://localhost:8080/h2/ terget=_blank >H2 Database</a> (**username**: *testing*, **password**: *challenge*).
Para pruebas y documentacion se recomienda usar <a href=https://swagger.io/ target=_blank>Swagger</a>,
accediendo al siguiente link:
<a href=http://localhost:8080/swagger-ui/index.html target=_blank >Swagger-UI</a>.

Tambien tiene a su disposicion un rudimentario frontend, que fue creado para testear algunos endpoints, aqui:
<a href=http://localhost:8080/> Alkemy Challenge API - Disney </a>

Los requerimientos tecnicos para el desarrollo de este proyecto se pueden consultar 
<a href="/Challenge Backend - Java Spring Boot (API).pdf">aqui</a>
