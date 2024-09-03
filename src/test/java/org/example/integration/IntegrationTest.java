package org.example.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.streakTracker.StreakTracker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class IntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void createStreakTracker() throws Exception {
        StreakTracker streakTracker = new StreakTracker(1L, "new_streak", LocalDate.now().minusDays(4), 100L);
        MockHttpServletResponse response = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/streak")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(streakTracker)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        StreakTracker actual = objectMapper.readValue(response.getContentAsString(), new TypeReference<StreakTracker>() {
        });

        assertEquals(streakTracker.getName(), actual.getName());
        assertEquals(streakTracker.getStreakCreated(), actual.getStreakCreated());
    }

    @Test
    public void createStreakTracker() throws Exception {
        StreakTracker streakTracker = new StreakTracker(1L, "new_streak", LocalDate.now().minusDays(4), 100L);
        MockHttpServletResponse response = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/streak")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(streakTracker)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        StreakTracker actual = objectMapper.readValue(response.getContentAsString(), new TypeReference<StreakTracker>() {
        });

        assertEquals(streakTracker.getName(), actual.getName());
        assertEquals(streakTracker.getStreakCreated(), actual.getStreakCreated());
    }
}
