package de.thi.inf.sesa.rest.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.thi.inf.sesa.rest.Application;
import de.thi.inf.sesa.rest.model.Post;
import de.thi.inf.sesa.rest.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")

public class PostControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PostRepository repo;

    @Before
    public void cleanUp() {
        repo.deleteAll();
    }

    @Test
    public void testCreatePost() throws Exception {
        Post post = new Post();
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
        Post post = new Post();
        post.setTitle("Dies ist ein Test");
        post.setContent("Ein Testtext");
        mvc.perform(get("/posts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void testListWithTwoEntries() throws Exception {
        Post post1 = new Post();
        post1.setTitle("T1");
        Post post2 = new Post();
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

    @Test
    public void testUpdate() throws Exception {
        Post post1 = new Post();
        post1.setTitle("T1");
        post1.setContent("C1");
        MvcResult result = mvc.perform(post("/posts")
                .content(asJsonString(post1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Post p = fromJsonString(result.getResponse().getContentAsString(), Post.class);
        p.setTitle("T2");
        p.setContent("C2");
        mvc.perform(put("/posts/" + p.getId())
                .content(asJsonString(p))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("T2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("C2"));
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

