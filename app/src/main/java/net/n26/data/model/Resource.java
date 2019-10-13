package net.n26.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    private AuthStatus status;

    @Nullable
    public T data;

    @Nullable
    private Throwable message;

    private Resource(@NonNull AuthStatus status, @Nullable T data, @Nullable Throwable message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(AuthStatus.AUTHENTICATED, data, null);
    }

    public static <T> Resource<T> error(@NonNull Throwable msg, T data) {
        return new Resource<>(AuthStatus.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(AuthStatus.LOADING, data, null);
    }

    public static <T> Resource<T> logout() {
        return new Resource<>(AuthStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum AuthStatus {AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED}

}