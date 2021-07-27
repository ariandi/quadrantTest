package com.example.quadranttest.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
public class GeneralResponse {

    public int resultCd;
    public String resultMsg;
    public HashMap<String, Object> data;

}
