package com.janita.mapreduce.map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-15 16:42
 *
 * KEYIN, VALUEIN, KEYOUT, VALUEOUT
 * KEYIN:   框架读取一行数据的起始偏移量,Long类型
 * VALUEIN: 框架读的这一行数据的内容，String类型
 * KEYOUT:  我们的业务逻辑要输出的数据的key的类型，在此为一个单词，String类型
 * VALUEOUT:我们的业务逻辑要输出的数据的value的类型，在此为一个整数，int类型
 *
 * hadoop自己实现了一套序列化机制，它的序列化机制相比于jdk中的seri序列化之后的数据更精简，可以提高网络传输效率
 * 所以，在hadoop编程中，不用使用Java原生的数据类型，要使用hadoop中经过包装的数据类型
 * Long --> LongWritable
 * String --> Text
 * Integer --> IntWritable
 * Null --> NullWritable
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

    /**
     * 重写。
     * 实现自己的业务逻辑
     * @param key       对应的是框架传给我们的KEYIN
     * @param value     框架传给我们的VALUEIN
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        //将拿到的这一行数据按空格切分
        String line = value.toString();
        String[] lineWords = line.split(" ");

        for (String word : lineWords){
            context.write(new Text(word),new IntWritable(1));
        }
    }
}
