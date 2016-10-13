package cn.yibulz.v2ex.model;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 10648 on 2016/10/13 0013.
 * 使用CompositeSubscription 管理subscription
 */

public class ImplBaseModel implements IBaseModel{
    private CompositeSubscription mCompositeSubscription;
    @Override
    public void addSubscription(Subscription subscription) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    public void unSubscribe() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }

    }
}
