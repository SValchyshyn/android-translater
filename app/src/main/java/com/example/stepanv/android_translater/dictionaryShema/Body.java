package com.example.stepanv.android_translater.dictionaryShema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by stepanv on 28.12.16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"entry"})
public class Body {

    @JsonProperty("entry")
    private List<Entry> entry = null;

    @JsonProperty("entry")
    public List<Entry> getEntry() {
        return entry;
    }

    @JsonProperty("entry")
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

}
