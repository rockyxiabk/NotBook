package com.rocky.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rocky.fragment.DiaryFragment;
import com.rocky.fragment.HomeFragment;
import com.rocky.fragment.PassingFragment;
import com.rocky.utils.FragmentChangeHelper;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private FragmentManager manager;
    private FragmentChangeHelper helper;
    private RadioButton main_rb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRadioGroupListener();
        DiaryFragment diaryFragment = new DiaryFragment();
        Bundle bundle = new Bundle();
        helper.setBundle(bundle);
        helper.setTargetFragment(diaryFragment);
        changeFragment(helper);
        main_rb1.setChecked(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            //更新listview
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        }else if (requestCode==200){
            //更新listview
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initRadioGroupListener() {
        helper = new FragmentChangeHelper();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment ft = null;
                Bundle bundle = new Bundle();
                switch (checkedId) {
                    case R.id.main_rb_tab1:
                        ft = new DiaryFragment();
                        helper.setBundle(bundle);
                        helper.setTargetFragment(ft);
                        changeFragment(helper);
                        break;
                    case R.id.main_rb_tab2:
                        ft = new PassingFragment();
                        helper.setBundle(bundle);
                        helper.setTargetFragment(ft);
                        changeFragment(helper);
                        break;
                    case R.id.main_rb_tab3:
                        ft = new HomeFragment();
                        helper.setBundle(bundle);
                        helper.setTargetFragment(ft);
                        changeFragment(helper);
                }
            }
        });
    }

    private void initView() {
        radioGroup = ((RadioGroup) findViewById(R.id.main_radiogroup));
        main_rb1 = ((RadioButton) findViewById(R.id.main_rb_tab1));
    }

    public void changeFragment(FragmentChangeHelper helper) {
        Fragment fragment = helper.getTargetFragment();
        Bundle bundle = helper.getBundle();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        boolean clearBackStack = helper.isClearBackStack();
        String tagFragment = helper.getTagFragment();
        manager = getSupportFragmentManager();
        if (clearBackStack) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (tagFragment != null) {
            transaction.addToBackStack(tagFragment);
        }
        transaction.replace(R.id.main_framelayout, fragment);
        transaction.commit();
    }
}
