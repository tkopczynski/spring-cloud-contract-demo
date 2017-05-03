package pl.net.kopczynski.cdc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;
import pl.net.kopczynski.cdc.model.Car;
import pl.net.kopczynski.cdc.model.InsuranceApplication;
import pl.net.kopczynski.cdc.model.Person;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Tomasz Kopczynski.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = {"pl.net.kopczynski.cdc:producer:+:stubs:8090"}, workOffline = true)
public class InsurancePolicyServiceTest {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @Test
    public void shouldBeGrantedInsurancePolicy() {
        InsuranceApplication insuranceApplication = new InsuranceApplication(new Person("Jack", 30), new Car("Audi", 5));

        boolean insuranceAllowed = insurancePolicyService.isInsuranceAllowed(insuranceApplication);

        assertThat(insuranceAllowed).isTrue();
    }

    @Test
    public void shouldBeDeniedInsurancePolicy() {
        InsuranceApplication insuranceApplication = new InsuranceApplication(new Person("Michael", 35), new Car("BMW", 10));

        boolean insuranceAllowed = insurancePolicyService.isInsuranceAllowed(insuranceApplication);

        assertThat(insuranceAllowed).isFalse();
    }

    @Test
    public void shouldBeDeniedForUnderaged() {
        InsuranceApplication insuranceApplication = new InsuranceApplication(new Person("Michael", 17), new Car("BMW", 8));

        boolean insuranceAllowed = insurancePolicyService.isInsuranceAllowed(insuranceApplication);

        assertThat(insuranceAllowed).isFalse();
    }
}
