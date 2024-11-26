package selenide.api_module.data.book_store_application;

/**
 * DTO
 * или так называемые POJO class
 * {
 *   "code": 0,
 *   "message": "string"
 * }
 */

public class BadResponse {

        private int code;
        private String message;

        public int getCode() {
                return code;
        }

        public String getMessage() {
                return message;
        }
}
