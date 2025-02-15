package com.ylean.soft.lfd.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ylean.soft.lfd.R;
import com.ylean.soft.lfd.activity.init.AgreementActivity;
import com.ylean.soft.lfd.activity.init.LoginActivity;
import com.ylean.soft.lfd.activity.init.VerifyMobileActvity;
import com.ylean.soft.lfd.utils.UpdateVersionUtils;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.Constant;
import com.zxdc.utils.library.util.DataCleanManager;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 设置
 * Created by Administrator on 2020/2/8.
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.rel_pwd)
    RelativeLayout relPwd;
    @BindView(R.id.view_pwd)
    View viePwd;
    @BindView(R.id.img_push)
    ImageView imgPush;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    private UserInfo.UserBean userBean;
    //0：微信，1：qq
    private int type;
    private IWXAPI api;
    private UMShareAPI umShareAPI;
    //openId
//    private String openId;
    /**
     * true：设置推送
     * false：不推送
     */
    private boolean isPush=false;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        //展示设置信息
        showBind();
    }


    /**
     * 初始化
     */
    private void initView(){
        //注册eventBus
        EventBus.getDefault().register(this);
        userBean= (UserInfo.UserBean) getIntent().getSerializableExtra("userBean");
        final int isPush=SPUtil.getInstance(this).getInteger(SPUtil.JPUSH);
        if(isPush==0){
            imgPush.setImageDrawable(getResources().getDrawable(R.mipmap.open_news));
        }else{
            imgPush.setImageDrawable(getResources().getDrawable(R.mipmap.close_news));
        }

        tvVersion.setText("V"+Util.getVersionName(this));
    }

    /**
     * 展示设置信息
     */
    private void showBind(){
        tvMobile.setText(SPUtil.getInstance(this).getString(SPUtil.ACCOUNT));
        if(userBean!=null){
            if(userBean.isBindWx()){
                tvWx.setText("已绑定");
            }else{
                tvWx.setText("未绑定");
            }
            if(userBean.isBindQq()){
                tvQq.setText("已绑定");
            }else{
                tvQq.setText("未绑定");
            }
        }
        //如果是第三方登录，就隐藏修改密码
        if(SPUtil.getInstance(this).getBoolean(SPUtil.IS_THREE_LOGIN)){
            relPwd.setVisibility(View.GONE);
            viePwd.setVisibility(View.GONE);
        }
        try {
            tvCache.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.img_bank, R.id.rel_version,R.id.rel_mobile, R.id.rel_wx, R.id.rel_qq, R.id.rel_pwd, R.id.rel_agreement, R.id.rel_privacy, R.id.rel_hezuo,R.id.rel_help, R.id.rel_feedback, R.id.rel_cache, R.id.rel_about, R.id.img_push,R.id.tv_out})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.img_bank:
                finish();
                break;
