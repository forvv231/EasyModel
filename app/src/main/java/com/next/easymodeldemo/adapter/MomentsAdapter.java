package com.next.easymodeldemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.next.easymodel.model.MomentModel;
import com.next.easymodel.util.EasyUtil;
import com.next.easymodeldemo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/12.
 */

public class MomentsAdapter extends BaseQuickAdapter<MomentModel, BaseViewHolder> {


    private Context mContext;

    private int size = 3;

    private float padding = 3;

    public MomentsAdapter(Context context) {
        super(R.layout.item_moments, new ArrayList<MomentModel>());
        mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MomentModel item) {

        ViewGroup photoViewGroup = holder.getView(R.id.viewgroup_image_ll);

        Glide.with(mContext).load(item.getAvator()).into((ImageView) holder.getView(R.id.item_head));
        holder.setText(R.id.item_nick, item.getNick());
        holder.setText(R.id.item_date, item.getTime());
        holder.setText(R.id.expand_text_view, item.getContent());

        photoViewGroup.removeAllViews();
        if (item.getPhotos().size() == 1) {
            final ImageView imageview = new ImageView(mContext);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.width = EasyUtil.dip2px(mContext, 180);
            params.height = params.width;
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setLayoutParams(params);
            Glide.with(mContext).load( item.getPhotos().get(0)).into( imageview);
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  Intent intent = new Intent(mContext, LookBigPicActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(LookBigPicActivity.PICDATALIST, (Serializable) setupCoords(imageview, (ArrayList<String>) item.getImage_arr(), 0));
                    intent.putExtras(bundle);
                    intent.putExtra(LookBigPicActivity.CURRENTITEM, 0);
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(0, 0);*/
                }
            });
            photoViewGroup.addView(imageview);
        } else {
            //holder.note_image_rv.setAdapter(new TravelNoteImageAdapter(context, imageList));
            int count = item.getPhotos().size();
            int row = ((count - 1) / 3) + 1;
            int column;
            for (int i = 0; i < row; i++) {
                LinearLayout liear = new LinearLayout(mContext);
                if (count > 3) {
                    column = 3;
                } else {
                    column = count;
                }
                for (int j = 0; j < column; j++) {
                    final ImageView imageview = new ImageView(mContext);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    params.width = (int) ((EasyUtil.getScreenWidth(mContext) - EasyUtil.dip2px(mContext, (padding+15) * 2)) / 3);
                    params.height = params.width;
                    if (j != 3)
                        params.rightMargin = EasyUtil.dip2px(mContext, padding);
                    if (i != row - 1 && row != 0)
                        params.bottomMargin = EasyUtil.dip2px(mContext, padding);
                    imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageview.setLayoutParams(params);
                    Glide.with(mContext).load(item.getPhotos().get(i * 3 + j)).into(imageview);
                    liear.addView(imageview);
                }
                count = count - 3;
                photoViewGroup.addView(liear);
            }
        }
    }


}
