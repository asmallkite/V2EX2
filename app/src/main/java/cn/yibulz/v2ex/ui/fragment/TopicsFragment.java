package cn.yibulz.v2ex.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.yibulz.v2ex.R;
import cn.yibulz.v2ex.adapter.TopicAdapter;
import cn.yibulz.v2ex.bean.TopicBean;
import cn.yibulz.v2ex.model.ImplTopic;
import cn.yibulz.v2ex.ui.IView.IFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicsFragment extends Fragment implements IFragment, SwipeRefreshLayout.OnRefreshListener {

    public static final String HOT_OR_LATEST = "hot.json";
    private Boolean isFirstLoad = true;


    ImplTopic mTopic;

    TopicAdapter mAdapter;
    private List<TopicBean> mBeanList = new ArrayList<>();

    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public TopicsFragment() {
        // Required empty public constructor
    }


    public static TopicsFragment getInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(HOT_OR_LATEST, type);
        TopicsFragment topicsFragment = new TopicsFragment();
        topicsFragment.setArguments(bundle);
        return topicsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topics, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RV_hot_latest);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        loadData();
    }


    private void loadData() {
        if (mBeanList.size() > 0) {
            mAdapter.clearData();
        }
        if (getArguments() != null) {
            mTopic.getTopicData(getArguments().getString(HOT_OR_LATEST));
            isFirstLoad = false;
        }
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                android.R.color.holo_green_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        mTopic = new ImplTopic(this);
        mAdapter = new TopicAdapter(mBeanList, getActivity());
    }

    @Override
    public void updateList(ArrayList<TopicBean> beanArrayList) {
        mAdapter.addItems(beanArrayList);
    }


    @Override
    public void showProgress() {
           if (!isFirstLoad) {
               mSwipeRefreshLayout.setRefreshing(true);
           }else {
               if (mProgressBar != null) {
                   mProgressBar.setVisibility(View.VISIBLE);
               }
           }
    }

    @Override
    public void hideProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(String error) {
        if (mRecyclerView != null) {
            Snackbar.make(mRecyclerView, getString(R.string.snack_infor),
                    Snackbar.LENGTH_LONG)
                    .setAction("重试", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadData();
                        }
                    }).show();
        }
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
