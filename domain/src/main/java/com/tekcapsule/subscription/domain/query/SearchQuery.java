package com.tekcapsule.subscription.domain.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import in.devstream.core.domain.Query;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SearchQuery extends Query {
}
