package com.sadeghian.banking.bootstrap.error;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard API error response")
public class ApiErrorResponse {

    @Schema(example = "404")
    private int status;

    @Schema(example = "NOT_FOUND")
    private String error;

    @Schema(example = "Customer not found")
    private String message;

    @Schema(example = "/api/transactions")
    private String path;

    public ApiErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
}
