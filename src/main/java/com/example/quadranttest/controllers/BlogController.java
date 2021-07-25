package com.example.quadranttest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BlogController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @GetMapping("/hello")
    public ResponseEntity<String> HelloWorld() throws JsonProcessingException {

        Map<String, Object> mapper = new HashMap<String, Object>();
        mapper.put("result", "hello world");

        ObjectMapper objMapper = new ObjectMapper();
        String response = objMapper.writeValueAsString(mapper);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<>(
                response, headers, HttpStatus.OK);
    }

    @RequestMapping(ERROR_PATH)
    public HashMap<String, Object> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMsg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        HashMap<String, Object> result = responseWs(999, "General Error");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                result = responseWs(HttpStatus.UNAUTHORIZED.value(), errorMsg.toString());
            }
            else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                result = responseWs(HttpStatus.NOT_FOUND.value(), errorMsg.toString());
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                result = responseWs(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMsg.toString());
            }

            return result;
        }

        return result;
    }

    public HashMap<String, Object> responseWs (int rsCd, String rsMsg) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("resultCd", rsCd);
        result.put("resultMessage", rsMsg);

        return result;
    }

    public HashMap<String, Object> responseWs (String rsCd, String rsMsg, HashMap<String, Object> data) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("resultCd", rsCd);
        result.put("resultMessage", rsMsg);
        result.put("data", data);

        return result;
    }
}
