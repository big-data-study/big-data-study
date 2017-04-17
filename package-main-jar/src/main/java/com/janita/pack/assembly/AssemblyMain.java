package com.janita.pack.assembly;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Janita on 2017-04-17 10:07
 */
public class AssemblyMain {

    /**
     * 用maven-assembly-plugin打包
     * 1.pom add maven-assembly-plugin
     * 2.mvn assembly:assembly
     * 3.也可以在项目的Pluins中使用assembly:assembly打包
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args){
        if (args == null || args.length == 0){
            System.out.println("******* 请输入参数");
            return;
        }
        System.out.println("******* " + JSONObject.toJSONString(args[0]));
    }

    /**
     * <plugin>
     <artifactId>maven-assembly-plugin</artifactId>
     <version>2.4</version>
     <configuration>
     <descriptorRefs>
     <descriptorRef>jar-with-dependencies</descriptorRef>
     </descriptorRefs>
     <archive>
     <manifest>
     <mainClass>Main.Main</mainClass>
     </manifest>
     </archive>
     </configuration>
     <executions>
     <execution>
     <id>make-assembly</id>
     <phase>package</phase>
     <goals>
     <goal>single</goal>
     </goals>
     </execution>
     </executions>
     </plugin>
     */
}
