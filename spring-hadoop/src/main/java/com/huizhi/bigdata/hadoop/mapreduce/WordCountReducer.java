package com.huizhi.bigdata.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values,
                          Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        int sum = 0 ;//统计总量
        for (IntWritable wri :values){ //取出所有的统计数据
            sum += wri.get();   //进行数据的累加处理
        }
        context.write(new Text(key),new IntWritable(sum));
    }

}
