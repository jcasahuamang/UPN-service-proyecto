package com.tech.application.rest.models.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.entity.MaeCompania;

@Repository
public interface IMaeCompaniaDao extends JpaRepository<MaeCompania, String>{

	
	@Query(nativeQuery = true, 
			value= "select COD_EMPRESA,DES_RAZON_SOCIAL,DES_NOMBRE_COMERCIAL,DES_DIRECCION,NUM_RUC_EMPRESA,TIP_DOC_REPRES,NUM_DOC_REPRES,DES_REPRES_LEGAL,BMP_LOGO,BMP_FIRMA from MAE_EMPRESAS where COD_EMPRESA = :id")
	public MaeCompania BuscarById(@Param("id") String id);

	@Query(nativeQuery = true, 
			value= "select COD_EMPRESA,DES_RAZON_SOCIAL,DES_NOMBRE_COMERCIAL,DES_DIRECCION,NUM_RUC_EMPRESA,TIP_DOC_REPRES,NUM_DOC_REPRES,DES_REPRES_LEGAL,BMP_LOGO,BMP_FIRMA\r\n"
			+ " from MAE_EMPRESAS where COD_EMPRESA in (select COD_EMPRESA FROM MAE_USUARIO WHERE COD_USUARIO = :usuario and cod_personal is not null)")
	public List<MaeCompania> BuscarAllByUsuario(@Param("usuario") String usuario);

	@Query(nativeQuery = true, 
	value= "select COD_EMPRESA,DES_RAZON_SOCIAL,DES_NOMBRE_COMERCIAL,DES_DIRECCION,NUM_RUC_EMPRESA,TIP_DOC_REPRES,NUM_DOC_REPRES,DES_REPRES_LEGAL,BMP_LOGO,BMP_FIRMA\r\n"
	+ " from MAE_EMPRESAS")
	public List<MaeCompania> BuscarAll();

}
