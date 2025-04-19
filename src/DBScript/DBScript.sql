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
    activo boolean not null default true,
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
    fin time not null,
    ID_doctor int not null,
    foreign key (ID_doctor) references doctor(ID)
);


-- Pacientes
Create table pacientes(
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
Create table citas(
ID int primary key auto_increment,
ID_paciente int not null,
ID_doctor int not null,
ID_servicio int not null,
ID_especialidad int not null,
fecha datetime not null,
estado ENUM ('PENDIENTE', 'CANCELADA', 'COMPLETADA'),
foreign key (ID_paciente) references pacientes(ID),
foreign key (ID_doctor) references doctor(ID),
foreign key (ID_servicio) references servicios(ID),
foreign key (ID_especialidad) references especialidad(ID)
);

create table metodos_pagos(
	ID int auto_increment primary key,
    descripcion varchar(40) not null
);

create table historico_pagos(
	ID int auto_increment primary key,
    ID_cita int not null,
    monto decimal(10,2) not null,
    ID_metodo_pago int not null,
    foreign key (ID_cita) references citas(ID),
    foreign key (ID_metodo_pago) references metodos_pagos(ID)
);

/*
Indicaciones tiene una relacion 1 a 1 con citas
create table indicaciones(
ID int primary key auto_increment,
ID_cita int not null,
descripcion varchar(200),
foreign key (ID_cita) references citas(ID)
);
*/

drop procedure VerificarLogin;
-- STORED PROCEDURE: verificar login
DELIMITER //
CREATE PROCEDURE VerificarLogin (
    IN p_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255)
)
BEGIN
    DECLARE v_usuario VARCHAR(255);
    DECLARE v_contrasena_db VARCHAR(255);
    DECLARE v_activo BOOLEAN;

    -- Buscar datos del usuario en la tabla
    SELECT usuario, contrasena, activo 
    INTO v_usuario, v_contrasena_db, v_activo
    FROM usuarios
    WHERE usuario = p_usuario;

    -- Verificar si el usuario existe
    IF v_usuario IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Usuario no encontrado';
    END IF;

    -- Verificar si la contraseña coincide
    IF v_contrasena_db != p_contrasena THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Credenciales incorrectas';
    END IF;

    -- Devolver los datos del usuario si todo es correcto
    SELECT id, usuario, rol, nombre, apellido, email, telefono, activo, imagen 
    FROM usuarios
    WHERE usuario = p_usuario;
END //
DELIMITER ;

-- STORED PROCEDURE: Mostrar Historial De pagos
DELIMETER //
CREATE PROCEDURE BuscarHistoricoPagos(IN filtro VARCHAR(100))
BEGIN
    SELECT 
        CONCAT(p.nombre, ' ', p.apellido) AS paciente,
        CONCAT(u.nombre, ' ', u.apellido) AS doctor,
        c.fecha,
        hp.monto AS total,
        s.descripcion AS servicio_realizado,
        mp.descripcion AS metodo_pago
    FROM 
        historico_pagos hp
    JOIN 
        metodos_pagos mp ON hp.ID_metodo_pago = mp.ID
    JOIN 
        citas c ON hp.ID_cita = c.ID
    JOIN 
        pacientes p ON c.ID_paciente = p.ID
    JOIN 
        doctor d ON c.ID_doctor = d.ID
    JOIN 
        usuarios u ON d.ID = u.ID
    JOIN 
        servicios s ON c.ID_servicio = s.ID
    WHERE 
        CONCAT(p.nombre, ' ', p.apellido) LIKE CONCAT('%', filtro, '%')
        OR CONCAT(u.nombre, ' ', u.apellido) LIKE CONCAT('%', filtro, '%')
        OR hp.monto LIKE CONCAT('%', filtro, '%')
        OR s.descripcion LIKE CONCAT('%', filtro, '%')
        OR mp.descripcion LIKE CONCAT('%', filtro, '%');
END$$

DELIMITER ;



-- Testing, pon aqui cualquier data de prueba.
/*

insert into usuarios(usuario,contrasena,rol,nombre,apellido,email,telefono,imagen) values ("admin", "1234", "ADMIN", "Fulano", "DeTal", "fulando@gmail.com","123456",null);
call VerificarLogin('admin', '1234');
select * from usuarios ;



*/



