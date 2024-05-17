package com.project.taxcalculate.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Component
public class GeneralUtil {

    public String convertToRM(BigDecimal amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("ms", "MY"));
        return Objects.nonNull(amount) ? currencyFormatter.format(amount).split("\\.")[0] : null;
    }
}
