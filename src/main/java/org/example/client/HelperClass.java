package org.example.client;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

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

    @NotNull
    public static String offsetDateTimeForString(@NotNull OffsetDateTime since) {
        return since.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @NotNull
    @Contract(pure = true)
    public static OffsetDateTime stringForOffsetDateTime(String since) {
        return OffsetDateTime.parse(since, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
