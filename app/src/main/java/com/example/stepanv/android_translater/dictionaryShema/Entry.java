package com.example.stepanv.android_translater.dictionaryShema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by stepanv on 28.12.16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"form", "sense"})
public class Entry {

    @JsonProperty("form")
    private Form form;

    @JsonProperty("sense")
    private List<Sense> sense;

    @JsonProperty("form")
    public Form getForm() {
        return form;
    }

    @JsonProperty("form")
    public void setForm(Form form) {
        this.form = form;
    }

    @JsonProperty("sense")
    public List<Sense> getSense() {
        return sense;
    }

    @JsonProperty("sense")
    public void setSense(List<Sense> sense) {
        this.sense = sense;
    }

}
