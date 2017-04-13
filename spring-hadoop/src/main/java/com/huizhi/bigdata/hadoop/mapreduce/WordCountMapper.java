package com.huizhi.bigdata.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    @Override
    protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {
        //读取每一行数据，map是根据行作为分隔符的
        String str = value.toString();//取每一行数据。
        String[] result = str.split(" "); //每一行数据根据" "进行分割。
        for( int x = 0; x< result.length ; x++){
            context.write(new Text(result[x]), new IntWritable(1));
        }
    }
}