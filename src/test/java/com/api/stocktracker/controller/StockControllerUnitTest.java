package com.api.stocktracker.controller;

import com.api.stocktracker.domain.stocks.dto.GetStockDataResponse;
import com.api.stocktracker.service.StockMarketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StockControllerUnitTest {

    @Mock
    private StockMarketService stockMarketService;

    private StockController stockController;

    @BeforeEach
    void setUp() {
        stockController = new StockController(stockMarketService);
    }

    @Test
    void getStockDataReturnsGetStockResponse() {
        String query = "biggest-gainers";
        GetStockDataResponse expectedResponse =
                new GetStockDataResponse();

        Mockito.when(stockMarketService.getStockData(query))
                .thenReturn(expectedResponse);

        ResponseEntity<GetStockDataResponse> response =
                stockController.getStockData(query);

        assertEquals(expectedResponse, response.getBody());
    }
}