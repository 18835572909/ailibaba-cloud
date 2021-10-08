package com.rhb.es.jest;

import com.google.gson.JsonArray;
import com.rhb.es.util.ExceptionUtil;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

/**
 * Es的基本操作类
 *
 * @author renhuibo
 * @date 2021/10/8 11:12
 */
@Slf4j
@Component
public class EsBaseOperate {

  @Resource
  JestClient jestClient;

  /**
   * 获取es的id
   *
   * @param index 索引
   * @param type 类型
   * @param field 属性
   * @param value 值
   * @return id
   */
  public String getEsId(String index,String type,String field,String value){
    String id = null;

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(field,value);
    searchSourceBuilder.query(termQueryBuilder);

    Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(index).addType(type).build();
    try{
      SearchResult searchResult = jestClient.execute(search);
      if(searchResult.isSucceeded()){
        JsonArray hits = searchResult.getJsonObject().getAsJsonObject("hits")
            .getAsJsonArray("hits");
        if(1==hits.size()){
          id = hits.get(0).getAsJsonObject().get("_id").getAsString();
        }
      }
    }catch (IOException ioe){
      log.info("ES检索错误|{}", ExceptionUtil.printExceptionMsg(ioe));
    }

    if(null != id){
      return id;
    }else{
      throw new RuntimeException("EsBaseOperate|getEsId|从es中根据gid查询id错误|index:"+index+"|type:"+type+"|field:"+field+"|value:"+value);
    }
  }

  public void updateByEsId(String index,String type,String id, Map<String,Object> params){
    if(null != params && 0 != params.size()){

    }else{
      throw new RuntimeException("EsBaseOperate|updateByEsId|从es中根据gid查询id错误|index:"+index+"|type:"+type+"|id:"+id);
    }
  }

}
