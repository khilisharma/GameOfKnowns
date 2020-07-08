package com.gameofknowns.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Choice {

  String choiceId;
  String text;
}
