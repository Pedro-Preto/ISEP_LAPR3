ALTER TABLE Client ADD CONSTRAINT FKClient338409 FOREIGN KEY (addressId) REFERENCES Address (id);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder455373 FOREIGN KEY (ClientNIF) REFERENCES Client (NIF);
ALTER TABLE Park ADD CONSTRAINT FKPark713009 FOREIGN KEY (PharmacyNIF) REFERENCES Pharmacy (NIF);
ALTER TABLE ParkSlot ADD CONSTRAINT FKParkSlot532390 FOREIGN KEY (ParkID) REFERENCES Park (id);
ALTER TABLE ChargingStation ADD CONSTRAINT FKChargingSt672723 FOREIGN KEY (ParkSlotID) REFERENCES ParkSlot (id);
ALTER TABLE Receipt ADD CONSTRAINT FKReceipt292250 FOREIGN KEY (orderID) REFERENCES "Order" (id);
ALTER TABLE Receipt ADD CONSTRAINT FKReceipt37643 FOREIGN KEY (ClientNIF) REFERENCES Client (NIF);
ALTER TABLE Receipt ADD CONSTRAINT FKReceipt791950 FOREIGN KEY (PharmacyNIF) REFERENCES Pharmacy (NIF);
ALTER TABLE StockItem ADD CONSTRAINT FKStockItem356724 FOREIGN KEY (itemId) REFERENCES Item (id);
ALTER TABLE StockItem ADD CONSTRAINT FKStockItem234246 FOREIGN KEY (stockId) REFERENCES Stock (id);
ALTER TABLE OrderItem ADD CONSTRAINT FKOrderItem701968 FOREIGN KEY (itemID) REFERENCES Item (id);
ALTER TABLE OrderItem ADD CONSTRAINT FKOrderItem417844 FOREIGN KEY (orderID) REFERENCES "Order" (id);
ALTER TABLE Courier ADD CONSTRAINT FKCourier143574 FOREIGN KEY (PharmacyNIF) REFERENCES Pharmacy (NIF);
ALTER TABLE Pharmacy ADD CONSTRAINT FKPharmacy831467 FOREIGN KEY (addressId) REFERENCES Address (id);
ALTER TABLE PharmacyAdministrator ADD CONSTRAINT FKPharmacyAd986507 FOREIGN KEY (PharmacyNIF) REFERENCES Pharmacy (NIF);
ALTER TABLE Stock ADD CONSTRAINT FKStock450795 FOREIGN KEY (PharmacyNIF) REFERENCES Pharmacy (NIF);
ALTER TABLE Credits ADD CONSTRAINT FKCredits380599 FOREIGN KEY (ClientNIF) REFERENCES Client (NIF);
ALTER TABLE GeoLocation ADD CONSTRAINT FKGeoLocatio693159 FOREIGN KEY (Addressid) REFERENCES Address (id);
ALTER TABLE DronePark ADD CONSTRAINT FKDronePark381771 FOREIGN KEY (Parkid) REFERENCES Park (id);
ALTER TABLE ScooterPark ADD CONSTRAINT FKScooterPar812258 FOREIGN KEY (Parkid) REFERENCES Park (id);
ALTER TABLE LandPath ADD CONSTRAINT FKLandPath959723 FOREIGN KEY (PathGeoLocationIdStart, PathGeoLocationIdEnd) REFERENCES Path (GeoLocationIdStart, GeoLocationIdEnd);
ALTER TABLE AerialPath ADD CONSTRAINT FKAerialPath205298 FOREIGN KEY (PathGeoLocationIdStart, PathGeoLocationIdEnd) REFERENCES Path (GeoLocationIdStart, GeoLocationIdEnd);
ALTER TABLE Path ADD CONSTRAINT "start" FOREIGN KEY (GeoLocationIdStart) REFERENCES GeoLocation (id);
ALTER TABLE Path ADD CONSTRAINT "end" FOREIGN KEY (GeoLocationIdEnd) REFERENCES GeoLocation (id);
ALTER TABLE Delivery ADD CONSTRAINT FKDelivery113460 FOREIGN KEY (Orderid) REFERENCES "Order" (id);
ALTER TABLE Delivery ADD CONSTRAINT FKDelivery279206 FOREIGN KEY (CourierNIF) REFERENCES Courier (NIF);
ALTER TABLE Vehicle ADD CONSTRAINT FKVehicle957191 FOREIGN KEY (PharmacyNIF) REFERENCES Pharmacy (NIF);
ALTER TABLE Scooter ADD CONSTRAINT FKScooter172615 FOREIGN KEY (Vehicleid) REFERENCES Vehicle (id);
ALTER TABLE Drone ADD CONSTRAINT FKDrone866674 FOREIGN KEY (Vehicleid) REFERENCES Vehicle (id);
ALTER TABLE ParkSlot ADD CONSTRAINT FKParkSlot872393 FOREIGN KEY (Vehicleid) REFERENCES Vehicle (id);
