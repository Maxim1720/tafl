package kz.trankwilizator.tafl.service.util;

import java.security.SecureRandom;
import java.util.Base64;

public class UniqueStringGenerator {
    private static final int LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();

    public static String generateUniqueString() {
        byte[] bytes = new byte[LENGTH];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes);
    }
}
