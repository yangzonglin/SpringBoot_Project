package com.yzl.user;

import com.yzl.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboCustomerUserApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        String str = userService.hello();
        System.out.println(str);
    }

}
