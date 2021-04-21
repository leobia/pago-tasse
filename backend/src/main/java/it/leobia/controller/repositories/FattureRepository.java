package it.leobia.controller.repositories;

import it.leobia.controller.entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FattureRepository extends JpaRepository<Fatture, Long> {
    List<Fatture> findAllByOrderByData();

    List<Fatture> findAllByDataBetweenOrderByData(Date dataStart, Date dataEnd);
}
