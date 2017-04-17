package com.janita.mapreduce.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-15 17:01
 *
 * KEYIN, VALUEIN, KEYOUT, VALUEOUT
 * KEYIN:   对应的是map输出的数据的key的类型，Text
 * VALUEIN: 对应的是map阶段输出的数据类型，IntWriable
 * KEYOUT:  reduce业务逻辑中药输出的key的类型，Text
 * VALUEOUT:reduce业务逻辑输出的value类型，IntWriable
 */
public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

    /**
     * 一组相同key的数据调用一次reduce方法
     * @param key       一个单词
     * @param values    这个单词在不同的map中统计出的出现次数
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        /**
         * 累计这一组kv数据中的value值即可
         */
        int count = 0;
        for (IntWritable value : values){
            count += value.get();
        }
        /**
         * 输出放到context中即可
         */
        context.write(key,new IntWritable(count));
    }
}
