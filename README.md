# springboot-library

Este proyecto es una aplicación de consola desarrollada con Spring Boot que permite buscar libros, listar libros registrados, listar autores registrados, listar autores vivos en un determinado año y listar libros por idioma. La aplicación interactúa con la API de Gutendex para obtener información sobre libros.

## Funcionalidades

1. **Buscar Libro por Título:** Permite buscar un libro por su título utilizando la API de Gutendex y, si no está registrado en la base de datos, lo guarda.
2. **Listar Libros Registrados:** Muestra todos los libros que han sido registrados en la base de datos.
3. **Listar Autores Registrados:** Muestra todos los autores que han sido registrados en la base de datos.
4. **Listar Autores Vivos en un Determinado Año:** Muestra todos los autores que estaban vivos en un año específico.
5. **Listar Libros por Idioma:** Muestra todos los libros registrados que están en un idioma específico.

## Requisitos Previos

- Java 17 o superior
- Maven 3.6.0 o superior

## Instalación

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/oscarmartinez1109/springboot-library.git
    cd tu-repositorio
    ```

2. Construir el proyecto con Maven:
    ```bash
    mvn clean install
    ```

3. Ejecutar la aplicación:
    ```bash
    mvn spring-boot:run
    ```

## Uso

Una vez iniciada la aplicación, se mostrará un menú en la consola con las siguientes opciones:

1. **Buscar Libro por Título:** Introduce el título del libro y la aplicación buscará en la API de Gutendex y mostrará los detalles del primer resultado encontrado.
2. **Listar Libros Registrados:** Muestra una lista de todos los libros que han sido guardados en la base de datos.
3. **Listar Autores Registrados:** Muestra una lista de todos los autores registrados en la base de datos.
4. **Listar Autores Vivos en un Determinado Año:** Introduce un año y se mostrarán todos los autores que estaban vivos en ese año.
5. **Listar Libros por Idioma:** Introduce el código del idioma (es, en, pt) y se mostrarán todos los libros registrados en ese idioma.

## Estructura del Proyecto

- `Principal.java`: Clase principal que contiene el menú y la lógica de la aplicación.
- `ConsumoAPI.java`: Clase de servicio para consumir la API de Gutendex.
- `ConvierteDatos.java`: Clase de servicio para convertir datos.
- `LibroRepository.java`: Interfaz de repositorio para gestionar los datos de los libros.
- `model`: Paquete que contiene las clases de modelo (`Libro`, `Autor`, `Datos`, `DatosLibro`, `DatosAutor`).

## Dependencias

El proyecto utiliza las siguientes dependencias:

- `org.springframework.boot:spring-boot-starter`
- `org.springframework.boot:spring-boot-starter-test`
- `com.fasterxml.jackson.core:jackson-databind`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.postgresql:postgresql`
- `org.springframework.boot:spring-boot-devtools`

Estas dependencias están definidas en el archivo `pom.xml` del proyecto:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.16.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
</dependencies>


## Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios necesarios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube tus cambios a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre una solicitud de pull.

## Contacto

Para cualquier consulta o sugerencia, puedes contactarme a través de:

- GitHub: [oscarmartinez1109](https://github.com/oscarmartinez1109o)
- Email: oskrmartinez1109@gmail.com

---

¡Gracias por usar nuestra aplicación de biblioteca con Spring Boot!
