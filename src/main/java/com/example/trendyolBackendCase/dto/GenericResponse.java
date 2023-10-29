package com.example.trendyolBackendCase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericResponse {
    private Boolean result;
    private String message;
}
