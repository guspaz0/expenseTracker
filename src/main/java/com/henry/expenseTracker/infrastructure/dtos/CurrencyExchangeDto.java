package com.henry.expenseTracker.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Currency;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchangeDto implements Serializable {

    private String result;
    @JsonProperty(value="time_last_update_unix")
    private Timestamp last_update;
    @JsonProperty(value="time_next_update_unix")
    private Timestamp next_update;
    private Currency base_code;
    private Currency target_code;
    private Double conversion_rate;

    public void setLast_update(Long last_update) {
        this.last_update = new Timestamp(last_update*1000L);
    }

    public Timestamp getLast_update(Long last_update) {
        return this.last_update;
    }
    public void setNext_update(Long next_update) {
        this.next_update = new Timestamp(next_update*1000L);
    }

    public Timestamp getNext_update(Long next_update) {
        return this.next_update;
    }
}
