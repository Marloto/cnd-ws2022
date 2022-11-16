package de.thi.inf.cnd.hexa.adapter.jpa;

import de.thi.inf.cnd.hexa.adapter.jpa.entities.PostEntity;
import de.thi.inf.cnd.hexa.ports.out.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaPostRepositoryImpl implements PostRepository {

    @Autowired
    private JpaPostRepository repository;
    @Override
    public void save(Post post) {
        PostEntity p = new PostEntity();
        // ... p.getTitel(post.getTitel());
        repository.save(p);
    }
}
