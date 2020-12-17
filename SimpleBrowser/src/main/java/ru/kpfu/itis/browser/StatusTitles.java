package ru.kpfu.itis.browser;

public enum StatusTitles {
    SUCCESS("Страница успешно загружена"),
    ERROR("Некорректный адрес");

    private String description;

    StatusTitles(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
