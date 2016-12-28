package com.example.stepanv.android_translater.dictionaryShema;

/**
 * Created by stepanv on 28.12.16.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"cit", "-n"})
public class Sense {

    @JsonProperty("cit")
    private List<Cit> cit;

    @JsonProperty("-n")
    private String n;

    @JsonProperty("cit")
    public List<Cit> getCit() {
        return cit;
    }

    @JsonProperty("cit")
    public void setCit(List<Cit> cit) {
        this.cit = cit;
    }

    @JsonProperty("-n")
    public String getN() {
        return n;
    }

    @JsonProperty("-n")
    public void setN(String n) {
        this.n = n;
    }
}

