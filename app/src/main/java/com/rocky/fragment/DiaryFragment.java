package com.rocky.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.rocky.notebook.MainActivity;
import com.rocky.notebook.NewDiaryActivity;
import com.rocky.notebook.R;
import com.rocky.utils.FragmentChangeHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {


    private View view;
    private ImageView iv_rili;
    private ImageView iv_search;
    private ImageView iv_bianji;
    private PullToRefreshListView listview;
    private MainActivity activity;
    private FragmentChangeHelper helper;

    public DiaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = ((MainActivity) context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new FragmentChangeHelper();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_diary, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View view) {
        iv_rili = ((ImageView) view.findViewById(R.id.diary_rili));
        iv_search = ((ImageView) view.findViewById(R.id.diary_search));
        iv_bianji = ((ImageView) view.findViewById(R.id.diary_bianji));
        listview = ((PullToRefreshListView) view.findViewById(R.id.diary_listview));
        iv_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewDiaryActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

}
