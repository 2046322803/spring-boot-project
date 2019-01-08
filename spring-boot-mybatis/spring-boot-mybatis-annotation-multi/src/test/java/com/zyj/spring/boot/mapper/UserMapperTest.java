package com.zyj.spring.boot.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zyj.spring.boot.enums.UserSexEnum;
import com.zyj.spring.boot.mapper.test1.User1Mapper;
import com.zyj.spring.boot.mapper.test2.User2Mapper;
import com.zyj.spring.boot.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testInsert() throws Exception {
        user1Mapper.insert(new User("aa111", "a123456", UserSexEnum.MAN));
        user1Mapper.insert(new User("bb111", "b123456", UserSexEnum.WOMAN));
        user2Mapper.insert(new User("cc222", "b123456", UserSexEnum.MAN));
    }
}
