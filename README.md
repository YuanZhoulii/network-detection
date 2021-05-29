# network-detection

#### 介绍
network-detection后端功能实现

#### 相关技术
Spring Cloud+Spring Boot+SSM+MySQL+Redis


#### 其它依赖项

Nacos、Sentinel服务器

#### 项目结构 

```
network-detection
├─admin  后台管理服务（基于renren-fast，用户管理、Nmap任务管理等后台管理功能后端实现）
│
├─auth-server  认证服务 （校验信息合法性）
│
├─common  公共服务 （定义通用类库，包含异常类、工具类、常量类等）
│
├─detect  探测服务 （执行Nmap命令探测网络资源信息，得到XML结果文件）
│
├─gateway  网关服务 （前端请求的统一入口）
│
├─save  存储服务 （解析XML探测结果文件，保存至数据库）
│
├─search  查询服务 （返回前台系统可视化图表所需要的数据）
│
└ user  用户服务 （个人信息管理、注册、登录后端实现）

