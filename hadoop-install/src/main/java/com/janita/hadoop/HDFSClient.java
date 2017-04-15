package com.janita.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Janita on 2017-04-15 09:08
 */
public class HDFSClient {

    private FileSystem fileSystem = null ;
    /**
     * 本测试中操作的HDFS文件系统的根目录
     */
    private static String HDFS_PRE = "/home/";

    @Before
    public void init() throws Exception {

        /**
         * 构造一个配置参数对象，设置一个参数，我们要访问的hdfs的URI
         * 从而FileSystem.get()方法就知道应该是去构造一个访问HDFS文件系统的客户的，
         * 以及HDFS的访问地址，new Configuration()的时候，它就会去加载jar包中的hdfs-default.xml
         * 然后加载classpath下的hdfs-site.xml
         */
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.128.101:9000");
        /**
         * 参数优先级：1,客户的代码中的设置值，2,classpath下的用户自定义的配置文件，3,然后是服务器默认的配置
         */
        configuration.set("dfs.replication","3");
        /**
         * 如果这样去获取，那么configuration里面就可以不用配"fs.defaultFS"参数，而且，这个客户端的身份表示已经是hadoop用户
         */
        fileSystem = FileSystem.get(new URI("hdfs://192.168.128.101:9000"),configuration,"root");
    }

    /**
     * copyFromLocalFile
     * @throws IOException
     */
    @Test
    public void addFileToHDFS() throws IOException {

        Path sourceFile = new Path("C:\\Users\\Administrator\\Desktop\\pic.png");
        Path destFile = new Path(HDFS_PRE+"pic.png");
        fileSystem.copyFromLocalFile(sourceFile,destFile);
        fileSystem.close();
    }

    /**
     * copyToLocalFile
     * @throws IOException
     */
    @Test
    public void downloadToLocal() throws IOException {
        fileSystem.copyToLocalFile(new Path(HDFS_PRE+"pic.png"),new Path("C:\\Users\\Administrator\\Desktop\\pp.png"));
        fileSystem.close();
    }

    /**
     * mkdirs
     * delete
     * rename
     * @throws IOException
     */
    @Test
    public void mkDirDeleteRename() throws IOException {
        fileSystem.mkdirs(new Path(""));
        fileSystem.delete(new Path(""));
        fileSystem.rename(new Path(""),new Path(""));
    }

    /**
     * 查看目录信息，只显示文件
     * @throws IOException
     */
    @Test
    public void listFiles() throws IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path(""),true);

        while (listFiles.hasNext()){
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("******* "+fileStatus.getPath().getName());
            System.out.println("******* "+fileStatus.getBlockSize());
            System.out.println("******* "+fileStatus.getPermission());
            System.out.println("******* "+fileStatus.getLen());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation location : blockLocations){
                System.out.println("******* block-length : "+location.getLength()+" --- "+"block-offset : "+location.getOffset());
                String[] hosts = location.getHosts();
                for (String host : hosts){
                    System.out.println("******* "+host);
                }
            }
            System.out.println("-----------为打印分割线-------------");
        }
    }

    /**
     * 查看文件及文件夹信息
     */
    @Test
    public void listAll() throws IOException {
        FileStatus[] fileStatus = fileSystem.listStatus(new Path(""));

        String flag = "d--              ";
        for (FileStatus status : fileStatus){
            if (status.isFile()){
                flag = "f--              ";
            }
            System.out.println("******* "+flag+status.getPath().getName());
        }
    }
}
