# SafeExecutor
[![Build Status](https://travis-ci.org/JSpiner/SafeExecutor.svg?branch=master)](https://travis-ci.org/JSpiner/SafeExecutor) [![Coverage Status](https://coveralls.io/repos/github/JSpiner/SafeExecutor/badge.svg?branch=master)](https://coveralls.io/github/JSpiner/SafeExecutor?branch=master) [ ![Download](https://api.bintray.com/packages/jspiner/executor/safeexecutor/images/download.svg) ](https://bintray.com/jspiner/executor/safeexecutor/_latestVersion)


SafeExecutor is event-based error handler.

[find a bug?](https://github.com/JSpiner/SafeExecutor/issues/new?template=bug.md)
[hava a question?](https://github.com/JSpiner/SafeExecutor/issues/new?template=question.md)


# STILL DEVELOPING !!!
### now support
- error listener
- add event

### support soon
- retry policy
- thread scheduler

# Usage

Before you know SafeExecutor,

```java
private void bindData(CarModel carModel) {
    carNameTextView.setText(carModel.detail.carName);
    carPriceTextView.setText(carModel.detail.price.toString());
    carNumberTextView.setText(carModel.detail.number.toString());
}
```

There are many problems.
1. `carModel` or `carModel.detail` can be null
2. `carNameTextView` can be null
3. `setText` is may not be able to run on other thread
4. etc....

Of course, you can use `try-catch` like below.

```java
private void bindData(CarModel carModel) {
    try {
        carNameTextView.setText(carModel.detail.carName);
        carPriceTextView.setText(carModel.detail.price.toString());
        carNumberTextView.setText(carModel.detail.number.toString());   
    }
    catch(Exception exception){
        logException(exception);
    }
}
```

But there are still some problems.
1. If first `setText` fails, rests are not executed.
2. You can use `try-catch` on every line, but it's uncomfortable.

After you know SafeExecutor,
```java
private void bindData(CarModel carModel) {
    SafeExecutor.build()
        .add(() -> carNameTextView.setText(carModel.detail.carName))
        .add(() -> carPriceTextView.setText(carModel.detail.price.toString()))
        .add(() -> carNumberTextView.setText(carModel.detail.number.toString()))
        .executeOn(Scheduler.MainThread)
        .onError(throwable -> logException(throwable))
        .run();
}
```
Simple and safe and... looks cool!!

# Download
[ ![Download](https://api.bintray.com/packages/jspiner/executor/safeexecutor/images/download.svg) ](https://bintray.com/jspiner/executor/safeexecutor/_latestVersion)

Maven
```xml
<dependency>
  <groupId>net.jspiner</groupId>
  <artifactId>safeexecutor</artifactId>
  <version>{VERSION_CODE_HERE}</version>
  <type>pom</type>
</dependency>
```

Gradle
```gradle
compile 'net.jspiner:safeexecutor:{VERSION_CODE_HERE}'
```
