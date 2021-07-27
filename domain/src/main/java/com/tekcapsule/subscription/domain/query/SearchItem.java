package com.tekcapsule.subscription.domain.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.ValueObject;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SearchItem implements ValueObject {
    private String emailId;
    private String activeSince;
}
