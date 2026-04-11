package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.IRelacionPrestamos;
import com.tech.application.rest.models.entity.SkmPrestamos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

@Repository
public interface ISkmPrestamosDao extends JpaRepository<SkmPrestamos, Long> {
    

    @Query(nativeQuery = true, 
		value= "select id,cod_empresa,cod_personal,tipo,fec_solicitud,moneda,importe,nro_cuotas,observacion,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_prestamos where id = :id")
    	public SkmPrestamos BuscarById(@Param("id") Long id);

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,tipo,fec_solicitud,moneda,importe,nro_cuotas,observacion,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_prestamos")
        public List<SkmPrestamos> BuscarAll();

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,tipo,fec_solicitud,moneda,importe,nro_cuotas,observacion,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_prestamos "
         +" where cod_empresa = :empresa")
        public List<SkmPrestamos> BuscarByEmpresa(@Param("empresa") String empresa);

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,tipo,fec_solicitud,moneda,importe,nro_cuotas,observacion,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_prestamos "
         +" where cod_empresa = :empresa and cod_personal = :personal")
        public List<SkmPrestamos> BuscarByEmpresaPersonal(
            @Param("empresa") String empresa,
            @Param("personal") String personal);

            

    @Query(value="{call sp_skm_valida_prestamos_registro(:empresa,:personal,:tiporegistro,:id,:fecsolicitud,:moneda,:importe,:cuota)}",nativeQuery=true)
	public List<ValidacionRespuesta>  execProcValidaPrestamos(
					@Param("empresa") String empresa,
					@Param("personal") String personal,                    
					@Param("tiporegistro") String tiporegistro,
					@Param("id") Long id,                    					
					@Param("fecsolicitud") String fecsolicitud,
					@Param("moneda") String moneda,
					@Param("importe") Double importe,                                      
                    @Param("cuota") Integer cuota										
					);

    @Query(value="{call sp_skm_consulta_prestamos(:empresa,:planilla,:estado,:personal,:usuario)}",nativeQuery=true)
	public List<IRelacionPrestamos>  execConsultaPrestamos(
					@Param("empresa") String empresa,
					@Param("planilla") String planilla,                    
					@Param("estado") String estado,
					@Param("personal") String personal,
                    @Param("usuario") String usuario										
					);                                        
}

