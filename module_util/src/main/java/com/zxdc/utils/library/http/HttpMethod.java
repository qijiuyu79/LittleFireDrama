package com.zxdc.utils.library.http;

import android.os.Handler;
import android.text.TextUtils;

import com.zxdc.utils.library.bean.Author;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.Comment;
import com.zxdc.utils.library.bean.Focus;
import com.zxdc.utils.library.bean.HotTop;
import com.zxdc.utils.library.bean.Login;
import com.zxdc.utils.library.bean.Project;
import com.zxdc.utils.library.bean.Screen;
import com.zxdc.utils.library.bean.Tag;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.VideoInfo;
import com.zxdc.utils.library.http.base.BaseRequst;
import com.zxdc.utils.library.http.base.Http;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpMethod extends BaseRequst {

    public static String pageSize="10";
    public static int size=10;

    /**
     * 获取站点
     */
//    public static void getSite(String city,final Handler handler) {
//        Http.getRetrofit().create(HttpApi.class).getSite(city).enqueue(new Callback<Site>() {
//            public void onResponse(Call<Site> call, Response<Site> response) {
//                BaseRequst.sendMessage(handler, HandlerConstant.GET_SITE_SUCCESS, response.body());
//            }
//            public void onFailure(Call<Site> call, Throwable t) {
//                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
//            }
//        });
//    }
//
//
//
//
//
    /**
     * 获取短信验证码
     */
    public static void sendCode(String phone,String smstype,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("smstype",smstype);
        Http.getRetrofit().create(HttpApi.class).sendCode(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_CODE_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 注册
     */
    public static void register(String code,String password,String phone,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("code", code);
        map.put("password",password);
        map.put("phone",phone);
        Http.getRetrofit().create(HttpApi.class).register(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.REGISTER_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 验证码登录
     */
    public static void smsLogin(String code,String phone,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("code", code);
        map.put("phone",phone);
        Http.getRetrofit().create(HttpApi.class).smsLogin(map).enqueue(new Callback<Login>() {
            public void onResponse(Call<Login> call, Response<Login> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.SMS_LOGIN_SUCCESS, response.body());
            }
            public void onFailure(Call<Login> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 密码登录
     */
    public static void pwdLogin(String logintype,String password,String phone,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("logintype", logintype);
        map.put("password",password);
        map.put("phone",phone);
        Http.getRetrofit().create(HttpApi.class).pwdLogin(map).enqueue(new Callback<Login>() {
            public void onResponse(Call<Login> call, Response<Login> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.PWD_LOGIN_SUCCESS, response.body());
            }
            public void onFailure(Call<Login> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 修改密码
     */
    public static void editPwd(String code,String password,String phone,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("code", code);
        map.put("password",password);
        map.put("phone",phone);
        Http.getRetrofit().create(HttpApi.class).editPwd(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.EDIT_PWD_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 第三方登录
     */
    public static void threeLogin(String openid,String thirdlogintype,String type,String code,String imgurl,String nickname,String phone,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("openid", openid);
        map.put("thirdlogintype",thirdlogintype);
        map.put("type",type);
        if(!TextUtils.isEmpty(code)){
            map.put("code",code);
        }
        map.put("imgurl",imgurl);
        map.put("nickname",nickname);
        if(!TextUtils.isEmpty(phone)){
            map.put("phone",phone);
        }
        Http.getRetrofit().create(HttpApi.class).threeLogin(map).enqueue(new Callback<Login>() {
            public void onResponse(Call<Login> call, Response<Login> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.PWD_LOGIN_SUCCESS, response.body());
            }
            public void onFailure(Call<Login> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取频道列表
     */
    public static void channel(String querytype,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("querytype",querytype);
        Http.getRetrofit().create(HttpApi.class).channel(map).enqueue(new Callback<Tag>() {
            public void onResponse(Call<Tag> call, Response<Tag> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_CHANNEL_SUCCESS, response.body());
            }
            public void onFailure(Call<Tag> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取用户信息
     */
    public static void getUser(final Handler handler) {
        Map<String,String> map=new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).getUser(map).enqueue(new Callback<UserInfo>() {
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_USER_SUCCESS, response.body());
            }
            public void onFailure(Call<UserInfo> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 修改用户信息
     */
    public static void editUser(String birthday,String imgurl,String introduction,String nickname,String sex,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        if(!TextUtils.isEmpty(birthday)){
            map.put("birthday",birthday);
        }
        if(!TextUtils.isEmpty(imgurl)){
            map.put("imgurl",imgurl);
        }
        if(!TextUtils.isEmpty(introduction)){
            map.put("introduction",introduction);
        }
        if(!TextUtils.isEmpty(nickname)){
            map.put("nickname",nickname);
        }
        if(!TextUtils.isEmpty(sex)){
            map.put("sex",sex);
        }
        Http.getRetrofit().create(HttpApi.class).editUser(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.EDIT_USER_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 上传图片
     * @param file
     */
    public static void upload(List<File> file, final Handler handler){
        Map<String, String> map = new HashMap<>();
        map.put("relationtype","0");
        Http.upLoadFile(HttpConstant.UPLOAD_FILE,"images", file, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                try {
                    final String str=response.body().string();
                    LogUtils.e(str+"__________________");
//                    sendMessage(handler, HandlerConstant.ADD_GOODS_SUCCESS, str);
                }catch (Exception e){
                    e.printStackTrace();
                    sendMessage(handler, HandlerConstant.REQUST_ERROR, null);
                }
            }

            public void onFailure(okhttp3.Call call, IOException e) {
                LogUtils.e(e.getMessage()+"_________");
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, e.getMessage());
            }
        });
    }


    /**
     * 设置感兴趣的频道
     */
    public static void setChannel(String ids,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("ids",ids);
        Http.getRetrofit().create(HttpApi.class).setChannel(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.SET_CHANNEL_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取热播和精选TOP剧集列表
     */
    public static void getHot_Top(String querytype,int pageindex,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("querytype",querytype);
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",pageSize);
        Http.getRetrofit().create(HttpApi.class).getHot_Top(map).enqueue(new Callback<HotTop>() {
            public void onResponse(Call<HotTop> call, Response<HotTop> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<HotTop> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 猜你喜欢
     */
    public static void guessLike(final Handler handler) {
        Map<String,String> map=new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).guessLike(map).enqueue(new Callback<HotTop>() {
            public void onResponse(Call<HotTop> call, Response<HotTop> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_GUESS_LIKE_SUCCESS, response.body());
            }
            public void onFailure(Call<HotTop> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 即将上线
     */
    public static void getOnline(int pageindex,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",pageSize);
        Http.getRetrofit().create(HttpApi.class).getOnline(map).enqueue(new Callback<HotTop>() {
            public void onResponse(Call<HotTop> call, Response<HotTop> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<HotTop> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 频道排序
     */
    public static void sortChannel(String json,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("json",json);
        Http.getRetrofit().create(HttpApi.class).sortChannel(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.SORT_CHANNEL_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 热门作者
     */
    public static void hotAuthor(int pageindex,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",pageSize);
        Http.getRetrofit().create(HttpApi.class).hotAuthor(map).enqueue(new Callback<Author>() {
            public void onResponse(Call<Author> call, Response<Author> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<Author> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取视频详情
     */
    public static void videoInfo(int episodeid,int serialid,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        if(episodeid!=0){
            map.put("episodeid",String.valueOf(episodeid));
        }
        if(serialid!=0){
            map.put("serialid",String.valueOf(serialid));
        }
        Http.getRetrofit().create(HttpApi.class).videoInfo(map).enqueue(new Callback<VideoInfo>() {
            public void onResponse(Call<VideoInfo> call, Response<VideoInfo> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_VIDEO_INFO_SUCCESS, response.body());
            }
            public void onFailure(Call<VideoInfo> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 关注、取消关注
     */
    public static void follow(int relateid,String type,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("relateid",String.valueOf(relateid));
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).follow(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.FOLLOW_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 点赞、取消点赞
     */
    public static void thump(int episodeid,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("episodeid",String.valueOf(episodeid));
        Http.getRetrofit().create(HttpApi.class).thump(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.THUMP_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 发送弹屏
     */
    public static void sendScreen(String content,int episodeid,int seconds,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("content",content);
        map.put("episodeid",String.valueOf(episodeid));
        map.put("seconds",String.valueOf(seconds));
        Http.getRetrofit().create(HttpApi.class).sendScreen(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.SEND_SCREEN_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取弹屏列表
     */
    public static void getScreen(int episodeid,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("episodeid",String.valueOf(episodeid));
        Http.getRetrofit().create(HttpApi.class).getScreen(map).enqueue(new Callback<Screen>() {
            public void onResponse(Call<Screen> call, Response<Screen> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_SCREEN_SUCCESS, response.body());
            }
            public void onFailure(Call<Screen> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 发送评论
     */
    public static void sendComment(String content,int episodeid,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("content",content);
        map.put("episodeid",String.valueOf(episodeid));
        Http.getRetrofit().create(HttpApi.class).sendComment(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.SEND_COMMENT_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取首页banner
     */
    public static void mainBanner(final Handler handler) {
        Map<String,String> map=new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).mainBanner(map).enqueue(new Callback<HotTop>() {
            public void onResponse(Call<HotTop> call, Response<HotTop> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_MAIN_BANNER, response.body());
            }
            public void onFailure(Call<HotTop> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取专题列表
     */
    public static void getProject(int pageindex,int pagesize,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",String.valueOf(pagesize));
        Http.getRetrofit().create(HttpApi.class).getProject(map).enqueue(new Callback<Project>() {
            public void onResponse(Call<Project> call, Response<Project> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<Project> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取专题列表
     */
    public static void getProjectList(int topicid,int pageindex,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("topicid",String.valueOf(topicid));
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",pageSize);
        Http.getRetrofit().create(HttpApi.class).getProjectList(map).enqueue(new Callback<HotTop>() {
            public void onResponse(Call<HotTop> call, Response<HotTop> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<HotTop> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 剧集列表
     */
    public static void serialList(int channelid,String name,int pageindex,int userid,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        if(channelid!=0){
            map.put("channelid",String.valueOf(channelid));
        }
        if(!TextUtils.isEmpty(name)){
            map.put("name",name);
        }
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",pageSize);
        if(userid!=0){
            map.put("userid",String.valueOf(userid));
        }
        Http.getRetrofit().create(HttpApi.class).serialList(map).enqueue(new Callback<HotTop>() {
            public void onResponse(Call<HotTop> call, Response<HotTop> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<HotTop> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 发现视频
     */
    public static void foundVideo(String clientid,int episodeid,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        if(!TextUtils.isEmpty(clientid)){
            map.put("clientid",clientid);
        }
        if(episodeid!=0){
            map.put("episodeid",String.valueOf(episodeid));
        }
        Http.getRetrofit().create(HttpApi.class).foundVideo(map).enqueue(new Callback<VideoInfo>() {
            public void onResponse(Call<VideoInfo> call, Response<VideoInfo> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.FOUND_VIDEO_SUCCESS, response.body());
            }
            public void onFailure(Call<VideoInfo> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 获取评论列表
     */
    public static void getComment(int episodeid,int pageindex,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("episodeid",String.valueOf(episodeid));
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize","20");
        Http.getRetrofit().create(HttpApi.class).getComment(map).enqueue(new Callback<Comment>() {
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.GET_COMMENT_SUCCESS, response.body());
            }
            public void onFailure(Call<Comment> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 回复评论
     */
    public static void reply(String content,int pid,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("content",content);
        map.put("pid",String.valueOf(pid));
        Http.getRetrofit().create(HttpApi.class).reply(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseRequst.sendMessage(handler, HandlerConstant.REPLY_SUCCESS, response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 关注的用户
     */
    public static void focusUser(int pageindex,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",pageSize);
        Http.getRetrofit().create(HttpApi.class).focusUser(map).enqueue(new Callback<Focus>() {
            public void onResponse(Call<Focus> call, Response<Focus> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<Focus> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }


    /**
     * 关注的剧集
     */
    public static void focusSerial(int pageindex,final int index,final Handler handler) {
        Map<String,String> map=new HashMap<>();
        map.put("pageindex",String.valueOf(pageindex));
        map.put("pagesize",pageSize);
        Http.getRetrofit().create(HttpApi.class).focusSerial(map).enqueue(new Callback<HotTop>() {
            public void onResponse(Call<HotTop> call, Response<HotTop> response) {
                BaseRequst.sendMessage(handler, index, response.body());
            }
            public void onFailure(Call<HotTop> call, Throwable t) {
                BaseRequst.sendMessage(handler, HandlerConstant.REQUST_ERROR, t.getMessage());
            }
        });
    }
}
