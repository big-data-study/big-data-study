package com.janita.pack.shade;

import com.alibaba.fastjson.JSONObject;
import com.janita.pack.bean.User;

/**
 * Created by Janita on 2017-04-17 09:48
 */
public class ShadeMain {

    /**
     * 演示用maven-shade-plugin打包
     * 1.在pom文件中添加插件 -- maven-shade-plugin
     * 2.mvn clean compile //清除之前target编译文件并重新编译
        mvn clean package //对项目进行打包(因为配置过插件，所以jar包是可执行的)
        mvn clean install //安装项目，然后就可以使用了
       3.页可以使用自带的maven管理工具代替执行上面的命令。
         依次点击Maven Project打包项目中找到Lifecycle
         点击：clean/compile/package/install
      4.在target中复制jar包即可
     *
     * @param args
     */
    public static void main(String[] args){

        if (args == null || args.length == 0){
            System.out.println("******* 请输入参数");
            return;
        }
        User user = new User();
        int id = Integer.parseInt(args[0]);
        user.setId(id);

        if (id == 1){
            user.setName("Janita");
            user.setAge(16);
        } else if (id == 2){
            user.setName("Jay");
            user.setAge(20);
        } else {
            user.setName("无名氏");
            user.setAge(0);
        }
        System.out.println("******* " + JSONObject.toJSONString(user));
    }

    /**
     * <plugin>
     <groupId>org.apache.maven.plugins</groupId>
     <artifactId>maven-shade-plugin</artifactId>
     <version>1.4</version>
     <configuration>
     <createDependencyReducedPom>true</createDependencyReducedPom>
     </configuration>
     <executions>
     <execution>
     <phase>package</phase>
     <goals>
     <goal>shade</goal>
     </goals>
     <configuration>
     <transformers>
     <transformer
     implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
     <mainClass>Main.Main</mainClass>
     </transformer>
     </transformers>
     </configuration>
     </execution>
     </executions>
     </plugin>
     */
}
