package com.janita.hdfs.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Janita on 2017-04-15 14:44
 */
public class RPCClient {

    /**
     * 客户端与服务端分表在不同的main方法
     * 模拟不同的进程
     * @param args
     */
    public static void main(String[] args) throws IOException {

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        /**
         *  不同直接拿到服务端进程中的对象的引用，
         *  但是可以拿到代理对象
         *  有了代理对象就可以调用目标对象的方法
         */
        Configuration conf = new Configuration();
        IRPCService proxy =
                RPC.getProxy
                        (IRPCService.class
                        , 10010
                        ,new InetSocketAddress(host,port)
                        ,conf);
        String result = proxy.sayHi("tom");
        System.out.println("******* "+result);

        RPC.stopProxy(IRPCService.class);
    }
}
