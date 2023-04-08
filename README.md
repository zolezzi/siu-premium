# Bienvenido a UnqPremium!

**UnqPremium** es un sistema informático utilizado para la gestión de los procesos académicos y administrativos. Este sistema permite a los estudiantes acceder a información relevante sobre su carrera, como las materias que deben cursar, sus calificaciones, horarios de clase, entre otros aspectos. También permite a los docentes registrar calificaciones, gestionar el plan de estudios y otras tareas administrativas.


## Descripción

**UnqPremium** Es desarrollado con Java 17 en su backend y con Angular v15 en su frontend. Además utiliza otros frameworks/tecnologías como Spring Boot, Docker, TypeScript, Gradle, Flyway, MariaDB.

## Instalación

Para la instalación del **backend** debemos tener instalado **Java** en nuestra máquina si no lo tienes podes bajar acá:  [link](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), también debemos tener **Gradle** si tenes windows o linux podes ver este: [tutorial](https://gradle.org/install/)  el proyecto cuenta con una base de datos dockerizada lo cual deberán tener docker instalado en su ambiente: [link windows](https://docs.docker.com/desktop/install/windows-install/) o [link Linux](https://docs.docker.com/engine/install/ubuntu/)

- Se debe ejecutar en la raíz del proyecto el comando ```gradle clean build```
- Luego debemos dirigirnos a ```unq-premium\unq-premium-docker\mariadb```
- Aquí debemos crear la carpeta ```data``` y dentro también debemos crear la carpeta ```config``` 
- Procedemos a ejecutar el comando de docker ```docker-compose up -d```
- Luego debemos entrar dentro del contenedor ```docker exec -it mariadb_container bash```
- Luego debemos crear una base de datos ejemplo: [link](https://dev.mysql.com/doc/refman/8.0/en/creating-database.html)
- Debemos otorgar permisos y privilegios al usuario: [link](https://dev.mysql.com/blog-archive/how-to-grant-privileges-to-users-in-mysql-80/)
- Una vez creado el **user, password** y **schema database** debemos configurar el yml del proyecto: [application.yml](https://github.com/zolezzi/unq-premium/blob/main/src/main/resources/application.yml)
- Para finalizar debemos levantar el proyecto backend con el comando ```gradle clean package```
-  Dirigirse a [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para ver documentación de la API Rest

Para la instalación del **frontend** debemos tener instalado **NodeJS** podes descargarlo [acá](https://nodejs.org/es/download) También es recomendable tener **Angular CLI** podes instalarlo desde [acá](https://angular.io/cli)

- Debemos dirigirnos a ```unq-premium\frontend\webapp```
- Ejecutamos el comando ```npm install```
- Levantamos el servicio de angular para eso ejecutamos el comando ```npm start```
- Por último nos dirigimos a [http://localhost:4200/](http://localhost:4200/) 

## Licencia

La Licencia Pública General de GNU versión 3 (GPL-3.0) es una licencia de software de código abierto que fue creada por la Free Software Foundation (FSF). La GPL-3.0 es una de las licencias más populares y utilizadas para proyectos de software libre debido a sus términos y condiciones. Aquí hay algunas razones por las que puedes considerar utilizar la licencia GPL-3.0
1.  Protección de derechos de autor: La GPL-3.0 protege los derechos de autor del autor original del software y asegura que el software seguirá siendo libre y de código abierto.
    
2.  Copyleft: La GPL-3.0 es una licencia copyleft, lo que significa que cualquier persona que distribuya el software modificado o derivado también debe hacerlo bajo los mismos términos y condiciones de la GPL-3.0. Esto asegura que el software se mantenga libre y de código abierto incluso si se modifican o distribuyen versiones modificadas.
    
3.  Comunidad de código abierto: La GPL-3.0 es ampliamente utilizada en la comunidad de código abierto, lo que significa que es más fácil para otros desarrolladores y usuarios entender y contribuir a tu proyecto si utilizas la misma licencia que otras herramientas de código abierto populares.
    
4.  Compatibilidad con otras licencias de código abierto: La GPL-3.0 es compatible con muchas otras licencias de código abierto, lo que significa que puedes combinar código de diferentes proyectos de código abierto y aún así cumplir con los términos y condiciones de la GPL-3.0.
