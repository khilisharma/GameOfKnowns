package com.gameofknowns.dao.model;

public enum PlayerStatusEnum {
  JOINED("JOINED"),
  WAIT("WAIT");
  private final String value;

  PlayerStatusEnum(String value) {
    this.value = value;
  }

  public static PlayerStatusEnum fromValue(String text) {
    for (PlayerStatusEnum b : PlayerStatusEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public String toString() {
    return String.valueOf(value);
  }
}
