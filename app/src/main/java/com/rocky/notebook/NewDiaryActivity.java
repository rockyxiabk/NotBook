package com.rocky.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class NewDiaryActivity extends AppCompatActivity {

    private ImageView iv_back;
    private ImageView iv_ok;
    private EditText et_body;
    private ImageView iv_Aa;
    private ImageView iv_beijing;
    private ImageView iv_xiangce;
    private ImageView iv_video;
    private ImageView iv_jianpan;
    private View view_popupwindow_Aa;
    private PopupWindow popupWindow_Aa;
    private LinearLayout aa_ll;
    private Animation animation;
    private RelativeLayout aa_re;
    private ImageView aa_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary);
        initView();
    }

    private void initView() {
        iv_back = ((ImageView) findViewById(R.id.newdiary_iv_back));
        iv_ok = ((ImageView) findViewById(R.id.newdiary_iv_ok));
        et_body = ((EditText) findViewById(R.id.newdiary_et_body));
        et_body.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        et_body.setGravity(Gravity.TOP);
        et_body.setSingleLine(false);
        et_body.setHorizontallyScrolling(false);
        et_body.clearFocus();
        iv_Aa = ((ImageView) findViewById(R.id.newdiary_iv_aa));
        iv_beijing = ((ImageView) findViewById(R.id.newdiary_iv_beijing));
        iv_xiangce = ((ImageView) findViewById(R.id.newdiary_iv_xiangce));
        iv_video = ((ImageView) findViewById(R.id.newdiary_iv_video));
        iv_jianpan = ((ImageView) findViewById(R.id.newdiary_iv_jianpan));
        //弹出菜单Aa选择
        view_popupwindow_Aa = getLayoutInflater().inflate(R.layout.popupwindow_aa, null);
        aa_ll = ((LinearLayout) view_popupwindow_Aa.findViewById(R.id.popup_aa_ll));
        aa_iv = ((ImageView) view_popupwindow_Aa.findViewById(R.id.popup_aa_iv));
        popupWindow_Aa = new PopupWindow(view_popupwindow_Aa, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //上滑动画
        animation = AnimationUtils.loadAnimation(this, R.anim.activity_translate_in);
        initListener();
    }

    private void initListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewDiaryActivity.this, MainActivity.class);
                NewDiaryActivity.this.setResult(100, intent);
                finish();
            }
        });
        iv_jianpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_body.setFocusable(true);
                et_body.setFocusableInTouchMode(true);
                et_body.requestFocus();
                et_body.findFocus();
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(et_body, 0);
            }
        });
        iv_Aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow_Aa.isShowing()) {
                    popupWindow_Aa.dismiss();
                } else {
                    aa_ll.setAnimation(animation);
                    popupWindow_Aa.showAtLocation(view_popupwindow_Aa, Gravity.BOTTOM, 0, 0);
                }
            }
        });
        aa_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow_Aa.isShowing()) {
                    popupWindow_Aa.dismiss();
                }
            }
        });
    }
}
