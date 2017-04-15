package com.janita.hdfs.hdfs;

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

    /**
     * SecondaryNameNode的工作机制
     * HA(high ability:高可用)的一个解决方案。但不支持热备。配置即可
     * 执行过程：从NameNode上下载元数据信息(fsimage,edits),然后把二者合并，生成新的fsimage，在本地保存，并将其
     * 推送到NameNode,替换旧的fsimage
     * 默认安装在NameNode节点上，但不安全
     *
     * 工作过程：
     * 1.snn通知namenode切换edits文件
     * 2.snn从namenode获得fsimageh和edits(通过http)
     * 3.....
     */

    /**
     * DataNode的机制
     * 提供真实文件数据的存储服务
     * 文件块(block):最基本的存储单位，对于文件内容而言，一个文件的长度大小是size，那么文件从0的偏移开始，按照固定的大小，
     * 顺序对文件进行划分并编号，划分好的每一个块成为一个block.
     * HDFS默认block的大小是125MB，以一个256MB的文件，共有256/128=2个block。
     * 若一个200MB的文件会分成2block
     * 不同于普通文件系统的是HDFS中，日过一个文件小于一个数据块的大小，并不占用整个数据块存储空间
     * Relpication，多副本，默认是三个
     */
}