//            case R.id.rel_mobile:
//                setClass(BingMobileActivity.class);
//                break;
            case R.id.rel_version:
                SPUtil.getInstance(this).removeMessage("today_time");
                UpdateVersionUtils updateVersionUtils=new UpdateVersionUtils();
                //查询最新版本
                updateVersionUtils.getVersion(this,1);
                 break;
            case R.id.rel_wx:
                 type=0;
                 if(userBean.isBindWx()){
                     isBind(1);
                 }else{
                     //注册微信d
                     api = WXAPIFactory.createWXAPI(this, Constant.WX_APPID, true);
                     api.registerApp(Constant.WX_APPID);
                     if (!api.isWXAppInstalled()) {
                         ToastUtil.showLong("请先安装微信客户端!");
                     } else {
                         final SendAuth.Req req = new SendAuth.Req();
                         req.scope = "snsapi_userinfo";
                         req.state = "wechat_sdk_demo_test";
                         api.sendReq(req);
                     }
                 }
                break;
            case R.id.rel_qq:
                type=1;
                if(userBean.isBindQq()){
                    isBind(2);
                }else{
                    umShareAPI = UMShareAPI.get(this);
                    umShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);
                }
                break;
            //修改密码
            case R.id.rel_pwd:
                setClass(VerifyMobileActvity.class);
                break;
            //用户协议
            case R.id.rel_agreement:
                intent.setClass(this,AgreementActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            //隐私协议
            case R.id.rel_privacy:
                intent.setClass(this,AgreementActivity.class);
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            //合作内容
            case R.id.rel_hezuo:
                 intent.setClass(this,AgreementActivity.class);
                 intent.putExtra("type",4);
                 startActivity(intent);
                  break;
            case R.id.rel_help:
                 setClass(HelpListActivity.class);
                break;
            case R.id.rel_feedback:
                setClass(FeedBackActivity.class);
                break;
            case R.id.rel_cache:
                clearCache();
                break;
            case R.id.rel_about:
                setClass(AbountActivity.class);
                break;
            //设置推送
            case R.id.img_push:
                if(isPush){
                    isPush=false;
                    imgPush.setImageDrawable(getResources().getDrawable(R.mipmap.close_news));
                    SPUtil.getInstance(this).addInt(SPUtil.JPUSH,1);
                    //停止推送
                    JPushInterface.stopPush(this);
                }else{
                    isPush=true;
                    imgPush.setImageDrawable(getResources().getDrawable(R.mipmap.open_news));
                    SPUtil.getInstance(this).addInt(SPUtil.JPUSH,0);
                    //恢复推送
                    JPushInterface.resumePush(this);
                }
                break;
            case R.id.tv_out:
                SPUtil.getInstance(this).removeMessage(SPUtil.TOKEN);
                SPUtil.getInstance(activity).removeMessage(SPUtil.WX_OPEN_ID);
                SPUtil.getInstance(activity).removeMessage(SPUtil.QQ_OPEN_ID);
                SPUtil.getInstance(activity).removeMessage(SPUtil.ACCOUNT);
                SPUtil.getInstance(activity).removeMessage(SPUtil.PASSWORD);
                intent.setClass(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }


    private Handler handler=new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            DialogUtil.closeProgress();
            switch (msg.what){
                //绑定/解绑回执
                case HandlerConstant.IS_BIND_SUCCESS:
                      BaseBean baseBean= (BaseBean) msg.obj;
                      if(baseBean==null){
                          break;
                      }
                      if(baseBean.isSussess()){
                          if(type==0){
                              if(userBean.isBindWx()){
                                  userBean.setBindWx(false);
                              }else{
                                  userBean.setBindWx(true);
                              }
                          }else{
                              if(userBean.isBindQq()){
                                  userBean.setBindQq(false);
                              }else{
                                  userBean.setBindQq(true);
                              }
                          }
                          //展示设置信息
                          showBind();
                      }
                      ToastUtil.showLong("操作成功");
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


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType) {
        switch (eventBusType.getStatus()) {
            //微信登录
            case EventStatus.WX_LOGIN:
                String msg = (String) eventBusType.getObject();
                if (TextUtils.isEmpty(msg)) {
                    return;
                }
                String[] str = msg.split(",");
                String openId=str[0];
                //存储openId
                SPUtil.getInstance(activity).addString(SPUtil.WX_OPEN_ID,openId);
                //绑定/解绑第三方信息
                isBind(1);
                break;
            default:
                break;
        }
    }


    /**
     * 监听QQ登录
     */
    private UMAuthListener umAuthListener = new UMAuthListener() {
        public void onStart(SHARE_MEDIA share_media) {
        }
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            String openId = map.get("openid");
            //存储openId
            SPUtil.getInstance(activity).addString(SPUtil.QQ_OPEN_ID,openId);
            //绑定/解绑第三方信息
            isBind(2);
        }
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        }
        public void onCancel(SHARE_MEDIA share_media, int i) {
        }
    };


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        umShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 清理缓存
     */
    private void clearCache(){
        DialogUtil.showProgress(this,"缓存清理中...");
        try {
            DataCleanManager.clearAllCache(this);
            handler.postDelayed(new Runnable() {
                public void run() {
                    DialogUtil.closeProgress();
                    tvCache.setText("0.00K");
                }
            },2000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 绑定/解绑第三方信息
     */
    private void isBind(int type){
        String openId;
        if(type==1){
            openId=SPUtil.getInstance(this).getString(SPUtil.WX_OPEN_ID);
        }else{
           openId=SPUtil.getInstance(this).getString(SPUtil.QQ_OPEN_ID);
        }
        DialogUtil.showProgress(this,"加载中");
        HttpMethod.isBind(String.valueOf(type),openId,handler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandler(handler);
        EventBus.getDefault().unregister(this);
    }
}
