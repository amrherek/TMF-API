CREATE TABLE SYSADM.HISTO_FAIL_LOG
  (
    histo_fail_log_id INTEGER NOT NULL ,
    platform          VARCHAR2 (45) ,
    login             VARCHAR2 (45) ,
    origin_host       VARCHAR2 (200) ,
    api               VARCHAR2 (30) ,
    method            VARCHAR2 (50) ,
    msisdn            VARCHAR2 (20) ,
    transaction_id    CHAR (36) ,
    call_date         DATE ,
    response_date     DATE ,
    response_time LONG ,
    error_code    INTEGER ,
    error_type    VARCHAR2 (10) ,
    error_message VARCHAR2 (500)
  ) ;
ALTER TABLE SYSADM.HISTO_FAIL_LOG ADD CONSTRAINT HISTO_FAIL_LOG_PK PRIMARY KEY ( histo_fail_log_id ) ;


CREATE TABLE SYSADM.HISTO_SUCCESS_LOG
  (
    histo_success_log_id INTEGER NOT NULL ,
    platform             VARCHAR2 (45) ,
    login                VARCHAR2 (45) ,
    origin_host          VARCHAR2 (200) ,
    api                  VARCHAR2 (30) ,
    method               VARCHAR2 (50) ,
    msisdn               VARCHAR2 (20) ,
    transaction_id       CHAR (36) ,
    call_date            DATE ,
    response_date        DATE ,
    response_time LONG
  ) ;
ALTER TABLE SYSADM.HISTO_SUCCESS_LOG ADD CONSTRAINT HISTO_SUCCESS_LOG_PK PRIMARY KEY ( histo_success_log_id ) ;

CREATE SEQUENCE SYSADM.HISTO_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER SYSADM.HISTO_FAIL_LOG_histo_fail_log_ BEFORE
  INSERT ON SYSADM.HISTO_FAIL_LOG FOR EACH ROW WHEN (NEW.histo_fail_log_id IS NULL) BEGIN :NEW.histo_fail_log_id := SYSADM.HISTO_SEQ.NEXTVAL;
END;
/

CREATE OR REPLACE TRIGGER SYSADM.HISTO_SUCCESS_LOG_histo_succes BEFORE
  INSERT ON SYSADM.HISTO_SUCCESS_LOG FOR EACH ROW WHEN (NEW.histo_success_log_id IS NULL) BEGIN :NEW.histo_success_log_id := SYSADM.HISTO_SEQ.NEXTVAL;
END;
/
