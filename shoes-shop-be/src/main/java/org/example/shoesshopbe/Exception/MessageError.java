package org.example.shoesshopbe.Exception;

public enum MessageError {

    INVALID_USERNAME("Invalid username or password."),
    REQUIRE("Vui lòng nhập đầy đủ thông tin");

    private String message;

    MessageError(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
