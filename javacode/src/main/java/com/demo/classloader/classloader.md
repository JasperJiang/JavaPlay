# Java类加载器

#### 为什么要有类加载器

我们知道java中所有的二进制文件，最后都是要放在jvm中解释运行的。纯粹的二进制文件，其实并没有什么卵用。jvm在第一次使用或者预加载时，都要将某个类的二进制文件加载进去，这时候不可避免的需要用到一个加载的触手，就是这个类加载器啦。

#### 类的加载过程

#### 简单来说，一般可分为加载、连接、初始化三个过程。

![classLoader_process](/Users/jjiang153/Documents/Play_ground/Java_Learning/imgs/jvm/classloader_process.png)

加载，顾名思义，就是将类的class文件读入到内存中，创建一个Class对象，你可能已经知道了，java中所有的类都是Class类的实例。jvm已经中提供了一些系统类加载器。

连接，我的理解就是将这些二进制数据放到jre中，准备初始化。而这一步一般也可分作三个阶段：

- 验证：保证类没有被加载跑偏，而且结构没被破坏；
- 准备：为各种变量分配内存空间，并设置它们的初始值；
- 解析：把类中的符号引用转换为直接引用；

初始化，主要就是类变量进行初始化，这里一般指static声明的变量，或者静态代码块。这里需要提一下，熟悉构造器的童鞋应该都有这样的体验：java中的类其实都很“孝顺”的——调用自己构造器之前，必先调用父类的构造器。初始化也是如此，如果该类的直接父类没有被初始化，则需要先初始化它的直接父类，如此调用直到Object类。另外由static final修饰的常量，一般在编译时值已经确定，通过类来访问它时则不会再对其进行初始化，有点类似于直接量。

#### 类加载器

前面已经说过，类加载器负责将.classs文件加载到内存中，同时生成一个Class对象，当下一个次需要加载时，JVM中如果存在同一个类，那么加载就不会继续。有童鞋可能已经有问题了：怎么判断是不是需要再加载呢？也就是说啥样的两个类才算同一个类？

对了，名字一样。在Java中，一个类通常会用它的命名空间（包名+类名）来作为它的唯一标识。Jvm中进一步约束了条件，通常会把命名空间和其类加载器作为它的唯一标识，也就是说同一个类必须满足包名、类名、加载器都一样才行。举个例子来讲，我们现在有一个fruit包，里面有一个Apple类，被类加载器classloader1加载了，那么这个类的实例在Jvm中就可以标识为（Apple,fruit,classloader1），很明显Jvm认为它与（Apple,fruit,classloader2）并不是同一个类（即所谓的ClassLoader隔离）。根加载器

一般的，Jvm中的加载器如果按照继承来分，可分为ClassLoader子类和非ClassLoader子类，如果按照层次结构来分，则可分为这三种：

- 根类加载器（Bootstrap ClassLoader）,主要加载Java的核心类库，它就不是ClassLoader的子类，由更屌的c++实现的；
- 扩展类加载器（Extension ClassLoader），主要负责加载jre扩展目录中jar包的类，就是另外添加的放在扩展路径下的jar文件；
- 系统类加载器（System ClassLoader）,这个就更常见了，你写的java文件或者classpath变量所指向的jar包和类，都是靠它来加载的。

#### 自定义类加载器

除了根类加载器之外，所有的类加载器都是ClassLoader的子类。那我们现在要自定义自己的类加载器，很自然地就要继承自ClassLoader。源码中提供了大量需要我们重写的方法，其中有两个是比较关键的：

loadClass():是ClassLoader的入口，加载器可根据类名来加载指定类对应的Class对象；

findClass():根据指定名来查找类；

两个方法关系紧密，如下图所示:

![classloader_function](../imgs/jvm/classloader_function.png)

这下明白通常推荐重写findClass()而不是loadClass()的原因了吧，重写findClass()不仅简单，而且还能都避免覆盖默认加载器的父类委托、缓存机制等，一举果有两虎之功呃。

再来一张它们的类关系图吧

![classLoader_relation](../imgs/jvm/classloader_relation.png)

