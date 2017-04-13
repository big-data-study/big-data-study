package com.huizhi.bigdata.hadoop.mapreduce;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class WordCount {
  /*  private static final Log log = LogFactory.getLog(WordCount.class);*/

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml", WordCount.class);
      /*  log.info("Wordcount with HDFS copy Application Running");*/
        context.registerShutdownHook();

    }
}

