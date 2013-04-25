CREATE TRIGGER Product_Update_Trigger
BEFORE Update ON Produkt
FOR EACH ROW 
BEGIN
	IF NEW.Lagerbestand < 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Not enough quantity.";
	END IF;
END

CREATE TRIGGER Order_Product_Trigger
BEFORE INSERT ON Bestellposition
FOR EACH ROW 
BEGIN
	UPDATE Produkt 
	Set Lagerbestand = Lagerbestand - NEW.Anzahl
	WHERE ID = NEW.Produkt_ID;
END