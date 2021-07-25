package rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtocolSignature;
import org.apache.hadoop.ipc.RPC;


import java.io.IOException;

public class MyinterfaceImpl implements MyInterface {
    @Override
    public String findName(String studentId) {
        if ("20210735010483".equals(studentId) ) {
            return "飞鸿踏雪sunny";
        }
        else if("20210123456789".equals(studentId)) {
            return "心心";
        }
        else {
            return null;
        }
    }

    @Override
    public long getProtocolVersion(String s, long l) throws IOException {
        return MyInterface.versionID;
    }

    @Override
    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return null;
    }

    public static void main(String[] args) {
        RPC.Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("127.0.0.1");
        builder.setPort(12345);
        builder.setProtocol(MyInterface.class);
        builder.setInstance(new MyinterfaceImpl());
        try {
            RPC.Server server = builder.build();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
