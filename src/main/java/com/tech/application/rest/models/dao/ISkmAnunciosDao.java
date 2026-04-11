package com.tech.application.rest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.ISkmAnuncios;
import com.tech.application.rest.models.entity.SkmAnuncios;

@Repository
public interface ISkmAnunciosDao extends JpaRepository<SkmAnuncios,Integer> {
    
    	@Query(nativeQuery = true, 
			value= "select id,titulo,descripcion,estado,alcance,fec_inicio_vigencia,fec_fin_vigencia,contenido,cod_empresa,usu_creacion,fec_creacion from skm_anuncios where id  = :id")
    	public SkmAnuncios BuscarById(@Param("id") Integer id);

       	@Query(nativeQuery = true, 
        value= "select id,titulo,descripcion,estado,alcance,fec_inicio_vigencia,fec_fin_vigencia,contenido,cod_empresa,usu_creacion,fec_creacion from skm_anuncios")
        public List<SkmAnuncios> BuscarAll();

        @Query(nativeQuery = true, 
        value= "select id,titulo,descripcion,estado,alcance,fec_inicio_vigencia,fec_fin_vigencia,contenido,cod_empresa,usu_creacion,fec_creacion from skm_anuncios "
         +" where len(coalesce(cod_empresa,''))=0 or   cod_empresa = :empresa")
        public List<SkmAnuncios> BuscarByEmpresa(@Param("empresa") String empresa);

        @Query(value="{call sp_skm_consulta_anuncios_todos(:estado)}",nativeQuery=true)
	public List<ISkmAnuncios>  execProcConsultaAnuncioTodos(
					@Param("estado") Integer estado								
					);

        @Query(value="{call sp_skm_consulta_anuncios_alerta(:usuario,:empresa,:alcance,:fecha)}",nativeQuery=true)
        public List<ISkmAnuncios>  execProcConsultaAnuncioAlerta(
                                        @Param("usuario") String usuario,
                                        @Param("empresa") String empresa,
                                        @Param("alcance") Integer alcance,
                                        @Param("fecha") String fecha
                                        );

        @Query(value="{call sp_skm_auditoria_anuncios(:anuncio,:usuario,:empresa)}",nativeQuery=true)
        public Integer  execProcRegistraAnuncioVisualizacion(
                                        @Param("anuncio") Integer anuncio,
                                        @Param("usuario") String usuario,					
                                        @Param("empresa") String empresa									
                                        );

}
