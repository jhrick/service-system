package org.example.client;

import at.favre.lib.crypto.bcrypt.BCrypt;

public interface PasswordUtilities {
    default String hash(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    default boolean checkHash(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);

        return result.verified;
    }
}
