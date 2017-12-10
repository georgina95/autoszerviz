package autoszerviz.utility;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Response<T> {

    private String error;
    private T data;

    public static <T> Response<T> ok(T data) {
        return new Response<T>(null, data);
    }

    public static <T> Response<T> error(String message) {
        return new Response<T>(message, null);
    }
}
