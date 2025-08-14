package org.example.shoesshopbe.Response;

import java.util.List;

public class AvailableServiceResponse {
    private List<ServiceData> data;

    public List<ServiceData> getData() {
        return data;
    }

    public void setData(List<ServiceData> data) {
        this.data = data;
    }

    public static class ServiceData {
        private int service_id;

        public int getService_id() {
            return service_id;
        }

        public void setService_id(int service_id) {
            this.service_id = service_id;
        }
    }
}
