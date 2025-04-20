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
-- cancelada y completada ya te la sabes
Create table citas(
ID int primary key auto_increment,
ID_paciente int not null,
ID_doctor int not null,
ID_horario_doc int not null,
ID_servicio int not null,
ID_especialidad int not null,
fecha date not null,
estado ENUM ('PENDIENTE', 'CANCELADA', 'COMPLETADA'),
foreign key (ID_paciente) references pacientes(ID),
foreign key (ID_doctor) references doctor(ID),
foreign key (ID_servicio) references servicios(ID),
foreign key (ID_especialidad) references especialidad(ID),
foreign key (ID_horario_doc) references horarios(ID)
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

-- ZONA DE VIEWS

-- Vista para las citas. De aqui se haran las consultas.
CREATE VIEW vista_cita AS
SELECT
	c.ID as ID_cita,
    c.fecha,
    c.estado,
    p.ID as ID_paciente,
    p.cedula,
    CONCAT(p.nombre,' ', p.apellido) as paciente,
    d.ID as ID_doctor,
    concat(u.nombre,' ',  u.apellido) as doctor,
    s.ID as ID_servicio,
    s.descripcion as servicio,
    e.ID ID_especialidad,
    e.descripcion as especialidad,
    h.ID as ID_horario,
    h.inicio,
    h.fin
FROM citas c
JOIN pacientes p ON c.ID_paciente = p.ID
JOIN doctor d ON C.ID_doctor = d.ID
JOIN usuarios u ON d.ID = u.ID
JOIN servicios s ON c.ID_servicio = s.ID
JOIN especialidad e ON c.ID_especialidad = e.ID
JOIN horarios h ON c.ID_horario_doc = h.ID
ORDER BY fecha;


-- Aqui se haran las consultas del dialog buscarDoctor
CREATE view vista_doctores
AS
SELECT
	d.ID,
	u.nombre,
	u.apellido,
	e.descripcion as especialidad,
	u.imagen
FROM doctor d
JOIN usuarios u ON d.ID = u.ID
JOIN doctor_especialidad de ON d.ID = de.ID_doctor
JOIN especialidad e ON de.ID_especialidad = e.ID;

-- Recopila todos los servicios que brinda cada  medico
CREATE VIEW vista_servicios_doctor AS
SELECT distinct
    d.ID AS ID_doctor,
    CONCAT(u.nombre, ' ', u.apellido) AS doctor,
    e.ID AS ID_especialidad,
    e.descripcion AS especialidad,
    s.ID AS ID_servicio,
    s.descripcion AS servicio,
    s.precio
FROM doctor d
JOIN usuarios u ON d.ID = u.ID
JOIN doctor_especialidad de ON d.ID = de.ID_doctor
JOIN especialidad e ON de.ID_especialidad = e.ID
JOIN servicios_especialidad se ON e.ID = se.ID_especialidad
JOIN servicios s ON se.ID_servicio = s.ID
ORDER BY d.ID, e.ID, s.ID;



-- ZONA DE TRIGGERS

-- Crear un registro en doctor atutomaticamente si el usuario es doctor
DELIMITER //
CREATE TRIGGER before_crear_usuario_doc
AFTER INSERT ON usuarios
FOR EACH ROW
BEGIN
	IF NEW.rol = 'DOCTOR' THEN
		INSERT INTO doctor(ID) VALUES (NEW.ID);
    END IF;
END //
DELIMITER ;



-- previene la eliminacion de pacientes que tengan citas
DELIMITER //
CREATE TRIGGER before_eliminar_paciente
BEFORE DELETE ON pacientes
FOR EACH ROW
BEGIN
	IF EXISTS (SELECT 1 FROM citas WHERE ID_paciente = OLD.ID) THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'No se pueden eliminar pacientes que tengan citas registradas';
    END IF;
END //
DELIMITER ;

    
    
-- verifica que el medico tenga la especialidad en la que sei ntenta agendar la cita
-- verifica que el medico tenga el servicio
drop trigger before_agendar_cita
DELIMITER //

CREATE TRIGGER before_agendar_cita 
BEFORE INSERT ON citas
FOR EACH ROW
BEGIN
    -- Validar que el doctor tenga la especialidad de la cita
    IF NOT EXISTS (
        SELECT 1
        FROM doctor_especialidad
        WHERE ID_doctor = NEW.ID_doctor
          AND ID_especialidad = NEW.ID_especialidad
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El doctor no posee la especialidad indicada en la cita';
    END IF;

    -- Validar que el doctor pueda brindar ese servicio
    IF NOT EXISTS (
        SELECT 1
        FROM vista_servicios_doctor
        WHERE ID_doctor = NEW.ID_doctor
          AND ID_servicio = NEW.ID_servicio
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El doctor no brinda ese servicio';
    END IF;
END //
DELIMITER ;
    
    


-- ZONA DE STORE PROCEDURES

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
-- Trae todos los pacientes
DELIMITER //
CREATE procedure sp_listar_pacientes ()
BEGIN
	SELECT id, nombre, apellido, cedula, sexo, email, telefono, direccion, seguro, fecha_nacimiento
    FROM pacientes;
END //
DELIMITER ;


-- filtra pacientes
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


-- Filtra pacientes para usarlo en pacienteDTO
DELIMITER //
CREATE PROCEDURE sp_filtrar_pacientesDTO (
    IN p_nombre VARCHAR(40),
    IN p_apellido VARCHAR(40),
    IN p_cedula VARCHAR(40)
)
BEGIN
    SELECT id, nombre, apellido, cedula
    FROM pacientes
    WHERE (p_nombre IS NULL OR nombre LIKE CONCAT('%', p_nombre, '%'))
      AND (p_apellido IS NULL OR apellido LIKE CONCAT('%', p_apellido, '%'))
      AND (p_cedula IS NULL OR cedula LIKE CONCAT('%', p_cedula, '%'));
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
	-- agregar verificacion que no tenga citas trigger
		DELETE FROM pacientes WHERE cedula = p_cedula;
        
		IF ROW_COUNT() = 0 THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Paciente no encontrado con esa cédula';
		END IF;

    COMMIT;
END //
DELIMITER ;


-- store procedures de doctores
drop procedure sp_filtrar_doctoresDTO
-- Doctores
DELIMITER //
CREATE PROCEDURE sp_filtrar_doctoresDTO(
	IN p_nombre varchar(40),
    IN p_apellido varchar(40),
    IN p_especialidad varchar(40)
)
BEGIN
	SELECT
		ID,
		nombre,
        apellido,
        especialidad,
        imagen
	FROM vista_doctores
    WHERE(p_nombre IS NULL OR nombre LIKE CONCAT('%', p_nombre, '%')) 
    AND (p_apellido IS NULL OR apellido LIKE CONCAT('%', p_apellido, '%'))
    AND (p_especialidad IS NULL or especialidad LIKE CONCAT('%', p_especialidad, '%'));
END //


-- Recupera los dias laborables de un doctor
-- digase, los dias en los que posee horario.
DELIMITER //
CREATE PROCEDURE sp_dias_habiles(
	IN p_id INT
)
BEGIN
	SELECT DISTINCT dia 
	FROM horarios 
	WHERE ID_doctor = p_id;
END //
DELIMITER ;


-- Recupera los horarios de un dia especifico
-- de un doctor en especifico
drop procedure sp_horarios_dia
DELIMITER //
CREATE PROCEDURE sp_horarios_dia(
	p_id INT,
	p_dia ENUM ('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO')
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        RESIGNAL;
    END;
    
    SELECT
		id,
        dia,
		inicio,
        fin
	FROM horarios
    WHERE (ID_doctor = p_id)
		AND (dia = p_dia);
	
    IF ROW_COUNT() = 0 THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Doctor no encontrado con ese ID';
    END IF;
END //
DELIMITER ;




-- ESPECIALIDADES
DELIMITER //
CREATE PROCEDURE filtrar_especialidades()
BEGIN
	SELECT id, descripcion from especialidad;
END //
DELIMITER ;


-- ESPECIALIDADES

DELIMITER //
CREATE PROCEDURE sp_listar_especialidades_medico_especifico(
    IN id_doc INT
)
BEGIN
    -- Validar si el doctor existe primero 
    IF NOT EXISTS (
        SELECT 1 FROM doctor WHERE ID = id_doc
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Doctor no encontrado con ese ID';
    END IF;

    -- Listar especialidades
    SELECT e.ID, e.descripcion
    FROM doctor_especialidad de
    JOIN especialidad e ON de.ID_especialidad = e.ID
    WHERE de.ID_doctor = id_doc;
END //
DELIMITER ;

call sp_listar_especialidades_medico_especifico(2)

-- Recopila todos los servicios que puede brindar un medico
DELIMITER //
CREATE PROCEDURE sp_listar_servicios_medico_especifico(
	IN id_doc INT
)
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM doctor WHERE ID = id_doc
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Doctor no encontrado con ese ID';
    END IF;
		
	SELECT DISTINCT
    id_servicio,
    servicio,
    precio
    FROM vista_servicios_doctor
    WHERE ID_doctor = id_doc;
END //






-- SERVICIOS


DELIMITER //
CREATE PROCEDURE listar_servicios()
BEGIN
	SELECT id, descripcion, precio FROM servicios;
END //
DELIMITER ;


-- CITAS
DELIMITER //
CREATE PROCEDURE sp_filtrar_citas(
	IN p_pacienteID INT,
	IN p_doctorID INT,
	IN p_serviciosID INT,
	IN p_especialidadID INT,
	IN p_estado ENUM('PENDIENTE', 'CANCELADA', 'COMPLETADA'),
	IN p_fechaInicio DATE,
	IN p_fechaFin DATE
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		RESIGNAL;
	END;

	-- Validación de fechas
	IF (p_fechaInicio IS NULL AND p_fechaFin IS NOT NULL) 
		OR (p_fechaInicio IS NOT NULL AND p_fechaFin IS NULL) THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Por favor, proporciona un periodo completo de tiempo';
	END IF;

	IF (p_fechaInicio IS NOT NULL AND p_fechaFin IS NOT NULL AND p_fechaInicio > p_fechaFin) THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'La fecha de inicio no puede ser mayor que la fecha de fin';
	END IF;

	SELECT
		*
	FROM vista_cita
	WHERE (p_pacienteID IS NULL OR ID_paciente = p_pacienteID)
		AND (p_doctorID IS NULL OR ID_doctor = p_doctorID)
		AND (p_serviciosID IS NULL OR ID_servicio = p_serviciosID)
		AND (p_especialidadID IS NULL OR ID_especialidad = p_especialidadID)
		AND (p_estado IS NULL OR estado = p_estado)
		AND (
			(p_fechaInicio IS NULL AND p_fechaFin IS NULL)
			OR (fecha BETWEEN p_fechaInicio AND p_fechaFin)
		);
END //
DELIMITER ;


-- Actualiza la fecha y horario en que se va a dar una cita medica;
drop procedure sp_actualizar_cita
DELIMITER //
CREATE PROCEDURE sp_actualizar_cita(
	IN p_id INT,
    IN p_paciente INT,
    IN p_doctor INT ,
    IN p_horario_doc INT,
    IN p_servicio INT,
    IN p_especialidad INT,
    IN p_fecha DATE,
    IN p_estado enum('PENDIENTE','CANCELADA','COMPLETADA')
) 
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		RESIGNAL;
	END;
    -- verficamos que el horario este asociado al doctor
    IF NOT EXISTS (
		SELECT * FROM citas c
		JOIN horarios h ON c.ID_doctor = h.ID_doctor
		WHERE c.ID = p_id AND h.ID = p_horario_doc
	) THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'El horario no corresponde al doctor de la cita';
	END IF;
    
    START TRANSACTION;
    UPDATE citas SET
		ID_paciente = p_paciente,
        ID_doctor = p_doctor,
        ID_horario_doc = p_horario_doc,
        ID_servicio = p_servicio,
        ID_especialidad = p_especialidad,
		fecha = p_fecha,
        estado = p_estado
        WHERE ID = p_id;
    COMMIT;
END //
DELIMITER ;

-- SP: Mostrar Histórico de Pagos
DELIMITER $$

CREATE PROCEDURE BuscarHistoricoPagos(IN filtro VARCHAR(100))
BEGIN
    SELECT 
        CONCAT(p.nombre, ' ', p.apellido) AS paciente,
        CONCAT(u.nombre, ' ', u.apellido) AS doctor,
        c.fecha,
        hp.monto AS total,  -- Ya no se necesita SUM
        s.descripcion AS servicio_realizado,
        mp.descripcion AS metodo_pago
    FROM 
        historico_pagos hp
    JOIN metodos_pagos mp ON hp.ID_metodo_pago = mp.ID
    JOIN citas c ON hp.ID_cita = c.ID
    JOIN pacientes p ON c.ID_paciente = p.ID
    JOIN doctor d ON c.ID_doctor = d.ID
    JOIN usuarios u ON d.ID = u.ID
    JOIN servicios s ON c.ID_servicio = s.ID
    WHERE 
        CONCAT(p.nombre, ' ', p.apellido) LIKE CONCAT('%', filtro, '%')
        OR CONCAT(u.nombre, ' ', u.apellido) LIKE CONCAT('%', filtro, '%')
        OR hp.monto = CAST(filtro AS DECIMAL(10,2))
        OR s.descripcion LIKE CONCAT('%', filtro, '%')
        OR mp.descripcion LIKE CONCAT('%', filtro, '%')
    GROUP BY 
        c.ID, p.nombre, p.apellido, u.nombre, u.apellido, c.fecha, s.descripcion, mp.descripcion, hp.monto;
END $$

DELIMITER ;


DELIMITER //

-- SP: Insertar Método de Pago
CREATE PROCEDURE insertar_metodo_pago(
    IN p_descripcion VARCHAR(40)
)
BEGIN
    INSERT INTO metodos_pagos (descripcion) VALUES (p_descripcion);
END //

DELIMITER ;
 


-- Testing, pon aqui cualquier data de prueba.





INSERT INTO usuarios(usuario, contrasena, rol, nombre, apellido, email, telefono, imagen) 
VALUES 
("admin", "1234", "ADMIN", "Fulano", "DeTal", "fulando@gmail.com","123456",null),
("doctor", "1234", "DOCTOR", "FulanoDoc", "DeTal", "doctor@gmail.com","123456",null),
("secretaria", "1234", "SECRETARIA", "FulanoSEC", "DeTal", "Secretaria@gmail.com","123456",null),
('dr.rodriguez', '1234', 'DOCTOR', 'Andrés', 'Rodríguez', 'andres.rodriguez@clinicmail.com', '8291234567', null),
('dr.natalia', '1234', 'DOCTOR', 'Natalia', 'Mejía', 'natalia.mejia@clinicmail.com', '8497654321', null),
('dr.gomez', '1234', 'DOCTOR', 'Ricardo', 'Gómez', 'ricardo.gomez@clinicmail.com', '8091122444', null);




INSERT INTO pacientes (nombre, apellido, cedula, sexo, email, telefono, direccion, seguro, fecha_nacimiento)
VALUES 
('Juan', 'Pérez', '12345678', 'M', 'juan.perez@gmail.com', '8091234567', 'Av. Siempre Viva 123', 'ARS Humano', '1990-05-14'),
('Ana', 'Gómez', '87654321', 'F', 'ana.gomez@hotmail.com', '8297654321', 'Calle Real 45', 'ARS Universal', '1985-11-30'),
('Carlos', 'Ramírez', '11223344', 'M', 'carlos.ramirez@yahoo.com', '8493344556', 'C/ Duarte #10', NULL, '1992-03-22'),
('Laura', 'Fernández', '99887766', 'F', 'laura.fernandez@gmail.com', '8096677889', 'Villa del Sol, Apt. 3B', 'ARS Mapfre', '2000-07-10'),
('Pedro', 'Martínez', '44332211', 'M', 'pedro.martinez@outlook.com', '8091122334', 'Zona Colonial, Edif. 5', 'ARS Monumental', '1978-09-05');


INSERT INTO horarios (dia, inicio, fin, ID_doctor)
VALUES 
('LUNES', '08:00:00', '15:00:00', 2);

-- Doctor 3: Andrés Rodríguez
INSERT INTO horarios (dia, inicio, fin, ID_doctor)
VALUES ('MARTES', '09:00:00', '17:00:00', 4);

-- Doctor 4: Natalia Mejía
INSERT INTO horarios (dia, inicio, fin, ID_doctor)
VALUES ('MIERCOLES', '08:00:00', '14:00:00', 5);

-- Doctor 5: Ricardo Gómez
INSERT INTO horarios (dia, inicio, fin, ID_doctor)
VALUES ('JUEVES', '10:00:00', '18:00:00', 6);

INSERT INTO servicios (descripcion, precio) 
VALUES 
('Consulta General', 200.00), 		-- ID = 1
('Consulta Pediátrica', 300.00),    -- ID = 2
('Consulta Dermatológica', 350.00), -- ID = 3
('Consulta Cardiológica', 400.00);  -- ID = 4


INSERT INTO especialidad (descripcion) 
VALUES 
('Medico General'),	  -- ID = 1
('Pediatría'),        -- ID = 2
('Dermatología'),     -- ID = 3
('Cardiología');      -- ID = 4


INSERT INTO servicios_especialidad(ID_servicio, ID_especialidad) 
VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- relaciones de este medico
INSERT INTO doctor_especialidad (ID_doctor, ID_especialidad)
VALUES
(2,2),
(2,3),
(2, 1);

-- Doctor Andrés Rodríguez → Pediatría
INSERT INTO doctor_especialidad (ID_doctor, ID_especialidad) 
VALUES (4, 2);

-- Doctor Natalia Mejía → Dermatología
INSERT INTO doctor_especialidad (ID_doctor, ID_especialidad) 
VALUES (5, 3);

-- Doctor Ricardo Gómez → Cardiología
INSERT INTO doctor_especialidad (ID_doctor, ID_especialidad) 
VALUES (6, 4);


INSERT INTO citas (ID_paciente, ID_doctor, ID_horario_doc, ID_servicio, ID_especialidad, fecha, estado) 
VALUES
(1, 2, 1, 3, 3, '2025-04-17', 'PENDIENTE'),
(1, 2, 1, 2, 2, '2025-04-16', 'PENDIENTE'),
(1, 2, 1, 1, 1, '2025-04-15', 'PENDIENTE');


-- Doctor 4 con paciente 2 (Ana Gómez)
INSERT INTO citas (ID_paciente, ID_doctor, ID_horario_doc, ID_servicio, ID_especialidad, fecha, estado) 
VALUES (2, 4, 2, 2, 2, '2025-04-17', 'PENDIENTE');

-- Doctor 5 con paciente 3 (Carlos Ramírez)
INSERT INTO citas (ID_paciente, ID_doctor, ID_horario_doc, ID_servicio, ID_especialidad, fecha, estado) 
VALUES (3, 5, 3, 3, 3, '2025-04-18', 'PENDIENTE');

-- Doctor 6 con paciente 5 (Pedro Martínez)
INSERT INTO citas (ID_paciente, ID_doctor, ID_horario_doc, ID_servicio, ID_especialidad, fecha, estado) 
VALUES (5, 6, 4, 4, 4, '2025-04-19', 'PENDIENTE');





