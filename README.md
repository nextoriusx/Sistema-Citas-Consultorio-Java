# Sistema de Citas para Consultorio Clínico

Proyecto desarrollado en **Java** para la materia **Computación en Java** de la **Universidad Tecmilenio**.

El sistema simula la administración básica de citas médicas de un consultorio clínico. Permite registrar doctores y pacientes, crear citas relacionando ambas entidades, consultar la información almacenada y conservar los datos mediante archivos CSV.

---

## Objetivo

Desarrollar una aplicación de consola utilizando Programación Orientada a Objetos en Java para administrar información básica de un consultorio médico.

Durante el desarrollo se aplican conceptos de:

- Clases y objetos.
- Herencia.
- Encapsulamiento.
- Interfaces.
- Colecciones.
- Manejo de archivos.
- Persistencia de información.
- Manejo de excepciones.
- Validación de datos.
- Control de versiones con Git y GitHub.

---

## Funcionalidades principales

La aplicación permite:

1. Iniciar sesión como administrador.
2. Dar de alta doctores.
3. Dar de alta pacientes.
4. Crear citas médicas.
5. Relacionar cada cita con un doctor y un paciente.
6. Consultar doctores, pacientes y citas registradas.
7. Guardar información en archivos CSV.
8. Recuperar automáticamente los datos al iniciar nuevamente el programa.
9. Validar identificadores, fechas y horas.
10. Evitar registros con identificadores duplicados.
11. Controlar entradas incorrectas sin finalizar inesperadamente la aplicación.

---

## Menú principal

Después de iniciar sesión, el sistema presenta el siguiente menú:

```text
========== MENÚ PRINCIPAL ==========
1. Dar de alta doctor
2. Dar de alta paciente
3. Crear cita
4. Consultar información
5. Salir
====================================
```

---

## Inicio de sesión

Para fines académicos y de demostración, el sistema utiliza las siguientes credenciales:

```text
Usuario: admin
Contraseña: 1234
```

Si las credenciales son incorrectas, el sistema informa el error y permite realizar un nuevo intento.

---

## Estructura de identificadores

El sistema utiliza identificadores con un formato definido para cada entidad.

### Doctor

Formato:

```text
DOC + tres números
```

Ejemplos:

```text
DOC001
DOC002
DOC003
```

### Paciente

Formato:

```text
PAC + tres números
```

Ejemplos:

```text
PAC001
PAC002
PAC003
```

### Cita

Formato:

```text
CIT + tres números
```

Ejemplos:

```text
CIT001
CIT002
CIT003
```

El programa valida automáticamente estos formatos antes de registrar la información.

---

## Validación de fecha

Las citas utilizan el formato:

```text
DD/MM/AAAA
```

Ejemplo válido:

```text
15/10/2026
```

Además de validar el formato, el programa comprueba que la fecha exista realmente.

Por ejemplo:

```text
31/02/2026
```

es rechazada porque febrero no puede tener 31 días.

---

## Validación de hora

La hora utiliza el formato de 24 horas:

```text
HH:MM
```

Ejemplo válido:

```text
14:30
```

Un valor como:

```text
25:70
```

es rechazado por el sistema.

---

## Manejo de errores

La aplicación incluye validaciones para evitar que una entrada incorrecta provoque la finalización inesperada del programa.

Se controlan, entre otros, los siguientes casos:

- Opciones no válidas del menú.
- Entradas no numéricas.
- Identificadores con formato incorrecto.
- Identificadores duplicados.
- Campos vacíos.
- Fechas inexistentes.
- Horas inválidas.
- Doctor inexistente al crear una cita.
- Paciente inexistente al crear una cita.
- Errores durante la lectura o escritura de archivos.

Después de detectar un error, el sistema muestra un mensaje y permite continuar con la operación.

Ejemplo:

```text
Fecha (DD/MM/AAAA): 31/02/2026

Error: la fecha ingresada no es válida.
Use el formato DD/MM/AAAA.
Ejemplo válido: 15/10/2026
```

---

## Persistencia de información

La información registrada se almacena localmente mediante archivos CSV dentro de la carpeta:

```text
db/
```

El sistema utiliza tres archivos:

