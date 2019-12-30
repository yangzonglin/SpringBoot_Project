package com.yzl.springboot.entity;

import javax.persistence.*;

/**
 * @Author yzl
 * @Create 2019/12/30
 */
//使用JPA注解配置映射关系
@Entity//告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "jpa_user")//@Table来指定和哪一个数据表对应，如果省略，则默认为user（类名）
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column //省略配置则列明为属性名，长度255等都有default值
    private String sname;
    @Column(name = "spass_word",length = 50)//配置数据表对应的列和字段长度
    private String spassWord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpassWord() {
        return spassWord;
    }

    public void setSpassWord(String spassWord) {
        this.spassWord = spassWord;
    }
}
