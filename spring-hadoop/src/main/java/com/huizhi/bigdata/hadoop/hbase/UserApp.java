package com.huizhi.bigdata.hadoop.hbase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class UserApp {
    private static final Log log = LogFactory.getLog(UserApp.class);
    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/huizhi/bigdata/hadoop/mapreduce/applicationContext.xml",
                UserApp.class);
        log.info("HBase Application Running");
        context.registerShutdownHook();
        System.setProperty("hadoop.home.dir", "E:"+ File.separator+"hadoop-2.7.3");
        UserUtils userUtils = context.getBean(UserUtils.class);

        userUtils.initialize();
        //userUtils.addUsers(); //增加操作
        User user = userUtils.find("user1");
        System.out.println(user);

		/*
			UserRepository userRepository = context.getBean(UserRepository.class);
			List<User> users = userRepository.findAll();
			System.out.println("Number of users = " + users.size());
			System.out.println(users);

		*/
        //userUtils.delete("user1");   //删除操作

    }
}
