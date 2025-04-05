package selenide.api_module.data.book_store_application_dto;

import java.util.Date;

public class GenerateTokenResponse {

    public String token;
    public Date expires;
    public String status;
    public String result;

    public String getToken() {
        return token;
    }

    public Date getExpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }
}
