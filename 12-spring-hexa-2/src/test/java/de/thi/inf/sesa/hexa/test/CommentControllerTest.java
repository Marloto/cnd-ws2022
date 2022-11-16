package de.thi.inf.sesa.hexa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.thi.inf.sesa.hexa.Application;
import de.thi.inf.sesa.hexa.adapter.jpa.JpaCommentRepository;
import de.thi.inf.sesa.hexa.adapter.jpa.JpaPostRepository;
import de.thi.inf.sesa.hexa.adapter.api.rest.dto.CreateCommentRequest;
import de.thi.inf.sesa.hexa.adapter.api.rest.dto.CreatePostRequest;
import de.thi.inf.sesa.hexa.adapter.api.rest.dto.PostResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

public class CommentControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JpaPostRepository posts;
    @Autowired
    private JpaCommentRepository comments;

    @Before
    public void cleanUp() {
        posts.deleteAll();
        comments.deleteAll();
    }

    @Test
    public void testCreatePost() throws Exception {
        CreatePostRequest post = new CreatePostRequest();
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
        PostResponse p = fromJsonString(result.getResponse().getContentAsString(), PostResponse.class);
        CreateCommentRequest comment = new CreateCommentRequest();
        comment.setContent("Interessant...");
        mvc.perform(post("/posts/" + p.getId() + "/comments")
                .content(asJsonString(comment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.content").value("Interessant..."));
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
