package com.huizhi.bigdata.hadoop.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@Repository
public class UserRepository {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private String tableName = "users"; // 表名称

    public static byte[] CF_INFO = Bytes.toBytes("cfInfo"); // 列族

    private byte[] qUser = Bytes.toBytes("name"); // 列族中列
    private byte[] qEmail = Bytes.toBytes("email");
    private byte[] qPassword = Bytes.toBytes("password");

    /**
     * 查找全部数据
     *
     * @return
     */
    public List<User> findAll() {
        return (List<User>) hbaseTemplate.find(tableName, "cfInfo", new RowMapper<User>() {
            @Override
            public User mapRow(Result result, int rowNum) throws Exception {
                return new User(Bytes.toString(result.getValue(CF_INFO, qUser)),
                        Bytes.toString(result.getValue(CF_INFO, qEmail)),
                        Bytes.toString(result.getValue(CF_INFO, qPassword)));
            }
        });
    }

    public User find(String rowName) {
        return hbaseTemplate.get(tableName, rowName, new RowMapper<User>() {

            @Override
            public User mapRow(Result result, int rowNum) throws Exception {
                User user = new User();
                List<Cell> ceList = result.listCells();
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        // 获取行名称
                        String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                        // 获取值
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                cell.getValueLength());
                        // 获取列簇
                        String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(),
                                cell.getFamilyLength());
                        // 获取列
                        String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
                                cell.getQualifierLength());
                        //System.out.println("获取行名称： " + row + " 获取值: " + value + "、列簇值: " + family + "、获取列: " + quali);
                        if (quali.equals("name")) {
                            user.setName(value);
                        }
                        if (quali.equals("email")) {
                            user.setEmail(value);
                        }
                        if (quali.equals("password")) {
                            user.setPassword(value);
                        }

                    }

                }
                return user;
            }
        });

    }

    /**
     * 增加用户，没有修改数据，要更改数据，将对应行键、列簇、列下的值重新写入就行了。它 可以同时保存多个时间版本的值，使用的时候直接取最新的数据就行了。
     *
     * @param userName
     * @param email
     * @param password
     * @return
     */
    public User save(final String userName, final String email, final String password) {
        return hbaseTemplate.execute(tableName, new TableCallback<User>() {
            public User doInTable(HTableInterface table) throws Throwable {
                User user = new User(userName, email, password);
                Put p = new Put(Bytes.toBytes(user.getName()));
                p.add(CF_INFO, qUser, Bytes.toBytes(user.getName()));
                p.add(CF_INFO, qEmail, Bytes.toBytes(user.getEmail()));
                p.add(CF_INFO, qPassword, Bytes.toBytes(user.getPassword()));
                table.put(p);
                return user;

            }
        });
    }

    public void delete(String rowName) {
        hbaseTemplate.delete(tableName, rowName, new String(CF_INFO));
    }

}
