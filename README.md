实验性项目,勿用于生产环境

效果:

![](https://github.com/jiang111/flutter_hotpatch/raw/master/art/result.gif)

DEMO:
> 1.下载patch文件夹中的app-release.apk,然后安装至手机,赋予相关的权限

> 2.将libapp_fix.so和hotpatch-resource.zip拷贝至sd卡的根目录

> 3.点击app中的启动老版本可查看老版本的内容,然后将app进程杀掉,再打开app,点击热更新,可实现sd卡中的libapp_fix.so和hotpatch-resource.zip动态加载


已经实现的功能:

> 1.已实现flutter module中的代码和资源的热更新

已知问题:
> 1.只能下发flutter代码,不能下发Android代码(这也不算问题)

> 2.flutter engine版本最好一致

> 3.点击热更新时必须保证fluttr engine没有初始化过,也就是app运行时没有初始化过flutter engine

原理:
> 1.资源的热更新是通过Android自带的AssetManager,通过反射它的addAssetPath方法来将hotpatch-resource.zip中的资源实现累加

> 2.dart代码层面的热更新通过生成的libapp_fix.so(so文件里包含的就是所有业务代码),修改FlutterLoader中的aotSharedLibraryFile字段实现,具体请参考源码
