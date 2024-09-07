package org.example.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class IntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


  /*  @Test
    public void createStreak() throws Exception {
        Streak streakTracker = new Streak(1L, "new_streak", LocalDate.now().minusDays(4), 100L);
        MockHttpServletResponse response = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/streak")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(streakTracker)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        Streak actual = objectMapper.readValue(response.getContentAsString(), new TypeReference<Streak>() {
        });

        assertEquals(streakTracker.getName(), actual.getName());
        assertEquals(streakTracker.getStreakCreated(), actual.getStreakCreated());
    }*/
}
