package com.skeleton.android.core.customviews;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

public class AspectRatioVideoView extends VideoView {

    private int mVideoWidth;
    private int mVideoHeight;

    public AspectRatioVideoView(Context context) {
        super(context);
    }

    public AspectRatioVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Sets video URI.
     *
     * @param uri the URI of the video.
     */
    @Override
    public void setVideoURI(Uri uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(getContext(), uri);
        try {
            // Store original video Width
            mVideoWidth = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
            // Store original video height
            mVideoHeight = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
        }catch (Exception e){

        }
        super.setVideoURI(uri);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        if (mVideoWidth > 0 && mVideoHeight > 0) {
            // Scale it...
            float scaleFactor = ScreenUtils.getScaleCropFactor(mVideoWidth, mVideoHeight, width, height);
            width = (int) (scaleFactor * mVideoWidth);
            height = (int) (scaleFactor * mVideoHeight);
            // }
        }
        setMeasuredDimension(width, height);
    }


}