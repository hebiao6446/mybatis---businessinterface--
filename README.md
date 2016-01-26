# mybatis---businessinterface--

sql脚本在resource中，使用MySQL数据库

访问方式
IP+端口+项目名称+@Namespace+@Action+.action 

若,
IP: 192.168.1.111
端口是: 8080
项目是: businessinterface
命名空间: @Namespace("/user")
Action注解: @Action(value = "userLogin")

注意，必须以.action结尾
那么用户的登录接口应该是:
http://192.168.1.111:8080/businessinterface/user/userLogin.action 
