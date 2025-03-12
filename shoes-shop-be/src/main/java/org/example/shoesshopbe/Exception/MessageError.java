package org.example.shoesshopbe.Exception;

public enum MessageError {

    INVALID_USERNAME("Invalid username or password."),
    SUCCESSFUL("Successful"),
    UNEXPECTED_ERROR("An unexpected error occurred"),
    REQUIRE("Vui lòng nhập đầy đủ thông tin");

    private String message;

    MessageError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
