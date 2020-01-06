package com.yzl.cache.mapper;

import com.yzl.cache.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id} ")
    public User getById(Integer id);

    @Update("update user set sname=#{sname},spass_word=#{spassWord} where id=#{id}")
    public int updateById(User user);

    @Delete("delete from user where id = #{id}")
    public int deleteById(Integer id);
}
