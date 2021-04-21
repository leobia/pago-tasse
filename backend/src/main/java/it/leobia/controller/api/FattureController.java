package it.leobia.controller.api;

import it.leobia.controller.entities.Fatture;
import it.leobia.controller.services.FattureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/fatture")
public class FattureController {
    private static final Logger LOGGER = LogManager.getLogger(FattureController.class);

    @Autowired
    private FattureService fattureService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> allFatture(@RequestParam(value = "year") Integer year) {
        return new ResponseEntity<>(fattureService.getFattureInYear(year), HttpStatus.OK);
    }

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insertFattura(@RequestBody Fatture fattura) {
        try {
            fattureService.save(fattura);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteFattura(@RequestBody Fatture fattura) {
        try {
            fattureService.delete(fattura);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
