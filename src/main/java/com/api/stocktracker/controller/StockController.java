package com.api.stocktracker.controller;

import com.api.stocktracker.common.constants.Paths;
import com.api.stocktracker.domain.stocks.dto.GetStockDataResponse;
import com.api.stocktracker.service.StockMarketService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.stocktracker.common.constants.Paths.GET_STOCK_DATA;

@RestController
@RequestMapping("/api/stocks")
@AllArgsConstructor
@Slf4j
public class StockController {

    private final StockMarketService stockMarketService;

    @GetMapping(GET_STOCK_DATA)
    public ResponseEntity<GetStockDataResponse> getStockData(@RequestParam @NotBlank String query ) {
        log.info("Request received for getStockData");
        return new ResponseEntity<>(stockMarketService.getStockData(query), HttpStatus.OK);
    }
}
