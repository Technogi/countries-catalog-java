package com.technogi.catalogs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CountriesCatalogTest {
  @Test
  public void list() throws Exception {
    CountriesCatalog catalog = new CountriesCatalog();
    List<CountryDto> countries = catalog.list(CountryCodeType.ISO2, "es");

    CountryDto c1 = countries.stream().filter(c -> c.getCode().equals("US")).findAny().orElse(null);
    assertNotNull(c1);
    assertEquals("US",c1.getCode());
    assertTrue(c1.getName().startsWith("Estados Unidos"));
    assertEquals("americano", c1.getLabel("nationality"));

    CountryDto c2 = countries.stream().filter(c -> c.getCode().equals("HK")).findAny().orElse(null);
    assertNotNull(c2);
    assertEquals("HK",c2.getCode());
    assertEquals("Hong kong",c2.getName());
    assertEquals("de Hong kong", c2.getLabel("nationality"));
  }

}