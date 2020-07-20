package io.swagger.api.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import io.swagger.api.NotFoundException;
import io.swagger.model.Statistics;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatsApiServiceImplTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String gameId = "7834657";
  private static final String questionId = "98374";
  private static final Map<String, Integer> MAP = Collections.singletonMap("1", 57);
  @Mock
  GameDao gameDao;

  private StatsApiServiceImpl statsApiService;

  @Before
  public void setUp() throws Exception {
    statsApiService = new StatsApiServiceImpl(MAPPER, gameDao);
  }

  @Test
  public void testGetStatisticsWith200Response() throws NotFoundException, JsonProcessingException {
    when(gameDao.getStatistics(questionId, gameId)).thenReturn(MAP);

    final Statistics response = new Statistics();
    response.put("1", 57);

    final javax.ws.rs.core.Response expected = javax.ws.rs.core.Response.ok().entity(MAPPER.writeValueAsString(response)).build();
    final Response actual = statsApiService.getStatistics(gameId, questionId, null);

    assertEquals("assert Entity", expected.getEntity(), actual.getEntity());
    assertEquals("assert status", expected.getStatus(), actual.getStatus());

  }

  @Test
  public void testGetStatisticsWith404Response() throws NotFoundException{
    when(gameDao.getStatistics(anyString(),anyString())).thenThrow(ResourceNotFoundException.class);

    final Response actual = statsApiService.getStatistics("p1", "g1", null);

    assertEquals("assert status", 404, actual.getStatus());

  }


}