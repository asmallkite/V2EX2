package cn.yibulz.v2ex.model;

import java.util.ArrayList;

import cn.yibulz.v2ex.api.ApiManage;
import cn.yibulz.v2ex.bean.TopicBean;
import cn.yibulz.v2ex.ui.IView.IFragment;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 10648 on 2016/10/13 0013.
 *
 */

public class ImplTopic extends ImplBaseModel implements ITopic {
    IFragment mIFragment;

    public ImplTopic(IFragment IFragment) {
        mIFragment = IFragment;
    }

    @Override
    public void getTopicData(String category) {
        Subscription subscription = ApiManage.getInstance()
                .getHotLatestApi()
                .getTopicDataService(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<TopicBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<TopicBean> beanArrayList) {
                        mIFragment.updateList(beanArrayList);
                    }
                });
        addSubscription(subscription);
    }
}
