package com.janita.hdfs.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-15 14:32
 */
public class RPCServer implements IRPCService{

    public String sayHi(String name) {
        return "Hi " + name;
    }

    /**
     * 客户端与服务端分表在不同的main方法
     * 模拟不同的进程
     * @param args
     */
    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        Server server =
                new RPC.Builder(conf)
                .setProtocol(IRPCService.class)
                .setBindAddress("192.168.100.89")
                .setPort(9527)
                .setInstance(new RPCServer())
                .build();
        server.start();
    }
}
