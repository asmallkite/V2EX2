package cn.yibulz.v2ex.api;

import java.util.ArrayList;

import cn.yibulz.v2ex.bean.TopicBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by 10648 on 2016/10/13 0013.
 *
 */

public interface HotLatestApi {
    @GET("/api/topics/{category}")
    Observable<ArrayList<TopicBean>> getTopicDataService(@Path("category") String category);
}
