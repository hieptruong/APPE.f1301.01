ALTER TABLE `Bestellposition` ADD Abgerechnet bool not NULL;

DELIMITER //
DROP PROCEDURE IF EXISTS StockOrderReceived//

CREATE PROCEDURE StockOrderReceived(IN stockorderId int)
BEGIN
	DECLARE count INT;
	DECLARE productid INT;
	DECLARE posId INT;
	DECLARE orderid INT;
	DECLARE finished INT DEFAULT FALSE;
	DECLARE posCursor CURSOR for 
		Select ID, Bestellung_ID from Bestellposition 
			where Produkt_ID = @productid and Abgerechnet = false;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET finished = TRUE; 

	Select @count:=Z.Anzahl, @productid:=Z.Produkt_ID from ZentrallagerBestellung Z
		where Z.ID = stockorderId;
	Delete from ZentrallagerBestellung where ID = stockorderId;
	Update Produkt SET Lagerbestand = Lagerbestand + @count
		where ID = @productid;
	Select @productid, @count;
	OPEN posCursor;
	REPEAT
		FETCH posCursor into posId, orderId;
		IF NOT finished THEN
			Select UpdateStorage(posId);
			CALL UpdateOrderDate(orderId);
		END IF;
	UNTIL finished END REPEAT;
	CLOSE posCursor;
END//

DROP PROCEDURE IF EXISTS UpdateOrderDate//

CREATE PROCEDURE UpdateOrderDate(orderid INT)
BEGIN
	DECLARE count INT;
	Select count(*) into @count from Bestellposition where Bestellung_ID = orderid AND Abgerechnet = false;
	
	IF @count = 0 THEN
		UPDATE Bestellung SET Liefertermin_Ist = SYSDATE() WHERE ID = orderid;
	END IF;
END//

DELIMITER ;