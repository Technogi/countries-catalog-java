package com.technogi.catalogs;

import java.io.Serializable;
import java.util.Map;

public class CountryDto implements Serializable{

  private static final long serialVersionUID = -4787430339459794794L;

  private final String code;
  private final String name;
  private final Map<String,String> labels;

  public CountryDto(String code, String name, Map<String, String> labels) {
    this.code = code;
    this.name = name;
    this.labels = labels;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getLabel(String label){
    return labels.get(label);
  }
}
