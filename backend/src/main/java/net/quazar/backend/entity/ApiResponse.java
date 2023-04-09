package net.quazar.backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @Builder
public class ApiResponse<T> {
    @JsonProperty("error")
    private final String error;
    @JsonProperty("code")
    private final Integer code;
    @JsonProperty("data")
    private final T data;
}
