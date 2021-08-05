package com.rhb.es.jest;

import com.rhb.es.pojo.User;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Index.Builder;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/8/5 10:18
 */
@Slf4j
//@Component
public class SearchUtil {

  @Autowired
  JestClient jestClient;

  public void addIndex(User user){
    Index index = new Builder(user).index("user-index").type("user").build();
    try {
      DocumentResult result = jestClient.execute(index);
    } catch (IOException e) {
      log.info("jestClient add fail!");
    }
  }
}
