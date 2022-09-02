CREATE DATABASE monitor
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\connect monitor

CREATE SCHEMA IF NOT EXISTS sensor
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS sensor.sensors
(
    uuid uuid NOT NULL,
    name character varying(30) NOT NULL,
    model character varying(15) NOT NULL,
    range_from double precision NOT NULL,
    range_to double precision NOT NULL,
    type_id bigint NOT NULL,
    unit_id bigint NOT NULL,
    location character varying(40),
    description character varying(200),
    CONSTRAINT sensors_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS sensor.sensors
    OWNER to postgres;

CREATE SEQUENCE IF NOT EXISTS sensor.type_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE sensor.type_id_seq
    OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS sensor.unit_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE sensor.unit_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS sensor.types
(
    id bigint NOT NULL UNIQUE DEFAULT nextval('sensor.type_id_seq'::regclass),
    type character varying(15) NOT NULL UNIQUE,
    CONSTRAINT types_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS sensor.types
    OWNER to postgres;



ALTER SEQUENCE sensor.type_id_seq
    OWNED BY sensor.types.id;

CREATE TABLE IF NOT EXISTS sensor.units
(
    id bigint NOT NULL UNIQUE DEFAULT nextval('sensor.unit_id_seq'::regclass),
    unit character varying(10) NOT NULL UNIQUE,
    CONSTRAINT units_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS sensor.units
    OWNER to postgres;

ALTER SEQUENCE sensor.unit_id_seq
    OWNED BY sensor.units.id;

ALTER TABLE sensor.sensors
    ADD CONSTRAINT sensors_type_id_fkey FOREIGN KEY (type_id)
        REFERENCES sensor.types (id);

ALTER TABLE sensor.sensors
    ADD CONSTRAINT sensors_unit_id_fkey FOREIGN KEY (unit_id)
        REFERENCES sensor.units (id);