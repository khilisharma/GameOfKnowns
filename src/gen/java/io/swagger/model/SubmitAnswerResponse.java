package io.swagger.model;

public enum SubmitAnswerResponse {
  RIGHT("RIGHT"),
  WRONG("WRONG");
  private String value;

  SubmitAnswerResponse(String value) {
    this.value = value;
  }

  public static SubmitAnswerResponse fromValue(String text) {
    for (SubmitAnswerResponse b : SubmitAnswerResponse.values()) {
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
