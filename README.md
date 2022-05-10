# Project NjCode

#### Url aplicacion: http://localhost:8080/swagger/swagger-ui/index.html#/
##### Instrucciones de despliegue:
* Ejecutar desde un IDE la clase Application, o lanzar desde la consola con maven, mvn springboot:run
* Autenticar desde el swagger con BasicAuth (njcode/njcode)

##Descripción de proyecto
* La arquitectura de este proyecto está basada en arquitectura hexagonal y patrón de arquitectura DDD, centrándome en la organización de carpetas por modelos de dominio.
* La base de datos es en memoria (H2) para simplificar la infraestructura.
* El modelo de dominio es el centro, trabajamos con él a nivel de servicio e implementaciones de repositorio. A parte tenemos un módelo para la implementación de JPA, y modelos RQ y RS para trabajar con la API expuesta.
* A parte de las excepciones de javax validation, tenemos excepciones propias para el modelo de dominio.
* Los modelos de dominio son no anémicos, incluyendo validaciones y namings constructor, para no permitir que se instancie el modelo a no ser que sea a través de los métodos definidos, asegurando solidez de éste.
* Se ha usado el patrón adapter para trabajar con JPARepository, para no acoplarnos a la solución de JPA, y permitir trabajar con la interfaz del repositorio, abriendo posibilidades a otras implementaciones futuras, cumpliendo principio open-closed.
* Tenemos validaciones a nivel de modelo de dominio, del DTO, y lógicas dentro de las implementaciones del repositorio.
* Se ha añadido un controller manejador de excepciones genéricos, se podría personalizar por excepciones y mostrar un http status u otro dependiendo del tipo de excepción recibida.
* Se ha añadido también securización con Basic Auth para las peticiones.
* Para los bonus de los clientes, la inserción se hace a través de eventos con un publisher y un listener, considerando que este proceso no tendría por qué ser síncrono y maximizar recursos en el proceso de entre de films.
* Trabajamos con swagger, la última versión de OpenApi. Hacemos uso de anotaciones en las Apis.
* Hay unos pequeños ejemplos de SLF4J de lombok en los servicios Rest.

##Extras
Si es necesario se puede hacer pero como comentásteis, entiendo que la pruebas es para hacer un poco la idea, pero podríamos haber añadido para completar la prueba:
* Añadir un pipeline simple para que trabaje con Jenkins, y definir un ciclo de vida con los pasos por defecto de build, test, compile, dependency-check, sonarqube, etc.
* Un trabajo más complejo de logs, para poder complementarse correctamente con Kibana y demás aplicaciones de métricas.
* Profiles de Springboot, para trabajar con PostgreSql por ejemplo en entornos de DES/INT.
* Makefile, con tareas simples, para despliegue, test, etc. Con el fin de simplificar la vida al desarrollador para desplegar el proyecto.

Un saludo
###Javier Tercedor Gijón