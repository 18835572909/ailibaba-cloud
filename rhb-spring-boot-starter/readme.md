# 自定义springboot-starter

## 主要关注点
1. spring-boot-autoconfigure.jar/META-INF下文件作用详解

2. ./META-INF下的文件使用源码详解

3. 根据源码解析，自定义xxx-spring-boot-starter

## META-INF文件详解
1. spring.factories: 加载需要自动配置的文件

2. additional-spring-configuration-metadata.json: 自定义配置文件提示信息

3. spring-configuration-metadata.json: 3和4都是编辑元数据信息，辅助配置文件提示

4. spring-autoconfigure-metadata.properties: 检测过滤文件列表，如果配置中的文件不存在排除加载

## SpringBoot自动装载源码解析【IOC】
@SpringBootApplication-> @EnableAutoConfiguration-> @Import({AutoConfigurationImportSelector.class})
-> AutoConfigurationImportSelector.selectImports():返回的就是需要注册到IoC容器中的对象对应的类型的全类路径名称的字符串数组
-> AutoConfigurationImportSelector.getAutoConfigurationEntry()     
```java      
protected AutoConfigurationImportSelector.AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
    if (!this.isEnabled(annotationMetadata)) {
        return EMPTY_ENTRY;
    } else {
        // 获取注解的属性信息
        AnnotationAttributes attributes = this.getAttributes(annotationMetadata);
        // 获取候选配置信息 加载的是 当前项目的classpath目录下的 所有的 spring.factories 文件中的 key 为
        // org.springframework.boot.autoconfigure.EnableAutoConfiguration 的信息
        // 再进去：SpringFactoriesLoader.loadSpringFactories():加载自己打包的配置文件和原来spring-boot-autoconfigure包的配置文件（spring.factories）
        List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
        // 因为会加载多个 spring.factories 文件，那么就有可能存在同名的，
        // removeDuplicates方法的作用是 移除同名的
        configurations = this.removeDuplicates(configurations);
        // 获取我们配置的 exclude 信息
        // @SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
        // 显示的指定不要加载那个配置类
        Set<String> exclusions = this.getExclusions(annotationMetadata, attributes);
        this.checkExcludedClasses(configurations, exclusions);
        configurations.removeAll(exclusions);
        // filter的作用是 过滤掉咱们不需要使用的配置类。
        // 检测spring-autoconfigure-metadata.properties配置的文件是否存在，不存在过滤掉
        configurations = this.getConfigurationClassFilter().filter(configurations);
        this.fireAutoConfigurationImportEvents(configurations, exclusions);
        return new AutoConfigurationImportSelector.AutoConfigurationEntry(configurations, exclusions);
    }
}
```
## JAVA的SPI
java spi的具体约定为:当服务的提供者，提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里
同时创建一个以服务接口**全路径**命名的文件。该文件里就是实现该服务接口的具体实现类的**全路径**（多个
实现类分行存放）。而当外部程序装配这个模块的时候，就能通过该jar包META-INF/services/里的配置文件找到
具体的实现类名，并装载实例化，完成模块的注入。基于这样一个约定就能很好的找到服务接口的实现类，而不需
要再代码里制定。jdk提供服务实现查找的一个工具类：java.util.ServiceLoader   
&& springboot自动装载使用SpringFactoriesLoader &&

## JNDI
// TODO

## JNDI是如何打破双亲委派机制
// TODO

## spring.factoies文件中配置文件详解
现在只是：org.springframework.boot.autoconfigure.EnableAutoConfiguration
// TODO
