package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.IRelacionVacaciones;
import com.tech.application.rest.models.entity.SkmVacaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

@Repository
public interface ISkmVacacionesDao extends JpaRepository<SkmVacaciones, Long> {

    @Query(nativeQuery = true, 
		value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_vacaciones where id = :id")
    	public SkmVacaciones BuscarById(@Param("id") Long id);

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_vacaciones")
        public List<SkmVacaciones> BuscarAll();

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_vacaciones "
         +" where cod_empresa = :empresa")
        public List<SkmVacaciones> BuscarByEmpresa(@Param("empresa") String empresa);

        @Query(nativeQuery = true, 
        value= "select id,cod_empresa,cod_personal,fec_inicio,fec_fin,num_dias,observacion,tipo,estado,usu_creacion,fec_creacion,codigo_transferencia from skm_vacaciones "
         +" where cod_empresa = :empresa and cod_personal = :personal")
        public List<SkmVacaciones> BuscarByEmpresaPersonal(
            @Param("empresa") String empresa,
            @Param("personal") String personal);

            

    @Query(value="{call sp_skm_valida_vacaciones_registro(:empresa,:personal,:tiporegistro,:idvac,:fecinicio,:fecfin)}",nativeQuery=true)
	public List<ValidacionRespuesta>  execProcValidaVacaciones(
					@Param("empresa") String empresa,
					@Param("personal") String personal,                    
					@Param("tiporegistro") String tiporegistro,
					@Param("idvac") Long idvac,                    					
					@Param("fecinicio") String fecinicio,
                    @Param("fecfin") String fecfin										
					);

    @Query(value="{call sp_skm_consulta_vacaciones(:empresa,:planilla,:estado,:personal,:usuario)}",nativeQuery=true)
	public List<IRelacionVacaciones>  execConsultaVacaciones(
					@Param("empresa") String empresa,
					@Param("planilla") String planilla,                    
					@Param("estado") String estado,
					@Param("personal") String personal,
                    @Param("usuario") String usuario										
					);

}
