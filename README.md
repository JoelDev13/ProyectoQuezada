# 🏥 Soft Nova Care

**Soft Nova Care** es un sistema de gestión de citas médicas desarrollado como proyecto final para la asignatura **Programación 1** del Instituto Tecnológico de las Américas (ITLA).  
El sistema permite a distintos usuarios interactuar con una clínica según su rol: **Secretaria**, **Doctor** o **Administrador**.

---

## 🧩 Tecnologías utilizadas

- **Lenguaje de programación:** Java
- **Interfaz gráfica:** Java Swing
- **Base de datos:** MySQL / SQL estándar
- **Arquitectura:** MVC (Modelo-Vista-Controlador)  
- **Patrón de diseño:** DAO (Data Access Object)
---

## 👥 Roles del sistema

### 👩‍💼 Secretaria
- Registrar y editar pacientes del consultorio.
- Agendar citas médicas.
- Ver y gestionar el listado de citas.

### 👨‍⚕️ Doctor
- Consultar sus citas medicas del dia.


### 👨‍💻 Administrador
- Registrar y editar pacientes del consultorio.
- Agendar citas médicas.
- Ver y gestionar el listado de citas.
- Crear, modificar y desactivar usuarios del sistema.
- Gestionar los doctores, asociarles horarios y especialidades.
- Agregar, modificar y eliminar especialidades.
- Agregar, modificar y eliminar servicios del consultorio.
- Consultar el historial de pagos.
- Registrar y eliminar metodos de que utiliza el consultorio.

---
### Capturas del software en funcionamiento.
![Texto alternativo](imagenesRepositorio/login.png)
![Texto alternativo](imagenesRepositorio/loginSUCCESS.png)
![Texto alternativo](imagenesRepositorio/pacientes.png)
![Texto alternativo](imagenesRepositorio/agendarCita.png)
![Texto alternativo](imagenesRepositorio/citas.png)
![Texto alternativo](imagenesRepositorio/usuarios.png)
![Texto alternativo](imagenesRepositorio/doctores.png)
![Texto alternativo](imagenesRepositorio/especialidades.png)
![Texto alternativo](imagenesRepositorio/servicios.png)
![Texto alternativo](imagenesRepositorio/historicoPagos.png)
![Texto alternativo](imagenesRepositorio/metodosPagos.png)
![Texto alternativo](imagenesRepositorio/acercaDe.png)

--- 
### Configuracion del software.
1. Clone este repositorio en su maquina local.
2. configure una base de datos MySQL y ejecute el script `DBScript` de la carpeta DBScript del proyecto.
3. en el archivo `config.properties` configure las credenciales que usara la APP para conectarse a la DB.
4. Asegurese de enlazar las librerias que se encuentran en `/lib` antes de compilar el software.
5. Compile el software usando su IDE preferido o el comando `javac`