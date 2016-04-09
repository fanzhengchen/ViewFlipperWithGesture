package com.example.administrator.viewflipper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    @Bind(R.id.viewFlipper)
    ViewFlipper viewFlipper;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private Animation inAnimation;
    private Animation outAnimation;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        int[] resId = {
                R.mipmap.image_1,
                R.mipmap.image_2,
                R.mipmap.image_3,
                R.mipmap.image_4
        };
        int n = resId.length;
        for (int i = 0; i < n; ++i) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }

        inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        inAnimation.setDuration(300);
        outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        outAnimation.setDuration(500);

        gestureDetector = new GestureDetector(this, this);


        viewFlipper.setInAnimation(inAnimation);
        viewFlipper.setOutAnimation(outAnimation);
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    return false;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void clickFab() {
        viewFlipper.showPrevious();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float DELTA = 30;
        float delta = e1.getX() - e2.getX();
        if (delta < -DELTA) {
            viewFlipper.showPrevious();
            return true;
        } else if (delta > DELTA) {
            viewFlipper.showNext();
            return true;
        }
        return false;
    }


}
