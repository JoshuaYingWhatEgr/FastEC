package com.examples.joshuayingwhat.latte.ec.detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.json.JSONException;

import java.util.ArrayList;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> TAB_TITLES = new ArrayList<>();


    private final ArrayList<ArrayList<String>> PICTURES = new ArrayList<>();

    public TabPagerAdapter(@NonNull FragmentManager fm, JSONObject data) {
        super(fm);
        //一条tabs信息
        final JSONArray tabs = data.getJSONArray("tabs");
        final int size = tabs.size();
        for (int i = 0; i < size; i++) {
            final JSONObject eachTab = tabs.getJSONObject(i);
            final String name = eachTab.getString("name");
            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
            //存储每个图片
            final int pictureSize = pictureUrls.size();
            for (int j = 0; j < pictureSize; j++) {
                eachTabPicturesArray.add(pictureUrls.getString(j));
            }
            TAB_TITLES.add(name);
            PICTURES.add(eachTabPicturesArray);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ImageDelegate.create(PICTURES.get(0));
        } else if (position == 1) {
            return ImageDelegate.create(PICTURES.get(1));
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
