package org.example.demomybtis.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.IEnum;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AccountStatus implements IEnum<String> {
  ENABLED("enabled", "启用"),
  DISABLED("disabled", "禁用"),
  LOCKED("locked", "锁定"),
  PENDING_ACTIVATION("pending_activation", "待激活"),
  DELETED("deleted", "已注销"),
  UNDER_REVIEW("under_review", "审核中"),
  TRIAL("trial", "试用期"),
  EXPIRED("expired", "已过期");

  private final String code;
  private final String desc;

  public static final AccountStatus fromCode(String code){
    return Arrays.stream(AccountStatus.values())
            .filter(e -> e.getCode().equals(code))
            .findFirst()
            .orElse(null);
  }
}
