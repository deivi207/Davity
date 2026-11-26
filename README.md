# Davity - Portal de Guías de Videojuegos

Aplicación web full-stack para la gestión de suscripciones y acceso a guías digitales de videojuegos (PDF y video walkthroughs), desarrollada con **Spring Boot**, **MySQL**, **Bootstrap 5** y **JavaScript**.

---

## Funcionalidades Principales

* **Registro con validaciones:** Creación de cuentas con validación en el servidor para impedir cédulas (`id`) y correos electrónicos duplicados (HTTP `409 Conflict`).
* **Autenticación por estado de suscripción:** El inicio de sesión verifica credenciales contra MySQL y bloquea el acceso si la suscripción está inactiva.
* **Control de contenido según el plan:**
  * **Plan Mensual ($10):** Acceso a lectura y descarga de todas las guías en PDF.
  * **Plan Anual ($90):** Acceso a las guías en PDF + video walkthroughs completos integrados.
* **Mejora de plan en tiempo real:** Los usuarios del Plan Mensual pueden actualizar a Plan Anual desde el portal mediante una petición `PUT`, desbloqueando los videos al instante.
* **Panel de administración (CRUD):** Listado, edición y eliminación de clientes conectados directamente a MySQL.

---

## Catálogo de Guías Incluidas

1. **Hollow Knight** (`src/main/resources/static/docs/hollow-knight-guia.pdf` + Walkthrough)
2. **Tunic** (`src/main/resources/static/docs/tunic-guia.pdf` + Walkthrough)
3. **ENDER LILIES: Quietus of the Knights** (`src/main/resources/static/docs/ENDER-LILIES-Quietus-of-the-Knights-Guide.pdf` + Walkthrough)

---

## Tecnologías Utilizadas

* **Back-end:** Java, Spring Boot, Spring Web, Spring Data JPA
* **Base de Datos:** MySQL (esquema `dbdavity`, tabla `clientes`)
* **Front-end:** HTML5, Bootstrap 5, JavaScript (Fetch API, `sessionStorage`)

---

## Cómo Ejecutar el Proyecto

1. Clona este repositorio en tu equipo local.
2. Asegúrate de tener activo el servidor de **MySQL** con el esquema `dbdavity`.
3. Verifica tus credenciales de base de datos en `src/main/resources/application.properties`.
4. Ejecuta la clase principal `WorkApplication.java` desde tu IDE o usando Maven:
   ```bash
   mvn spring-boot:run
   ```
5. Abre el navegador en `http://localhost:8080`.

---

## Direcciones Accesibles

### Vistas Web (Front-end)

| Ruta | Descripción |
| :--- | :--- |
| `http://localhost:8080/` (o `/index.html`) | Formulario de registro y selección de plan. |
| `http://localhost:8080/login.html` | Inicio de sesión para suscriptores. |
| `http://localhost:8080/transmision.html` | Biblioteca de guías y walkthroughs *(requiere iniciar sesión)*. |
| `http://localhost:8080/admin.html` | Panel de administración para gestionar clientes (CRUD). |

### Endpoints de la API REST (Back-end)

Base URL: `http://localhost:8080/api/clientes`

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/clientes` | Lista todos los clientes registrados. |
| `GET` | `/api/clientes/{id}` | Obtiene un cliente específico por su cédula (`id`). |
| `POST` | `/api/clientes` | Registra un nuevo cliente (valida cédula y correo duplicados). |
| `PUT` | `/api/clientes/{id}` | Actualiza los datos o el plan de un cliente existente. |
| `DELETE` | `/api/clientes/{id}` | Elimina un cliente por su cédula (`id`). |
| `POST` | `/api/clientes/login` | Valida correo, contraseña y estado activo de la suscripción. |