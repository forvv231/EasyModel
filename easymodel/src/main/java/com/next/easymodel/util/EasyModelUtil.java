package com.next.easymodel.util;

import com.next.easymodel.config.Constant;
import com.next.easymodel.model.MomentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.next.easymodel.config.Constant.avatorItems;
import static com.next.easymodel.config.Constant.contentItems;
import static com.next.easymodel.config.Constant.nickItems;

public class EasyModelUtil {

    private static int limit = 10;

    public static List<MomentModel> getMomentList() {
        Random rand = new Random();
        int index = rand.nextInt(limit);

        List<MomentModel> momentModelList = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            MomentModel momentModel = new MomentModel();
            momentModel.setNick(nickItems[rand.nextInt(nickItems.length)]);
            momentModel.setAvator(avatorItems[rand.nextInt(avatorItems.length)]);
            momentModel.setTime("3天前");
            momentModel.setContent(contentItems[(index++) % (contentItems.length)]);
            momentModel.setLikeCount(rand.nextInt(1000) + "");
            momentModel.setCommentCount(rand.nextInt(1000) + "");
            momentModel.setShareCount(rand.nextInt(1000) + "");
            List<String> photoList = new ArrayList<>();
            int count = rand.nextInt(9);
            for (int j = 0; j < count; j++) {
                photoList.add(avatorItems[rand.nextInt(avatorItems.length)]);
            }
            momentModel.setPhotos(photoList);
            momentModelList.add(momentModel);
        }
        return momentModelList;
    }

    public static List<MomentModel> getMomentList(int page, int limit, int maxpage) {
        Random rand = new Random();
        int index = rand.nextInt(limit);

        List<MomentModel> momentModelList = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (i == maxpage) {
                break;
            }
            MomentModel momentModel = new MomentModel();
            momentModel.setNick(nickItems[rand.nextInt(nickItems.length)]);
            momentModel.setAvator(avatorItems[rand.nextInt(avatorItems.length)]);
            momentModel.setTime("3天前");
            momentModel.setContent(contentItems[(index++) % (contentItems.length)]);
            momentModel.setLikeCount(rand.nextInt(1000) + "");
            momentModel.setCommentCount(rand.nextInt(1000) + "");
            momentModel.setShareCount(rand.nextInt(1000) + "");
            List<String> photoList = new ArrayList<>();
            int count = rand.nextInt(9);
            for (int j = 0; j < count; j++) {
                photoList.add(avatorItems[rand.nextInt(avatorItems.length)]);
            }
            momentModel.setPhotos(photoList);
            momentModelList.add(momentModel);
        }
        return momentModelList;
    }


    public static List<MomentModel> getBannerList(int page, int limit, int maxpage) {
        Random rand = new Random();
        int index = rand.nextInt(limit);

        List<MomentModel> momentModelList = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (i == maxpage) {
                break;
            }
            MomentModel momentModel = new MomentModel();
            momentModel.setNick(nickItems[rand.nextInt(nickItems.length)]);
            momentModel.setAvator(avatorItems[rand.nextInt(avatorItems.length)]);
            momentModel.setTime("3天前");
            momentModel.setContent(contentItems[(index++) % (contentItems.length)]);
            momentModel.setLikeCount(rand.nextInt(1000) + "");
            momentModel.setCommentCount(rand.nextInt(1000) + "");
            momentModel.setShareCount(rand.nextInt(1000) + "");
            List<String> photoList = new ArrayList<>();
            int count = rand.nextInt(9);
            for (int j = 0; j < count; j++) {
                photoList.add(avatorItems[rand.nextInt(avatorItems.length)]);
            }
            momentModel.setPhotos(photoList);
            momentModelList.add(momentModel);
        }
        return momentModelList;
    }
}