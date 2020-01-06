package com.yzl.cache.pojo;

import org.apache.ibatis.annotations.Select;

public class User {
    private Integer id;
    private String sname;
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
