package com.toan_itc.data.local.cache.serializer;

import com.google.gson.Gson;
import com.toan_itc.data.model.rss.RssFeedItem;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public JsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param userEntity {@link com.toan_itc.data.model.rss.RssFeedItem} to serialize.
   */
  public String serialize(RssFeedItem userEntity) {
    String jsonString = gson.toJson(userEntity, RssFeedItem.class);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link RssFeedItem}
   */
  public RssFeedItem deserialize(String jsonString) {
    RssFeedItem userEntity = gson.fromJson(jsonString, RssFeedItem.class);
    return userEntity;
  }
}
