package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.ISkmPeriodoVisualizacion;
import com.tech.application.rest.models.entity.ISkmTipoPlanilla;
import com.tech.application.rest.models.entity.ISkmVisualizacion;
import com.tech.application.rest.models.entity.SkmConfigVisualizacion;
import com.tech.application.rest.models.entity.keys.SkmConfigVisualizacionId;

@Repository
public interface ISkmConfigVisualizacionDao extends JpaRepository<SkmConfigVisualizacion, SkmConfigVisualizacionId> {

    @Query(value="{call SP_SKM_REGISTRA_VISUALIZACION(:codempresa,:anoproceso,:mesproceso,:codpersonal,:codusuario,:numdocidentidad,:tipdocumento)}",nativeQuery=true)
	public Integer  execProcRegistraVisualizacion(
					@Param("codempresa") String codempresa,
					@Param("anoproceso") String anoproceso,					
					@Param("mesproceso") String mesproceso,
                    @Param("codpersonal") String codpersonal,
                    @Param("codusuario") String codusuario,
                    @Param("numdocidentidad") String numdocidentidad,
                    @Param("tipdocumento") String tipdocumento										
					);


    @Query(value="{call SP_SKM_CONSULTA_VISUALIZACION(:codempresa,:planilla,:anoproceso,:mesproceso,:tipdocumento)}",nativeQuery=true)
	public List<ISkmVisualizacion>  execProcConsultaVisualizacion(
					@Param("codempresa") String codempresa,
					@Param("planilla") String planilla,                    
					@Param("anoproceso") String anoproceso,					
					@Param("mesproceso") String mesproceso,
                    @Param("tipdocumento") String tipdocumento										
					);
                
    @Query(value="{call SP_SKM_CONFIGURA_VISUALIZACION(:codempresa,:anoproceso,:mesproceso,:codtipoplanilla,:tipdocumento,:accion)}",nativeQuery=true)
    public Integer execProcConfiguravisualizacion(
                    @Param("codempresa") String codempresa,
                    @Param("anoproceso") String anoproceso,					
                    @Param("mesproceso") String mesproceso,
                    @Param("codtipoplanilla") String codtipoplanilla,
                    @Param("tipdocumento") String tipdocumento,
                    @Param("accion") String accion
                    );

    @Query(value="{call SP_SKM_PERIODOS_VISUALIZACION(:codempresa,:tipoplanilla,:tipdocumento,:anoproceso)}",nativeQuery=true)
    public List<ISkmPeriodoVisualizacion>  execProcPeriodoVisualizacion(
                    @Param("codempresa") String codempresa,
                    @Param("tipoplanilla") String tipoplanilla,					
                    @Param("tipdocumento") String tipdocumento,
                    @Param("anoproceso") String anoproceso										
                    );

    @Query(value="{call sp_skm_consulta_planillas(:codempresa)}",nativeQuery=true)
    public List<ISkmTipoPlanilla>  execProcTipoPlanilla(
                    @Param("codempresa") String codempresa										
                    );                    

    @Query(value="{call SP_SKM_ACTUALIZA_USUARIO(:usuario,:claveant,:clavenuevo)}",nativeQuery=true)
    public Integer execActualizaUsuario(
                    @Param("usuario") String usuario,
                    @Param("claveant") String claveant,					
                    @Param("clavenuevo") String clavenuevo);                    
}
