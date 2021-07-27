package com.example.quadranttest.repositories;

import com.example.quadranttest.models.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogsRepository extends JpaRepository<Blogs, Integer> {

    Blogs findByTitle(String title);

}
