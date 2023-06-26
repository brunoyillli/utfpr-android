package io.github.brunoyillli.organizadorreceitas.entity;

import androidx.room.TypeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.brunoyillli.organizadorreceitas.entity.MealTypeEnum;

public class MealTypeConverter {
    @TypeConverter
    public static List<MealTypeEnum> fromString(String value) {
        List<MealTypeEnum> mealTypes = new ArrayList<>();
        String[] values = value.split(",");
        for (String mealType : values) {
            mealTypes.add(MealTypeEnum.valueOf(mealType));
        }
        return mealTypes;
    }

    @TypeConverter
    public static String toString(List<MealTypeEnum> mealTypes) {
        StringBuilder sb = new StringBuilder();
        for (MealTypeEnum mealType : mealTypes) {
            sb.append(mealType.name()).append(",");
        }
        return sb.toString();
    }
}
