package util;


public class RequestContext {
    private static final ThreadLocal<String> CORRELATION_ID = new ThreadLocal<>();

    public static void set(String id) {
        CORRELATION_ID.set(id);
    }

    public static String get() {
        return CORRELATION_ID.get() != null ? CORRELATION_ID.get() : "SYSTEM";
    }

    public static void clear() {
        CORRELATION_ID.remove();
    }
}
