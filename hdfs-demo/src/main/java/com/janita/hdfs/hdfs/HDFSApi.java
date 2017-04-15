package com.janita.hdfs.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by Janita on 2017-04-15 10:39
 */
public class HDFSApi {

    private FileSystem fileSystem = null ;

//    @Before
    public void init1() throws IOException {
        Configuration conf = new Configuration();
        /**
         * 权限设置方法1
         */
        System.setProperty("HADOOP_USER_NAME","root");
        conf.set("fs.defaultFS","hdfs://192.168.128.101:9000");
        fileSystem = FileSystem.get(conf);
    }

    @Before
    public void init2() throws Exception {
        Configuration conf = new Configuration();
        /**
         * 权限设置方法2
         */
        fileSystem = FileSystem.get(new URI("hdfs://192.168.128.101:9000"),conf,"root");
    }

    @Test
    public void testUpLoad() throws IOException {
        InputStream in = new FileInputStream("d://swagger.log");
        FSDataOutputStream out = fileSystem.create(new Path("/home/swagger2.log"));
        IOUtils.copyBytes(in,out,1024,true);

        fileSystem.close();
    }

    /**
     * 还可以指定访问权限
     * @throws IOException
     */
    @Test
    public void testMkDir() throws IOException {
        fileSystem.mkdirs(new Path("/janita"));
        fileSystem.close();
    }

    /**
     * 递归删除
     * @throws IOException
     */
    @Test
    public void testDel() throws IOException {
        boolean flag = fileSystem.delete(new Path("/home"),true);
        System.out.println("******* " + flag);
    }

    @Test
    public void testFile(){
        System.out.println("******* "+fileSystem.getDefaultBlockSize()/1024);
    }
}
