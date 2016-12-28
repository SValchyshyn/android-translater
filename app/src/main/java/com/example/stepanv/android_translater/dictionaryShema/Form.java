package com.example.stepanv.android_translater.dictionaryShema;

/**
 * Created by stepanv on 28.12.16.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"orth", "pron"})
public class Form {

    @JsonProperty("orth")
    private String orth;

    @JsonProperty("pron")
    private String pron;

    @JsonProperty("orth")
    public String getOrth() {
        return orth;
    }

    @JsonProperty("orth")
    public void setOrth(String orth) {
        this.orth = orth;
    }

    @JsonProperty("pron")
    public String getPron() {
        return pron;
    }

    @JsonProperty("pron")
    public void setPron(String pron) {
        this.pron = pron;
    }

}
