package it.unibo.ds.auth;

import com.google.protobuf.Timestamp;
import it.unibo.ds.auth.grpc.Proto;

import java.time.LocalDate;

public class Conversions {
    public static Proto.Role toProto(Role value) {
        return Proto.Role.forNumber(value.ordinal());
    }

    public static Role toJava(Proto.Role role) {
        return Role.values()[role.ordinal()];
    }

    public static Proto.Token toProto(Token value) {
        return Proto.Token.newBuilder()
                .setUsername(value.getUsername())
                .setRole(toProto(value.getRole()))
                .build();
    }

    public static Token toJava(Proto.Token value) {
        return new Token(value.getUsername(), toJava(value.getRole()));
    }

    public static Proto.Credentials toProto(Credentials value) {
        var builder = Proto.Credentials.newBuilder();
        if (value.getUserId() != null) builder.setId(value.getUserId());
        if (value.getPassword() != null) builder.setPassword(value.getPassword());
        return builder.build();
    }

    public static Credentials toJava(Proto.Credentials value) {
        return new Credentials(
                value.getId(),
                value.getPassword()
        );
    }

    private static final long SECONDS_PER_DAY = 60 * 60 * 24;

    public static LocalDate toJava(Timestamp value) {
        return LocalDate.ofEpochDay(value.getSeconds() / SECONDS_PER_DAY);
    }

    public static Timestamp toProto(LocalDate value) {
        return Timestamp.newBuilder()
                .setSeconds(value.toEpochDay()  * SECONDS_PER_DAY)
                .build();
    }

    public static Proto.User toProto(User value) {
        throw new Error("not implemented");
    }

    public static User toJava(Proto.User value) {
        throw new Error("not implemented");
    }
}
