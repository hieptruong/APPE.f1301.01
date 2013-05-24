DELIMITER //
DROP PROCEDURE IF EXISTS OrderProduct//

CREATE PROCEDURE OrderProduct(orderId int, count int, productid int, price int, fromLocalStock bool)
RETURNS INT
BEGIN
	IF fromLocalStock THEN
		IF NOT CanOrderProduct(productid, count) THEN
			RETURN 0;
		ELSE
			INSERT INTO Bestellposition VALUES (NULL, count, price, productid, orderid, false);
			RETURN UpdateStorage(last_insert_id());
		END IF;
	ELSE
		INSERT INTO Bestellposition VALUES (NULL, count, price, productid, orderid, fromLocalStock);
		RETURN 1;
	END IF;
END//

DROP PROCEDURE IF EXISTS UpdateStorage//

CREATE PROCEDURE UpdateStorage(positionid int)
RETURNS BOOL
BEGIN
	DECLARE productid INT;
	DECLARE count INT;
	DECLARE alreadyDone BOOL;

	SELECT Produkt_ID, Anzahl, Abgerechnet into @productid, @count, @alreadyDone from Bestellposition where ID = positionid;

	IF @alreadyDone THEN
		RETURN 0;
	END IF;

	IF NOT CanOrderProduct(@productid, @count) THEN
		RETURN 0;		
	ELSE
		UPDATE Produkt Set Lagerbestand = Lagerbestand - @count WHERE ID = @productid;
		UPDATE Bestellposition SET Abgerechnet = true WHERE ID = positionid;
		RETURN 1;
	END IF;
END//

DROP FUNCTION IF EXISTS CanOrderProduct//

CREATE FUNCTION CanOrderProduct(productid int, count int)
RETURNS BOOL
BEGIN
	DECLARE remCount INT;

	SELECT Lagerbestand into @remCount from Produkt Where Id = productid;

	RETURN @remCount - count >= 0;
END//

DELIMITER ;