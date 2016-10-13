package cn.yibulz.v2ex.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.yibulz.v2ex.R;
import cn.yibulz.v2ex.bean.TopicBean;
import cn.yibulz.v2ex.widget.RelativeTimeTextView;

/**
 * Created by 10648 on 2016/10/13 0013.
 * Topics Adapter
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder>{

    private List<TopicBean> mBeanList = new ArrayList<>();
    private Context mContext;

    public TopicAdapter(List<TopicBean> beanList, Context context) {
        mBeanList = beanList;
        mContext = context;
    }


    @Override
    public TopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, parent, false);
        return new TopicHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicHolder holder, int position) {
        holder.topic_title.setText(mBeanList.get(position).getTitle());
        holder.topic_content.setText(mBeanList.get(position).getContent());
        Glide.with(mContext).load("https:" + mBeanList.get(position).getMember().getAvatar_large()).into(holder.img_view_topic_head);
        holder.txt_view_topic_name.setText(mBeanList.get(position).getMember().getUsername());
        holder.txt_view_topic_node.setText(mBeanList.get(position).getNode().getName());
        holder.txt_view_topic_time.setReferenceTime(mBeanList.get(position).getCreated() * 1000);
        holder.txt_view_topic_replies.setText(String.valueOf(mBeanList.get(position).getReplies()) + "条 回复");
        holder.itemView.setTag(mBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public void clearData() {
        mBeanList.clear();
        notifyDataSetChanged();
    }
    public void addItems(ArrayList<TopicBean> beanList) {
        mBeanList.addAll(beanList);
        notifyDataSetChanged();
    }


     class TopicHolder extends RecyclerView.ViewHolder {
       TextView topic_title;
       TextView topic_content;
       ImageView img_view_topic_head;
       TextView txt_view_topic_name;
       RelativeTimeTextView txt_view_topic_time;
       TextView txt_view_topic_replies;
       TextView txt_view_topic_node;

         TopicHolder(View itemView) {
           super(itemView);
           topic_title = (TextView) itemView.findViewById(R.id.txt_view_topic_title);
           topic_content = (TextView) itemView.findViewById(R.id.txt_view_topic_content);
           img_view_topic_head = (ImageView) itemView.findViewById(R.id.img_view_topic_head);
           txt_view_topic_name = (TextView) itemView.findViewById(R.id.txt_view_topic_name);
           txt_view_topic_time = (RelativeTimeTextView) itemView.findViewById(R.id.txt_view_topic_time);
           txt_view_topic_replies = (TextView) itemView.findViewById(R.id.txt_view_topic_replies);
           txt_view_topic_node = (TextView) itemView.findViewById(R.id.txt_view_topic_node);

       }
   }
}
