package com.example.trendyolBackendCase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisplayCartResponse {
    private Boolean result;
    private CartDTO message;
}
