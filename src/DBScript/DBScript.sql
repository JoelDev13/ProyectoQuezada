Create Database Sistema_Citas;

Use Sistema_Citas;

Create table Usuarios(
ID int auto_increment primary key,
NombreUsuario varchar(70) not null,
Constrasena varchar(70) not null,
Email varchar(50) not null,
Imagen MEDIUMBLOB,
Rol varchar(20) not null
);

DELIMITER //
CREATE PROCEDURE sp_verificar_usuario(
    IN p_nombreUsuario VARCHAR(70),
    IN p_contrasena VARCHAR(70)
)
BEGIN
    SELECT ID, NombreUsuario, Email, Imagen, Rol
    FROM Usuarios
    WHERE NombreUsuario = p_nombreUsuario AND Constrasena = p_contrasena;
END //
DELIMITER ;

/******************************************************
*DE AQUI HACIA ABAJO ES SOLO TEXTO, NO ESTA EJECUTADO *
*******************************************************/

Create table Pacientes(
ID int auto_increment primary key,
Nombre varchar(40) not null,
Apellido varchar(40) not null,
Cedula varchar(40) not null,
Sexo char not null,
Enail varchar(70) not null
Telefono varchar(12) not null,
Direccion varchar(70) not null,
Seguro varchar(30)
)