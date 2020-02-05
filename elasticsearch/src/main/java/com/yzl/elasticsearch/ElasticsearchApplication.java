package com.yzl.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持3种技术来和ES交互（1.X为2种，2,X新增RestClient方式）
 * 1.Jest（默认不生效）
 *      需要导入jest的工具包：（大版本号需要和ES的大版本号相同）
 *          <dependency>
 *             <groupId>io.searchbox</groupId>
 *             <artifactId>jest</artifactId>
 *             <version>5.3.4</version>
 *         </dependency>
 *  2.SpringData ElasticSearch[ES版本需要和springdata的es版本对应]
 *      （PS：如果遇到不适配情况，可以通过两个方式解决：1.升级springboot版本2.安装对应版本的ES）
 *      https://docs.spring.io/spring-data/elasticsearch/docs/3.2.0.RC3/reference/html/#preface.versions
 *      1）Client：节点信息 clusterNodes、clusterName
 *      2）ElasticSearchTemplate操作ES
 *      3）编写一个ElasticsearchRepository的子接口来操作ES
 *  3.RestClient(Java High Level REST Client)操作ES
 */
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

}
