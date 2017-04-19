package com.nicky

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Nicky_chin  --Created on 2017/4/12 0012
  */
object WordCount {

  def main(args: Array[String]) {

    /**
      * 第一步：创建Spark的配置对象SparkConf,设置Spark程序的运行时的配置信息，例如说通过setMaster来设置程序
      * 要连接的Spark集群的Master的URL,如果设置为local,则代表Spark程序在本地运行，特别适合机器配置条件非常差
      */
    val conf = new SparkConf()
    /**
      * appname: 应用的名称
      * master:主机地址
      * 本地，不运行在集群值:local
      * 运行在集群:spark://s0:7077
      */
    conf.setAppName("WordCount").setMaster("spark://192.168.100.12:7077")

    /**
      * 创建SparkContext对象
      * SparkContext 是Spark程序的所有功能的唯一入口，无论是采用Scala,Java,Python,R等
      * 同时还会负责Spark程序往Master注册程序等
      * SparkContext是整个Spark应用程序中最为至关重要的一个对象
      */
    val sc = new SparkContext(conf)

//    textFile(hdfs://node1.itcast.cn:9000/words.txt)是hdfs中读取数据
//    flatMap(_.split(" "))先map在压平
//      map((_,1))将单词和1构成元组
//      reduceByKey(_+_)按照key进行reduce，并将value累加
//    saveAsTextFile("hdfs://node1.itcast.cn:9000/out")将结果写入到hdfs中
    /**
      * 根据具体的数据来源(HDFS、Hbase、local Fs、DB、S3等通过SparkContext来创建RDD)
      * RDD创建有三种方式:根据外部的数据来源例 如HDFS、根据Scala集合、由其它的RDD操作
      * 数据会被RDD划分成为一系列的Partitions,分配到每个Partition的数据属于一个Task的处理范畴
      */
    val path = "hdfs://192.168.100.12:9000/wordcount/input/sparktest.txt"
    val lines = sc.textFile(path, 3)  //读取文 件，并设置为3个Partitions (相当于几个Task去执行)

    val mapArray = lines.flatMap { x => x.split(" ") } //对每一行的字符串进行单词拆分并把所有行的拆分结果通过flat合并成为一个结果
    val mapMap = mapArray.map { x => (x,1) }

    val result  =  mapMap.reduceByKey(_+_) //对相同的Key进行累加
    result.collect().foreach(x => println("key:"+ x._1 + " value"  + x._2))
    val resultValue = result.map(x =>(x._2,x._1))
    val resultOrder = resultValue.sortByKey(false).map(x => (x._2 ,x._1))
    resultOrder.collect().foreach(x => println("key:"+ x._1 + " value:"  + x._2))
    result.saveAsTextFile("hdfs://192.168.100.12:9000/wordcount/output/")

    //停止sc，结束该任务
    sc.stop()

  }

}
