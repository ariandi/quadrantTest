package com.example.quadranttest.controllers;

import com.example.quadranttest.config.JwtTokenUtil;
import com.example.quadranttest.models.Blogs;
import com.example.quadranttest.models.GeneralResponse;
import com.example.quadranttest.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class BlogController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    BlogsService blogsService;

    @Autowired
    GeneralResponse generalResponse;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/blogs")
    public List<Blogs> findAll() {
        return blogsService.getAll();
    }

    @GetMapping("/blogs/{id}")
    public Blogs findById(@PathVariable int id) {
        return blogsService.getById(id);
    }

    @GetMapping("/blogs/title/{title}")
    public Blogs findByTitle(@PathVariable String title) {
        return blogsService.getByTitle(title);
    }

    @PostMapping("/blogs")
    public GeneralResponse add(@RequestBody Blogs blog) {
        if (!validateAddBlog(blog)) {
            return generalResponse;
        }

        HashMap<String, Object> mapUser = new HashMap<>();
        try {
            Blogs res = blogsService.save(blog);
            mapUser.put("blog", res);
            generalResponse.setResultMsg("Success");
            generalResponse.setResultCd(200);
            generalResponse.setData(mapUser);

            return generalResponse;
        } catch (Exception e) {
            e.getMessage();
            generalResponse.setResultMsg(e.getMessage());
            generalResponse.setResultCd(403);
        }
        return generalResponse;
    }

    @PutMapping("/blog")
    public GeneralResponse update(@RequestBody Blogs blog) {
        HashMap<String, Object> mapUser = new HashMap<>();
        String token = "";

        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);

            blog.setUpdatedBy(username);

            Blogs res = blogsService.update(blog);
            mapUser.put("blog", res);
            generalResponse.setResultMsg("Success");
            generalResponse.setResultCd(200);
            generalResponse.setData(mapUser);

            return generalResponse;
        } catch (Exception e) {
            e.getMessage();
            generalResponse.setResultMsg(e.getMessage());
            generalResponse.setResultCd(403);
        }
        return generalResponse;
    }

    private boolean validateAddBlog(Blogs blogs) {
        boolean valid = true;
        generalResponse.setData(null);

        if (blogs.getTitle() == null || blogs.getTitle().equals("")) {
            generalResponse.setResultCd(403);
            generalResponse.setResultMsg("blog title field is mandatory");
            valid = false;
        }

        if (blogs.getContent() == null || blogs.getContent().equals("")) {
            generalResponse.setResultCd(403);
            generalResponse.setResultMsg("blog content field is mandatory");
            valid = false;
        }

        return valid;
    }

    @RequestMapping(ERROR_PATH)
    public GeneralResponse handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMsg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        GeneralResponse result = new GeneralResponse();
        result.setResultCd(999);
        result.setResultMsg("General Error");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                result.setResultCd(HttpStatus.UNAUTHORIZED.value());
            }
            else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                result.setResultCd(HttpStatus.NOT_FOUND.value());
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                result.setResultCd(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }

            result.setResultMsg(errorMsg.toString());

            return result;
        }

        return result;
    }
}
