package it.leobia.controller.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final Logger LOGGER = LogManager.getLogger(TestController.class);

    @GetMapping(value = "/api/test")
    public void test() {
        LOGGER.info("test");
    }

}
