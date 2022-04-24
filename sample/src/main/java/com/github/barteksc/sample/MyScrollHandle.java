package com.github.barteksc.sample;

import android.content.Context;
import android.os.Handler;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;

public class MyScrollHandle implements ScrollHandle {

    protected Context context;
    private PDFView pdfView;
    private Handler handler = new Handler();
    private Runnable hidePageScrollerRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    public MyScrollHandle(Context context) {
        this.context = context;
    }

    @Override
    public void setScroll(float position) {
        if (!shown()) {
            show();
        } else {
            handler.removeCallbacks(hidePageScrollerRunnable);
        }
    }

    @Override
    public void setupLayout(PDFView pdfView) {
        this.pdfView = pdfView;
    }

    @Override
    public void destroyLayout() {

    }

    @Override
    public void setPageNum(int pageNum) {

    }

    @Override
    public boolean shown() {
        return pdfView.showCustomView;
    }

    @Override
    public void show() {
        pdfView.showCustomView = true;
        pdfView.invalidate();
//        handler.postDelayed(hidePageScrollerRunnable, 1000);
    }

    @Override
    public void hide() {
        pdfView.showCustomView = false;
        pdfView.invalidate();
    }

    @Override
    public void hideDelayed() {
        handler.postDelayed(hidePageScrollerRunnable, 1000);
    }
}
