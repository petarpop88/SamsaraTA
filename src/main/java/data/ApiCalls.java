package data;

public final class ApiCalls {

    private static final String CHECK_IF_USER_EXISTS = "/api/users/exists/";

    public static String createCheckIfUserExistsApiCall(String sUsername) {
        return CHECK_IF_USER_EXISTS + sUsername;
    }
}



