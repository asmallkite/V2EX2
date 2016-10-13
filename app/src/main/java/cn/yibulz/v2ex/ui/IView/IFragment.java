package cn.yibulz.v2ex.ui.IView;

import java.util.ArrayList;

import cn.yibulz.v2ex.bean.TopicBean;

/**
 * Created by 10648 on 2016/10/13 0013.
 */

public interface IFragment extends IBaseFragment {
    void updateList(ArrayList<TopicBean> beanArrayList);
}
