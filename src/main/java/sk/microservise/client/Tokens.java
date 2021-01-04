package sk.microservise.client;


public class Tokens {
    public static final String userUrl = "http://localhost:8999/registrator";
    public static final String flightUrl = "http://localhost:8999/letovi";
    public static final String ticketUrl = "http://localhost:8999/karte";

    public static final String HEADER_STRING = "Authorization";

    private static String adminToken = "";

    public static String getAdminToken() {
        return adminToken;
    }

    public static void setAdminToken(String adminToken) {
        Tokens.adminToken = adminToken;
    }
}
