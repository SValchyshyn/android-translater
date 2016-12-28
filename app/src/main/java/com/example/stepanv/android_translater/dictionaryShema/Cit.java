package com.example.stepanv.android_translater.dictionaryShema;

/**
 * Created by stepanv on 28.12.16.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"-type", "quote"})
public class Cit {

    @JsonProperty("-type")
    private String type;

    @JsonProperty("quote")
    private String quote;

    @JsonProperty("-type")
    public String getType() {
        return type;
    }

    @JsonProperty("-type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("quote")
    public String getQuote() {
        return quote;
    }

    @JsonProperty("quote")
    public void setQuote(String quote) {
        this.quote = quote;
    }

}
