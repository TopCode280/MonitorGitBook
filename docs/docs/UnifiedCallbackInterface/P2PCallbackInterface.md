# P2PCallback (P2PCallback Interface)

```java
/**
 *
 * @param <T> 数据泛型类型
 */
public interface P2PCallback<T> {
    //错误码
    int ERR_TIME_OUT=-3;//超时
    /**
     *
     * @param data 实际返回数据
     */
    void onSuccess(T data);

    /**
     *
     * @param errCode 错误码
     * @param message 错误提示
     */
    void onFailure(int errCode,String message);
}


```

