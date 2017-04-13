package com.huizhi.bigdata.hadoop.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@Component
public class UserUtils implements InitializingBean {

    private String table = "users";
    private byte[] tableNameAsBytes = Bytes.toBytes("users");

    @Resource(name = "hbaseConfiguration")
    private Configuration config;

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private UserRepository userRepository;

    private HBaseAdmin admin;

    /**
     * 数据库表基本初始化
     * @throws IOException
     */
    public void initialize() throws IOException {

        if (!admin.tableExists(tableNameAsBytes)) { //判断数据表是否存在，不存在则创建
            TableName tableName = TableName.valueOf(table.getBytes()) ; //数据表名
            HTableDescriptor tableDesc = new HTableDescriptor(tableName) ;
            tableDesc.addFamily(new HColumnDescriptor(UserRepository.CF_INFO)) ;//添加列族
            admin.createTable(tableDesc); //创建数据表
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        admin = new HBaseAdmin(config);
    }

    /**
     * 保存数据
     */
    public void addUsers() {
        //伪实现
        userRepository.save("user" + 1,"user" + 1 + "@yahoo.com", "password" + 1);
    }

    public void delete(String rowName){
        userRepository.delete(rowName);
    }

    public User find(String rowName){
        return userRepository.find(rowName);
    }
}
