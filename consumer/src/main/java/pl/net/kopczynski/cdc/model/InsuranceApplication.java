package pl.net.kopczynski.cdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Tomasz Kopczynski
 */
@AllArgsConstructor
@Getter
public class InsuranceApplication {

    private Person insured;
    private Car car;
}
