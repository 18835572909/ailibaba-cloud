package com.rhb.rpc.json;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/6 14:56
 */

import java.util.Date;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonitorData {
  private Date optime;
  private long longValue;
  private String host;
  private Map<String,String> tags;
  private String cluster;
  private String routeKey;

  private QueryRequest request;
  private RunStatus runStatus;

  @Data
  @Builder
  public static class QueryRequest{
    private String queryField;
    private String queryValue;
    private double score;
    private boolean isLike;
  }

  public enum RunStatus{
    /**
     *
     */
    UNKNOW,RUNNING,STOP;
  }
}
