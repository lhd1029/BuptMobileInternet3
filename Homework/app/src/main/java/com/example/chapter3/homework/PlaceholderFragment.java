package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.sql.Time;

public class PlaceholderFragment extends Fragment {

    private static final String TAG = "debug3";
    private LottieAnimationView animationView;
    private ListView listView;
    private Toast mToast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animationView = getActivity().findViewById(R.id.animation_view);
        listView = (ListView) getActivity().findViewById(R.id.list_numbers);
        animationView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                animationView.animate()
                        .alpha(0f)
                        .setDuration(2000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                animationView.setVisibility(View.GONE);
                            }
                        });

            }
        }, 5000);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 2s 后执行
                listView.setAdapter(new ListBaseAdapter());
                listView.setDivider(null);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mToast != null) {
                            mToast.cancel();
                        }
                        String toastMessage = "Item #" + position + " clicked.";
                        mToast = Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG);
                        mToast.show();
                    }
                });
                listView.setAlpha(0f);
                listView.setVisibility(View.VISIBLE);
                listView.animate()
                        .alpha(1f)
                        .setDuration(2000)
                        .setListener(null);
            }
        }, 7000);
    }
}
