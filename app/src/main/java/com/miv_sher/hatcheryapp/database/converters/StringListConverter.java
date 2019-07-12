package com.miv_sher.hatcheryapp.database.converters;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConverter {
    @TypeConverter
    public String fromStringList(List<String> stringList) {
        return stringList.toString();
    }

    @TypeConverter
    public List<String> toStringList(String data) {
        return Arrays.asList(data);
    }
}

