package com.example.final_project.enums;

public enum ShopItem {
    START_30_POINTS("start30Points", 200, "시작점수를 30점으로 설정(200원)"),
    DOUBLE_POINTS("doublePoints", 400, "공부 점수를 2배로 증가(400원)");

    private final String key;
    private final int price;
    private final String description;

    ShopItem(String key, int price, String description) {
        this.key = key;
        this.price = price;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
