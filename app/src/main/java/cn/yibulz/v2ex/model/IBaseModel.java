package cn.yibulz.v2ex.model;

import rx.Subscription;

/**
 * Created by 10648 on 2016/10/13 0013.
 */

public interface IBaseModel {
    void addSubscription(Subscription subscription);
    void unSubscribe();
}
