package com.rhb.rpc;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import com.rhb.rpc.protobuf.pojo.MonitorDataPojo.MonitorData;
import com.rhb.rpc.protobuf.pojo.MonitorDataPojo.MonitorData.Builder;
import com.rhb.rpc.protobuf.pojo.MonitorDataPojo.MonitorData.QueryRequest;
import com.rhb.rpc.protobuf.pojo.MonitorDataPojo.MonitorData.RunStatus;
import java.util.Arrays;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/6 14:05
 */
public class RpcApplication {

  public static void main(String[] args) {
    test1();
    test2();
  }

  public static void test1(){
    Builder builder = MonitorData.newBuilder();
    builder.setCluster("cluster-1");
    builder.setHost("127.0.0.1");
    builder.setLongValue(955595L);
    builder.setRouteKey("key-5656");
    builder.setRunStatus(RunStatus.RUNNING);
    builder.setQueryRequest(QueryRequest.newBuilder()
        .setQueryField("name")
        .setQueryValue("测试")
        .setIsLike(Boolean.FALSE)
        .setScore(0.0d).build());
    MonitorData monitorData = builder.build();
    // 序列化
    byte[] bytes = monitorData.toByteArray();

    try {
      // 反序列化
      MonitorData monitorData1 = MonitorData.parseFrom(bytes);
      System.out.println(monitorData1.getRouteKey());
      System.out.println(monitorData1.getCluster());
    } catch (InvalidProtocolBufferException e) {
      e.printStackTrace();
    }

    System.out.println(Arrays.toString(bytes));
    System.out.println(bytes.length);
  }

  public static void test2(){
    com.rhb.rpc.json.MonitorData data = com.rhb.rpc.json.MonitorData.builder()
        .host("127.0.0.1")
        .cluster("cluster-1")
        .longValue(955595L)
        .routeKey("key-5656")
        .runStatus(com.rhb.rpc.json.MonitorData.RunStatus.RUNNING)
        .request(com.rhb.rpc.json.MonitorData.QueryRequest.builder()
            .isLike(Boolean.FALSE)
            .queryField("name")
            .queryValue("测试")
            .score(0.0d).build()).build();
    JSONObject jsonObject = JSONUtil.parseObj(data);
    byte[] bytes = jsonObject.toString().getBytes();

    System.out.println(Arrays.toString(bytes));
    System.out.println(bytes.length);
  }
}
