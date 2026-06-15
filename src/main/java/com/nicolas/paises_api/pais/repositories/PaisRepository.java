package com.nicolas.paises_api.pais.repositories;

import com.nicolas.paises_api.pais.models.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    List<Pais> findByNomeContainingIgnoreCase(String filtro);
}