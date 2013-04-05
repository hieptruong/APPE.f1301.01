



-- -----------------------------------------------------------
-- Entity Designer DDL Script for MySQL Server 4.1 and higher
-- -----------------------------------------------------------
-- Date Created: 04/05/2013 15:22:58
-- Generated from EDMX file: C:\Users\bollha\Documents\HSLU\DMG\Semester\ConsoleApplication1\ConsoleApplication1\Model1.edmx
-- Target version: 2.0.0.0
-- --------------------------------------------------

DROP DATABASE IF EXISTS `grp01`;
CREATE DATABASE `grp01`;
USE `grp01`;

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- NOTE: if the constraint does not exist, an ignorable error will be reported.
-- --------------------------------------------------

--    ALTER TABLE `Bestellposition` DROP CONSTRAINT `FK_ProduktBestellposition`;
--    ALTER TABLE `Bestellposition` DROP CONSTRAINT `FK_BestellungBestellposition`;
--    ALTER TABLE `Korrespondenz` DROP CONSTRAINT `FK_PersonKorrespondenz`;
--    ALTER TABLE `Korrespondenz` DROP CONSTRAINT `FK_PersonKorrespondenz1`;
--    ALTER TABLE `Bestellung` DROP CONSTRAINT `FK_PersonBestellung`;
--    ALTER TABLE `Rechnung` DROP CONSTRAINT `FK_PersonRechnung`;
--    ALTER TABLE `Rechnung` DROP CONSTRAINT `FK_BestellungRechnung`;
--    ALTER TABLE `Bestellung` DROP CONSTRAINT `FK_PersonBestellung1`;

-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------
SET foreign_key_checks = 0;
    DROP TABLE IF EXISTS `Produkt`;
    DROP TABLE IF EXISTS `Bestellung`;
    DROP TABLE IF EXISTS `Bestellposition`;
    DROP TABLE IF EXISTS `Korrespondenz`;
    DROP TABLE IF EXISTS `Person`;
    DROP TABLE IF EXISTS `Rechnung`;
    DROP TABLE IF EXISTS `KorrespondenzTemplate`;
SET foreign_key_checks = 1;

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

CREATE TABLE `Produkt`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Bezeichnung` nvarchar (1000) NOT NULL, 
	`Preis` int NOT NULL, 
	`Lagerbestand` int NOT NULL, 
	`MinimalMenge` int NOT NULL);

ALTER TABLE `Produkt` ADD PRIMARY KEY (ID);




CREATE TABLE `Bestellung`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Bestelldatum` datetime NOT NULL, 
	`Liefertermin_Ist` datetime NOT NULL, 
	`Liefertermin_Soll` datetime NOT NULL, 
	`Quelle` int NOT NULL, 
	`Kunde_ID` int NOT NULL, 
	`Verkaeufer_ID` int NOT NULL);

ALTER TABLE `Bestellung` ADD PRIMARY KEY (ID);




CREATE TABLE `Bestellposition`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Anzahl` int NOT NULL, 
	`Stueckpreis` int NOT NULL, 
	`Produkt_ID` int NOT NULL, 
	`Bestellung_ID` int NOT NULL);

ALTER TABLE `Bestellposition` ADD PRIMARY KEY (ID);




CREATE TABLE `Korrespondenz`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Inhalt` nvarchar (1000) NOT NULL, 
	`Typ` int NOT NULL, 
	`User_ID` int NOT NULL, 
	`Kunde_ID` int NOT NULL);

ALTER TABLE `Korrespondenz` ADD PRIMARY KEY (ID);




CREATE TABLE `Person`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Name` nvarchar (1000) NOT NULL, 
	`Vorname` nvarchar (1000) NOT NULL, 
	`Strasse` nvarchar (1000) NOT NULL, 
	`Ort` nvarchar (1000) NOT NULL, 
	`Geburtstag` datetime NOT NULL, 
	`PLZ` int NOT NULL, 
	`EMail` nvarchar (1000) NOT NULL, 
	`Passwort` nvarchar (1000), 
	`Rolle` int NOT NULL, 
	`Benutzername` nvarchar (1000), 
	`Aktiv` bool NOT NULL);

ALTER TABLE `Person` ADD PRIMARY KEY (ID);




CREATE TABLE `Rechnung`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Mahnstufe` int NOT NULL, 
	`Betrag` int NOT NULL, 
	`Bezahlter_Betrag` int NOT NULL, 
	`ZahlbarBis` datetime NOT NULL, 
	`Person_ID` int NOT NULL, 
	`Bestellung_ID` int NOT NULL);

