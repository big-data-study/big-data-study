package com.janita.hdfs;

/**
 * Created by Janita on 2017-04-15 13:24
 * HDFS原理
 */
public class HDFSArchitecture {

    /**
     * HDFS的架构
     * 1.文件切块
     * 2.副本存放
     * 3.元数据
     * NameNode:管理（元数据）
     * SecondaryNameNode:同步磁盘与内存中的数据,在伪分布式及非高可用的集群才有
     * DataNode：存储数据
     * DataNode启动后主动向NameNode汇报自己的情况
     *
     * NameNode检查哪些DataName上有空间，写一份，然后数据在DataName之间水平复制多个副本
     * NameNode(FileName,replicas,block-ids,id-host...)这个简单的描述了NameNode元数据的模型
     */

    /**
     * SNN同步元数据过程
     * NameNode
     * 1.是整个文件系统的管理节点，维护整个系统的目录树
     * 2.接收用户的操作请求（各种操作指令）
     * 3.维护的文件包括：
     *      3.1.fsimage：元数据的镜像文件，存储某段时间NameNode内存元数据信息
     *      3.2.edits：操作日志文件
     *      3.3.fstime：保存最近一次checkpoint的时间
     *
     *  NameNode始终在内存中保存metedata,用于处理"读请求"
     *  当有"写请求"时，首先写到editlog到磁盘，成功之后才会修改内存，并向客户的返回
     *  NameNode会维护一个fsimage磁盘文件，也就是namenode中的metedata(内存)的镜像，但是fsimage不会随时与
     *  namenode内存中的metedata保持一致，而是每隔一段时间通过合并edits文件来更新内容。
     *  SecondaryNameNode就是用来合并fsimage和edits文件来更新namenode的metedata的
     */
}
