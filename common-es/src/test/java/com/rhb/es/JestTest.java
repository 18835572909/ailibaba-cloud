package com.rhb.es;

import cn.hutool.json.JSONUtil;
import com.rhb.es.pojo.User;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Index.Builder;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Jest单元测试类
 *
 * @author renhuibo
 * @date 2021/8/5 10:30
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JestTest {

  @Resource
  JestClient jestClient;

  @Test
  public void addIndex(){
    User user = User.builder().city("广州").age(23).username("小张").sex(1).password("admin").build();
    Index index = new Builder(user).index("user-index").type("user").build();
    try {
      DocumentResult result = jestClient.execute(index);
    } catch (IOException e) {
      e.printStackTrace();
      log.info("JestClient Add Fail!");
    }
  }

  @Test
  public void search(){
    Map<String,String> fieldMap = new HashMap<>(1);
    fieldMap.put("username","张");
    Map<String,Map<String,String>> matchMap = new HashMap<>(1);
    matchMap.put("match",fieldMap);
    Map<String,Map<String,Map<String,String>>> queryMap = new HashMap<>(1);
    queryMap.put("query",matchMap);

    Search search = new Search.Builder(JSONUtil.toJsonStr(queryMap))
        .addIndex("user-index")
        .addType("user")
        .build();

    try {
      SearchResult result = jestClient.execute(search);
      log.info("result:{}",result.getJsonString());
    } catch (IOException e) {
      log.info("Jest Search Fail!");
    }
  }

}
