package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Request.AvailableServiceRequest;
import org.example.shoesshopbe.Request.PaymentRequest;
import org.example.shoesshopbe.Response.AvailableServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Value("${ghn.token}")
    private String token;

    private final RestTemplate restTemplate;

    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", token);
        return headers;
    }
    @GetMapping("/provinces")
    public ResponseEntity<?> getProvinces() {
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        String url = "https://online-gateway.ghn.vn/shiip/public-api/master-data/province";
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    @PostMapping("/districts")
    public ResponseEntity<?> getDistricts(@RequestParam int province_id) {
        HttpHeaders headers = getHeaders();
        headers.set("Content-Type", "application/json");

        String body = String.format("{\"province_id\": %d}", province_id);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        String url = "https://online-gateway.ghn.vn/shiip/public-api/master-data/district";
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }


    @PostMapping("/commune")
    public ResponseEntity<?> getWards(@RequestParam int district_id) {
        HttpHeaders headers = getHeaders();
        headers.set("Content-Type", "application/json");

        String body = String.format("{\"district_id\": %d}", district_id);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        String url = "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward";
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
    @PostMapping("/shipping-fee")
    public ResponseEntity<?> calculateShippingFee(@RequestBody PaymentRequest paymentRequest) {
        HttpHeaders headers = getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 1. Call /available-services
        int shopId = 123456; // TODO: Replace with your actual shop ID
        AvailableServiceRequest serviceReq = new AvailableServiceRequest(
                shopId,
                paymentRequest.getFrom_district_id(),
                paymentRequest.getTo_district_id()
        );

        HttpEntity<AvailableServiceRequest> serviceEntity = new HttpEntity<>(serviceReq, headers);

        String serviceUrl = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services";

        ResponseEntity<AvailableServiceResponse> serviceResp = restTemplate.exchange(
                serviceUrl,
                HttpMethod.POST,
                serviceEntity,
                AvailableServiceResponse.class
        );

        List<AvailableServiceResponse.ServiceData> services = serviceResp.getBody().getData();
        if (services == null || services.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No available services found.");
        }

        int serviceId = services.get(0).getService_id(); // Lấy service_id đầu tiên

        // 2. Chuẩn bị payload cho API /fee
        Map<String, Object> feePayload = new HashMap<>();
        feePayload.put("from_district_id", paymentRequest.getFrom_district_id());
        feePayload.put("to_district_id", paymentRequest.getTo_district_id());
        feePayload.put("to_ward_code", String.valueOf(paymentRequest.getTo_commune_code()));
        feePayload.put("height", paymentRequest.getHeight());
        feePayload.put("length", paymentRequest.getLength());
        feePayload.put("weight", paymentRequest.getWeight());
        feePayload.put("width", paymentRequest.getWidth());
        feePayload.put("insurance_value", paymentRequest.getInsurance_value());
        feePayload.put("service_id", serviceId);
        feePayload.put("shop_id", shopId);

        HttpEntity<Map<String, Object>> feeEntity = new HttpEntity<>(feePayload, headers);

        String feeUrl = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee";

        return restTemplate.exchange(feeUrl, HttpMethod.POST, feeEntity, String.class);
    }


}
