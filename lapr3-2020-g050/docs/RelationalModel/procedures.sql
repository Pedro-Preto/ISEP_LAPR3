delimiter /

-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addAddress(A_ID ADDRESS.ID%TYPE,
			A_STREET ADDRESS.STREET%TYPE,
			A_DOORNUMBER ADDRESS.DOORNUMBER%TYPE,
			A_CITY ADDRESS.CITY%TYPE,
			A_COUNTRY ADDRESS.COUNTRY%TYPE)
AS
BEGIN
	INSERT INTO ADDRESS VALUES (A_ID, A_STREET, A_DOORNUMBER, A_CITY, A_COUNTRY);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeAddress(A_ID ADDRESS.ID%TYPE)
AS
    addressNotFoundException EXCEPTION;
    ocurrences NUMBER;
BEGIN
    SELECT COUNT(A.ID)
    INTO ocurrences
    FROM ADDRESS A
    WHERE A.ID = A_ID;

    if ocurrences = 0 then
        raise addressNotFoundException;
    end if;

	DELETE FROM ADDRESS WHERE ADDRESS.ID = A_ID;

EXCEPTION
    WHEN addressNotFoundException then
        raise_application_error(-20002,'There is no address with the id ' || A_ID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addChargingStation(CS_PARKSLOTID CHARGINGSTATION.PARKSLOTID%TYPE,
                                            CS_POWER CHARGINGSTATION.POWER%TYPE,
                                            CS_CAPACITY CHARGINGSTATION.CAPACITY%TYPE)
AS
BEGIN
	INSERT INTO CHARGINGSTATION VALUES (CS_PARKSLOTID, CS_POWER, CS_CAPACITY);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeChargingStation(CS_PARKSLOTID CHARGINGSTATION.PARKSLOTID%TYPE)
AS
BEGIN
	DELETE FROM CHARGINGSTATION WHERE CS_PARKSLOTID = CHARGINGSTATION.PARKSLOTID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addClient(C_NIF CLIENT.NIF%TYPE,
			C_EMAIL CLIENT.EMAILADDRESS%TYPE,
			C_NAME CLIENT.NAME%TYPE,
			C_ADDRESSID CLIENT.ADDRESSID%TYPE)
AS
BEGIN
	INSERT INTO CLIENT VALUES(C_NIF, C_EMAIL, C_NAME, C_ADDRESSID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeClient(C_NIF CLIENT.NIF%TYPE)
AS
BEGIN
	DELETE FROM CLIENT WHERE C_NIF = CLIENT.NIF;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addCourier(C_NIF COURIER.NIF%TYPE,
			C_EMAIL COURIER.EMAILADDRESS%TYPE,
			C_NAME COURIER.NAME%TYPE,
			C_IBAN COURIER.IBAN%TYPE,
			C_CELLPHONE COURIER.CELLPHONE%TYPE,
			C_WEIGHT COURIER.WEIGHT%TYPE,
			C_PHARMACYNIF COURIER.PHARMACYNIF%TYPE,
			C_PAYLOAD COURIER.PAYLOAD%TYPE)
AS
BEGIN
	INSERT INTO COURIER VALUES (C_NIF, C_EMAIL, C_NAME, C_IBAN, C_CELLPHONE, C_WEIGHT, C_PHARMACYNIF, C_PAYLOAD);
END;
/			
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeCourier(C_NIF COURIER.NIF%TYPE)
AS
BEGIN
	DELETE FROM COURIER WHERE C_NIF = COURIER.NIF;
END;
/			
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addCredits (C_ID CREDITS.ID%TYPE,
			C_NUMBEROFCREDITS CREDITS.NUMBEROFCREDITS%TYPE,
			C_CLIENTNIF CREDITS.CLIENTNIF%TYPE)
AS
BEGIN
	INSERT INTO CREDITS VALUES (C_ID, C_NUMBEROFCREDITS, C_CLIENTNIF);
END;
/
------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeCredits (C_ID CREDITS.ID%TYPE)
AS
BEGIN
	DELETE FROM CREDITS WHERE C_ID = CREDITS.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addDrone (V_ID VEHICLE.ID%TYPE,
                                    V_BATTERYSIZE VEHICLE.BATTERYSIZE%TYPE,
                                    V_BATTERYCONSUMPTION VEHICLE.BATTERYCONSUMPTION%TYPE,
                                    V_BATTERYEFFICIENCY VEHICLE.BATTERYEFFICIENCY%TYPE,
                                    V_CURRENTBATTERY VEHICLE.CURRENTBATTERY%TYPE,
                                    V_WEIGHT VEHICLE.WEIGHT%TYPE,
                                    V_FRONTALAREA VEHICLE.FRONTALAREA%TYPE,
                                    V_MOTORPOWER VEHICLE.MOTORPOWER%TYPE,
                                    V_PHARMACYNIF VEHICLE.PHARMACYNIF%TYPE,
                                    D_PAYLOAD DRONE.PAYLOAD%TYPE)
AS
BEGIN
    addVehicle(V_ID, V_BATTERYSIZE, V_BATTERYCONSUMPTION, V_BATTERYEFFICIENCY, 
        V_CURRENTBATTERY, V_WEIGHT, V_FRONTALAREA, V_MOTORPOWER, V_PHARMACYNIF);
	INSERT INTO DRONE VALUES (D_PAYLOAD, V_ID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeDrone (D_VEHICLEID DRONE.VEHICLEID%TYPE)
AS
BEGIN
	DELETE FROM DRONE WHERE D_VEHICLEID = DRONE.VEHICLEID;
	removeVehicle(D_VEHICLEID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addGeoLocation (GL_ID GEOLOCATION.ID%TYPE,
				GL_LATITUDE GEOLOCATION.LATITUDE%TYPE,
				GL_LONGITUDE GEOLOCATION.LONGITUDE%TYPE,
				GL_ALTITUDE GEOLOCATION.ALTITUDE%TYPE,
				GL_ADDRESSID GEOLOCATION.ADDRESSID%TYPE)
AS
BEGIN
	INSERT INTO GEOLOCATION VALUES (GL_ID, GL_LATITUDE, GL_LONGITUDE, GL_ALTITUDE, GL_ADDRESSID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeGeoLocation (GL_ID GEOLOCATION.ID%TYPE)
AS
BEGIN
	DELETE FROM GEOLOCATION WHERE GL_ID = GEOLOCATION.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addItem (I_ID ITEM.ID%TYPE,
			I_NAME ITEM.NAME%TYPE,
			I_PRICE ITEM.PRICE%TYPE,
			I_WEIGHT ITEM.WEIGHT%TYPE)
AS
BEGIN
	INSERT INTO ITEM VALUES (I_ID, I_NAME, I_PRICE, I_WEIGHT);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeItem (I_ID ITEM.ID%TYPE)
AS
BEGIN
	DELETE FROM ITEM WHERE I_ID = ITEM.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addOrderItem (OI_ITEMID ORDERITEM.ITEMID%TYPE,
							OI_ORDERID ORDERITEM.ORDERID%TYPE,
							OI_ITEMSNQUANTITY ORDERITEM.ITEMSQUANTITY%TYPE)
AS
BEGIN
	INSERT INTO ORDERITEM VALUES (OI_ITEMID, OI_ORDERID, OI_ITEMSNQUANTITY);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeOrderItem (OI_ITEMID ORDERITEM.ITEMID%TYPE,
							OI_ORDERID ORDERITEM.ORDERID%TYPE)
AS
BEGIN
	DELETE FROM ORDERITEM WHERE (OI_ITEMID = ORDERITEM.ITEMID AND
								OI_ORDERID = ORDERITEM.ORDERID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addOrder (O_ID "Order".id%type,
						O_STATUS "Order".STATUS%TYPE,
						O_ITEMSPRICE "Order".ITEMSPRICE%TYPE,
						O_CLIENTNIF "Order".CLIENTNIF%TYPE)
AS
BEGIN
	INSERT INTO "Order" VALUES (O_ID, O_STATUS, O_ITEMSPRICE, O_CLIENTNIF);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeOrder (O_ID "Order".ID%TYPE)
AS
BEGIN
	DELETE FROM "Order" WHERE O_ID = "Order".ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addPark (P_ID PARK.ID%TYPE,
						P_PHARMACYNIF PARK.PHARMACYNIF%TYPE,
						P_POWER PARK.POWER%TYPE)
AS
BEGIN
	INSERT INTO PARK VALUES (P_ID, P_PHARMACYNIF, P_POWER);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removePark (P_ID PARK.ID%TYPE)
AS
BEGIN
	DELETE FROM PARK WHERE P_ID = PARK.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addParkSlot (PS_ID PARKSLOT.ID%TYPE,
							PS_PARKID PARKSLOT.PARKID%TYPE)
AS
BEGIN
	INSERT INTO PARKSLOT(ID, PARKID) VALUES (PS_ID,PS_PARKID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeParkSlot (PS_ID PARKSLOT.ID%TYPE)
AS
BEGIN
	DELETE FROM PARKSLOT WHERE PS_ID = PARKSLOT.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addPharmacyAdministrator (PA_EMAIL PHARMACYADMINISTRATOR.EMAIL%TYPE,
										PA_NAME PHARMACYADMINISTRATOR.NAME%TYPE,
										PA_PHARMACYNIF PHARMACYADMINISTRATOR.PHARMACYNIF%TYPE)
AS
BEGIN
	INSERT INTO PHARMACYADMINISTRATOR VALUES (PA_EMAIL, PA_NAME, PA_PHARMACYNIF);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removePharmacyAdministrator (PA_EMAIL PHARMACYADMINISTRATOR.EMAIL%TYPE)
AS
BEGIN
	DELETE FROM PHARMACYADMINISTRATOR WHERE PA_EMAIL = PHARMACYADMINISTRATOR.EMAIL;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addPharmacy (P_NIF PHARMACY.NIF%TYPE,
							P_DESIGNATION PHARMACY.DESIGNATION%TYPE,
							P_ADDRESSID PHARMACY.ADDRESSID%TYPE)
AS
BEGIN
	INSERT INTO PHARMACY VALUES (P_NIF, P_DESIGNATION, P_ADDRESSID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removePharmacy (P_NIF PHARMACY.NIF%TYPE)
AS
BEGIN
	DELETE FROM PHARMACY WHERE P_NIF = PHARMACY.NIF;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addReceipt (R_ID RECEIPT.ID%TYPE,
							R_TOTALPRICE RECEIPT.TOTALPRICE%TYPE,
							R_ORDERID RECEIPT.ORDERID%TYPE,
							R_CLIENTNIF RECEIPT.CLIENTNIF%TYPE,
							R_PHARMACYID RECEIPT.PHARMACYNIF%TYPE)
AS
BEGIN
	INSERT INTO RECEIPT VALUES (R_ID, R_TOTALPRICE, R_ORDERID, R_CLIENTNIF, R_PHARMACYID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeReceipt (R_ID RECEIPT.ID%TYPE)
AS
BEGIN
	DELETE FROM RECEIPT WHERE R_ID = RECEIPT.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addScooter (V_ID VEHICLE.ID%TYPE,
							V_BATTERYSIZE VEHICLE.BATTERYSIZE%TYPE,
							V_BATTERYCONSUMPTION VEHICLE.BATTERYCONSUMPTION%TYPE,
							V_BATTERYEFFICIENCY VEHICLE.BATTERYEFFICIENCY%TYPE,
							V_CURRENTBATTERY VEHICLE.CURRENTBATTERY%TYPE,
							V_WEIGHT VEHICLE.WEIGHT%TYPE,
							V_FRONTALAREA VEHICLE.FRONTALAREA%TYPE,
							V_MOTORPOWER VEHICLE.MOTORPOWER%TYPE,
							V_PHARMACYNIF VEHICLE.PHARMACYNIF%TYPE,
                            S_QRCODE SCOOTER.QRCODE%TYPE)
AS
BEGIN
    addVehicle(V_ID, V_BATTERYSIZE, V_BATTERYCONSUMPTION, V_BATTERYEFFICIENCY, 
            V_CURRENTBATTERY, V_WEIGHT, V_FRONTALAREA, V_MOTORPOWER, V_PHARMACYNIF);
	INSERT INTO SCOOTER VALUES (S_QRCODE, V_ID);
    
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeScooter (S_VEHICLEID SCOOTER.VEHICLEID%TYPE)
AS
BEGIN
	DELETE FROM SCOOTER WHERE S_VEHICLEID = SCOOTER.VEHICLEID;
	removeVehicle(S_VEHICLEID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addStock (S_ID STOCK.ID%TYPE,
						S_PHARMACYNIF STOCK.PHARMACYNIF%TYPE)
AS
BEGIN
	INSERT INTO STOCK VALUES (S_ID, S_PHARMACYNIF);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeStock (S_ID STOCK.ID%TYPE)
AS
BEGIN
	DELETE FROM STOCK WHERE S_ID = STOCK.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addStockItem (SI_ITEMID STOCKITEM.ITEMID%TYPE,
							SI_STOCKID STOCKITEM.STOCKID%TYPE,
							SI_ITEMSQUANTITY STOCKITEM.ITEMSQUANTITY%TYPE)
AS
BEGIN
	INSERT INTO STOCKITEM VALUES (SI_ITEMID, SI_STOCKID, SI_ITEMSQUANTITY);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeStockItem (SI_ITEMID STOCKITEM.ITEMID%TYPE,SI_STOCKID STOCKITEM.STOCKID%TYPE)
AS
BEGIN
    DELETE FROM STOCKITEM WHERE SI_ITEMID = STOCKITEM.ITEMID AND SI_STOCKID = STOCKITEM.STOCKID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addPath (P_GEOLOCATIONIDSTART PATH.GEOLOCATIONIDSTART%TYPE,
						P_GEOLOCATIONIDEND PATH.GEOLOCATIONIDEND%TYPE,
						P_DISTANCE PATH.DISTANCE%TYPE,
						P_WINDDIRECTION PATH.WINDDIRECTION%TYPE,
						P_WINDVELOCITY PATH.WINDVELOCITY%TYPE)
AS
BEGIN
	INSERT INTO PATH VALUES (P_GEOLOCATIONIDSTART, P_GEOLOCATIONIDEND, P_DISTANCE, P_WINDDIRECTION, P_WINDVELOCITY);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removePath (P_GEOLOCATIONIDSTART PATH.GEOLOCATIONIDSTART%TYPE,
									P_GEOLOCATIONIDEND PATH.GEOLOCATIONIDEND%TYPE)
AS
BEGIN
	DELETE FROM PATH WHERE (P_GEOLOCATIONIDSTART = PATH.GEOLOCATIONIDSTART
						AND P_GEOLOCATIONIDEND = PATH.GEOLOCATIONIDEND);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addLandPath (P_GEOLOCATIONIDSTART PATH.GEOLOCATIONIDSTART%TYPE,
									P_GEOLOCATIONIDEND PATH.GEOLOCATIONIDEND%TYPE,
                                    P_DISTANCE PATH.DISTANCE%TYPE,
                                    P_WINDDIRECTION PATH.WINDDIRECTION%TYPE,
                                    P_WINDVELOCITY PATH.WINDVELOCITY%TYPE,
                                    LP_ROADRESISTANCECOEFFICIENT LANDPATH.ROADRESISTANCECOEFFICIENT%TYPE,
                                    LP_ELEVATION LANDPATH.ELEVATION%TYPE)
AS
    ocorrences number;
BEGIN
    select count(*)
        into ocorrences
            from PATH
                where GEOLOCATIONIDEND = P_GEOLOCATIONIDEND and GEOLOCATIONIDSTART = P_GEOLOCATIONIDSTART;

    if(ocorrences = 0) then
            addPath(P_GEOLOCATIONIDSTART, P_GEOLOCATIONIDEND, P_DISTANCE, P_WINDDIRECTION, P_WINDVELOCITY);
    end if;

	INSERT INTO LANDPATH VALUES (LP_ROADRESISTANCECOEFFICIENT, LP_ELEVATION, P_GEOLOCATIONIDEND, P_GEOLOCATIONIDSTART);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeLandPath (LP_PATHGEOLOCATIONIDSTART LANDPATH.PATHGEOLOCATIONIDSTART%TYPE,
                                            LP_PATHGEOLOCATIONIDEND LANDPATH.PATHGEOLOCATIONIDEND%TYPE)
AS
BEGIN
	DELETE FROM LANDPATH WHERE (LP_PATHGEOLOCATIONIDSTART = LANDPATH.PATHGEOLOCATIONIDSTART
								AND LP_PATHGEOLOCATIONIDEND = LANDPATH.PATHGEOLOCATIONIDEND);
	removePath(LP_PATHGEOLOCATIONIDSTART, LP_PATHGEOLOCATIONIDEND);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addAerialPath (P_GEOLOCATIONIDSTART PATH.GEOLOCATIONIDSTART%TYPE,
                                        P_GEOLOCATIONIDEND PATH.GEOLOCATIONIDEND%TYPE,
                                        P_DISTANCE PATH.DISTANCE%TYPE,
                                        P_WINDDIRECTION PATH.WINDDIRECTION%TYPE,
                                        P_WINDVELOCITY PATH.WINDVELOCITY%TYPE,
                                        AP_AIRDENSITY AERIALPATH.AIRDENSITY%TYPE,
                                        AP_AIRDRAGCOEFFICIENT AERIALPATH.AIRDRAGCOEFFICIENT%TYPE)
AS
    ocorrences number;
BEGIN
    select count(*)
        into ocorrences
            from PATH
                where GEOLOCATIONIDEND = P_GEOLOCATIONIDEND and GEOLOCATIONIDSTART = P_GEOLOCATIONIDSTART;

    if(ocorrences = 0) then
            addPath(P_GEOLOCATIONIDSTART, P_GEOLOCATIONIDEND, P_DISTANCE, P_WINDDIRECTION, P_WINDVELOCITY);
    end if;
    INSERT INTO AERIALPATH VALUES (AP_AIRDENSITY, AP_AIRDRAGCOEFFICIENT, P_GEOLOCATIONIDEND,P_GEOLOCATIONIDSTART);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeAerialPath (AP_PATHGEOLOCATIONIDSTART AERIALPATH.PATHGEOLOCATIONIDSTART%TYPE,
                                              AP_PATHGEOLOCATIONIDEND AERIALPATH.PATHGEOLOCATIONIDEND%TYPE)
AS
    pathNotFound EXCEPTION;
    occurrences NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO occurrences
    FROM AERIALPATH AP
    WHERE (AP.PATHGEOLOCATIONIDSTART = AP_PATHGEOLOCATIONIDSTART AND
          AP.PATHGEOLOCATIONIDEND = AP_PATHGEOLOCATIONIDEND);

    if occurrences = 0 then
        raise pathNotFound;
    end if;

	DELETE FROM AERIALPATH WHERE (AP_PATHGEOLOCATIONIDEND = AERIALPATH.PATHGEOLOCATIONIDEND AND
								AP_PATHGEOLOCATIONIDSTART = AERIALPATH.PATHGEOLOCATIONIDSTART);
	removePath(AP_PATHGEOLOCATIONIDSTART, AP_PATHGEOLOCATIONIDEND);
EXCEPTION
     WHEN pathNotFound THEN
            raise_application_error(-20001,'There is no path with the start id of '|| AP_PATHGEOLOCATIONIDSTART || ' and end id of'
                || AP_PATHGEOLOCATIONIDEND);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addScooterPark (SP_PARKID SCOOTERPARK.PARKID%TYPE,
                                        SP_PHARMACYNIF PARK.PHARMACYNIF%TYPE,
                                        SP_POWER PARK.POWER%TYPE)
AS
BEGIN
    addPark(SP_PARKID, SP_PHARMACYNIF, SP_POWER);
	INSERT INTO SCOOTERPARK VALUES (SP_PARKID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeScooterPark (SP_PARKID SCOOTERPARK.PARKID%TYPE)
AS
BEGIN
	DELETE FROM SCOOTERPARK WHERE SP_PARKID = SCOOTERPARK.PARKID;
	removePark(SP_PARKID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addDronePark (DP_PARKID DRONEPARK.PARKID%TYPE,
                                        DP_PHARMACYNIF PARK.PHARMACYNIF%TYPE,
                                        DP_POWER PARK.POWER%TYPE)
AS
BEGIN
    addPark(DP_PARKID, DP_PHARMACYNIF, DP_POWER);
	INSERT INTO DRONEPARK VALUES (DP_PARKID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeDronePark (DP_PARKID DRONEPARK.PARKID%TYPE)
AS
BEGIN
	DELETE FROM DRONEPARK WHERE DP_PARKID = DRONEPARK.PARKID;
	removePark(DP_PARKID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addVehicle (V_ID VEHICLE.ID%TYPE,
							V_BATTERYSIZE VEHICLE.BATTERYSIZE%TYPE,
							V_BATTERYCONSUMPTION VEHICLE.BATTERYCONSUMPTION%TYPE,
							V_BATTERYEFFICIENCY VEHICLE.BATTERYEFFICIENCY%TYPE,
							V_CURRENTBATTERY VEHICLE.CURRENTBATTERY%TYPE,
							V_WEIGHT VEHICLE.WEIGHT%TYPE,
							V_FRONTALAREA VEHICLE.FRONTALAREA%TYPE,
							V_MOTORPOWER VEHICLE.MOTORPOWER%TYPE,
							V_PHARMACYNIF VEHICLE.PHARMACYNIF%TYPE)
AS
BEGIN
	INSERT INTO VEHICLE VALUES (V_ID, V_BATTERYSIZE, V_BATTERYCONSUMPTION, V_BATTERYEFFICIENCY, V_CURRENTBATTERY, V_WEIGHT, V_FRONTALAREA, V_MOTORPOWER, V_PHARMACYNIF);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeVehicle (V_ID VEHICLE.ID%TYPE)
AS
BEGIN
	DELETE FROM VEHICLE WHERE V_ID = VEHICLE.ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addDelivery (D_ORDERID DELIVERY.ORDERID%TYPE,
							D_STATUS DELIVERY.STATUS%TYPE,
							D_SHIPPINGPRICE DELIVERY.SHIPPINGPRICE%TYPE)
AS
BEGIN
	INSERT INTO DELIVERY (ORDERID, STATUS, SHIPPINGPRICE) VALUES (D_ORDERID, D_STATUS, D_SHIPPINGPRICE);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeDelivery (D_ORDERID DELIVERY.ORDERID%TYPE)
AS
BEGIN
	DELETE FROM DELIVERY WHERE D_ORDERID = DELIVERY.ORDERID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addUser (U_EMAIL "User".EMAIL%TYPE,
                                    U_PASSWORD "User".PASSWORD%TYPE,
                                    U_USER_ROLE_ID "User".USERROLEID%TYPE)
AS
BEGIN
    INSERT INTO "User" VALUES (U_EMAIL, U_PASSWORD, U_USER_ROLE_ID);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeUser (U_EMAIL "User".EMAIL%TYPE)
AS
    userNotFoundException EXCEPTION;
    ocorrences NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO ocorrences
    from "User"
    where "User".EMAIL = U_EMAIL;

    if ocorrences = 0 then
        raise userNotFoundException;
    end if;

    DELETE FROM "User" WHERE U_EMAIL = "User".EMAIL;

exception
    when userNotFoundException then
        raise_application_error(-20005, 'There is no user with the email: ' || U_EMAIL);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addUserRole (UR_ID USERROLE.ID%TYPE,
                                        UR_ROLE USERROLE.ROLE%TYPE,
                                        UR_DESCRIPTION USERROLE.DESCRIPTION%TYPE)
AS
BEGIN
    INSERT INTO USERROLE VALUES (UR_ID, UR_ROLE, UR_DESCRIPTION);
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeUserRole (UR_ROLE USERROLE.ROLE%TYPE)
AS
    roleNotFoundException EXCEPTION;
    usedRoleException EXCEPTION;
    ocorrences NUMBER;
BEGIN
    select count(*)
    into ocorrences
    from USERROLE UR
    where UR.ROLE = UR_ROLE;

    if ocorrences = 0 then
        raise roleNotFoundException;
    end if;

    SELECT COUNT(*)
    INTO ocorrences
    FROM USERROLE UR
        INNER JOIN "User" U on U.USERROLEID = UR.ID
    WHERE UR.ROLE = UR_ROLE;

    if ocorrences > 0 then
        raise usedRoleException;
    end if;

    DELETE FROM USERROLE WHERE UR_ROLE = USERROLE.ROLE;

EXCEPTION
    WHEN roleNotFoundException THEN
        RAISE_APPLICATION_ERROR(-20006, 'Role with role name ' || UR_ROLE || ' is not registered on system.');
    when usedRoleException then
        raise_application_error(-20003,'User role is being used by ' || ocorrences || ' users.');
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE removeUserRoleAndUsers (UR_ID USERROLE.ID%TYPE)
AS
    roleNotFoundException EXCEPTION;
    usercursor SYS_REFCURSOR;
    email VARCHAR(255);
    ocorrences NUMBER;
BEGIN

    SELECT COUNT(*)
    INTO ocorrences
    FROM USERROLE UR
    WHERE UR.ID = UR_ID;

    if ocorrences = 0 then
        raise roleNotFoundException;
    end if;

    SELECT COUNT(*)
    INTO ocorrences
    FROM USERROLE UR
        INNER JOIN "User" U on U.USERROLEID = UR_ID;

    if ocorrences > 0 then
        OPEN usercursor for
            select "User".EMAIL from "User" INNER JOIN USERROLE on "User".USERROLEID = USERROLE.ID;

        loop
            fetch usercursor into email;
            exit when usercursor%notfound;
            REMOVEUSER(email);
        end loop;

        close usercursor;
    end if;

    DELETE FROM USERROLE WHERE UR_ID = USERROLE.ID;

EXCEPTION
    WHEN roleNotFoundException THEN
        RAISE_APPLICATION_ERROR(-20006, 'Role with id ' || UR_ID || ' is not registered on system.');
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE updateParkSlot (PS_ID PARKSLOT.ID%TYPE,
							VEHICLE_ID PARKSLOT.VEHICLEID%TYPE)
AS
BEGIN
	UPDATE PARKSLOT P
	    SET P.VEHICLEID = VEHICLE_ID
    where P.ID = PS_ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE updateOrder (O_ID "Order".id%type,
                                        O_STATUS "Order".STATUS%TYPE,
                                        O_ITEMSPRICE "Order".ITEMSPRICE%TYPE,
                                        O_CLIENTNIF "Order".CLIENTNIF%TYPE)
AS
BEGIN
	UPDATE "Order" O
	    SET O.ID = O_ID, O.STATUS = O_STATUS, O.ITEMSPRICE = O_ITEMSPRICE, O.CLIENTNIF = O_CLIENTNIF
    where O.ID = O_ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE updateCredits (C_ID CREDITS.ID%TYPE,
			C_NUMBEROFCREDITS CREDITS.NUMBEROFCREDITS%TYPE,
			C_CLIENTNIF CREDITS.CLIENTNIF%TYPE)
AS
BEGIN
    UPDATE CREDITS C
        SET C.ID = C_ID, C.NUMBEROFCREDITS = C_NUMBEROFCREDITS, C.CLIENTNIF = C_CLIENTNIF
    WHERE C.ID = C_ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addCourierToDelivery (D_ID DELIVERY.ORDERID%TYPE,
							D_COURIERNIF DELIVERY.COURIERNIF%TYPE)
AS
BEGIN
	UPDATE DELIVERY D 
        SET D.COURIERNIF = D_COURIERNIF 
    WHERE D.ORDERID = D_ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE addDroneToDelivery (D_ID DELIVERY.ORDERID%TYPE,
							D_VEHICLEID DELIVERY.DRONEVEHICLEID%TYPE)
AS
BEGIN
	UPDATE DELIVERY D 
        SET D.DRONEVEHICLEID = D_VEHICLEID
    WHERE D.ORDERID = D_ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE emptyParkSlot (PS_ID PARKSLOT.ID%TYPE)
AS
BEGIN
    UPDATE PARKSLOT PS
        SET PS.VEHICLEID = NULL
    WHERE PS.ID = PS_ID;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------