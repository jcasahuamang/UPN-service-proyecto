package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.IRelacionPermisos;
import com.tech.application.rest.models.entity.SkmPermisos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

@Repository
public interface ISkmPermisosDao extends JpaRepository<SkmPermisos, Long> {

    @Query(nativeQuery = true, 
		value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_permisos where id = :id")
    	public SkmPermisos BuscarById(@Param("id") Long id);

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_permisos")
        public List<SkmPermisos> BuscarAll();

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_permisos "
         +" where cod_empresa = :empresa")
        public List<SkmPermisos> BuscarByEmpresa(@Param("empresa") String empresa);

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_permisos "
         +" where cod_empresa = :empresa and cod_personal = :personal")
        public List<SkmPermisos> BuscarByEmpresaPersonal(
            @Param("empresa") String empresa,
            @Param("personal") String personal);

            

    @Query(value="{call sp_skm_valida_permisos_registro(:empresa,:personal,:tiporegistro,:idvac,:fecinicio,:fecfin)}",nativeQuery=true)
	public List<ValidacionRespuesta>  execProcValidaPermisos(
					@Param("empresa") String empresa,
					@Param("personal") String personal,                    
					@Param("tiporegistro") String tiporegistro,
					@Param("idvac") Long idvac,                    					
					@Param("fecinicio") String fecinicio,
                    @Param("fecfin") String fecfin										
					);

    @Query(value="{call sp_skm_consulta_permisos(:empresa,:planilla,:estado,:personal,:usuario)}",nativeQuery=true)
	public List<IRelacionPermisos>  execConsultaPermisos(
					@Param("empresa") String empresa,
					@Param("planilla") String planilla,                    
					@Param("estado") String estado,
					@Param("personal") String personal,
                    @Param("usuario") String usuario										
					);                    
}
