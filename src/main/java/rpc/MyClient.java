package rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyClient {
    public static void main(String[] args) throws IOException {
        try {
           MyInterface proxy = RPC.getProxy(MyInterface.class,1L,new InetSocketAddress("127.0.0.1",12345),new Configuration());
           String res = proxy.findName("20210735010483");
            System.out.println(res);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
