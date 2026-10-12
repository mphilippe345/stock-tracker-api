package com.api.stocktracker.service;

import com.api.stocktracker.domain.stocks.dto.GetStockDataResponse;

public interface StockMarketService {

    GetStockDataResponse getStockData(String request);
}
