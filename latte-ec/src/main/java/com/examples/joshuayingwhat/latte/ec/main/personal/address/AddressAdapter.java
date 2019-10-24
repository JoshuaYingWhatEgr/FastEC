package com.examples.joshuayingwhat.latte.ec.main.personal.address;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.net.rx.RxRestClient;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleFields;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleItemEntity;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleRecyclerAdapter;
import com.joshuayingwhat.latte_ui.ui.recycler.MultipleViewHolder;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddressAdapter extends MultipleRecyclerAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getFields(MultipleFields.NAME);
                final String phone = entity.getFields(AddressItemFields.PHONE);
                final String address = entity.getFields(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getFields(MultipleFields.TAG);
                final int id = entity.getFields(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RxRestClient.builder()
                                .url("address.php")
                                .params("id", id)
                                .build()
                                .post()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        remove(holder.getLayoutPosition());
                                    }
                                });
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
