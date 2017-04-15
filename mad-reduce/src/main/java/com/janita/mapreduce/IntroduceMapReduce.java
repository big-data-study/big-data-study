package com.janita.mapreduce;

/**
 * Created by Janita on 2017-04-15 15:55
 * mapreduce
 */
public class IntroduceMapReduce {

    /**
     * mapreduce:
     * 1.是一个分布式数据处理框架
     * 2.mapreduce系统的角色：
     *      2.1.MRAppMaster     1个
     *      2.2.MapTask         多个
     *      2.3.ReduceTask      多个
     *
     *  其实就是把处理数据的过程分为两个阶段
     *  需要等map完成之后
     *  reduce才能根据map的结果进一步处理
     *
     *  第一个阶段大家各自处理各自分到的数据，互不影响
     *  第二个阶段汇总
     */



}
