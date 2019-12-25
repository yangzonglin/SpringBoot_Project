package com.yzl.springbootrestfulcrud.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @Author yzl
 * @Create 2019/12/20
 */
//给容器中加入我们自己定义的ErrorAttribute
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {

    //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map =  super.getErrorAttributes(webRequest, includeStackTrace);
        //webRequest封装了HttpServletRequest，该方法第二个参数表示从哪个域中获取，0是从request，1是从session
        Map<String,Object> params = (Map<String, Object>) webRequest.getAttribute("ext",0);
        map.put("author",params==null?"null":params.get("author"));
        return map;
    }
}
