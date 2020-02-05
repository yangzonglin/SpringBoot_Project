package com.yzl.elasticsearch;

import com.yzl.elasticsearch.bean.Article;
import com.yzl.elasticsearch.bean.Book;
import com.yzl.elasticsearch.repository.BookRepostory;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepostory bookRepostory;

    @Test
    void testSpringDataElasticsearch(){
        Book book = new Book();
        book.setId(1);
        book.setAuthor("吴承恩");
        book.setBookName("西游记");
        book.setContent("三打白骨精");
        bookRepostory.index(book);

        List<Book> books = bookRepostory.findByBookNameLike("吴");
    }

    //测试索引
    @Test
    void contextLoads() {
        Article article = new Article();
        article.setId(1);
        article.setAuthor("yzl");
        article.setTitle("newspaper");
        article.setContent("Hello World");

        //索引
        Index index = new Index.Builder(article).index("yzl").type("newspaper").build();
        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试检索(查询表达式)
    @Test
    void search(){
        String searchStr = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"Hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //addIndex和addType可以省略，则检索范围增大
        Search search = new Search.Builder(searchStr).addIndex("yzl").addType("news").build();
        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
