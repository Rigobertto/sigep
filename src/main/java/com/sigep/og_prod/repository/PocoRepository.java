package com.sigep.og_prod.repository;

import com.sigep.og_prod.model.Poco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PocoRepository extends JpaRepository<Poco, String> {

    @Query("SELECT p FROM Poco p WHERE " +
           "(:poco = '%%' OR LOWER(p.poco) LIKE :poco) AND " +
           "(:estado IS NULL OR p.estado = :estado) AND " +
           "(:bacia IS NULL OR p.bacia = :bacia) AND " +
           "(:operador = '%%' OR LOWER(p.operador) LIKE :operador)")
    Page<Poco> findByFiltros(@Param("poco") String poco,
                             @Param("estado") String estado, 
                             @Param("bacia") String bacia, 
                             @Param("operador") String operador, 
                             Pageable pageable);
}
