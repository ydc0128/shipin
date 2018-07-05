package com.example.administrator.douyinhuadong.activity;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.dingmouren.layoutmanagergroup.viewpager.OnViewPagerListener;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;
import com.example.administrator.douyinhuadong.MyView;
import com.example.administrator.douyinhuadong.R;
import com.example.administrator.douyinhuadong.adapter.VideoAdapter;



public class DouyinActivity extends BaseActivity {
    private final static String TAG = DouyinActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ViewPagerLayoutManager mLayoutManager;
    private VideoAdapter adapter;
    private MyView txtAgreeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douyin);
        initView();
        setListener();
        txtAgreeView = findViewById(R.id.myView);
        txtAgreeView.setClickListener(new MyView.MyViewClickListener() {
            @Override
            public void onAgreeClick(View view) {
                Log.e(TAG, "onAgreeClick: " + "IMG");
            }
        });
    }


    private void initView() {
        mRecyclerView = findViewById(R.id.rv_video_list);
        mLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        adapter = new VideoAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void setListener() {
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                playVideo();
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG,"释放位置:"+position +" 下一页:"+isNext);
                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position,boolean isBottom) {
                playVideo();
            }
        });
    }

    private void releaseVideo(int pos) {
        View itemView = mRecyclerView.getChildAt(pos);
        final VideoView videoView = itemView.findViewById(R.id.video_item);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void playVideo() {
        View itemview = mRecyclerView.getChildAt(0);
        VideoView videoView = itemview.findViewById(R.id.video_item);
        final ImageView imgPlay = itemview.findViewById(R.id.img_play);
        final ImageView imgThumb = itemview.findViewById(R.id.img_thumb);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
    }
}
