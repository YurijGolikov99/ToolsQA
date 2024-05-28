package selenide.api_module.data.Registartion;

/**
 * {
 *   "code": 0,
 *   "message": "string"
 * }
 */

public class BadRegistrationResponse {

        private int code;
        private String message;

//        public BadRegistrationResponse(int code, String message) {
//                this.code = code;
//                this.message = message;
//        }

        public int getCode() {
                return code;
        }

        public String getMessage() {
                return message;
        }
}
