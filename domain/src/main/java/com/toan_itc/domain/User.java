/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toan_itc.domain;

/**
 * Class that represents a User in the domain layer.
 */
public class User {
  String id;
  String image_preview_url;
  String title;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImage_preview_url() {
    return image_preview_url;
  }

  public void setImage_preview_url(String image_preview_url) {
    this.image_preview_url = image_preview_url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getShort_description() {
    return short_description;
  }

  public void setShort_description(String short_description) {
    this.short_description = short_description;
  }

  String short_description;
}
