package com.rhb.rpc.thrift.application;

import com.rhb.rpc.thrift.gen.java.shared.SharedStruct;
import com.rhb.rpc.thrift.gen.java.tutorial.Calculator;
import com.rhb.rpc.thrift.gen.java.tutorial.InvalidOperation;
import com.rhb.rpc.thrift.gen.java.tutorial.Operation;
import com.rhb.rpc.thrift.gen.java.tutorial.Work;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Key-Tool：（生成我们需要的.keystore和.truststore）
 * keytool -genkeypair -alias certificatekey -keyalg RSA -validity 365 -keystore .keystore
 * keytool -export -alias certificatekey -keystore .keystore -rfc -file server.cer
 * keytool -import -alias certificatekey -file server.cer -keystore .truststore
 *
 *
 * @author renhuibo
 * @date 2021/9/6 17:51
 */
public class JavaClient {

  private final static String basePath = "D:/opensource/alibaba-cloud/common-rpc/src/main/java/com/rhb/rpc/thrift";

  public static void main(String [] args) {
    try {
      TTransport transport = null;
//      if (args[0].contains("simple")) {
//        transport = new TSocket("localhost", 9090);
//        transport.open();
//      }else {
      TSSLTransportParameters params = new TSSLTransportParameters();
      params.setTrustStore(basePath+"/.truststore", "thrift", "SunX509", "JKS");
      transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
//      }

      TProtocol protocol = new TBinaryProtocol(transport);
      Calculator.Client client = new Calculator.Client(protocol);

      perform(client);
      transport.close();
    } catch (TException x) {
      x.printStackTrace();
    }
  }

  public static void getSimpleTransport(TTransport transport) throws TTransportException {
    transport = new TSocket("localhost", 9090);
    transport.open();
  }

  public static void getTSSLTransport(TTransport transport) throws TTransportException {
    /*
     * Similar to the server, you can use the parameters to setup client parameters or
     * use the default settings. On the client side, you will need a TrustStore which
     * contains the trusted certificate along with the public key.
     * For this example it's a self-signed cert.
     */
    TSSLTransportParameters params = new TSSLTransportParameters();
    params.setTrustStore(basePath+"/.truststore", "thrift", "SunX509", "JKS");
    /*
     * Get a client transport instead of a server transport. The connection is opened on
     * invocation of the factory method, no need to specifically call open()
     */
    transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
  }

  private static void perform(Calculator.Client client) throws TException{
    client.ping();
    System.out.println("ping()");

    int sum = client.add(1,1);
    System.out.println("1+1=" + sum);

    Work work = new Work();
    work.op = Operation.DIVIDE;
    work.num1 = 1;
    work.num2 = 0;
    try {
      int quotient = client.calculate(1, work);
      System.out.println("Whoa we can divide by 0");
    } catch (InvalidOperation io) {
      System.out.println("Invalid operation: " + io.why);
    }

    work.op = Operation.SUBTRACT;
    work.num1 = 15;
    work.num2 = 10;
    try {
      int diff = client.calculate(1, work);
      System.out.println("15-10=" + diff);
    } catch (InvalidOperation io) {
      System.out.println("Invalid operation: " + io.why);
    }

    SharedStruct log = client.getStruct(1);
    System.out.println("Check log: " + log.value);
  }
}
