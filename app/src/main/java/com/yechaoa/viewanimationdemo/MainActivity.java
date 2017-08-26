package com.yechaoa.viewanimationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private Button mBtnGone, mBtnVisible, mBtnMoveToLeft, mBtnMoveToRight,
            mBtnRotate, mBtnScale, mBtnAlpha, mBtnAnimGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();

    }

    private void initView() {
        mImageView = findViewById(R.id.imageView);

        mBtnGone = findViewById(R.id.btn_gone);
        mBtnVisible = findViewById(R.id.btn_visible);
        mBtnMoveToLeft = findViewById(R.id.btn_move_to_left);
        mBtnMoveToRight = findViewById(R.id.btn_move_to_right);

        mBtnRotate = findViewById(R.id.btn_rotate);
        mBtnScale = findViewById(R.id.btn_Scale);
        mBtnAlpha = findViewById(R.id.btn_alpha);
        mBtnAnimGroup = findViewById(R.id.btn_anim_group);

    }

    private void initListener() {
        mBtnGone.setOnClickListener(this);
        mBtnVisible.setOnClickListener(this);
        mBtnMoveToLeft.setOnClickListener(this);
        mBtnMoveToRight.setOnClickListener(this);

        mBtnRotate.setOnClickListener(this);
        mBtnScale.setOnClickListener(this);
        mBtnAlpha.setOnClickListener(this);
        mBtnAnimGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_gone:
                //位移动画
                //4个数值分别表示坐标轴中以远点为中心的正负X轴Y轴 f表示float
                TranslateAnimation mHiddenAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        -1.0f);
                //设置时长
                mHiddenAnimation.setDuration(500);
                mImageView.startAnimation(mHiddenAnimation);
                //0.0f 加一个渐变的效果
                mImageView.animate().alpha(0);
                mImageView.setVisibility(View.GONE);
                break;
            case R.id.btn_visible:
                TranslateAnimation mShowAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mShowAnimation.setDuration(500);
                mImageView.startAnimation(mShowAnimation);
                mImageView.animate().alpha(1);
                mImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_move_to_left:
                TranslateAnimation mLeftAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mLeftAnimation.setDuration(500);
                mImageView.startAnimation(mLeftAnimation);
                mImageView.animate().alpha(0);
                mImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_move_to_right:
                TranslateAnimation mRightAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mRightAnimation.setDuration(500);
                mImageView.startAnimation(mRightAnimation);
                mImageView.animate().alpha(0);
                mImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_rotate:
                //旋转动画
                RotateAnimation mRotateAnimation = new RotateAnimation(0, 60, 0.5f, 0.5f);
                mRotateAnimation.setDuration(2000);
                mImageView.startAnimation(mRotateAnimation);
                break;
            case R.id.btn_Scale:
                /**
                 * 一共8个参数
                 * 前4个参数是分别是X轴、Y轴的起始位置和结束位置
                 * 后4个参数相对位置值，Animation.RELATIVE_TO_SELF表示自己，也就是以自己为中心点向四周缩放
                 */
                //缩放动画
                ScaleAnimation mScaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                mScaleAnimation.setDuration(2000);
                //设置重复次数
                mScaleAnimation.setRepeatCount(2);
                //动画执行完后是否停留在执行完的状态
                mScaleAnimation.setFillAfter(true);
                //执行前的等待时间
                mScaleAnimation.setStartOffset(500);
                //取消动画
                //mScaleAnimation.cancel();
                mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.d("onAnimationStart", "动画开始");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d("onAnimationEnd", "动画结束");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.d("onAnimationRepeat", "动画重复");
                    }

                });
                mImageView.startAnimation(mScaleAnimation);
                break;
            case R.id.btn_alpha:
                //渐变动画
                AlphaAnimation mAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                mAlphaAnimation.setDuration(2000);
                mAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //淡出之后再淡入
                        //xml的方式引用，参数与代码设置一样
                        Animation aa = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
                        mImageView.startAnimation(aa);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mImageView.startAnimation(mAlphaAnimation);
                break;
            case R.id.btn_anim_group:
                /**
                 * 两种方式可以实现组合动画
                 * 1.给第一个动画设置监听，结束之后执行第二个动画
                 * 2.用AnimationSet，动画集合类
                 */
                RotateAnimation ra = new RotateAnimation(0, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setDuration(2000);

                AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
                aa.setDuration(2000);

                ScaleAnimation sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                sa.setDuration(2000);

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(ra);
                animationSet.addAnimation(aa);
                animationSet.addAnimation(sa);
                //animationSet.setInterpolator(new DecelerateInterpolator(5));
                mImageView.startAnimation(animationSet);
                animationSet.start();
                /**
                 * Interpolator定义动画的速率
                 * AccelerateDecelerateInterpolator延迟减速，在动作执行到中间的时候才执行该特效。
                 * AccelerateInterpolator会使慢慢以(float)的参数降低速度
                 * CycleInterpolator曲线运动特效，要传递float型的参数。
                 * DecelerateInterpolator开头和结尾慢，中间快
                 * LinearInterpolator匀速
                 */
                break;
        }
    }

}
