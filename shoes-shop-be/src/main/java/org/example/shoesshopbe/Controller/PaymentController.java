package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Model.Emails;
import org.example.shoesshopbe.Request.AvailableServiceRequest;
import org.example.shoesshopbe.Request.AddressRequest;
import org.example.shoesshopbe.Request.PaymentRequest;
import org.example.shoesshopbe.Response.AvailableServiceResponse;
import org.example.shoesshopbe.Service.PaymentService;
import org.example.shoesshopbe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;
   @Autowired
   private PaymentService paymentService;

    // RestTemplate là công cụ giúp gửi HTTP request từ Java tới API bên ngoài
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
        //Tạo nội dung (payload) cho request POST, dạng chuỗi JSON:
        String body = String.format("{\"province_id\": %d}", province_id);
       //Gói cả headers và body lại thành một HttpEntity để gửi trong RestTemplate.
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        String url = "https://online-gateway.ghn.vn/shiip/public-api/master-data/district";
        //Gửi request tới GHN bằng RestTemplate với"
        //url: địa chỉ API
        //HttpMethod.POST: dùng phương thức POST
        //entity: bao gồm cả headers và body
        //String.class: kiểu dữ liệu mong muốn nhận về là chuỗi JSON (kiểu String)
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
    public ResponseEntity<?> calculateShippingFee(@RequestBody AddressRequest addressRequest) {
        HttpHeaders headers = getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 1. Call /available-services
        int shopId = 5839993;

//      //Tạo request object gửi lên GHN để tra các dịch vụ phù hợp từ quận A → quận B.
        AvailableServiceRequest serviceReq = new AvailableServiceRequest(
                shopId,
                addressRequest.getFrom_district_id(),
                addressRequest.getTo_district_id()
        );

        HttpEntity<AvailableServiceRequest> serviceEntity = new HttpEntity<>(serviceReq, headers);

        String serviceUrl = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services";

        //GHN trả về JSON chứa danh sách các dịch vụ → ánh xạ thành AvailableServiceResponse.
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
        feePayload.put("from_district_id", addressRequest.getFrom_district_id());
        feePayload.put("to_district_id", addressRequest.getTo_district_id());
        feePayload.put("to_ward_code", String.valueOf(addressRequest.getTo_commune_code()));
        feePayload.put("height", addressRequest.getHeight());
        feePayload.put("length", addressRequest.getLength());
        feePayload.put("weight", addressRequest.getWeight());
        feePayload.put("width", addressRequest.getWidth());
        feePayload.put("insurance_value", addressRequest.getInsurance_value());
        feePayload.put("service_id", serviceId);
        feePayload.put("shop_id", shopId);

        HttpEntity<Map<String, Object>> feeEntity = new HttpEntity<>(feePayload, headers);

        String feeUrl = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee";

        return restTemplate.exchange(feeUrl, HttpMethod.POST, feeEntity, String.class);
    }
    @PostMapping("/email")
    public ResponseEntity<?> payment(@RequestBody Emails email) {
        paymentService.saveEmail(email);
        return ResponseEntity.ok("ok");
    }
}
