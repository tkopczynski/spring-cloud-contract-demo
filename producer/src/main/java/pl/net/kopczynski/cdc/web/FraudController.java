package pl.net.kopczynski.cdc.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Tomasz Kopczynski.
 */
@RestController
public class FraudController {

    @GetMapping(value = "/fraud", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> fraud(@RequestParam("age") int age) {
        boolean fraud = false;

        if (age > 9) {
            fraud = true;
        }

        return Collections.singletonMap("fraud", fraud);
    }
}
