package com.gameofknowns.dao.model;

public enum GameStatusEnum {
  CLOSED("CLOSED"), IN_PROGRESS("IN_PROGRESS"), WAITING_FOR_PLAYERS("WAITING_FOR_PLAYERS");

  private String value;

  GameStatusEnum(String value) {
    this.value =value;
  }

  public String toString() {
    return String.valueOf(value);
  }
}
