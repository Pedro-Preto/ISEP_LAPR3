select * FROM ADDRESS;
select * from AERIALPATH;
select * from CHARGINGSTATION;
select * from CLIENT;
select * from COURIER;
select * from CREDITS;
select * from DELIVERY;
select * from DRONE;
select * from DRONEPARK;
select * from GEOLOCATION;
select * from ITEM;
select * from LANDPATH;
select * from "ORDER";
select * from "Order";
SELECT * FROM ORDERITEM;
SELECT * FROM PARK;
SELECT * FROM PARKSLOT;
SELECT * FROM PATH;
SELECT * FROM PHARMACY;
SELECT * FROM PHARMACYADMINISTRATOR;
SELECT * FROM RECEIPT;
SELECT * FROM SCOOTER;
SELECT * FROM SCOOTERPARK;
SELECT * FROM STOCK;
SELECT * FROM STOCKITEM;
SELECT * FROM VEHICLE;
------------

SELECT * FROM USERROLE;

SELECT * FROM "User";

select EMAIL, ROLE, U.ID AS "USER ROLE ID" from "User"
    RIGHT join USERROLE U on U.ID = "User".USERROLEID
ORDER BY U.ID, EMAIL;

begin
    REMOVEUSERROLEANDUSERS(1);
    REMOVEUSERROLEANDUSERS(2);
    REMOVEUSERROLEANDUSERS(3);
    REMOVEUSERROLEANDUSERS(4);
end;


------
SELECT ADDRESSID as ID, STREET, LATITUDE, LONGITUDE, ALTITUDE
FROM ADDRESS
INNER JOIN GEOLOCATION G on ADDRESS.ID = G.ADDRESSID
ORDER BY ADDRESSID;

SELECT PATHGEOLOCATIONIDSTART as START_ID, PATHGEOLOCATIONIDEND AS END_ID, A.STREET as START_STREET, A2.STREET as END_STREET ,DISTANCE
FROM LANDPATH
INNER JOIN PATH P on P.GEOLOCATIONIDSTART = LANDPATH.PATHGEOLOCATIONIDSTART and P.GEOLOCATIONIDEND = LANDPATH.PATHGEOLOCATIONIDEND
INNER JOIN GEOLOCATION G on G.ID = P.GEOLOCATIONIDSTART
INNER JOIN GEOLOCATION G2 on G2.ID = P.GEOLOCATIONIDEND
INNER JOIN ADDRESS A on A.ID = G.ADDRESSID
INNER JOIN ADDRESS A2 on A2.ID = G2.ADDRESSID;

SELECT PATHGEOLOCATIONIDSTART as START_ID, PATHGEOLOCATIONIDEND AS END_ID, A.STREET as START_STREET, A2.STREET as END_STREET ,DISTANCE
FROM AERIALPATH
INNER JOIN PATH P on P.GEOLOCATIONIDSTART = AERIALPATH.PATHGEOLOCATIONIDSTART and AERIALPATH.PATHGEOLOCATIONIDEND = P.GEOLOCATIONIDEND
INNER JOIN GEOLOCATION G on G.ID = P.GEOLOCATIONIDSTART
INNER JOIN GEOLOCATION G2 on G2.ID = P.GEOLOCATIONIDEND
INNER JOIN ADDRESS A on A.ID = G.ADDRESSID
INNER JOIN ADDRESS A2 on A2.ID = G2.ADDRESSID;

SELECT * FROM AERIALPATH;

BEGIN
    REMOVEPATH(88883,88884);
    REMOVEPATH(88883,88885);
    REMOVEGEOLOCATION(88883);
    REMOVEADDRESS(88883);
    REMOVEGEOLOCATION(88884);
    REMOVEADDRESS(88884);
    REMOVEGEOLOCATION(88885);
    REMOVEADDRESS(88885);
end;

select * from ADDRESS;

SELECT ADDRESSID as ID, STREET, LATITUDE, LONGITUDE, ALTITUDE
    FROM ADDRESS
    INNER JOIN GEOLOCATION G on ADDRESS.ID = G.ADDRESSID
    ORDER BY ADDRESSID;

SELECT STOCKID,  ITEMID, NAME, ITEMSQUANTITY, DESIGNATION
FROM STOCK
INNER JOIN PHARMACY P on P.NIF = STOCK.PHARMACYNIF
INNER JOIN STOCKITEM S on STOCK.ID = S.STOCKID
INNER JOIN ITEM I on I.ID = S.ITEMID;

SELECT *
FROM PARK
INNER JOIN SCOOTERPARK D on PARK.ID = D.PARKID;

SELECT *
FROM PARK
INNER JOIN PARKSLOT P on PARK.ID = P.PARKID;

SELECT * FROM PARKSLOT;
SELECT * FROM PARK;

select * from STOCKITEM;
select *
from STOCK;