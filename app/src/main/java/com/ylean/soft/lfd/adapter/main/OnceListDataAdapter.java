package com.ylean.soft.lfd.adapter.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylean.soft.lfd.R;
import com.ylean.soft.lfd.activity.main.VideoPlayActivity;
import com.zxdc.utils.library.bean.Project;
import com.zxdc.utils.library.view.OvalImageViews;
import java.util.List;
public class OnceListDataAdapter extends RecyclerView.Adapter<OnceListDataAdapter.MyHolder> {

    private Activity activity;
    private List<Project.ListData> list;
    public OnceListDataAdapter(Activity activity,List<Project.ListData> list) {
        this.activity = activity;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_main_look, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        Project.ListData listData=list.get(i);
        //图片
        String imgUrl=listData.getImgurl();
        holder.imgHead.setTag(R.id.imageid,imgUrl);
        if(holder.imgHead.getTag(R.id.imageid)!=null && imgUrl==holder.imgHead.getTag(R.id.imageid)){
            Glide.with(activity).load(imgUrl).into(holder.imgHead);
        }
        holder.tvSize.setText(listData.getPlayCount()+"w");
        holder.tvTitle.setText(listData.getName());
        holder.tvNum.setText("第"+listData.getEpisodeCount()+"集");

        /**
         * 播放视频
         */
        holder.imgHead.setTag(R.id.tag1,listData);
        holder.imgHead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Project.ListData listData= (Project.ListData) v.getTag(R.id.tag1);
                Intent intent=new Intent(activity, VideoPlayActivity.class);
                intent.putExtra("serialId",listData.getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
       OvalImageViews imgHead;
        TextView tvTitle,tvNum,tvSize;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgHead=itemView.findViewById(R.id.img_head);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvNum=itemView.findViewById(R.id.tv_num);
            tvSize=itemView.findViewById(R.id.tv_size);
        }
    }

}

