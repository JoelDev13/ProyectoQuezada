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
-- ZONA DE TRIGGERS



-- ZONA DE ESTORE PROCEDURES

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


--  Store Procedures de Paciente
DELIMITER //
CREATE procedure sp_listar_pacientes ()
BEGIN
	SELECT id, nombre, apellido, cedula, sexo, email, telefono, direccion, seguro, fecha_nacimiento
    FROM pacientes;
END //
DELIMITER ;

drop procedure sp_filtrar_pacientes
DELIMITER //
CREATE PROCEDURE sp_filtrar_pacientes (
    IN p_nombre VARCHAR(40),
    IN p_apellido VARCHAR(40),
    IN p_cedula VARCHAR(40),
    IN p_sexo CHAR(1),
    IN p_email VARCHAR(70),
    IN p_telefono VARCHAR(12),
    IN p_direccion VARCHAR(70),
    IN p_seguro VARCHAR(30),
    IN p_fecha_nacimiento DATE
)
BEGIN
    SELECT id, nombre, apellido, cedula, sexo, email, telefono, direccion, seguro, fecha_nacimiento
    FROM pacientes
    WHERE (p_nombre IS NULL OR nombre LIKE CONCAT('%', p_nombre, '%'))
      AND (p_apellido IS NULL OR apellido LIKE CONCAT('%', p_apellido, '%'))
      AND (p_cedula IS NULL OR cedula LIKE CONCAT('%', p_cedula, '%'))
      AND (p_sexo IS NULL OR sexo = p_sexo)
      AND (p_email IS NULL OR email LIKE CONCAT('%', p_email, '%'))
      AND (p_telefono IS NULL OR telefono LIKE CONCAT('%', p_telefono, '%'))
      AND (p_direccion IS NULL OR direccion LIKE CONCAT('%', p_direccion, '%'))
      AND (p_seguro IS NULL OR seguro LIKE CONCAT('%', p_seguro, '%'))
      AND (p_fecha_nacimiento IS NULL OR fecha_nacimiento = p_fecha_nacimiento);
END //
DELIMITER ;


