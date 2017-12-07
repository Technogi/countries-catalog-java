package com.technogi.catalogs;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class CountriesCatalog {

  private Map<String, Map<String, Object>> map = null;

  @PostConstruct
  private void init() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("countries.json").getFile());
    map = mapper.readValue(file, Map.class);
  }


  public List<CountryDto> list(CountryCodeType code, String lang) {
    LinkedList<CountryDto> countryDtos = new LinkedList<>();
    if (map == null) try {
      init();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (String key : map.keySet()) {
      Map<String, Object> c = map.get(key);
      String name = ((Map<String, String>) c.get("names")).get(lang);
      String codeValue;
      if (CountryCodeType.ISO3.equals(code)) {
        codeValue = c.get("iso3Code").toString();
      } else {
        codeValue = c.get("iso2Code").toString();
      }
      Map<String, String> labels = ((List<Map<String, String>>) c.get("labels"))
          .stream()
          .filter(label -> label.get("lang").equals(lang)).collect(Collectors.toMap(v -> v.get("name"), v -> v.get("value")));

      //Normalize nationality
      if (!labels.containsKey("nationality") && "es" .equals(lang)) {
        labels.put("nationality", "de " + name);
      }

      if (!labels.containsKey("nationality") && "en" .equals(lang)) {
        labels.put("nationality", "from " + name);
      }

      countryDtos.add(new CountryDto(codeValue, name, labels));

    }


    return countryDtos;
  }
}
