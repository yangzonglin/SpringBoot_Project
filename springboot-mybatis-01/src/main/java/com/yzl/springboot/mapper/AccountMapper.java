package com.yzl.springboot.mapper;

import com.yzl.springboot.bean.Account;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface AccountMapper {

    /**
     * useGeneratedKeys:使用自动生成的主键；keyProperty：主键名称；ID会自动回填到实体了
     * @param account
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "aid")
    @Insert("insert into tb_account(alogin,aname,apass) values(#{alogin},#{aname},#{apass})")
    public int addAccount(Account account);

    @Select("select * from tb_account where aid = #{aid}")
    public Account queryById(String aid);

    @Delete("delete from tb_account where aid = #{aid}")
    public int deleteAccount(String aid);

    @Update("update tb_account set alogin=#{alogin},aname=#{aname},apass=#{apass} where aid=#{aid}")
    public int updateAccount(Account account);
}
