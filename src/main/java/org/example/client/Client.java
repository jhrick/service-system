package org.example.client;

import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class Client {
    private final UUID uniqueId;
    private String name;
    private final OffsetDateTime since;
    private String password;

    public Client(@Nullable UUID uniqueId, String name, OffsetDateTime since, String password) {
        this.uniqueId = (!(uniqueId == null) ? uniqueId : UUID.randomUUID());
        this.name = name;
        this.since= since;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public OffsetDateTime getSince() {
        return since;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
