CREATE TRIGGER Product_Update_Trigger
BEFORE Update ON Produkt
FOR EACH ROW 
BEGIN
	IF NEW.Lagerbestand < 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Not enough quantity.";
	END IF;
END

DELIMITER //
DROP TRIGGER IF EXISTS Order_Product_Trigger//

CREATE TRIGGER Order_Product_Trigger
BEFORE INSERT ON Bestellposition
FOR EACH ROW 
BEGIN
	IF NEW.Abgerechnet THEN
		UPDATE Produkt 
		Set Lagerbestand = Lagerbestand - NEW.Anzahl
		WHERE ID = NEW.Produkt_ID;
	END IF;
END//

DROP TRIGGER IF EXISTS Order_Product_Update_Trigger//

CREATE TRIGGER Order_Product_Update_Trigger
BEFORE UPDATE ON Bestellposition
FOR EACH ROW 
BEGIN
	IF NEW.Abgerechnet AND NOT OLD.Abgerechnet THEN
		UPDATE Produkt 
		Set Lagerbestand = Lagerbestand - NEW.Anzahl
		WHERE ID = NEW.Produkt_ID;
	END IF;
END//

DELIMITER ;