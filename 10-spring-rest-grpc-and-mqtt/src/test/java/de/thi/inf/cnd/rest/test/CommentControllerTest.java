package de.thi.inf.cnd.rest.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.thi.inf.cnd.rest.Application;
import de.thi.inf.cnd.rest.model.Comment;
import de.thi.inf.cnd.rest.model.Post;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")

public class CommentControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreateComment() throws Exception {
        Post post = new Post();
        post.setTitle("Dies ist ein Test");
        post.setContent("Ein Testtext");
        MvcResult result = mvc.perform(post("/posts")
                .content(asJsonString(post))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Dies ist ein Test"))
                .andExpect(jsonPath("$.content").value("Ein Testtext"))
                .andReturn();
        Post p = fromJsonString(result.getResponse().getContentAsString(), Post.class);
        Comment comment = new Comment();
        comment.setText("Interessant...");
        mvc.perform(post("/posts/" + p.getId() + "/comments")
                .content(asJsonString(comment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").value("Interessant..."));
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
