CREATE SEQUENCE IF NOT EXISTS designer_seq;
CREATE TABLE IF NOT EXISTS designer (
    id bigint DEFAULT nextval('designer_seq') NOT NULL PRIMARY KEY,
    designer_name varchar(255) UNIQUE
);

CREATE SEQUENCE IF NOT EXISTS collection_seq;
CREATE TABLE IF NOT EXISTS collection (
    id bigint DEFAULT nextval('collection_seq') NOT NULL PRIMARY KEY,
    collection_name varchar(255) UNIQUE,
    collection_designer_id bigint,
    collection_season varchar(255),
    collection_year integer,
    FOREIGN KEY (collection_designer_id) REFERENCES designer(id)
);

CREATE SEQUENCE IF NOT EXISTS clothing_garment_seq;
CREATE TABLE IF NOT EXISTS clothing_garment (
    id bigint DEFAULT nextval('clothing_garment_seq') NOT NULL PRIMARY KEY,
    clothing_name varchar(255) UNIQUE,
    clothing_price real,
    clothing_stock integer,
    clothing_year integer,
    clothing_collection_id bigint,
    FOREIGN KEY (clothing_collection_id) REFERENCES collection(id)
);

CREATE SEQUENCE IF NOT EXISTS material_seq;
CREATE TABLE IF NOT EXISTS material (
    id bigint DEFAULT nextval('material_seq') NOT NULL PRIMARY KEY,
    material_name varchar(255) UNIQUE,
    material_producer varchar(255)
);

-- join table clothing_material
CREATE TABLE IF NOT EXISTS clothing_material (
    clothing_id bigint NOT NULL,
    material_id bigint NOT NULL,
    FOREIGN KEY (clothing_id) REFERENCES clothing_garment(id),
    FOREIGN KEY (material_id) REFERENCES material(id)
);

CREATE SEQUENCE IF NOT EXISTS user_table_seq;
CREATE TABLE IF NOT EXISTS user_table (
    id int DEFAULT nextval('user_table_seq') NOT NULL PRIMARY KEY,
    user_firstname varchar(255),
    user_email varchar(255),
    user_password varchar(255),
    user_role int
);