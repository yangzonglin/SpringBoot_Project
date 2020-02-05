package com.yzl.elasticsearch.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 使用SpringData ElasticSearch操作ES
 */
@Document(indexName = "yzl",type = "book")
public class Book {

    private Integer id;
    private String author;
    private String bookName;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
