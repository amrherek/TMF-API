CREATE TABLE SYSADM.ROLES
  (
    role_id   INTEGER NOT NULL ,
    role_name VARCHAR2 (45) NOT NULL
  ) ;
ALTER TABLE SYSADM.ROLES ADD CONSTRAINT ROLES_PK PRIMARY KEY ( role_id ) ;
ALTER TABLE SYSADM.ROLES ADD CONSTRAINT ROLES__UN UNIQUE ( role_name ) ;


CREATE TABLE SYSADM.USERS
  (
    user_id       INTEGER NOT NULL ,
    login         VARCHAR2 (45) NOT NULL ,
    password      VARCHAR2 (100) NOT NULL ,
    platform      VARCHAR2 (45) NOT NULL ,
    is_active     CHAR (1) NOT NULL ,
    origin_sender VARCHAR2 (45)
  ) ;
ALTER TABLE SYSADM.USERS ADD CONSTRAINT USERS_PK PRIMARY KEY ( user_id ) ;
ALTER TABLE SYSADM.USERS ADD CONSTRAINT USERS__UN UNIQUE ( login ) ;


CREATE TABLE SYSADM.USERS_ROLES
  (
    user_role_id INTEGER NOT NULL ,
    user_id      INTEGER NOT NULL ,
    role_id      INTEGER NOT NULL
  ) ;
ALTER TABLE SYSADM.USERS_ROLES ADD CONSTRAINT USERS_ROLES_PK PRIMARY KEY ( user_role_id ) ;


ALTER TABLE SYSADM.USERS_ROLES ADD CONSTRAINT USERS_ROLES_ROLES_FK FOREIGN KEY ( role_id ) REFERENCES ROLES ( role_id ) ;

ALTER TABLE SYSADM.USERS_ROLES ADD CONSTRAINT USERS_ROLES_USERS_FK FOREIGN KEY ( user_id ) REFERENCES USERS ( user_id ) ;
