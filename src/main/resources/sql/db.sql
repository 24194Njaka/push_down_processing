-- 1. Création de l'utilisateur (Remplacer 'mon_mot_de_passe' par le vôtre)
CREATE USER hei_processing WITH PASSWORD 'hei1234';

-- 2. Création de la base de données (si non existante)
-- CREATE DATABASE hei_prog3;
-- \c hei_prog3; -- Se connecter à la base si vous utilisez psql

-- 3. Attribution des privilèges de connexion
GRANT CONNECT ON DATABASE postgres TO hei_processing;

-- 4. Autoriser l'usage du schéma public (où sont vos tables)
GRANT USAGE ON SCHEMA public TO hei_processing;

-- 5. Attribution des permissions sur les tables existantes
-- (À exécuter APRÈS avoir créé les tables avec votre compte admin)
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE invoice TO hei_processing;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE invoice_line TO hei_processing;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE tax_config TO hei_processing;

-- 6. TRES IMPORTANT : Privilèges sur les séquences (pour les colonnes SERIAL / AUTO_INCREMENT)
-- Sans cela, votre code Java plantera lors des INSERT car il ne pourra pas incrémenter les ID.
GRANT USAGE, SELECT ON SEQUENCE invoice_id_seq TO hei_processing;
GRANT USAGE, SELECT ON SEQUENCE invoice_line_id_seq TO hei_processing;
GRANT USAGE, SELECT ON SEQUENCE tax_config_id_seq TO hei_processing;

-- 7. Optionnel : Donner les droits par défaut pour les futures tables
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO hei_processing;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO hei_processing;