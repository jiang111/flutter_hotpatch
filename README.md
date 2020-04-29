Experimental


> 1.将patch文件夹中的app-release.apk,然后安装至手机,赋予相关的权限
> 2.将libapp_fix.so和hotpatch-resource.zip拷贝至sd卡的根目录
> 3.点击app中的启动老版本可查看老版本的内容,然后将app进程杀掉,再打开app,点击热更新,可实现sd卡中的libapp_fix.so和hotpatch-resource.zip动态加载


已经实现的功能:
> 1.已实现flutter module中的代码和资源的热更新

已知问题:
> 1.只能下发flutter代码,不能下发Android代码(这也不算问题)
> 2.flutter engine版本最好一致
> 3.点击热更新时必须保证fluttr engine没有初始化过,也就是app运行时没有初始化过flutter engine

