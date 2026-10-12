package com.api.stocktracker.service;

import com.api.stocktracker.domain.stocks.dto.GetStockDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockMarketServiceImpl implements StockMarketService {

    private final LambdaClient lambdaClient;
    private final ObjectMapper objectMapper;
    @Value("${aws.lambda.function-name}")
    private String functionName;

    @Override
    public GetStockDataResponse getStockData(String request) {
        GetStockDataResponse getStockDataResponse = new GetStockDataResponse();

        Map<String, Object> requestBody = Map.of(
                "query", request
        );

        String jsonPayload =
                objectMapper.writeValueAsString(requestBody);

        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(jsonPayload))
                .build();
        InvokeResponse response = lambdaClient.invoke(invokeRequest);

        if (response.functionError() != null) {
            throw new RuntimeException("Server error: " + response.functionError());
        }
        JsonNode jsonResponse = objectMapper.readTree(response.payload().asUtf8String());
        getStockDataResponse.setStockData((ArrayNode) jsonResponse);

        return getStockDataResponse;
    }
}
