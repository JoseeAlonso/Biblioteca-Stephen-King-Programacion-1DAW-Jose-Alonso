# Biblioteca-Stephen-King-Programacion-1DAW-Jose-Alonso
Proyecto 2/2 Programación 1ºDAW Jose Alonso

Aplicación de consola desarrollada en **Java** que permite gestionar una biblioteca de libros obtenidos desde una API externa, con funcionalidades de búsqueda y gestión de favoritos mediante persistencia de datos.

El objetivo de este proyecto es practicar **Programación Orientada a Objetos**, consumo de **APIs**, manejo de **archivos en Java** y organización del código mediante una arquitectura simple por capas.

## Funcionalidades

- **Importar libros** desde una API externa
- **Buscar libros por endpoint**
- **Buscar libros por ID** dentro de la biblioteca
- **Agregar libros a favoritos**
- **Exportar favoritos** a un archivo local
- **Importar favoritos** previamente guardados
- Manejo de excepciones y validación de entradas del usuario


## Arquitectura del proyecto

El proyecto sigue una estructura organizada separando responsabilidades entre **controllers** y **models**.

### Controllers

- **APIController**  
  Encargado de consumir la API externa y obtener los libros.

- **BibliotecaController**  
  Gestiona la lógica de la biblioteca: búsqueda de libros y gestión de favoritos.

- **FileController**  
  Maneja la persistencia de datos mediante archivos.

### Models

- **Libro** → Representa la entidad libro.
- **Biblioteca** → Contiene las listas de libros y favoritos.

---

## Persistencia de datos

Los libros favoritos se almacenan utilizando **serialización de objetos en Java**.

Archivo generado:


favoritos/favoritos.obj


Tecnologías utilizadas:

- `ObjectOutputStream`
- `ObjectInputStream`
- `Serializable`

Esto permite **guardar y recuperar los favoritos entre ejecuciones del programa**.


## Tecnologías utilizadas

- **Java**
- **Lombok**
- **Java Collections**
- **Serialización de objetos**
- **Consumo de API REST**
- **Programación Orientada a Objetos**

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/JoseeAlonso/Biblioteca-Stephen-King-Programacion-1DAW-Jose-Alonso.git

# Para visualizar:

1. Tener un IDE donde pueda ser ejecutado el desarrollo
2. Abrir el proyecto en el IDE
3. Ejecutar/Run main.java
4. Interactuar por consola


# Posibles errores comunes

1. SDK incompatibles. En uso: Oracle OpenJDK 24.0.2
