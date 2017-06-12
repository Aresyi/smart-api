# smart-api ![bu](https://camo.githubusercontent.com/8c93af46bfd72070ec0dcdf68bf74ec37349673b/68747470733a2f2f6170692e7472617669732d63692e6f72672f7468782f5241502e737667) 17Smart，一起SMART
With this system, improve the efficiency of team members to communicate, reduce API maintenance costs, and make changes in the API has a historical accumulation of precipitation. Through the API score to identify team members in a timely manner to help improve performance.







### 一、平台简介



#### 1.1、概述
Smart-api是一个极简API管理平台，提供API的"发布&管理"、"数据字典"、"即时沟通"、"文档"、"Mock"和"测试"等功能。

借助此系统，可以避免更多的重复劳动，提高沟通效率、减少API维护成本，且使API更新变动有所历史积累沉淀。通过API多维匿名评分及时发现团队成员问题，帮助其提高绩效。

#### 1.2、特性
- 1、极简：一分钟上手；
- 2、隔离：成员分组、API以项目为维度进行拆分隔离；
- 3、高效：半自动化生成API文档（如愿意在项目引入17smart注解可全自动化）；
- 4、多版本：API可多版本维，变更历史一目了然；
- 5、即时沟通：实时通知、在线沟通；
- 6、在线测试：可自行配置、选择环境测试API（如，测试环境、Mock测试）；
- 7、即时更新：数据字典随数据库表结构实时同步更新展现；

#### 1.3、如何使用
- 1、直接访问由作者维护的[http://www.179smart.com/smart-api/](http://www.179smart.com/smart-api/)(账号：admin@17smart.com；密码：654321)
- 2、自己本地部署（很简单，详见下面[“快速部署”](#部署)）



### 二、系统介绍



#### 2.1、功能图
![这里写图片描述](http://img.blog.csdn.net/20170610171730760?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 2.2、流程图
![这里写图片描述](http://img.blog.csdn.net/20170610171751088?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 2.3、架构图
![这里写图片描述](http://img.blog.csdn.net/20170610171806761?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 2.4、主要技术
- 1、前端：Jsp + Velocity + jQuery 
- 2、后端：Java7 + Springmvc + Websocket + Json
- 3、数据库：Mongodb2.4+
- 4、部署：Tomcat7+ / Springboot
- 5、开发：Maven + Git



### 三、快速部署

#### 3.1、下载源码
- 以MyEclipse为例：Import -> Git -> Clone URI -> Next...成功导入工程结构如下：
![这里写图片描述](http://img.blog.csdn.net/20170610174634233?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 3.2、配置Mongodb连接
修改src/main/resources目录下系统配置sysConfig.properties文件：

```
db.mongo.host=192.168.254.18
db.mongo.port=27017
db.mongo.dbName=smartAPI
```
备注:
- 1、Mongodb官网下载安装很简单；
- 2、无需像Mysql那样的系统初始化Sql；
- 3、dbName可按自己喜欢修改。

#### 3.3、部署
- 将应用"smart-api"部署在容器如Tomcat下之后，启动后访问如http://192.168.254.50:7070/smart-api即可进入以下界面：
![这里写图片描述](http://img.blog.csdn.net/20170610180213210?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
备注：war包、jar包部署都可以.



### 四、核心操作指南

#### 4.1、注册账号
- 注册管理员账号如下，注册好后查收邮件，可见密码：
![这里写图片描述](http://img.blog.csdn.net/20170610181501009?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.2、创建分组
- 使用注册的管理员账号登录系统，创建用户分组(团队 -> 新建小组)：
![这里写图片描述](http://img.blog.csdn.net/20170610182534643?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.3、添加用户
- 使用注册的管理员账号登录系统，创建用户分组(团队 -> 添加新成员)：
![这里写图片描述](http://img.blog.csdn.net/20170610183020693?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.4、创建项目
- 主页 -> 新建项目：
![这里写图片描述](http://img.blog.csdn.net/20170610183923370?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.5、创建模块
- 主页 -> 新建项目 -> 选择指定项目 -> 添加新模块：
![这里写图片描述](http://img.blog.csdn.net/20170610185021696?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.6、用户权限
- 团队 -> 点击知道用户头像：
![这里写图片描述](http://img.blog.csdn.net/20170610185706394?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.7、系统配置
- 数据 -> 配置中心：
![这里写图片描述](http://img.blog.csdn.net/20170610190657898?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.8、发布接口
![这里写图片描述](http://img.blog.csdn.net/20170610192017655?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 4.9、测试接口
![这里写图片描述](http://img.blog.csdn.net/20170610193135220?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvTG92ZUphdmFZREo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



### 五、技术交流


#### 5.1、技术群：

- QQ群：582216911[![17Smart 【2号群】](https://raw.githubusercontent.com/Aresyi/smart-api/master/doc/group.png)](//shang.qq.com/wpa/qunwpa?idkey=d407716d1a9691094f3bb7fe20fce9021dfec1279bcaf0d534639ccc335627ea)

#### 5.2、ISSUES：

- smart-api托管在[Github](https://github.com/Aresyi/smart-api)上，如有问题可在 [ISSUES](https://github.com/Aresyi/smart-api/issues/1) 上提问



### 六、一起Smart的客户


- 人脉通
- 滴滴找布
- 好汽配
- 美业帮
- 人人都是产品经理
- 什马金融
- 传化集团


还在用Word管理接口文档吗？还在拼接URL测试吗？是时候和Word/Excel说再见了！让我们一起SMART~

[http://www.179smart.com/smart-api/](http://www.179smart.com/smart-api/)(账号：admin@17smart.com；密码：654321)

您也想一起smart吗？烦请[此处](https://github.com/Aresyi/smart-api/issues/2)告知。
