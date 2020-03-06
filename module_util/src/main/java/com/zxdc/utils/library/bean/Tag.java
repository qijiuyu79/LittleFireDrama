package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/2/28.
 */

public class Tag extends BaseBean {

    private List<TagBean> data;

    public List<TagBean> getData() {
        return data;
    }

    public void setData(List<TagBean> data) {
        this.data = data;
    }

    public static class TagBean implements Serializable{

        private AbvertBean banner;
        private String imgurl;
        private String name;
        private int id;
        private List<ListData> serialList;

        public TagBean(){};

        public TagBean(String name){
            this.name=name;
        }

        public AbvertBean getBanner() {
            return banner;
        }

        public void setBanner(AbvertBean banner) {
            this.banner = banner;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ListData> getSerialList() {
            return serialList;
        }

        public void setSerialList(List<ListData> serialList) {
            this.serialList = serialList;
        }
    }


    public static class AbvertBean implements Serializable{
        private int channelId;
        private String content;
        private int id;
        private String imgurl;
        private int jumpType;
        private String linkUrl;
        private int serialId;
        private int type;

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getJumpType() {
            return jumpType;
        }

        public void setJumpType(int jumpType) {
            this.jumpType = jumpType;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public int getSerialId() {
            return serialId;
        }

        public void setSerialId(int serialId) {
            this.serialId = serialId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


    public static class ListData implements Serializable{
        private int id;//剧集id
        private String imgurl;//剧集图片
        private int playCount;//总播放量
        private boolean followUser;//是否已关注用户
        private String userImg;//用户头像
        private int episodeCount;//已更新集数
        private String name;//剧集名称
        private boolean appointment;//是否已预约剧集
        private String channelName;//频道名称
        private String userNickName;
        private String introduction;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public boolean isFollowUser() {
            return followUser;
        }

        public void setFollowUser(boolean followUser) {
            this.followUser = followUser;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public int getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(int episodeCount) {
            this.episodeCount = episodeCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isAppointment() {
            return appointment;
        }

        public void setAppointment(boolean appointment) {
            this.appointment = appointment;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}