```text
doctores.csv
pacientes.csv
citas.csv
```

Los archivos son creados automáticamente cuando no existen.

Al iniciar nuevamente la aplicación, la información almacenada es recuperada y cargada en memoria.

---

## Archivo doctores.csv

Formato:

```text
ID,Nombre,Especialidad
```

Ejemplo:

```text
DOC001,Laura Hernandez,Medicina General
```

---

## Archivo pacientes.csv

Formato:

```text
ID,Nombre
```

Ejemplo:

```text
PAC001,Carlos Ramirez
```

---

## Archivo citas.csv

Formato:

```text
ID,Fecha,Hora,Motivo,IdDoctor,IdPaciente
```

Ejemplo:

```text
CIT001,03/10/2026,10:30,Consulta general,DOC001,PAC001
```

La cita almacena los identificadores del doctor y del paciente para poder reconstruir posteriormente la relación entre los objetos.

---

## Protección de datos locales

Los archivos CSV generados durante la ejecución no se almacenan en el repositorio remoto.

La carpeta `db` contiene un archivo:

```text
.gitignore
```

configurado para conservar la estructura de la carpeta, pero excluir:

```text
doctores.csv
pacientes.csv
citas.csv
```

De esta manera, los datos generados durante las pruebas permanecen únicamente en el entorno local.

---

## Estructura del proyecto

```text
SistemaCitasConsultorio/
│
├── db/
│   └── .gitignore
│
├── src/
│   ├── Administrador.java
│   ├── Cita.java
│   ├── Doctor.java
│   ├── Main.java
│   ├── Paciente.java
│   ├── Persistencia.java
│   ├── Persona.java
│   ├── RepositorioCSV.java
│   └── SistemaCitas.java
│
├── .gitignore
├── README.md
└── SistemaCitasConsultorio.iml
```

---

## Descripción de las clases

### Persona

Clase abstracta que contiene los atributos comunes utilizados por doctores y pacientes:

```text
id
nombreCompleto
```

Sirve como clase base para `Doctor` y `Paciente`.

---

### Doctor

Hereda de la clase `Persona` y agrega el atributo:

```text
especialidad
```

Representa a los médicos disponibles en el sistema.

---

### Paciente

Hereda de la clase `Persona`.

Representa a las personas que pueden ser relacionadas con una cita médica.

---

### Cita

Representa una cita médica y contiene:

```text
id
fecha
hora
motivo
doctor
paciente
```

Cada cita conserva una relación directa con un objeto `Doctor` y un objeto `Paciente`.

---

### Administrador

Gestiona las credenciales utilizadas para autenticar el acceso al sistema.

---

### SistemaCitas

Contiene la lógica principal de la aplicación.

Administra las colecciones de:

```text
Doctor
Paciente
Cita
```

También realiza operaciones de:

```text
registro
búsqueda
consulta
validación
guardado
carga de información
```

---

### Persistencia

Interfaz que define las operaciones necesarias para almacenar y recuperar información:

```text
guardarDatos()
cargarDatos()
```

---

### RepositorioCSV

Implementa la interfaz `Persistencia`.

Es responsable de:

```text
crear archivos CSV
guardar doctores
guardar pacientes
guardar citas
cargar doctores
cargar pacientes
reconstruir citas
relacionar doctores y pacientes
manejar errores de archivos
```

---

### Main

Contiene el método principal:

```java
public static void main(String[] args)
```

También contiene el menú interactivo y las validaciones de entrada utilizadas por el usuario.

---

## Flujo general del sistema

```text
Inicio
  │
  ▼
Carga de archivos CSV
  │
  ▼
Inicio de sesión
  │
  ▼
Menú principal
  │
  ├── Dar de alta doctor
  │
  ├── Dar de alta paciente
  │
  ├── Crear cita
  │       │
  │       ├── Buscar doctor
  │       └── Buscar paciente
  │
  ├── Consultar información
  │
  └── Salir
          │
          ▼
   Guardar información
          │
          ▼
         Fin
```

---

## Ejemplo de una cita registrada

