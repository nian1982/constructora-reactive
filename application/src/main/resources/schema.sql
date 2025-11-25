CREATE TABLE IF NOT EXISTS materiales (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    sigla VARCHAR(2) NOT NULL CHECK (LENGTH(sigla) = 2),
    cantidad INT NOT NULL
);

CREATE TABLE IF NOT EXISTS construcciones (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    dias INTEGER
);

CREATE TABLE IF NOT EXISTS tipo_construccion (
    id BIGSERIAL PRIMARY KEY,
    cantidad INTEGER,
    construccion_id BIGINT,
    material_id BIGINT
);

CREATE TABLE IF NOT EXISTS solicitudes (
    id BIGSERIAL PRIMARY KEY,
    coordenada_x DOUBLE PRECISION,
    coordenada_y DOUBLE PRECISION,
    fecha_solicitud TIMESTAMP,
    fecha_inicio TIMESTAMP,
    fecha_finalizacion TIMESTAMP,
    estado VARCHAR(50) DEFAULT 'PROGRAMADA',
    construccion_id BIGINT,
    CONSTRAINT fk_solicitud_construccion
        FOREIGN KEY (construccion_id) REFERENCES construcciones(id)
);




