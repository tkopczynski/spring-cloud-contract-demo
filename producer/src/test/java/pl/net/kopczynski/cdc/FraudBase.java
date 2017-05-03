package pl.net.kopczynski.cdc;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import pl.net.kopczynski.cdc.web.FraudController;

/**
 * Created by Tomasz Kopczynski.
 */
public class FraudBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudController());
    }
}