```text
===== CITAS =====

ID Cita: CIT001
Fecha: 03/10/2026
Hora: 10:30
Motivo: Consulta general
Doctor: Laura Hernandez
Especialidad: Medicina General
Paciente: Carlos Ramirez
```

---

## Tecnologías utilizadas

```text
Java JDK 11
IntelliJ IDEA
Git
GitHub
Archivos CSV
```

---

## Control de versiones

El proyecto utiliza Git y GitHub para controlar los cambios realizados durante el desarrollo.

Las ramas principales son:

```text
master
develop
```

Las funcionalidades se desarrollan utilizando ramas independientes.

Entre las ramas creadas durante el proyecto se encuentran:

```text
feature/persistencia-csv
feature/validaciones-errores
feature/documentacion-build
```

El flujo utilizado es:

```text
feature
   │
   ▼
develop
   │
   ▼
master
```

Las funcionalidades terminadas se integran primero a `develop`.

Después de validar la versión completa del sistema, la versión estable se integra a `master`.

---

## Persistencia implementada

La rama:

```text
feature/persistencia-csv
```

se utilizó para implementar el almacenamiento y recuperación de doctores, pacientes y citas mediante archivos CSV.

Posteriormente fue integrada a:

```text
develop
```

---

## Validaciones y manejo de errores

La rama:

```text
feature/validaciones-errores
```

se utilizó para implementar validaciones de:

```text
identificadores
fechas
horas
campos obligatorios
entradas incorrectas
```

Posteriormente fue integrada a:

```text
develop
```

---

## Ejecución desde IntelliJ IDEA

El programa puede ejecutarse desde:

```text
src/Main.java
```

Ejecutando el método:

```java
public static void main(String[] args)
```

IntelliJ IDEA compila y ejecuta la aplicación utilizando el JDK configurado en el proyecto.

---

## Archivo JAR ejecutable

El proyecto fue compilado y probado también como una aplicación ejecutable en formato JAR.

El archivo generado es:

```text
SistemaCitasConsultorio.jar
```

Para generar manualmente el ejecutable desde PowerShell se utilizaron los siguientes comandos:

```powershell
New-Item -ItemType Directory -Force .\out\jar-build\classes

$archivos = Get-ChildItem .\src\*.java | ForEach-Object { $_.FullName }

javac -encoding UTF-8 -d .\out\jar-build\classes $archivos

jar cfe .\out\jar-build\SistemaCitasConsultorio.jar Main -C .\out\jar-build\classes .
```

Para ejecutarlo desde la raíz del proyecto:

```powershell
java -jar .\out\jar-build\SistemaCitasConsultorio.jar
```

La ejecución del JAR fue verificada correctamente, incluyendo:

- Inicio de la aplicación.
- Lectura de archivos CSV.
- Recuperación de doctores.
- Recuperación de pacientes.
- Recuperación de citas.
- Inicio de sesión.
- Menú interactivo.
- Consulta de información.
- Cierre correcto de la aplicación.

La aplicación utiliza la carpeta relativa `db`, por lo que el JAR debe ejecutarse desde la raíz del proyecto para utilizar los archivos CSV almacenados localmente.

---

## Requisitos

Para desarrollar o ejecutar el proyecto se requiere:

```text
Java Development Kit (JDK) 11 o compatible
IntelliJ IDEA o IDE compatible con Java
Git
```

---

## Estado actual del proyecto

Actualmente se encuentran implementadas y verificadas las siguientes funciones:

```text
Inicio de sesión
Alta de doctores
Alta de pacientes
Creación de citas
Relación Doctor-Paciente-Cita
Consulta de información
Persistencia CSV
Carga automática de datos
Validación de identificadores
Validación de fechas
Validación de horas
Manejo de entradas incorrectas
Control de identificadores duplicados
Manejo de errores de archivos
```

---

## Autor

**Néstor Villagrana Heredia**

Materia: **Computación en Java**

Institución: **Universidad Tecmilenio**

---

## Licencia y uso académico

Este proyecto fue desarrollado exclusivamente con fines académicos para la materia **Computación en Java** de la **Universidad Tecmilenio**.

El código y la documentación forman parte de una actividad académica y tienen como propósito demostrar la aplicación de los conocimientos adquiridos durante el curso.