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