package vn.midatri.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Chart {
    private String title;
    private BigDecimal total;
    private Instant dates;
    private String thang;


    public Chart(String title, BigDecimal total) {
        this.title = title;
        this.total = total;
    }

    public Chart(BigDecimal total, Instant dates) {
        this.total = total;
        this.dates = dates;
    }
    public Chart(String thang) {
        this.thang = thang;
    }
}

