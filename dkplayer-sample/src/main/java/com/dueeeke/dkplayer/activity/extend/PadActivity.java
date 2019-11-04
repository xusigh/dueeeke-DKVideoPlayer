package com.dueeeke.dkplayer.activity.extend;

import android.view.View;
import android.widget.Toast;

import com.dueeeke.dkplayer.R;
import com.dueeeke.dkplayer.activity.BaseActivity;
import com.dueeeke.videocontroller.StandardVideoController;

public class PadActivity extends BaseActivity {

    private StandardVideoController mController;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pad;
    }

    @Override
    protected void initView() {
        super.initView();
        mVideoView = findViewById(R.id.video_view);

        mVideoView.setUrl("http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4");

        mController = new StandardVideoController(this);
        mController.addDefaultControlComponent("pad", false);

        mController.findViewById(R.id.fullscreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoView.isFullScreen()) {
                    mVideoView.stopFullScreen();
                } else {
                    mVideoView.startFullScreen();
                }
            }
        });

        mController.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.stopFullScreen();
            }
        });

        mVideoView.setVideoController(mController);

        mVideoView.start();
    }


    @Override
    public void onBackPressed() {
        if (mController.isLocked()) {
            mController.showInner();
            Toast.makeText(this, R.string.dkplayer_lock_tip, Toast.LENGTH_SHORT).show();
            return;
        }
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
            return;
        }
        finish();
    }
}
