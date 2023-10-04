package org.example.client;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public final class HelperClass {
    @NotNull
    @Contract("_ -> new")
    public static String toHash(@NotNull String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean checkHash(@NotNull String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);

        return result.verified;
    }
}
