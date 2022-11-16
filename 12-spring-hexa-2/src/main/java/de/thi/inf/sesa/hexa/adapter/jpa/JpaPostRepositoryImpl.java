package de.thi.inf.sesa.hexa.adapter.jpa;

import de.thi.inf.sesa.hexa.adapter.jpa.entities.PostEntity;
import de.thi.inf.sesa.hexa.domain.model.Post;
import de.thi.inf.sesa.hexa.domain.model.UserReference;
import de.thi.inf.sesa.hexa.ports.outgoing.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaPostRepositoryImpl implements PostRepository {

    @Autowired
    JpaPostRepository repo;

    @Override
    public void save(Post post) {
        PostEntity p = new PostEntity();
        p.setTitle(post.getTitle());
        p.setId(post.getId().toString());
        p.setContent(post.getContent());
        if(post.getUserRef() != null) {
            p.setUserRef(post.getUserRef().getReference().toString());
        }
        repo.save(p);
    }

    @Override
    public List<Post> listAll() {
        Iterable<PostEntity> all = repo.findAll();
        List<Post> posts = new ArrayList<>();
        all.forEach(el -> posts.add(el.toPost()));
        return posts;
    }

    @Override
    public Post findById(UUID id) {
        Optional<PostEntity> byId = repo.findById(id.toString());
        if(byId.isPresent()) {
            return byId.get().toPost();
        }
        return null;
    }

    @Override
    public long count() {
        return this.repo.count();
    }
}
