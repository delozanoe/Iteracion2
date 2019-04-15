
  CREATE TABLE "ISIS2304A211910"."EMPLEADO" 
   (	"IDHOTEL" NUMBER(*,0), 
	"IDUSUARIO" NUMBER(*,0), 
	"IDEMPLEADO" NUMBER(*,0), 
	 CONSTRAINT "IDUSUARIO_PK" PRIMARY KEY ("IDUSUARIO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOLOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD"  ENABLE, 
	 CONSTRAINT "IDHOTELEMPLEADO_FK" FOREIGN KEY ("IDHOTEL")
	  REFERENCES "ISIS2304A211910"."HOTEL" ("ID") ENABLE, 
	 CONSTRAINT "IDUSUARIOEMPLEADO_FK" FOREIGN KEY ("IDUSUARIO")
	  REFERENCES "ISIS2304A211910"."USUARIO" ("ID") ENABLE, 
	 CONSTRAINT "IDTIPOEMPLEADOEMPLEADO_FK" FOREIGN KEY ("IDEMPLEADO")
	  REFERENCES "ISIS2304A211910"."TIPOEMPLEADO" ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "TBSPROD" ;