ALTER TABLE `Rechnung` ADD PRIMARY KEY (ID);




CREATE TABLE `KorrespondenzTemplate`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Inhalt` nvarchar (1000) NOT NULL, 
	`Typ` int NOT NULL);

ALTER TABLE `KorrespondenzTemplate` ADD PRIMARY KEY (ID);




CREATE TABLE `ZentrallagerBestellung`(
	`ID` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Anzahl` varchar (1000) NOT NULL, 
	`Liefertermin` datetime NOT NULL, 
	`Produkt_ID` int NOT NULL);

ALTER TABLE `ZentrallagerBestellung` ADD PRIMARY KEY (ID);






-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on `Produkt_ID` in table 'Bestellposition'

ALTER TABLE `Bestellposition`
ADD CONSTRAINT `FK_ProduktBestellposition`
    FOREIGN KEY (`Produkt_ID`)
    REFERENCES `Produkt`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ProduktBestellposition'

CREATE INDEX `IX_FK_ProduktBestellposition` 
    ON `Bestellposition`
    (`Produkt_ID`);

-- Creating foreign key on `Bestellung_ID` in table 'Bestellposition'

ALTER TABLE `Bestellposition`
ADD CONSTRAINT `FK_BestellungBestellposition`
    FOREIGN KEY (`Bestellung_ID`)
    REFERENCES `Bestellung`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_BestellungBestellposition'

CREATE INDEX `IX_FK_BestellungBestellposition` 
    ON `Bestellposition`
    (`Bestellung_ID`);

-- Creating foreign key on `User_ID` in table 'Korrespondenz'

ALTER TABLE `Korrespondenz`
ADD CONSTRAINT `FK_PersonKorrespondenz`
    FOREIGN KEY (`User_ID`)
    REFERENCES `Person`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_PersonKorrespondenz'

CREATE INDEX `IX_FK_PersonKorrespondenz` 
    ON `Korrespondenz`
    (`User_ID`);

-- Creating foreign key on `Kunde_ID` in table 'Korrespondenz'

ALTER TABLE `Korrespondenz`
ADD CONSTRAINT `FK_PersonKorrespondenz1`
    FOREIGN KEY (`Kunde_ID`)
    REFERENCES `Person`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_PersonKorrespondenz1'

CREATE INDEX `IX_FK_PersonKorrespondenz1` 
    ON `Korrespondenz`
    (`Kunde_ID`);

-- Creating foreign key on `Kunde_ID` in table 'Bestellung'

ALTER TABLE `Bestellung`
ADD CONSTRAINT `FK_PersonBestellung`
    FOREIGN KEY (`Kunde_ID`)
    REFERENCES `Person`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_PersonBestellung'

CREATE INDEX `IX_FK_PersonBestellung` 
    ON `Bestellung`
    (`Kunde_ID`);

-- Creating foreign key on `Person_ID` in table 'Rechnung'

ALTER TABLE `Rechnung`
ADD CONSTRAINT `FK_PersonRechnung`
    FOREIGN KEY (`Person_ID`)
    REFERENCES `Person`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_PersonRechnung'

CREATE INDEX `IX_FK_PersonRechnung` 
    ON `Rechnung`
    (`Person_ID`);

-- Creating foreign key on `Bestellung_ID` in table 'Rechnung'

ALTER TABLE `Rechnung`
ADD CONSTRAINT `FK_BestellungRechnung`
    FOREIGN KEY (`Bestellung_ID`)
    REFERENCES `Bestellung`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_BestellungRechnung'

CREATE INDEX `IX_FK_BestellungRechnung` 
    ON `Rechnung`
    (`Bestellung_ID`);

-- Creating foreign key on `Verkaeufer_ID` in table 'Bestellung'

ALTER TABLE `Bestellung`
ADD CONSTRAINT `FK_PersonBestellung1`
    FOREIGN KEY (`Verkaeufer_ID`)
    REFERENCES `Person`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_PersonBestellung1'

CREATE INDEX `IX_FK_PersonBestellung1` 
    ON `Bestellung`
    (`Verkaeufer_ID`);

-- Creating foreign key on `Produkt_ID` in table 'ZentrallagerBestellung'

ALTER TABLE `ZentrallagerBestellung`
ADD CONSTRAINT `FK_ProduktZentrallagerBestellung`
    FOREIGN KEY (`Produkt_ID`)
    REFERENCES `Produkt`
        (`ID`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ProduktZentrallagerBestellung'

CREATE INDEX `IX_FK_ProduktZentrallagerBestellung` 
    ON `ZentrallagerBestellung`
    (`Produkt_ID`);

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------
