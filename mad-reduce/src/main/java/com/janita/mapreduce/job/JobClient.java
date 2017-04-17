package com.janita.mapreduce.job;

import com.janita.mapreduce.map.WordCountMapper;
import com.janita.mapreduce.reduce.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-15 17:25
 *
 * job提交器其实是yarn集群的客户的
 * 它负责将mr程序需要的信息封装成一个配置文件
 * 然后联通mr程序所在的jar包一起提交给yarn
 * 由yarn去启动mr程序中的MrAppMaster
 */
public class JobClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        conf.set("yarn.resourcemanager.hostname","192.168.101");

        //创建一个job提交器对象
        Job job = Job.getInstance(conf);
        //告知mrappmaster，程序的map函数在哪里
        job.setMapperClass(WordCountMapper.class);
        //同上
        job.setReducerClass(WordCountReducer.class);
        //告知我们程序的map阶段和reduce阶段输出的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //告知我们程序处理数据所在目录，以及要求输出结果所在目录
        FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.128.101:9000/home"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.128.101:9000/home"));
        //提交
//        job.submit();
        boolean res = job.waitForCompletion(true);
        System.exit(res  ? 0 : 100);
    }
}
