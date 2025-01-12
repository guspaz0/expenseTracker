package com.henry.expenseTracker.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Currency;
import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDTO implements Serializable {
    @JsonProperty(value="cca2")
    public String cca2;
    @JsonProperty(value="flags")
    public Map<String, String> flags;
    @JsonProperty(value="currencies")
    public Map<Currency,Map<String,String>> currencies;
    @JsonProperty(value="capital")
    public List<String> capital;
    @JsonProperty(value="name")
    public Map<String, Object> name;

}
