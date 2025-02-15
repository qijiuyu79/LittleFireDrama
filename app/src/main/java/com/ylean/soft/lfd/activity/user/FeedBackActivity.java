package com.ylean.soft.lfd.activity.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.soft.lfd.R;
import com.ylean.soft.lfd.adapter.user.HelpListAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Help;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.ClickTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/2/8.
 */

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        tvTitle.setText("意见反馈");
    }

    @OnClick({R.id.img_bank, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_bank:
                finish();
                break;
            case R.id.tv_confirm:
                String name=etName.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入您想反馈的意见或建议");
                    return;
                }
                DialogUtil.showProgress(this,"反馈中");
                HttpMethod.feedBack(name,handler);
                break;
            default:
                break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                case HandlerConstant.FEEDBACK_SUCCESS:
                    BaseBean baseBean= (BaseBean) msg.obj;
                    if(baseBean==null){
                        break;
                    }
                    if(baseBean.isSussess()){
                        finish();
                    }
                    ToastUtil.showLong(baseBean.getDesc());
                    break;
                case HandlerConstant.REQUST_ERROR:
                    ToastUtil.showLong(msg.obj.toString());
                    break;
                default:
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandler(handler);
    }
}
