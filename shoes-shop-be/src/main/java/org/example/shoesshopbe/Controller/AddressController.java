package org.example.shoesshopbe.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Value("${ghn.token}")
    private String token;

    private final RestTemplate restTemplate;

    public AddressController(RestTemplate restTemplate) {
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

}
