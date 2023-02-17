package com.seerbit.transactionstatistics.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticsResponse {
    private String sum;
    private String avg;
    private String max;
    private String min;
    private long count;
}
