package com.rhb.es.jest;

import cn.hutool.json.JSONUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rhb.es.util.ExceptionUtil;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.Update;
import io.searchbox.core.Update.Builder;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
   * 获取ES的id
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
      log.info("ES检索查询错误|{}", ExceptionUtil.printExceptionMsg(ioe));
    }

    if(null != id){
      return id;
    }else{
      throw new RuntimeException("EsBaseOperate|getEsId|从es中根据gid查询id错误|index:"+index+"|type:"+type+"|field:"+field+"|value:"+value);
    }
  }

  /**
   * ES更新索引
   *
   * >>>常见更新索引方式：（5种）<<<
   * 1. API: curl -XPOST 'xxx:9200/index/type/id/_update' -d '{"doc":{"name":"new_name"}}'
   *
   * 2. API+detect_noop:
   *  detect_noop： 默认为true，及只有新数据跟旧数据不同时，才触发索引更新
   *  curl -XPOST 'xxx:9200/index/type/id/_update' -d '{"doc":{"name":"new_name"},"detect_noop":false}'
   *
   * 3. script + upset
   *
   * 4. scripted_upsert
   *
   * 5. doc_as_upsert
   *
   * @param index 索引
   * @param type 类型
   * @param id id
   * @param params 更新目标参数
   */
  public Boolean updateByEsId(String index,String type,String id, Map<String,Object> params){
    if(null != params && 0 != params.size()){
      String paramStr = JSONUtil.parseObj(params).toString();
      String script = "{\"doc\":"+paramStr+"}";
      try{
        DocumentResult result = jestClient
            .execute(new Update.Builder(script).index(index).type(type).id(id).build());
        if(result.isSucceeded()){
          return true;
        }
        log.info("ES更新索引结果：{}",result);
      }catch (Exception e){
        log.info("ES检索更新错误|{}", ExceptionUtil.printExceptionMsg(e));
        throw new RuntimeException("EsBaseOperate|updateByEsId|更新索引错误|index:"+index+"|type:"+type+"|id:"+id);
      }
    }else{
      throw new RuntimeException("EsBaseOperate|updateByEsId|更新索引错误|index:"+index+"|type:"+type+"|id:"+id);
    }
    return false;
  }

  /**
   * ES添加索引
   *
   * @param index 索引
   * @param type 类型
   * @param list 数据集合
   */
  public Boolean batchInsertDocs(String index,String type, List<Object> list){
    Bulk.Builder builder = new Bulk.Builder()
        .defaultIndex(index)
        .defaultType(type);

    list.forEach(x->{
      builder.addAction(new Index.Builder(x).build());
    });

    Bulk bulk = builder.build();
    try{
      BulkResult result = jestClient.execute(bulk);
      if(result.isSucceeded()){
        return true;
      }
      log.info("ES添加索引结果：{}",JSONUtil.parseObj(result));
    }catch (IOException e){
      log.info("ES添加检索错误|{}", ExceptionUtil.printExceptionMsg(e));
      throw new RuntimeException("EsBaseOperate|addDatas|添加索引错误|index:"+index+"|type:"+type);
    }
    return false;
  }

  /**
   * 根据ESid获取索引
   *
   * @param index 索引
   * @param type 类型
   * @param id id
   * @return 索引数据
   */
  public JsonObject getObjByEsId(String index,String type,String id){
    JsonObject resultJson = null;
    Get get = new Get.Builder(index, id).type(type).build();
    try{
      DocumentResult result = jestClient.execute(get);
      if(result.isSucceeded() && result.getJsonObject().has("_source")){
        resultJson = result.getJsonObject().get("_source").getAsJsonObject();
      }
    }catch (IOException ioe){
      log.info("ESId获取检索错误|{}", ExceptionUtil.printExceptionMsg(ioe));
      throw new RuntimeException("EsBaseOperate|getObjByEsId|根据Es_id获取索引错误|index:"+index+"|type:"+type+"|id:"+id);
    }

    if(resultJson!=null){
      return resultJson;
    }else{
      throw new RuntimeException("EsBaseOperate|getObjByEsId|根据Es_id获取索引空|index:"+index+"|type:"+type+"|id:"+id);
    }
  }


}
