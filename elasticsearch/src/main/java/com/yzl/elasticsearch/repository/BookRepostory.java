package com.yzl.elasticsearch.repository;

import com.yzl.elasticsearch.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepostory extends ElasticsearchRepository<Book,Integer> {
    //自定义查询
    //相关资料参照文档
    //https://docs.spring.io/spring-data/elasticsearch/docs/3.2.0.RC3/reference/html/#elasticsearch.query-methods
    List<Book> findByBookNameLike(String bookName);
}
