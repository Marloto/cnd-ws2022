package de.thi.inf.sesa.hexa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.thi.inf.sesa.hexa.Application;
import de.thi.inf.sesa.hexa.adapter.jpa.JpaPostRepository;
import de.thi.inf.sesa.hexa.adapter.api.rest.dto.CreatePostRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc

public class PostControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JpaPostRepository repo;

    @Before
    public void cleanUp() {
        repo.deleteAll();
    }

    @Test
    public void testCreatePost() throws Exception {
        CreatePostRequest post = new CreatePostRequest();
        post.setTitle("Dies ist ein Test");
        post.setContent("Ein Testtext");
        mvc.perform(post("/posts")
                .content(asJsonString(post))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Dies ist ein Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Ein Testtext"));
    }

    @Test
    public void testEmptyList() throws Exception {
        mvc.perform(get("/posts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void testListWithTwoEntries() throws Exception {
        CreatePostRequest post1 = new CreatePostRequest();
        post1.setTitle("T1");
        CreatePostRequest post2 = new CreatePostRequest();
        post2.setTitle("T2");
        mvc.perform(post("/posts")
                .content(asJsonString(post1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(post("/posts")
                .content(asJsonString(post2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(get("/posts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].title", containsInAnyOrder("T1", "T2")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <D> D fromJsonString(final String data, final Class<D> clazz) {
        try {
            return new ObjectMapper().readValue(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

