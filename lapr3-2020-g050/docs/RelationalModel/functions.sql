delimiter /

-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAddress (A_ID ADDRESS.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	A_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN A_CURSOR FOR
		SELECT * FROM ADDRESS WHERE A_ID = ADDRESS.ID;
	RETURN A_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getChargingStation (CS_PARKSLOTID CHARGINGSTATION.PARKSLOTID%TYPE) RETURN SYS_REFCURSOR
AS 
	CS_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN CS_CURSOR FOR
		SELECT * FROM CHARGINGSTATION WHERE CS_PARKSLOTID = CHARGINGSTATION.PARKSLOTID;
	RETURN CS_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getClient(C_NIF CLIENT.NIF%TYPE) RETURN SYS_REFCURSOR
AS 
	C_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN C_CURSOR FOR
		SELECT * FROM CLIENT WHERE C_NIF = CLIENT.NIF;
	RETURN C_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getClientByEmail(C_EMAIL CLIENT.EMAILADDRESS%TYPE) RETURN SYS_REFCURSOR
AS 
	C_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN C_CURSOR FOR
		SELECT * FROM CLIENT WHERE C_EMAIL = CLIENT.EMAILADDRESS;
	RETURN C_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getCourier(C_NIF COURIER.NIF%TYPE) RETURN SYS_REFCURSOR
AS 
	C_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN C_CURSOR FOR
		SELECT * FROM COURIER WHERE C_NIF = COURIER.NIF;
	RETURN C_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getCredits (C_ID CREDITS.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	C_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN C_CURSOR FOR
		SELECT * FROM CREDITS WHERE C_ID = CREDITS.ID;
	RETURN C_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getCreditsByClientNif (C_NIF CREDITS.CLIENTNIF%TYPE) RETURN SYS_REFCURSOR
AS 
	C_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN C_CURSOR FOR
		SELECT * FROM CREDITS WHERE C_NIF = CREDITS.CLIENTNIF;
	RETURN C_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getDrone (D_VEHICLEID DRONE.VEHICLEID%TYPE) RETURN SYS_REFCURSOR
AS 
    D_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN D_CURSOR FOR
        SELECT V.ID, V.BATTERYSIZE, V.BATTERYCONSUMPTION, V.BATTERYEFFICIENCY, V.CURRENTBATTERY, V.WEIGHT,
        V.FRONTALAREA, V.MOTORPOWER, V.PHARMACYNIF, D.PAYLOAD FROM DRONE D
        INNER JOIN VEHICLE V ON V.ID = D.VEHICLEID
        WHERE D_VEHICLEID = V.ID;
    RETURN D_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getGeoLocation (GL_ID GEOLOCATION.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	GL_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN GL_CURSOR FOR
		SELECT * FROM GEOLOCATION WHERE GL_ID = GEOLOCATION.ID;
	RETURN GL_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getGeoLocationByAddressID (AD_ID ADDRESS.ID%TYPE) RETURN SYS_REFCURSOR
AS
	GL_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN GL_CURSOR FOR
		SELECT *
        FROM GEOLOCATION
        INNER JOIN ADDRESS A on A.ID = GEOLOCATION.ADDRESSID
	    WHERE A.ID = AD_ID;

	RETURN GL_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getItem (I_ID ITEM.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	I_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN I_CURSOR FOR
		SELECT * FROM ITEM WHERE I_ID = ITEM.ID;
	RETURN I_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getItemByName (I_NAME ITEM.NAME%TYPE) RETURN SYS_REFCURSOR
AS 
	I_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN I_CURSOR FOR
		SELECT * FROM ITEM WHERE I_NAME = ITEM.NAME;
	RETURN I_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllItems RETURN SYS_REFCURSOR
AS 
	I_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN I_CURSOR FOR
		SELECT * FROM ITEM;
	RETURN I_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getOrderItem (OI_ITEMID ORDERITEM.ITEMID%TYPE,
							OI_ORDERID OrderItem.ORDERID%TYPE) RETURN SYS_REFCURSOR
AS 
	OI_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN OI_CURSOR FOR
		SELECT * FROM ORDERITEM 
			WHERE (OI_ITEMID = ORDERITEM.ITEMID AND OI_ORDERID = ORDERITEM.ORDERID);
	RETURN OI_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllItemsOfOrder (OI_ORDERID OrderItem.ORDERID%TYPE) RETURN SYS_REFCURSOR
AS 
	OI_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN OI_CURSOR FOR
		SELECT * FROM ORDERITEM WHERE OI_ORDERID = ORDERITEM.ORDERID;
	RETURN OI_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getOrder (O_ID "Order".id%type) RETURN SYS_REFCURSOR
AS 
	O_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN O_CURSOR FOR
		SELECT * FROM "Order" WHERE O_ID = "Order".id;
	RETURN O_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getPark (P_ID PARK.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	P_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN P_CURSOR FOR
		SELECT * FROM PARK WHERE P_ID = PARK.ID;
	RETURN P_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getParkSlot (PS_ID PARKSLOT.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	PS_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN PS_CURSOR FOR
		SELECT * FROM PARKSLOT WHERE PS_ID = PARKSLOT.ID;
	RETURN PS_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getPharmacyAdministrator (PA_EMAIL PHARMACYADMINISTRATOR.EMAIL%TYPE) RETURN SYS_REFCURSOR
AS 
	PA_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN PA_CURSOR FOR
		SELECT * FROM PHARMACYADMINISTRATOR WHERE PA_EMAIL = PHARMACYADMINISTRATOR.EMAIL;
	RETURN PA_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getPharmacy (P_NIF PHARMACY.NIF%TYPE) RETURN SYS_REFCURSOR
AS 
	P_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN P_CURSOR FOR
		SELECT * FROM PHARMACY WHERE P_NIF = PHARMACY.NIF;
	RETURN P_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getReceipt (R_ID RECEIPT.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	R_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN R_CURSOR FOR
		SELECT * FROM RECEIPT WHERE R_ID = RECEIPT.ID;
	RETURN R_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getReceiptByPharmacyNif (R_PHARNIF RECEIPT.PHARMACYNIF%TYPE) RETURN SYS_REFCURSOR
AS 
	R_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN R_CURSOR FOR
		SELECT *
		FROM RECEIPT R
		INNER JOIN PHARMACY P on R.PHARMACYNIF = P.NIF
		WHERE R.PHARMACYNIF = R_PHARNIF
	    ORDER BY R.ID;
	RETURN R_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getScooter (S_VEHICLEID SCOOTER.VEHICLEID%TYPE) RETURN SYS_REFCURSOR
AS 
    S_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN S_CURSOR FOR
        SELECT V.ID, V.BATTERYSIZE, V.BATTERYCONSUMPTION, V.BATTERYEFFICIENCY, V.CURRENTBATTERY, V.WEIGHT,
        V.FRONTALAREA, V.MOTORPOWER, V.PHARMACYNIF, S.QRCODE FROM SCOOTER S
        INNER JOIN VEHICLE V ON V.ID = S.VEHICLEID
        WHERE S_VEHICLEID = V.ID;
    RETURN S_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getScooterByQrCode (S_QRCODE SCOOTER.QRCODE%TYPE) RETURN SYS_REFCURSOR
AS 
	S_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN S_CURSOR FOR
		SELECT * FROM SCOOTER WHERE S_QRCODE = SCOOTER.QRCODE;
	RETURN S_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getStockByPharmacyNif (S_NIF STOCK.PHARMACYNIF%TYPE) RETURN SYS_REFCURSOR
AS 
	S_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN S_CURSOR FOR
		SELECT * FROM STOCK WHERE S_NIF = STOCK.PHARMACYNIF;
	RETURN S_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getStock (S_ID STOCK.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	S_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN S_CURSOR FOR
		SELECT * FROM STOCK WHERE S_ID = STOCK.ID;
	RETURN S_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllStocks RETURN SYS_REFCURSOR
AS 
	S_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN S_CURSOR FOR
		SELECT * FROM STOCK;
	RETURN S_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getStockItem (SI_ITEMID STOCKITEM.ITEMID%TYPE,
							SI_STOCKID STOCKITEM.STOCKID%TYPE) RETURN SYS_REFCURSOR
AS 
	SI_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN SI_CURSOR FOR
		SELECT * FROM STOCKITEM 
			WHERE (SI_ITEMID = STOCKITEM.ITEMID AND SI_STOCKID = STOCKITEM.STOCKID);
	RETURN SI_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllItemsOfStock (SI_STOCKID STOCKITEM.STOCKID%TYPE) RETURN SYS_REFCURSOR
AS 
	SI_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN SI_CURSOR FOR
		SELECT * FROM STOCKITEM WHERE (SI_STOCKID = STOCKITEM.STOCKID) ORDER BY STOCKITEM.ITEMID;
	RETURN SI_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getPath (P_GEOLOCATIONIDSTART PATH.GEOLOCATIONIDSTART%TYPE,
									P_GEOLOCATIONIDEND PATH.GEOLOCATIONIDEND%TYPE) RETURN SYS_REFCURSOR
AS 
	P_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN P_CURSOR FOR
		SELECT * FROM PATH 
			WHERE (P_GEOLOCATIONIDSTART = PATH.GEOLOCATIONIDSTART AND
				P_GEOLOCATIONIDEND = PATH.GEOLOCATIONIDEND);
	RETURN P_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getLandPath (LP_PATHGEOLOCATIONIDSTART LANDPATH.PATHGEOLOCATIONIDSTART%TYPE,
                                        LP_PATHGEOLOCATIONIDEND LANDPATH.PATHGEOLOCATIONIDEND%TYPE) RETURN SYS_REFCURSOR
AS 
	LP_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN LP_CURSOR FOR
		SELECT GEOLOCATIONIDSTART, GEOLOCATIONIDEND, DISTANCE, WINDDIRECTION, WINDVELOCITY, ROADRESISTANCECOEFFICIENT, ELEVATION
		FROM LANDPATH
		INNER JOIN PATH P on P.GEOLOCATIONIDSTART = LANDPATH.PATHGEOLOCATIONIDSTART and P.GEOLOCATIONIDEND = LANDPATH.PATHGEOLOCATIONIDEND
			WHERE (LP_PATHGEOLOCATIONIDEND = LANDPATH.PATHGEOLOCATIONIDEND AND
				LP_PATHGEOLOCATIONIDSTART = LANDPATH.PATHGEOLOCATIONIDSTART);
	RETURN LP_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAerialPath (AP_PATHGEOLOCATIONIDSTART AERIALPATH.PATHGEOLOCATIONIDSTART%TYPE,
                                            AP_PATHGEOLOCATIONIDEND AERIALPATH.PATHGEOLOCATIONIDEND%TYPE) RETURN SYS_REFCURSOR
AS 
	AP_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN AP_CURSOR FOR
		SELECT GEOLOCATIONIDSTART, GEOLOCATIONIDEND, DISTANCE, WINDDIRECTION, WINDVELOCITY, AIRDENSITY, AIRDRAGCOEFFICIENT
		FROM AERIALPATH AE
		INNER JOIN PATH P on P.GEOLOCATIONIDSTART = AE.PATHGEOLOCATIONIDSTART and P.GEOLOCATIONIDEND = AE.PATHGEOLOCATIONIDEND
			WHERE (AP_PATHGEOLOCATIONIDEND = AE.PATHGEOLOCATIONIDEND AND
				AP_PATHGEOLOCATIONIDSTART = AE.PATHGEOLOCATIONIDSTART);
	RETURN AP_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getScooterPark (SP_PARKID SCOOTERPARK.PARKID%TYPE) RETURN SYS_REFCURSOR
AS 
	SP_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN SP_CURSOR FOR
		SELECT * FROM PARK WHERE SP_PARKID = PARK.ID;
	RETURN SP_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getDronePark (DP_PARKID DRONEPARK.PARKID%TYPE) RETURN SYS_REFCURSOR
AS 
	DP_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN DP_CURSOR FOR
		SELECT * FROM PARK WHERE DP_PARKID = PARK.ID;
	RETURN DP_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getVehicle (V_ID VEHICLE.ID%TYPE) RETURN SYS_REFCURSOR
AS 
	V_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN V_CURSOR FOR
		SELECT * FROM VEHICLE WHERE V_ID = VEHICLE.ID;
	RETURN V_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getDelivery (D_ORDERID DELIVERY.ORDERID%TYPE) RETURN SYS_REFCURSOR
AS 
	D_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN D_CURSOR FOR
		SELECT * FROM DELIVERY WHERE D_ORDERID = DELIVERY.ORDERID;
	RETURN D_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getScootertParkedOnPharmacy (V_PHARNIF VEHICLE.PHARMACYNIF%TYPE) RETURN SYS_REFCURSOR
AS
    R_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN R_CURSOR FOR
    SELECT V.ID, V.BATTERYSIZE, V.BATTERYCONSUMPTION, V.BATTERYEFFICIENCY, V.CURRENTBATTERY, V.WEIGHT,
        V.FRONTALAREA, V.MOTORPOWER, V.PHARMACYNIF, S.QRCODE FROM SCOOTER S
        INNER JOIN VEHICLE V ON V.ID = S.VEHICLEID
        INNER JOIN PARK P ON P.PHARMACYNIF = V.PHARMACYNIF
        INNER JOIN SCOOTERPARK SP ON P.ID =SP.PARKID
        INNER JOIN PARKSLOT PS ON PS.PARKID = SP.PARKID
            WHERE V.PHARMACYNIF = V_PHARNIF ;

    RETURN R_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getUser (U_EMAIL "User".EMAIL%TYPE) RETURN SYS_REFCURSOR
AS
    U_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN U_CURSOR FOR
        SELECT * FROM "User" WHERE (U_EMAIL = "User".EMAIL);
    RETURN U_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getUserRoleByID (UR_ID USERROLE.ID%TYPE) RETURN SYS_REFCURSOR
AS
    U_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN U_CURSOR FOR
        SELECT * FROM USERROLE WHERE (UR_ID = USERROLE.ID);
    RETURN U_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getUserRoleByRole (UR_ROLE USERROLE.ROLE%TYPE) RETURN SYS_REFCURSOR
AS
    U_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN U_CURSOR FOR
        SELECT * FROM USERROLE WHERE (UR_ROLE = USERROLE.ROLE);
    RETURN U_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getReceiptByClientNif (R_CLIENTNIF RECEIPT.CLIENTNIF%TYPE) RETURN SYS_REFCURSOR
AS
	R_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN R_CURSOR FOR
		SELECT *
		FROM RECEIPT R
		INNER JOIN CLIENT C ON R.CLIENTNIF = C.NIF
		WHERE R.CLIENTNIF = R_CLIENTNIF
	    ORDER BY R.ID;
	RETURN R_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getOccupiedChargingStationsOfPark(P_ID PARK.ID%TYPE) RETURN SYS_REFCURSOR
AS
    CS_CURSOR SYS_REFCURSOR;
BEGIN
    OPEN CS_CURSOR FOR
        SELECT * FROM CHARGINGSTATION CS
            INNER JOIN PARKSLOT PS ON CS.PARKSLOTID = PS.ID
            WHERE PS.PARKID = P_ID AND PS.VEHICLEID <> 0
            ORDER BY CS.PARKSLOTID;
    RETURN CS_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllPathsBetweenPharmacies RETURN SYS_REFCURSOR
AS
	P_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN P_CURSOR FOR
		SELECT * FROM LANDPATH P
		INNER JOIN GEOLOCATION G ON(P.PATHGEOLOCATIONIDSTART = G.ID AND P.PATHGEOLOCATIONIDEND = G.ID)
		INNER JOIN PHARMACY P ON P.ADDRESSID = G.ID;
	RETURN P_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getBetterScooterToDelivery(P_NIF PHARMACY.NIF%TYPE) RETURN SYS_REFCURSOR
AS
	S_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN S_CURSOR FOR
	    SELECT V.ID, V.BATTERYSIZE, V.BATTERYCONSUMPTION, V.BATTERYEFFICIENCY,V.CURRENTBATTERY, V.WEIGHT,
        V.FRONTALAREA, V.MOTORPOWER, V.PHARMACYNIF, S.QRCODE FROM SCOOTER S
        INNER JOIN VEHICLE V ON V.ID = S.VEHICLEID
	    INNER JOIN PARKSLOT PS ON PS.VEHICLEID = V.ID
	    INNER JOIN PARK P ON P.ID = PS.PARKID
	    INNER JOIN PHARMACY P ON P.PHARMACYNIF = P_NIF
	    WHERE (V.CURRENTBATTERY = V.BATTERYSIZE)
	    ORDER BY V.BATTERYCONSUMPTION;
	RETURN S_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getPharmacyCouriers(P_NIF PHARMACY.NIF%TYPE) RETURN SYS_REFCURSOR
AS
	S_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN S_CURSOR FOR
	    SELECT * FROM COURIER C
	        WHERE (C.PHARMACYNIF = P_NIF);
	RETURN S_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllPathsBetweenPharmaciesAndClients RETURN SYS_REFCURSOR
AS
	P_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN P_CURSOR FOR
		SELECT P.GEOLOCATIONIDSTART,P.GEOLOCATIONIDEND,P.DISTANCE,P.WINDDIRECTION,P.WINDVELOCITY,LP.ROADRESISTANCECOEFFICIENT,LP.ELEVATION FROM LANDPATH LP
		INNER JOIN PATH P ON LP.PATHGEOLOCATIONIDSTART =P.GEOLOCATIONIDSTART
		                         AND LP.PATHGEOLOCATIONIDEND =P.GEOLOCATIONIDEND
		                         AND P.GEOLOCATIONIDSTART IN (SELECT G.ID FROM GEOLOCATION G
		                                                      WHERE G.ADDRESSID IN (SELECT C.ADDRESSID FROM CLIENT C))
		                         AND P.GEOLOCATIONIDEND IN (SELECT G.ID FROM GEOLOCATION G
		                                                      WHERE G.ADDRESSID IN (SELECT P.ADDRESSID FROM PHARMACY P))
	ORDER BY P.GEOLOCATIONIDSTART;
	RETURN P_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getAllPharmacies RETURN SYS_REFCURSOR
AS
	P_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN P_CURSOR FOR
    SELECT * FROM PHARMACY P
	ORDER BY P.ADDRESSID;
    RETURN P_CURSOR;
END;
/
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getPharmacyAdministratorByPharmacyNif(PA_PNIF PHARMACYADMINISTRATOR.PHARMACYNIF%TYPE) RETURN SYS_REFCURSOR
AS
	PA_CURSOR SYS_REFCURSOR;
BEGIN
	OPEN PA_CURSOR FOR
		SELECT * FROM PHARMACYADMINISTRATOR WHERE PA_PNIF = PHARMACYADMINISTRATOR.PHARMACYNIF;
	RETURN PA_CURSOR;
END;
/