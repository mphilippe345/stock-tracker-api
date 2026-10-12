package com.api.stocktracker.controller;

import com.api.stocktracker.domain.stocks.dto.GetStockDataResponse;
import com.api.stocktracker.service.StockMarketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StockControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StockMarketService stockMarketService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        reset(stockMarketService);
    }

    @Test
    void getStockDataReturns200WhenRequestSucceeds() throws Exception {
        GetStockDataResponse response = new GetStockDataResponse();
        ArrayNode stockData = objectMapper.createArrayNode();
        ObjectNode stock = objectMapper.createObjectNode();
        stock.put("symbol", "AAPL");
        stock.put("price", 250.50);
        response.setStockData(stockData);

        when(stockMarketService.getStockData("biggest-gainers"))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/stocks/getStockData")
                                .param("query", "biggest-gainers")
                )
                .andExpect(status().isOk());

        verify(stockMarketService)
                .getStockData("biggest-gainers");
    }

    @Test
    void getStockDataReturns400WhenQueryIsMissing() throws Exception {
        mockMvc.perform(get("/api/stocks/getStockData"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(stockMarketService);
    }

    @Test
    void getStockDataReturns400WhenQueryIsEmpty() throws Exception {
        mockMvc.perform(
                        get("/api/stocks/getStockData")
                                .param("query", "")
                )
                .andExpect(status().isBadRequest());

        verifyNoInteractions(stockMarketService);
    }

    @Test
    void getStockDataReturns500WhenServiceFails() throws Exception {
        when(stockMarketService.getStockData("biggest-gainers"))
                .thenThrow(new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Stock service unavailable"
                ));
        mockMvc.perform(
                        get("/api/stocks/getStockData")
                                .param("query", "biggest-gainers")
                )
                .andExpect(status().isInternalServerError());

        verify(stockMarketService)
                .getStockData("biggest-gainers");
    }
}