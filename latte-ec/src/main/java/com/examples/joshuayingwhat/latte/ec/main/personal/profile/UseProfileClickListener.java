package com.examples.joshuayingwhat.latte.ec.main.personal.profile;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListBean;
import com.joshuayingwhat.latte_ui.ui.data.DataDialogUtil;

public class UseProfileClickListener extends SimpleClickListener {

    private final LatteDelegate delegate;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    UseProfileClickListener(LatteDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getmId();
        switch (id) {
            case 1:
                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getmDelegate();
                delegate.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DataDialogUtil dialogUtil = new DataDialogUtil();
                dialogUtil.setDateListener(new DataDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dialogUtil.showDialog(delegate.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(delegate.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
