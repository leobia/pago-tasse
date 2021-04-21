package it.leobia.controller.services;

import it.leobia.controller.entities.Fatture;
import it.leobia.controller.repositories.FattureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FattureService {
    private static final Logger LOGGER = LogManager.getLogger(FattureService.class);

    @Autowired
    private FattureRepository fattureRepository;

    public List<Fatture> getFattureInYear(Integer year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date start = cal.getTime();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date end = cal.getTime();

        LOGGER.info("Requesting fatture between {} and {}", start, end);

        return fattureRepository.findAllByDataBetweenOrderByData(start, end);
    }

    public void save(Fatture fattura) {
        fattureRepository.save(fattura);
    }

    public void delete(Fatture fattura) {
        fattureRepository.delete(fattura);
    }
}
