项目说明：
    本工程库可作为引用库，主要总结了一些通用的工具类，方法，控件等的使用方法，方便在项目中直接使用，
节约开发时间。
    主要功能介绍如下：
        1.CrashHandler
          包名：com.yk.library.util.CrashHandler
          说明：将程序运行中的异常信息保存到本地文件
          使用方法：在 MyApplication 的 onCreate 方法中调用
                    CrashHandler.getInstance().init(getApplicationContext());
