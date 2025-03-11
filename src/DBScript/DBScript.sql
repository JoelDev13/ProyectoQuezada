Create Database citas;

use citas;

Create table usuarios(
ID int auto_increment primary key,
nombreUsuario varchar(70) not null,
contrasena varchar(70) not null,
email varchar(50) not null,
imagen MEDIUMBLOB,
rol varchar(20) not null
);

DELIMITER //
CREATE PROCEDURE sp_verificar_usuario(
    IN p_nombreUsuario VARCHAR(70),
    IN p_contrasena VARCHAR(70)
)
BEGIN
    SELECT ID, nombreUsuario, contrasena, email, imagen, rol
    FROM usuarios
    WHERE nombreUsuario = p_nombreUsuario AND contrasena = p_contrasena;
END //
DELIMITER ;

insert into usuarios (nombreUsuario, Contrasena, email, imagen, rol) values ('admin', '1234', 'pan@gmail.com', null, 'admin');
call sp_verificar_usuario('admin','1234');

/******************************************************
*DE AQUI HACIA ABAJO ES SOLO TEXTO, NO ESTA EJECUTADO *
*******************************************************/

Create table pacientes(
ID int auto_increment primary key,
nombre varchar(40) not null,
apellido varchar(40) not null,
cedula varchar(40) not null,
sexo char not null,
enail varchar(70) not null,
telefono varchar(12) not null,
direccion varchar(70) not null,
seguro varchar(30)
)