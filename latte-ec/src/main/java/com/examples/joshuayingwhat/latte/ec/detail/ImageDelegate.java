package com.examples.joshuayingwhat.latte.ec.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.joshuayingwhat.latte_ui.ui.recycler.ItemType;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleFields;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

public class ImageDelegate extends LatteDelegate {
    private static final String ARG_PICTURES = "ARG_PICTURES";
    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegat_image;
    }

    public static ImageDelegate create(ArrayList<String> pictures) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ARG_PICTURES, pictures);
        ImageDelegate delegate = new ImageDelegate();
        delegate.setArguments(bundle);
        return delegate;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }

    private void initImages() {
        final ArrayList<String> pictures = getArguments().getStringArrayList(ARG_PICTURES);
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size;
        if (pictures != null) {
            size = pictures.size();
            for (int i = 0; i < size; i++) {
                final String imageUrl = pictures.get(i);
                final MultipleItemEntity entity = MultipleItemEntity
                        .builder()
                        .setItemType(ItemType.SINGLE_BIG_IMAGE)
                        .setField(MultipleFields.IMAGE_URL, imageUrl)
                        .build();
                entities.add(entity);
            }
            final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
