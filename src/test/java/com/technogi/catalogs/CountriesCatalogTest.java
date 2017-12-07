package com.technogi.catalogs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CountriesCatalogTest {
  @Test
  public void list() throws Exception {
    CountriesCatalog catalog = new CountriesCatalog();
    List<CountryDto> countries = catalog.list(CountryCodeType.ISO2, "es");

    CountryDto c1 = countries.stream().filter(c -> c.getCode().equals("MX")).findAny().orElse(null);
    assertNotNull(c1);
    assertEquals("MX",c1.getCode());
    assertEquals("México",c1.getName());
    assertEquals("mexicano", c1.getLabel("nationality"));

    CountryDto c2 = countries.stream().filter(c -> c.getCode().equals("HK")).findAny().orElse(null);
    assertNotNull(c2);
    assertEquals("HK",c2.getCode());
    assertEquals("Hong kong",c2.getName());
    assertEquals("de Hong kong", c2.getLabel("nationality"));
  }

}