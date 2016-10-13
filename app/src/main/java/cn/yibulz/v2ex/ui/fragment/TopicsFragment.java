package cn.yibulz.v2ex.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class TopicsFragment extends Fragment implements IFragment {

    public static final String HOT_OR_LATEST = "hot.json";


    ImplTopic mTopic;

    TopicAdapter mAdapter;
    private List<TopicBean> mBeanList = new ArrayList<>();

    RecyclerView mRecyclerView;


    public TopicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topics, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RV_hot_latest);
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
        mTopic.getTopicData(HOT_OR_LATEST);
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
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
    public void showError(String error) {
        Toast.makeText(getActivity(), "无网络", Toast.LENGTH_SHORT).show();
    }
}
