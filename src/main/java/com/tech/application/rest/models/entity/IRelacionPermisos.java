package com.tech.application.rest.models.entity;

import java.util.Date;

public interface IRelacionPermisos {
    Long getId();
    String getcodempresa();
    String getdesempresa();
    String getcodpersonal();
    String getdespersonal();
    String getdocidentidad();
    Date getfecingreso();
    Date getfeccesado();
    String getcodplanilla();
    String getdesplanilla();
    Date getfecinicio();
    Date getfecfin();
    Double getdias();
    String getobservacion();
    String gettipo();
    String getdestipo();
    String getestado();
    String getdesestado();
    String getusucreacion();
    Date getfeccreacion();
    String getcodigotransferencia();    
}
