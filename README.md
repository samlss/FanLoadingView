# FanLoadingView
A fan rotation loading view(一个风扇旋转的loading view).

[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/FanLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/FanLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)

<br>

  * [中文](#%E4%B8%AD%E6%96%87)
  * [English](#english)
  * [License](#license)

<br>

![gif](https://github.com/samlss/FanLoadingView/blob/master/screenshots/screenshot1.gif)



## 中文

### 使用<br>
在根目录的build.gradle添加这一句代码：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用：
```
dependencies {
    implementation 'com.github.samlss:FanLoadingView:1.0'
}
```

布局中使用：
```
 <com.iigo.library.FanLoadingView
        app:main_color="@color/colorPrimary"
        app:interpolator="OvershootInterpolator"
        android:layout_centerInParent="true"
        android:layout_width="50dp"
        android:layout_height="50dp" />

```

<br>

代码中使用：
```
  fanLoadingView.pause(); //暂停动画
  fanLoadingView.resume(); //恢复动画
   
  fanLoadingView.start(); //开始动画
  fanLoadingView.stop(); //停止动画
  
  fanLoadingView.setColor(Color.RED); //设置风扇主体颜色
```

<br>

属性说明：

| 属性        | 说明           |
| ------------- |:-------------:|
| main_color      | 风扇主体颜色 |
| interpolator | 动画加速器 |

### 插值器值interpolator: <br>
* AccelerateDecelerateInterpolator
* AccelerateInterpolator
* DecelerateInterpolator
* BounceInterpolator
* CycleInterpolator
* LinearInterpolator
* AnticipateOvershootInterpolator
* AnticipateInterpolator
* OvershootInterpolator

<br>

## English

### Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:FanLoadingView:1.0'
}
```


in layout.xml：
```
 <com.iigo.library.FanLoadingView
        app:main_color="@color/colorPrimary"
        app:interpolator="OvershootInterpolator"
        android:layout_centerInParent="true"
        android:layout_width="50dp"
        android:layout_height="50dp" />

```

<br>

in java code：
```
  fanLoadingView.pause(); //pause animation
  fanLoadingView.resume(); //resume animation
   
  fanLoadingView.start(); //start animation
  fanLoadingView.stop(); //stop animation
  
  fanLoadingView.setColor(Color.RED); //set the color of fan
```
<br>


Attributes description：

| attr        | description  |
| ------------- |:-------------:|
| main_color      | the color |
| interpolator | the animator interpolator |

### interpolator: <br>
* AccelerateDecelerateInterpolator
* AccelerateInterpolator
* DecelerateInterpolator
* BounceInterpolator
* CycleInterpolator
* LinearInterpolator
* AnticipateOvershootInterpolator
* AnticipateInterpolator
* OvershootInterpolator

<br>

[id]: http://example.com/ "Optional Title Here"

## [LICENSE](https://github.com/samlss/FanLoadingView/blob/master/LICENSE)
