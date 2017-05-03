package pl.net.kopczynski.cdc;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.net.kopczynski.cdc.model.FraudServiceResponse;
import pl.net.kopczynski.cdc.model.InsuranceApplication;

/**
 * Created by tkopczynski on 30/04/17.
 */
@Service
public class InsurancePolicyService {

    private final RestTemplate restTemplate;
    private int port = 8090;

    public InsurancePolicyService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public boolean isInsuranceAllowed(InsuranceApplication insuranceApplication) {

        if (insuranceApplication.getInsured().getAge() < 18) {
            return false;
        }

        ResponseEntity<FraudServiceResponse> fraudResponse =
                restTemplate.getForEntity("http://localhost:" + port + "/fraud?age={age}", FraudServiceResponse.class, insuranceApplication.getCar().getAge());

        FraudServiceResponse fraudResponseBody = fraudResponse.getBody();

        return !fraudResponseBody.isFraud();
    }

}
