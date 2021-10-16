package com.li88qq.service.serviceImpl;

import com.li88qq.service.service.IGService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author li88qq
 * @version 1.0 2021/8/29 23:17
 */
@Service
public class GService implements IGService {

    @Override
    public List<Map<String, Object>> g(String q) {
        return new ArrayList<>();
    }
}
