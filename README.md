# SafeExecutor
SafeExecutor is event based error handler.

# STILL DEVELOPING !!!
### now support
- error listener
- add event

### will support
- retry policy
- thread scheduler

# Useage

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

But there are still proglems.
1. If first `setText` fails, the rest is not executed.
2. You can use `try-catch` on every line. But it is uncomfortable.

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

