package rvt.utils;

public enum ConsoleColors {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RESET("\u001B[0m");

    private final String code;

    ConsoleColors(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}