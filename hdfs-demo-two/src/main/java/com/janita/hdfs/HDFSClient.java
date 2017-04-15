package com.janita.hdfs;

import org.apache.hadoop.conf.Configuration;

/**
 * Created by Janita on 2017-04-15 15:47
 */
public class HDFSClient {

    public static void main(String[] args){

        Configuration conf = new Configuration();
        System.out.println("******* "+conf.get("myGirl"));
    }
}
