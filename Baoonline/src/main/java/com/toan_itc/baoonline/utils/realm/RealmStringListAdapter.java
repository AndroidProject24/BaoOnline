package com.toan_itc.baoonline.utils.realm;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import io.realm.RealmList;

/**
 * RealmList<String> Adapter for Gson
 */
public class RealmStringListAdapter implements JsonSerializer<RealmList<RealmString>>, JsonDeserializer<RealmList<RealmString>> {

    @Override
    public JsonElement serialize(RealmList<RealmString> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        for (RealmString string : src) {
            jsonArray.add(context.serialize(string));
        }
        return jsonArray;
    }

    @Override
    public RealmList<RealmString> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        RealmList<RealmString> realmStrings = new RealmList<>();
        JsonArray stringList = json.getAsJsonArray();
        for (JsonElement stringElement : stringList) {
            //realmStrings.add((RealmString) context.deserialize(stringElement, RealmString.class));
            realmStrings.add(new RealmString(stringElement.getAsString()));
        }
        return realmStrings;
    }
}
