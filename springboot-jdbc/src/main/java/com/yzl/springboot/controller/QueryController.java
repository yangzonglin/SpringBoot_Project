package com.yzl.springboot.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author yzl
 * @Create 2019/12/28
 */
@Controller
public class QueryController {

    @Resource
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/query")
    @ResponseBody
    public List<Map<String,Object>> query(){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from stests");
        return list;
    }
}
