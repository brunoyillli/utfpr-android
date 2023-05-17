package io.github.brunoyillli.organizadorreceitas.entity;

import io.github.brunoyillli.organizadorreceitas.R;

public enum MealTypeEnum {
    CAFE_DA_MANHA(R.string.cafe_da_manha),
    ALMOCO(R.string.almoco),
    CAFE_DA_TARDE(R.string.cafe_da_tarde),
    JANTA(R.string.janta);

    private int displayNameResId;

    MealTypeEnum(int displayNameResId) {
        this.displayNameResId = displayNameResId;
    }

    public int getDisplayNameResId() {
        return displayNameResId;
    }
}