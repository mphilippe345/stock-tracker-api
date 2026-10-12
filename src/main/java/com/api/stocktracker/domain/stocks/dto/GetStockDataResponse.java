package com.api.stocktracker.domain.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tools.jackson.databind.node.ArrayNode;

@Getter
@Setter
@ToString
public class GetStockDataResponse {
    private ArrayNode stockData;
}
