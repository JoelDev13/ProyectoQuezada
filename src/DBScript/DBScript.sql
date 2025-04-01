create database citas;
use citas;

create table usuarios (
	ID int auto_increment primary key,
    usuario varchar(50) unique not null,
    contrasena varchar(50) not null,
    rol enum('DOCTOR', 'SECRETARIA', 'ADMIN'),
    nombre varchar(40) not null,
    apellido varchar(40) not null,
    email varchar(70) unique not null,
    telefono varchar(12) not null,
    imagen mediumblob
);

Create table doctor(
	ID int primary key, 
    foreign key (ID) references usuarios(ID)
);

create table especialidad (
	ID int auto_increment primary key,
    descripcion varchar(20) not null
);

-- Relacion M:N de Doctores y especialidad
create table doctor_especialidad (
	ID_doctor int not null,
    ID_especialidad int not null,
    primary key(ID_doctor, ID_especialidad),
    foreign key(ID_doctor) references doctor(ID),
    foreign key (ID_especialidad) references especialidad(ID)
);

create table servicios (
	ID int auto_increment primary key,
    descripcion varchar(40) not null,
    precio decimal(10,2) not null
);

-- Relacion M:N de Servicios y especialidad
create table servicios_especialidad (
    ID_servicio int not null, 
    ID_especialidad int not null,
    primary key(ID_servicio, ID_especialidad),
    foreign key (ID_servicio) references servicios(ID),
    foreign key (ID_especialidad) references especialidad(ID)
);



create table horarios (
	ID int auto_increment primary key,
    dia enum('LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO'),
    inicio time not null,
    fin time not null
);

-- relacion M:N de Doctores y horarios

create table doctores_horarios (
	ID_doctor int not null,
    ID_horario int not null,
    primary key (ID_doctor, ID_horario),
    foreign key (ID_doctor) references doctor(ID),
    foreign key (ID_horario) references horarios(ID)
);

-- Tabla de descansos. Impide que se asignen citas dentro de este periodo de tiempo
-- dentro de un horario al que este asociado un doctor
create table descansos (
	ID int auto_increment primary key not null,
    inicio time not null,
    fin time not null
);
-- Relacion M:N de descansos_horarios. 
create table descansos_horarios(
	ID_descanso int not null,
    ID_horario int not null,
    primary key (ID_descanso, ID_horario),
	foreign key (ID_descanso) references descansos(ID),
    foreign key (ID_horario) references horarios(ID)
);

-- tabla de ausencias. Se sobre pone a cualquier horario. Impide que se le
-- asignen citas a dicho doctor en ese dia.

create table ausencias (
	ID int auto_increment primary key,
    ID_doctor int not null,
    fecha date not null,
    motivo varchar(40),
    foreign key (ID_doctor) references doctor(ID)
);

-- Para poder manejar Fechas que tienen horarios / descansos especiales
-- Un descanso_especial posee una relación de 1 a 1 con un horario_especial
create table descansos_especiales (
	ID int auto_increment primary key,
    inicio date not null,
    fin date not null
);

create table horarios_especiales (
	ID int auto_increment primary key,
    fecha date not null,
    inicio time not null,
    fin time not null,
    ID_descanso_especial int not null,
    foreign key (ID_descanso_especial) references descansos_especiales(ID)
);

-- Pacientes
create table pacientes(
ID int auto_increment primary key,
nombre varchar(40) not null,
apellido varchar(40) not null,
cedula varchar(40) unique not null,
sexo char(1) not null,
email varchar(70) unique not null,
telefono varchar(12) not null,
direccion varchar(70) not null,
seguro varchar(30),
fecha_nacimiento date not null 
);

-- Pendiente si la cita no ha ocurrido aun
-- Reprogramada, cancelada y completada ya te la sabes
create table citas(
ID int primary key auto_increment,
ID_paciente int not null,
ID_doctor int not null,
ID_servicio int not null,
fecha datetime not null,
estado ENUM ('PENDIENTE', 'REPROGRAMADA', 'CANCELADA', 'COMPLETADA'),
foreign key (ID_paciente) references pacientes(ID),
foreign key (ID_doctor) references doctor(ID),
foreign key (ID_servicio) references servicios(ID)
);

-- Indicaciones tiene una relacion 1 a 1 con citas
create table indicaciones(
ID int primary key auto_increment,
ID_cita int not null,
descripcion varchar(200),
foreign key (ID_cita) references citas(ID)
);

--STORED PROCEDURE: REGISTRAR UN NUEVO USUARIO
DELIMITER //
CREATE PROCEDURE sp_registrar_usuario (
    IN p_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255),
    IN p_rol ENUM('DOCTOR', 'SECRETARIA', 'ADMIN'),
    IN p_nombre VARCHAR(40),
    IN p_apellido VARCHAR(40),
    IN p_email VARCHAR(70),
    IN p_telefono VARCHAR(12),
    IN p_imagen MEDIUMBLOB
)
BEGIN
    INSERT INTO usuarios (usuario, contrasena, rol, nombre, apellido, email, telefono, imagen)
    VALUES (p_usuario, p_contrasena, p_rol, p_nombre, p_apellido, p_email, p_telefono, p_imagen);
END //
DELIMITER ;


--Stored Procedure: Crear una nueva cita
DELIMITER //
CREATE PROCEDURE sp_crear_cita (
    IN p_ID_paciente INT,
    IN p_ID_doctor INT,
    IN p_ID_servicio INT,
    IN p_fecha DATETIME
)
BEGIN
    INSERT INTO citas (ID_paciente, ID_doctor, ID_servicio, fecha, estado) 
    VALUES (p_ID_paciente, p_ID_doctor, p_ID_servicio, p_fecha, 'PENDIENTE');
END //
DELIMITER ;
