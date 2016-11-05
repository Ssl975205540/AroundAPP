# AroundAPP
组长：刘瀚璘，组员：孙彦飞，刘庆，武神
适配器怎么不封装,是不是得给你们造成点冲突
自定义组件的分包有问题    

首页的搜索框动画有问题,显示效果不是很美观    

分类的搜索框适配有问题    

接口设计的不太合理,OnCompleted这个接口与下面的接口重复    

RecyclerHolder这个类有啥用,ViewHolder本身不是就有itemView的全局属性么    

创建了Adapter基类,为什么不使用    

flingswipe自定义组件,放到自定义组件的包里面,不要跟所有的包同一个等级    

MVP理解的不好,用的不熟练

------

gradle.build文件中添加的扩展包依赖,版本需要统一

严格来说,CheckAllActivity类中读取数据流需要放到子线程中进行.并且这个过程也需要使用MVP来写,Activity中不要写读流和解析的逻辑

CheckAllAdapter,不要用集合.get(position)那么多次

声明集合对象时,要面向接口,不要直接使用ArrayList声明对象

ClassifyTypeActivity中Intent传值的key要写成常量

ClassifyAdapter里面代码写的乱,mSubCateArrBeanList数据处理的不合理,不应该是全局属性

好多适配器没有封装

ClassifyDigitAdapter里面的集合为啥要new一个之后又重新通过构造方法赋值,那new还有什么用

ClassifyFragment,一个Fragment用构造方法传值???谁教你的

怎么还是用System.out.pringln去输出呢

View层的接口没有泛型,覆写出来的方法参数都是Object类型的

自定义的组件分包不对

MVP的理解不太好,关于数据处理的逻辑部分要放到M层,比如各种判断,整理集合数据类型之类的

