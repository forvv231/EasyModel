package com.next.easymodeldemo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.next.easymodel.model.MomentModel;
import com.next.easymodel.util.EasyModelUtil;
import com.next.easymodel.util.EasyUtil;
import com.next.easymodeldemo.R;
import com.next.easymodeldemo.adapter.MomentsAdapter;
import com.next.easymodeldemo.config.Instance;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MomentFragment extends Fragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private MomentsAdapter mAdapter;
    private int page = 1;
    private int maxpage = 3;
    private int limit = 5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.fragment_moment, null);

        ButterKnife.bind(this, mContentView);

        initData();

        return mContentView;
    }

    private void initData() {

        Logger.json(Instance.gson.toJson(EasyModelUtil.getMomentList(5, 5, 5)));

        mAdapter = new MomentsAdapter(getActivity());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getContext())
                        .color(Color.parseColor("#f5f5f5"))
                        .size(EasyUtil.dip2px(getContext(),10))
                        .margin(15)
                        .build());

        mAdapter.setNewData(EasyModelUtil.getMomentList(page, limit, maxpage));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
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
        boolean isRefresh = false;
        setData(isRefresh, EasyModelUtil.getMomentList(page, limit, maxpage));
    }

    private void refresh() {
        page = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        setData(true, EasyModelUtil.getMomentList(page, limit, maxpage));
        mAdapter.setEnableLoadMore(true);
        refreshLayout.finishRefresh();
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
        if (size < limit) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
