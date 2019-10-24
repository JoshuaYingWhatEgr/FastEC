package com.examples.joshuayingwhat.latte.ec.main.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.joshuayingwhat.latte.delegates.bottom.BottomItemDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.ec.main.personal.address.AddressDelegate;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListAdapter;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListBean;
import com.examples.joshuayingwhat.latte.ec.main.personal.list.ListItemType;
import com.examples.joshuayingwhat.latte.ec.main.personal.order.OrderListDelegate;
import com.examples.joshuayingwhat.latte.ec.main.personal.profile.UserProfileDelegate;
import com.examples.joshuayingwhat.latte.ec.main.personal.settings.SettingsDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 个人中心页
 *
 * @author joshuayingwhat
 */
public class PersonalDelegate extends BottomItemDelegate {
    @BindView(R2.id.img_user_avatar)
    CircleImageView imgUserAvatar;
    @BindView(R2.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R2.id.tv_all_account_arrow)
    IconTextView tvAllAccountArrow;
    @BindView(R2.id.ll_pay)
    LinearLayout llPay;
    @BindView(R2.id.textView)
    TextView textView;
    @BindView(R2.id.ll_receive)
    LinearLayout llReceive;
    @BindView(R2.id.ll_evaluate)
    LinearLayout llEvaluate;
    @BindView(R2.id.ll_after_market)
    LinearLayout llAfterMarket;
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings;

    public static final String ORDER_TYPE = "order_type";

    private Bundle args = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = new Bundle();
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(args);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //模拟网络请求
        ListBean address = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_NORAL)
                .setmId(1)
                .setmDelegate(new AddressDelegate())
                .setmText("收货地址")
                .build();

        ListBean system = new ListBean.Builder()
                .setmItemType(ListItemType.ITEM_NORAL)
                .setmId(2)
                .setmDelegate(new SettingsDelegate())
                .setmText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();

        data.add(address);
        data.add(system);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }

    @OnClick({R2.id.tv_all_order})
    void allListClick() {
        startOrderListByType();
    }

    /**
     * 头像的点击事件
     * @param view
     */
    @OnClick({R2.id.img_user_avatar})
    void onClickAvatar(View view) {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }


}
