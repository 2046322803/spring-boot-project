证书生成
使用SSL需要我们先生成一个证书，这个证书我们可以自己生成，也可以从SSL证书授权中心获得，自己生成的不被客户端认可，从授权中心获得的可以被客户端认可，提供SSL授权证书的服务商有很多，小伙伴们有兴趣可以自行查找。
我这里以自己生成的证书为例。 生成方式也很简单，直接使用java自带的命令keytool来生成，生成命令如下：
keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650

这里涉及到几个参数的含义简单说一下：
1.-storetype 指定密钥仓库类型 
2.-keyalg 生证书的算法名称，RSA是一种非对称加密算法 
3.-keysize 证书大小 
4.-keystore 生成的证书文件的存储路径 
5.-validity 证书的有效期

执行完上面一行命令后，在你的系统的当前用户目录下会生成一个keystore.p12文件（如果你修改了证书文件的名称那就是你修改的名字），将这个文件拷贝到我们项目的根目录下，然后修改application.properties文件，添加HTTPS支持。
在application.properties中添加如下代码：
#指定签名文件
server.ssl.key-store=keystore.p12
#指定签名密码
server.ssl.key-store-password=111111
#指定密钥仓库类型
server.ssl.keyStoreType=PKCS12
#别名
server.ssl.keyAlias:tomcat