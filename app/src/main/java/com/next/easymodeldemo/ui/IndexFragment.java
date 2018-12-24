package com.next.easymodeldemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.next.easymodel.util.EasyModelUtil;
import com.next.easymodeldemo.R;
import com.next.easymodeldemo.adapter.MomentsAdapter;
import com.next.easymodeldemo.base.BaseApplication;
import com.next.easymodeldemo.config.Instance;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.ZoomOutSlideTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexFragment extends Fragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private MomentsAdapter mAdapter;
    private int page = 1;
    private Banner mBanner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.fragment_moment, null);

        ButterKnife.bind(this, mContentView);

        initData();

        return mContentView;
    }

    private void initData() {
        mAdapter = new MomentsAdapter(getActivity());

        Logger.json(Instance.gson.toJson(EasyModelUtil.getMomentList()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.index_header, null);
        mBanner = headView.findViewById(R.id.mBanner);
        mBanner.setImageLoader(new com.next.easymodeldemo.config.ImageLoader.GlideImageLoader());
        mBanner.setImages(EasyModelUtil.getImageList(5));
        mBanner.setBannerAnimation(ZoomOutSlideTransformer.class);
        mBanner.setDelayTime(4000);
        mBanner.start();
        mAdapter.addHeaderView(headView);
        mAdapter.setNewData(EasyModelUtil.getMomentList());
        mAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                refresh();
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                    }
                }, 2000);
            }
        }, mRecyclerView);
    }

    private void loadMore() {
        boolean isRefresh = page == 1;
        setData(isRefresh, EasyModelUtil.getMomentList());
    }

    private void refresh() {
        page = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        setData(true, EasyModelUtil.getMomentList());
        mAdapter.setEnableLoadMore(true);
        //mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setData(boolean isRefresh, List data) {
        page++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < 10) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

}
