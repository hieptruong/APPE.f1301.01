ALTER TABLE `Bestellposition` ADD Abgerechnet bool not NULL;

DELIMITER //
DROP PROCEDURE IF EXISTS StockOrderReceived//

CREATE PROCEDURE StockOrderReceived(IN orderId int)
BEGIN
	DECLARE count INT;
	DECLARE productid INT;
	DECLARE posId INT;
	DECLARE posCount INT;
	DECLARE finished INT DEFAULT FALSE;
	DECLARE posCursor CURSOR for 
		Select ID, Anzahl from Bestellposition 
			where Produkt_ID = @productid and Abgerechnet = false;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET finished = TRUE; 

	Select @count:=Z.Anzahl, @productid:=Z.Produkt_ID from ZentrallagerBestellung Z
		where Z.ID = orderId;
	Delete from ZentrallagerBestellung where ID = orderId;
	Update Produkt SET Lagerbestand = Lagerbestand + @count
		where ID = @productid;
	Select @count:=P.Lagerbestand from Produkt P where P.ID = @productid;
	OPEN posCursor;
	REPEAT
		FETCH posCursor into posId, posCount;
		IF NOT finished THEN
			IF posCount <= @count THEN
				UPDATE Bestellposition SET Abgerechnet = true
					where ID = posID;
				SET @count = @count - posCount;
			END IF;
		END IF;
	UNTIL finished END REPEAT;
	CLOSE posCursor;
END//

DELIMITER ;