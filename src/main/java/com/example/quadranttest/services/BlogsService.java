package com.example.quadranttest.services;

import com.example.quadranttest.models.Blogs;
import com.example.quadranttest.models.Users;
import com.example.quadranttest.repositories.BlogsRepository;
import com.example.quadranttest.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BlogsService {

    @Autowired
    private BlogsRepository blogsRepository;

    public Blogs save(Blogs blog) throws NoSuchAlgorithmException {
        if (blog.getCreatedAt() == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            blog.setCreatedAt(dtf.format(now));
        }

        return blogsRepository.save(blog);
    }

    public List<Blogs> getAll() {
        return blogsRepository.findAll();
    }

    public Blogs getById(int id) {
        return blogsRepository.findById(id).orElse(null);
    }

    public Blogs getByTitle(String title) {
        return blogsRepository.findByTitle(title);
    }

    public String delete(int id) {
        blogsRepository.deleteById(id);
        return "blog " + id + " deleted";
    }

    public Blogs update(Blogs blog) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        blog.setUpdatedBy(dtf.format(now));

        Blogs existingBlog = blogsRepository.findById(blog.getId()).orElse(null);
        assert existingBlog != null;
        existingBlog.setTitle(blog.getTitle());
        existingBlog.setContent(blog.getContent());
        if (blog.getImg() != null) {
            existingBlog.setImg(blog.getImg());
        }
        if (blog.getActive() != 0) {
            existingBlog.setActive(blog.getActive());
        }

        existingBlog.setUpdatedAt(blog.getUpdatedAt());
        existingBlog.setUpdatedBy(blog.getUpdatedBy());
        return blogsRepository.save(existingBlog);
    }
}
