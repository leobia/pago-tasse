package it.leobia.controller.entities;

import it.leobia.controller.utils.DateUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "fatture", schema = "pago_tasse")
public class Fatture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "fattura", precision = 12, scale = 2)
    private BigDecimal fattura;

    @Column(name = "tassa", precision = 12, scale = 2)
    private BigDecimal tassa;

    @Column(name = "guadagno", precision = 12, scale = 2)
    private BigDecimal guadagno;

    @Column(name = "data")
    private Date data;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getFattura() {
        return fattura;
    }

    public void setFattura(BigDecimal fattura) {
        this.fattura = fattura;
    }

    public BigDecimal getTassa() {
        return tassa;
    }

    public void setTassa(BigDecimal tassa) {
        this.tassa = tassa;
    }

    public BigDecimal getGuadagno() {
        return guadagno;
    }

    public void setGuadagno(BigDecimal guadagno) {
        this.guadagno = guadagno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDataFormatted() {
        return DateUtils.formatDate(data);
    }
}
