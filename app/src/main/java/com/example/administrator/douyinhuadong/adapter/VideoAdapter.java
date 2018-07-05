package com.example.administrator.douyinhuadong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.administrator.douyinhuadong.R;


/**
 * 类描述：
 *
 * @Author 许少东
 * Created at 2018/6/25.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private boolean _pressed = false;
    private int[] imgs = {R.mipmap.img_video_1, R.mipmap.img_video_2};
    private int[] videos = {R.raw.video_1, R.raw.video_2};
//    private String[] videoUrls = {"http://ali.cdn.kaiyanapp.com/18cea6c2da0d1b6cb66e25f76df67dfb_1280x720.mp4?auth_key=1529914449-0-0-90f033a6c20657d740d99ff709507b9c"
//            , "http://ali.cdn.kaiyanapp.com/1529550600568_68b40dd8_1280x720.mp4?auth_key=1529914568-0-0-a49923a86b75b157bf936fdc2d5d2912"};
    private Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_video, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.img_thumb.setImageResource(imgs[position % 2]);
        holder.videoView.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+videos[position%2]));
//        holder.videoView.setVideoURI(Uri.parse(videoUrls[position%2]));
        holder.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_pressed) {
                    _pressed = false;
                    holder.videoView.pause();
                }else {
                    _pressed = true;
                    holder.videoView.start();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_thumb;
        VideoView videoView;
        ImageView img_play;
        RelativeLayout rootView;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            videoView = itemView.findViewById(R.id.video_item);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);
        }


    }

}
