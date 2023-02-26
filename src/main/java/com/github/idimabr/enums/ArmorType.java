package com.github.idimabr.enums;

import lombok.Getter;

@Getter
public enum ArmorType {

    HELMET(3), CHESTPLATE(2), LEGGINGS(1), BOOTS(0);

    private final int id;

    ArmorType(int id) {
        this.id = id;
    }
}
