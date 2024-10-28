package io.graversen.minecraft.rcon.model;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-10-28
 */
@Data
public class R<T> {

    private Integer code;

    private T data;

    private String message;

    public static R ok() {
        R<Void> r = new R<>();
        r.setCode(200);
        r.setMessage("ok");
        return r;
    }

    public static R ok(String msg) {
        R<Void> r = new R<>();
        r.setCode(200);
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> okData(T data) {
        R<T> r = new R<>();
        r.setCode(200);
        r.setMessage("ok");
        r.setData(data);
        return r;
    }

    public static <T> R<T> okData(String msg, T data) {
        R<T> r = new R<>();
        r.setCode(200);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static R fail() {
        R<Void> r = new R<>();
        r.setCode(-999);
        r.setMessage("fail");
        return r;
    }

    public static R fail(String msg) {
        R<Void> r = new R<>();
        r.setCode(-999);
        r.setMessage(msg);
        return r;
    }
}
