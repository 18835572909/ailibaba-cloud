package com.rhb.sharding.suppert;

/**
 * 雪花算法
 *
 * @author renhuibo
 * @date 2021/7/23 10:25
 */
public class SnowFlake {

  /**
   * 起始的时间戳
   */
  private final static long START_STMP = 1480166465631L;

  /**
   * 每一部分占用的位数
   */
  private final static long SEQUENCE_BIT = 12; //序列号占用的位数
  private final static long MACHINE_BIT = 5;   //机器标识占用的位数
  private final static long DATACENTER_BIT = 5;//数据中心占用的位数

  /**
   * ### 基础穿插：
   * <<1  左移，相当于除以2
   * >>1  右移，相当于乘以2
   * <<<  无符号左移，忽略符号位后左移
   * ~    非 0变1，1变0  (公式：-（原码+1）)
   *
   * 有符数据展示包括：原码、反码、补码
   * 原码： 人类可以直观理解
   * 反码：
   *  - 正数反码还是自己，
   *  - 负数反码-符号位不变其余取反
   * 补码： 计算器用来表示负数，使得负数可以参与加法器运算的
   *  - 正数补码是自己
   *  - 负数补码，符号位不变，其余取反再+1
   *
   *
   *  ~2 = -3
   *
   *  2：   0000 0000 0000 0000 0000 0000 0010
   *  取反：1111 1111 1111 1111 1111 1111 1101
   *  符号位是1 ，表示现在是补码
   *  反码：1111 1111 1111 1111 1111 1111 1100
   *  原码：1000 0000 0000 0000 0000 0000 0011 -> -3
   */

  /**
   * 每一部分的最大值
   */
  private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
  private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
  private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

  /**
   * 每一部分向左的位移
   */
  private final static long MACHINE_LEFT = SEQUENCE_BIT;
  private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
  private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

  private long datacenterId;            //数据中心
  private long machineId;               //机器标识
  private long sequence = 0L;           //序列号
  private long lastStmp = -1L;          //上一次时间戳

  public SnowFlake(long datacenterId, long machineId) {
    if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
      throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
    }
    if (machineId > MAX_MACHINE_NUM || machineId < 0) {
      throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
    }
    this.datacenterId = datacenterId;
    this.machineId = machineId;
  }

  /**
   * 产生下一个ID
   *
   * @param ifEvenNum 是否偶数 true 时间不连续全是偶数  时间连续 奇数偶数 false 时间不连续 奇偶都有  所以一般建议用false

   * @return
   */
  public synchronized long nextId(boolean ifEvenNum) {
    long currStmp = getNewstmp();
    if (currStmp < lastStmp) {
      throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
    }
    /**
     * 时间不连续出来全是偶数
     */
    if(ifEvenNum){
      if (currStmp == lastStmp) {
        //相同毫秒内，序列号自增
        sequence = (sequence + 1) & MAX_SEQUENCE;
        //同一毫秒的序列数已经达到最大
        if (sequence == 0L) {
          currStmp = getNextMill();
        }
      } else {
        //不同毫秒内，序列号置为0
        sequence = 0L;
      }
    }else {
      //相同毫秒内，序列号自增
      sequence = (sequence + 1) & MAX_SEQUENCE;
    }

    lastStmp = currStmp;

    return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
        | datacenterId << DATACENTER_LEFT           //数据中心部分
        | machineId << MACHINE_LEFT                 //机器标识部分
        | sequence;                                 //序列号部分
  }

  private long getNextMill() {
    long mill = getNewstmp();
    while (mill <= lastStmp) {
      mill = getNewstmp();
    }
    return mill;
  }

  private long getNewstmp() {
    return System.currentTimeMillis();
  }

  public static void main(String[] args) {
    // 10 01
    System.out.println(~2);
  }

}
