package com.huizhi.bigdata.spark.hellospark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
  * 统计/usr/local/spark/README.md文件中 'a','b'个数
  */
object App {
  def main(args: Array[String]) {
    val logFile = "/usr/local/spark/README.md"
    val conf = new SparkConf().setAppName("App")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s,Lines with b: %s".format(numAs, numBs))

  }
}
