# WQStore
an easy WebProj for WQ
## 项目分包
- Dao(Data Access Object 数据访问对象)
	- 建立一个接口,接口中定义了此项目中将会用到的事务方法
	- Dao类简单来说就是主要做数据库的交互工作
- domain包
	- 实体类包:一个储存简单的Java对象的包
	- 作为数据容器来储存数据,将该类的成员变量进行封装
- service包
	- 做相应的业务逻辑处理,供外部调用
- servlet包
	- 用来获取 Http 客户端请求的数据，处理它们，
	- 然后生成 html 等格式的数据并发送给客户端
- Util包
	- 提供一些该项目用到的工具类,比如JDBC和UUID工具类
- Filter包
	- 一个简单的过滤器类,阻止非用户状态提交订单
- 前端页面
	- UI界面,与用户交互
