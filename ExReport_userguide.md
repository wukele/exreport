# Introduction #

基于Excel，最贴近用户和开发者的报表系统。


# Details #

ExReport报表用户文档
当前版本
> V0.1

文档作者
> Kimmking wanghaibin

修订日期
> 2008年6月20日6:06:09



声 明
项目站点: http://code.google.com/p/exreport/

ExReport报表系统

授权方式:LGPL

作者：kimmking  wanghaibin

你可以基于各种目的免费使用本报表，无论是个人的目的还是商业性的，使用时请保留本文件。

唯一的一个建议是：如果你修改了本系统的一部分或者全部，请给本报表的作者发一封email来说明

你改进了什么和大概是如何做到的。（仅仅是一个建议）

作者email (kimmking.cn@gmail.com)

如果你因为商业性的目的，想用本报表来改进你的系统，并且需要我们的协助。请联系我们。

+86 13488713022.




ExReport资源

所有源代码：svn checkout http://exreport.googlecode.com/svn/trunk/

release下载：http://code.google.com/p/exreport/downloads/list



ExReport报表简介
基于Excel，最贴近用户和开发者的报表系统。

报表由数据和模板组成。数据为服务器端传递的用以生成报表所需的各种数据。模板为一个Excel文件，内置了处理数据和生成报表的通用VBA宏。

整个数据处理和生成报表都在客户端，减小了服务器端压力。

ExReport提供了序列化数据的服务器端程序、处理数据生成报表的vba程序、下载文件的activex组件和在web页面展示Excel的activex组件。

目前v0.1版本只提供了无限级的交叉报表，其通过网页上的json变量bean来传递数据到dsoframer中加载的Excel模板。

计划2008年7月初发布一个1.0的正式版，提供如下功能（均以实现，都在整理代码）：

1、  展示数据的普通报表。（一般的表格。）

2、  对于大数据量的异步加载方式。（不考虑网络因素，对于60M数据量，40S左右出表。）

3、  更为详细的文档或手册。





图1、ExReport系统

ExReport报表基本功能
1.         导出报表

2.         打印报表

3.         打印预览

4.         报表转置

5.         报表放大

6.         报表缩小

7.         隐藏/显示指定列



本报表使用的系统软件环境
Windows xp/2003

IE 5.**/6.**/7.

Excel 2000/xp/2003/2007

此环境即可演示本地的报表示例。



本报表支持的开发环境
web（asp，asp。net，php，jsp），java程序，swt，vb，vc，delphi， .net winform等各种支持activex的系统下使用。



目前整理的web项目示例为jsp示例,需要jre环境和jsp服务器（如tomcat5.x等）的支持。

Demo项目为myeclipse项目，可以使用myeclipse导入直接查看所有项目源码。



部署ExReport报表
1.         下载all.zip到本地。解压文件，双击运行ocx目录的reg.bat注册activex组件。

2.         打开excel，依次点击“工具”“选项”“安全性”“宏安全性”，将“安全级”设置为低。

3.         打开IE浏览器，依次点击“工具”“Internet选项”“安全”“可信站点”“站点”，将本网址，默认的demo是http://localhost添加到信任区域，同时将此区域的安全级别设置为低。如果要运行本地的release示例，需要将“本地Internat”的安全级别也设置为低。

4.         打开release目录下的report1.html文件。

5.         将var localfolder = "D:\\qsoft\\exreport\\exreport\release\\"; //你需要把这个路径改为你实际的绝对路径。不可使用相对路径。中的文件夹路径修改为此文件夹的实际路径。保存文件。

6.         在IE中打开report1.html文件，如果IE出现activex安全提示，双击提示，允许activex运行。如果能生成如下图的报表，证明部署报表成功。







图2、ExReport示例之一





报表开发使用说明
1.       ExReport报表模板的定义

ExReport整个报表区域可以分为报表头和报表体两部分。报表所需的全部数据在服务器端表示为一个HashMap。参见demo项目。

报表头包括标题（例如“xxxx报表”）和报表参数（例如“制表人：xxx”、“单位：万元”等等）。

报表体包括列标题区（一级或多级的列标题），行标题区（一级或多级的行标题）和数据区。

本报表规定：

使用#{exreport.crosstab.metadata.A}表示报表标题和参数，A表示参数名，服务器端序列化的map中存其键值对。

分别使用#{exreport.crosstab.row.P}和#{exreport.crosstab.column.P}表示报表行标题和列标题，均可以有多级，P表示bean中数据对象DTO\_DATA\_ARRAYS对应的属性的名称，与服务器端Dto的属性名称对应。






---




貌似太多了，发不下了。 详见文档。
