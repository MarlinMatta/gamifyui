package edu.uapa.ui.gamify.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    public static <T> T toObject(String json, Class<T> classOfJSON) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfJSON);
    }

    public static JsonObject toJsonObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonObject.class);
    }

    public static <T> List<T> toObjectList(String json, Class<T> typeClass) {
        return new Gson().fromJson(json, new ListOfJson<T>(typeClass));
    }

    public static String toJSON(Object object) {
        return new Gson().toJson(object);
    }

    private static class ListOfJson<T> implements ParameterizedType {
        private Class<?> wrapped;

        ListOfJson(Class<T> wrapper) {
            this.wrapped = wrapper;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
