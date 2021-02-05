package it.leobia.controller.repositories;

import it.leobia.controller.entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface FattureRepository extends JpaRepository<Fatture, Long> {

}
