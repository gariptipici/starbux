package com.bestseller.starbux.testutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.springframework.test.web.servlet.MvcResult;

public class TestUtil {

  private static final ObjectMapper MAPPER = new ObjectMapper()
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .registerModule(new JavaTimeModule());

  public static String requestBody(Object request) {
    try {
      return MAPPER.writeValueAsString(request);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
    try {
      String contentAsString = result.getResponse().getContentAsString();
      return MAPPER.readValue(contentAsString, responseClass);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
