
  CREATE TABLE "ISIS2304A211910"."HOTEL" 
   (	"ID" NUMBER(*,0) NOT NULL ENABLE, 
	"PAIS" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"CIUDAD" VARCHAR2(20 BYTE), 
	"OFERTAHABITACIONAL" NUMBER(*,0) NOT NULL ENABLE, 
	 CONSTRAINT "HOTEL_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD"  ENABLE, 
	 CONSTRAINT "CARACTERISTICASHOTEL_FK" CHECK (ofertaHabitacional >0) ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;
