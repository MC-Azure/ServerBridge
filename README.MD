# ServerBridge

子服交流

**ServerBridge** is a plugin for [Bukkit](https://bukkit.org/), [Spigot](https://www.spigotmc.org/), [Paper](https://papermc.io/software/paper), [Bungeecord](https://www.spigotmc.org/), [Waterfall](https://papermc.io/software/waterfall) as well as [Velocity](https://papermc.io/software/velocity), which serves as a communicator for different servers that are
even not connected to one Proxy.

**子服交流**是一款实现让即使不连接到一个代理的服务器交流的插件。

Relying on *Redis Server* means you can hook it **outside** Minecraft servers.
基于Redis服务器意味着你甚至可以在服务器外操作。
____

## For developers 对于开发者

### If you're willing to make *ServerBridge* as a dependency of your plugins, simply follow these steps:

### Resolving dependencies

**ServerBridgeAPI** is available in Maven Central Repository, which you can import directly without adding repository.

#### Maven

If you're using Maven, simply add this to the `dependencies` section of your POM.

   ```xml

<dependencies>
    <dependency>
        <group>io.github.vlouboos</group>
        <artifactId>serverbridge-api</artifactId>
        <version>1.0</version>
        <scope>provided</scope>
    </dependency>
    ...
</dependencies>
   ```

#### Gradle

If you're using Gradle, you need to add these lines to your build script.

##### Groovy DSL:

```gradle
repositories {
    mavenCentral()
}

dependencies {
    compileOnly 'io.github.vlouboos:serverbridge-api:1.0'
}
```

##### Kotlin DSL:

```gradle
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.github.vlouboos:serverbridge-api:1.0")
}
```

#### Manual

If you want to manually add the API dependency to your classpath, you can obtain the jar by:

1. Navigating to [
   `https://repo1.maven.org/maven2/io/github/vlouboos/serverbridge-api/`](https://repo1.maven.org/maven2/io/github/vlouboos/serverbridge-api/)
2. Selecting the version you wish to use
3. Downloading the jar titled `api-x.x.jar`

### Obtain API

The API can be accessed via `ServerBridgeAPI.getApi()`

### The use

You're required to subscribe **all** the channels and register **all** the listeners you'll need **BEFORE THE SERVER IS ENABLED**.

**To create a listener**, just create a class like this:
```java
import io.github.vlouboos.serverbridge.MessageListener;

public class ExampleListener implements MessageListener {
   public void onMessage(@NotNull String channel, String @NotNull ... messages) {
      // ...
   }
}
```

Then register it like
```java
ServerBridgeAPI.getApi().registerListeners(new ExampleListener());
```

**To subscribe a channel**, add the code before server is enabled.
```java
ServerBridgeAPI.getApi().subscribeChannels("channel1", "channel2", "...");
```

**To send messages**, use
```java
ServerBridgeAPI.getApi().sendMessages("channel", "msg1", "msg2", "...");
```
____

## 构建(Building)

1. 克隆Git项目(Clone Git Project) ```git clone https://github.com/MC-Azure/ServerBridge```
2. 执行maven任务(Execute Maven Goal) ```mvn clean package```
3. 找到构建出的文件(Find the jar file) `ServerBridge/target/ServerBridge-版本(version).jar`

____

## 许可证(License)

ServerBridge受到开源许可证BSD 4-Clause保护。详情参阅 [
`LICENSE.txt`](https://https://github.com/MC-Azure/ServerBridge/blob/master/LICENSE.txt)。
