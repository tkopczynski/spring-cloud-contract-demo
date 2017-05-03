package pl.net.kopczynski.cdc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tomasz Kopczynski.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc
public class IntegrationRestDocsTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldMarkApplicationAsFraudTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fraud?age=12"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"fraud\": true}"))
                .andDo(WireMockRestDocs.verify()
                        .wiremock(
                                get(urlMatching("\\/fraud.*"))
                                    .withQueryParam("age", matching("[1-9][0-9]+")))
                        .stub("shouldMarkApplicationAsFraudRestDocs"))
                .andDo(MockMvcRestDocumentation.document("fraud", SpringCloudContractRestDocs.dslContract()));
    }

    @Test
    public void shouldNotMarkApplicationAsFraudTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fraud?age=5"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"fraud\": false}"))
                .andDo(WireMockRestDocs.verify()
                        .wiremock(
                                get(urlMatching("\\/fraud.*"))
                                        .withQueryParam("age", matching("[1-9]")))
                        .stub("shouldNotMarkApplicationAsFraudRestDocs"))
                .andDo(MockMvcRestDocumentation.document("notFraud", SpringCloudContractRestDocs.dslContract()));
    }
}
