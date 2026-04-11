package com.tech.application.rest.models.entity;

import java.util.Date;

public interface IRelacionPrestamos {
    
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

    Date getfecsolicitud();
    String getmoneda();
    String getdesmoneda();
    Double getimporte();
    Integer getcuotas();
    String getobservacion();
    String gettipo();
    String getdestipo();
    String getestado();
    String getdesestado();
    String getusucreacion();
    Date getfeccreacion();
    String getcodigotransferencia();
}
