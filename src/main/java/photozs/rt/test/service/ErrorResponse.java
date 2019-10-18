package photozs.rt.test.service;

public class ErrorResponse {
    private int status;
    private String message;

    ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
