-- Suppression des contraintes de clé étrangère
SET REFERENTIAL_INTEGRITY FALSE;

-- Suppression des tables dans l'ordre
DROP TABLE IF EXISTS emplois_du_temps;
DROP TABLE IF EXISTS etudiants;
DROP TABLE IF EXISTS cours;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS professeurs;

-- Création des tables
CREATE TABLE classes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    niveau VARCHAR(255) NOT NULL,
    capacite INT NOT NULL,
    nombre_etudiants INT,
    annee_scolaire VARCHAR(255) NOT NULL
);

CREATE TABLE professeurs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matricule VARCHAR(255) NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telephone VARCHAR(255),
    specialite VARCHAR(255)
);

CREATE TABLE cours (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code_cours VARCHAR(255),
    nom_cours VARCHAR(255),
    coefficient INT,
    professeur_id BIGINT,
    FOREIGN KEY (professeur_id) REFERENCES professeurs(id)
);

CREATE TABLE etudiants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_etudiant VARCHAR(255) NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    date_naissance VARCHAR(255),
    classe_id BIGINT NOT NULL,
    FOREIGN KEY (classe_id) REFERENCES classes(id)
);

CREATE TABLE emplois_du_temps (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cours_id BIGINT,
    classe_id BIGINT,
    date_debut DATETIME,
    date_fin DATETIME,
    salle VARCHAR(255),
    FOREIGN KEY (cours_id) REFERENCES cours(id),
    FOREIGN KEY (classe_id) REFERENCES classes(id)
);

-- Réactivation des contraintes
SET REFERENTIAL_INTEGRITY TRUE; 