DELIMITER //
CREATE procedure sp_crear_pacientes (
	IN p_nombre varchar(40),
    IN p_apellido varchar(40),
    IN p_cedula varchar(40),
    IN p_sexo CHAR(1),
    IN p_email VARCHAR(70),
    IN p_telefono VARCHAR(12),
    in p_direccion VARCHAR(70),
    IN p_seguro VARCHAR(30),
    IN p_fecha_nacimiento DATE
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
		IF p_cedula IS NULL OR CHAR_LENGTH(p_cedula) < 10 THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cédula debe tener al menos 10 caracteres';
		END IF;
		
			IF p_telefono IS NULL OR CHAR_LENGTH(p_telefono) < 10 THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'el numero de telefono debe tener al menos 10 caracteres';
		END IF;

		INSERT INTO pacientes(nombre, apellido, cedula, sexo, email, telefono, direccion, seguro, fecha_nacimiento)
		VALUES (p_nombre, p_apellido, p_cedula, p_sexo, p_email, p_telefono, p_direccion, p_seguro, p_fecha_nacimiento);
    COMMIT;
END //
DELIMITER ;

DELIMITER //
CREATE procedure sp_actualizar_pacientes (
	IN p_nombre varchar(40),
    IN p_apellido varchar(40),
    IN p_cedula varchar(40),
    IN p_sexo CHAR(1),
    IN p_email VARCHAR(70),
    IN p_telefono VARCHAR(12),
    in p_direccion VARCHAR(70),
    IN p_seguro VARCHAR(30),
    IN p_fecha_nacimiento DATE
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
    END;
	START TRANSACTION;
		UPDATE pacientes SET 
		nombre = p_nombre,
		apellido = p_apellido,
		sexo = p_sexo,
		email = p_email,
		telefono = p_telefono,
		direccion = p_direccion,
		seguro = p_seguro,
		fecha_nacimiento = p_fecha_nacimiento
		WHERE cedula = p_cedula;
		
		IF ROW_COUNT() = 0 THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Paciente no encontrado con esa cédula';
		END IF;

    COMMIT;
END //
DELIMITER ;




DELIMITER //
CREATE procedure sp_eliminar_paciente (
    IN p_cedula varchar(40)
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
    END;
	START TRANSACTION;
	
		DELETE FROM pacientes WHERE cedula = p_cedula;
        
		IF ROW_COUNT() = 0 THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Paciente no encontrado con esa cédula';
		END IF;

    COMMIT;
END //
DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_listar_usuarios()
BEGIN
    SELECT ID, usuario, nombre, apellido, contrasena, email, telefono, rol, activo, imagen
    FROM usuarios;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_crear_usuario(
    IN p_nombre VARCHAR(40),
    IN p_apellido VARCHAR(40),
    IN p_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(50),
    IN p_rol ENUM('DOCTOR', 'SECRETARIA', 'ADMIN'),
    IN p_email VARCHAR(70),
    IN p_telefono VARCHAR(12),
    IN p_imagen MEDIUMBLOB
)
BEGIN
    INSERT INTO usuarios (nombre, apellido, usuario, contrasena, rol, email, telefono, imagen)
    VALUES (p_nombre, p_apellido, p_usuario, p_contrasena, p_rol, p_email, p_telefono, p_imagen);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_actualizar_usuario(
    IN p_id INT,
    IN p_nombre VARCHAR(40),
    IN p_apellido VARCHAR(40),
    IN p_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(50),
    IN p_rol ENUM('DOCTOR', 'SECRETARIA', 'ADMIN'),
    IN p_email VARCHAR(70),
    IN p_telefono VARCHAR(12),
    IN p_activo BOOLEAN,
    IN p_imagen MEDIUMBLOB
)
BEGIN
    UPDATE usuarios
    SET nombre = p_nombre,
        apellido = p_apellido,
        usuario = p_usuario,
        contrasena = p_contrasena,
        rol = p_rol,
        email = p_email,
        telefono = p_telefono,
        activo = p_activo,
        imagen = p_imagen
    WHERE ID = p_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_filtrar_usuarios(
    IN p_nombre VARCHAR(40),
    IN p_apellido VARCHAR(40),
    IN p_usuario VARCHAR(50),
    IN p_rol ENUM('DOCTOR', 'SECRETARIA', 'ADMIN'),
    IN p_email VARCHAR(70),
    IN p_telefono VARCHAR(12),
    IN p_activo BOOLEAN,
    IN p_imagen MEDIUMBLOB
)
BEGIN
    SELECT ID, usuario, nombre, apellido, email, telefono, rol, activo, imagen
    FROM usuarios
    WHERE (p_nombre IS NULL OR nombre LIKE CONCAT('%', p_nombre, '%'))
      AND (p_apellido IS NULL OR apellido LIKE CONCAT('%', p_apellido, '%'))
      AND (p_usuario IS NULL OR usuario LIKE CONCAT('%', p_usuario, '%'))
      AND (p_rol IS NULL OR rol = p_rol)
      AND (p_email IS NULL OR email LIKE CONCAT('%', p_email, '%'))
      AND (p_telefono IS NULL OR telefono LIKE CONCAT('%', p_telefono, '%'))
      AND (p_activo IS NULL OR activo = p_activo)
      AND (p_imagen IS NULL OR imagen = p_imagen);
END //

DELIMITER ;



-- Testing, pon aqui cualquier data de prueba.
/*

insert into usuarios(usuario,contrasena,rol,nombre,apellido,email,telefono,imagen) values ("admin", "1234", "ADMIN", "Fulano", "DeTal", "fulando@gmail.com","123456",null);
insert into usuarios(usuario,contrasena,rol,nombre,apellido,email,telefono,imagen) values ("doctor", "1234", "DOCTOR", "Fulano", "DeTal", "doctor@gmail.com","123456",null);
insert into usuarios(usuario,contrasena,rol,nombre,apellido,email,telefono,imagen) values ("secretaria", "1234", "SECRETARIA", "Fulano", "DeTal", "Secretaria@gmail.com","123456",null);

INSERT INTO pacientes (nombre, apellido, cedula, sexo, email, telefono, direccion, seguro, fecha_nacimiento)
VALUES 
('Juan', 'Pérez', '12345678', 'M', 'juan.perez@gmail.com', '8091234567', 'Av. Siempre Viva 123', 'ARS Humano', '1990-05-14'),
('Ana', 'Gómez', '87654321', 'F', 'ana.gomez@hotmail.com', '8297654321', 'Calle Real 45', 'ARS Universal', '1985-11-30'),
('Carlos', 'Ramírez', '11223344', 'M', 'carlos.ramirez@yahoo.com', '8493344556', 'C/ Duarte #10', NULL, '1992-03-22'),
('Laura', 'Fernández', '99887766', 'F', 'laura.fernandez@gmail.com', '8096677889', 'Villa del Sol, Apt. 3B', 'ARS Mapfre', '2000-07-10'),
('Pedro', 'Martínez', '44332211', 'M', 'pedro.martinez@outlook.com', '8091122334', 'Zona Colonial, Edif. 5', 'ARS Monumental', '1978-09-05');





call VerificarLogin('admin', '1234');
select * from usuarios ;

*/

