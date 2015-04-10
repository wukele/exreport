this is a report solution or system that is based on the Excel.<br>
<br>
you can download the zip file here:<br>
<a href='http://code.google.com/p/exreport/downloads/list'>http://code.google.com/p/exreport/downloads/list</a><br>
<br>
you can sumbit your questions or bugs or request here:<br>
<a href='http://code.google.com/p/exreport/wiki/bugslist'>http://code.google.com/p/exreport/wiki/bugslist</a><br>
<br>
if there are others questions,please email me:<br>
kimmking.cn@gmail.com <br>
<br>
<br>
目前提供jsp和asp.net的例子和支持    for free for fun<br>
<img src='http://setting.javaeye.com/upload/picture/pic/33027/5e5478e7-b832-3205-b028-da29cfc82756.jpg' />
<br>
你可以在此下载本报表所有内容的zip文件：<br>
<a href='http://code.google.com/p/exreport/downloads/list'>http://code.google.com/p/exreport/downloads/list</a><br>
<br>
你可以提交你的问题或者bug：<br>
<a href='http://code.google.com/p/exreport/wiki/bugslist'>http://code.google.com/p/exreport/wiki/bugslist</a><br>
<br>
其他相关问题，可以email：<br>
kimmking.cn@gmail.com <br>
<br>
<hr />
目前实现了三种数据加载方式：<br>
1、通过页面json对象传递（default）<br>
2、通过Ajax异步加载（这个数据量略大，有点慢，一般情况下还算理想）<br>
3、通过zip包下载数据文件，导入excel（适用于大数据量，某个项目里成功地用解压过50M的文本数据出表）<br>
<hr />

本报表来源于实际做项目的经验，也同样得到了用户的一致认可。<br>
所有资料和技术都来源与开源相关的产品和技术，没有任何版权问题和纠纷。<br>
同时，本报表目前还是一个毛坯级产品，需要一个总的规划和设计，<br>
需要更多的有相关技术背景或是业务背景的人加入其中或是给予帮助。<br>
众人拾柴火焰高。不管我们以后自己怎么选择。<br>
我和我的团队都不会因为任何目的或是压力改变本产品的授权方式。<br>
exreport都是对任何人和组织开源的，免费的，不管你是任何目的。<br>
永远欢迎使用，批评，修改或是建议。<br>

<br>
==> 针对64位win7的说明<br>
<br>
1.控件注册不上的问题：<br>
进入“开始”->"所有程序"-> "附件"->“命令提示符”（cmd.exe）,这里比较关键，必须进行右击cmd.exe，“以管理员身份……”进入该cmd.exe，即可进入管理员身份来注册ocx。然后需要手动输入reg.bat执行即可。<br>
2.office安全性的问题：<br>
进入excel，点击左上角的按钮，excel选项，信任中心，信任中心设置，宏设置，<br>
选上 启动所有宏和信任对vba的访问<br>
<br>
3.report2.html找不到zlibwapi.dll的问题<br>
手动复制ocx文件夹下的zlibwapi.dll复制到c:\windows和c:\windows\system32<br>
<br><br>
==> 针对ie10的说明<br>
<br>
1.无法生成报表的问题：<br>
打开report0.html文件，删除掉document.onkeydown 函数即可。<br>
这个语法不支持了。