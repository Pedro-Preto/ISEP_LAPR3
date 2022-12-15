CREATE TABLE "User" (
  email      varchar2(255) NOT NULL, 
  password   varchar2(255) NOT NULL, 
  UserRoleid number(10) NOT NULL);
CREATE TABLE UserRole (
  id          number(10) GENERATED AS DEFAULT,
  role	      varchar2(255) UNIQUE NOT NULL, 
  description varchar2(255) NOT NULL);
