package lanou.around.bean;

/**
 * Created by dllo on 16/10/26.
 */

public class VideoDetailsBean {




    private long userId;
    private String channelName;
    private String avatar;
    private String channelIntro;
    private long videoId;
    private String title;
    private String link;
    private String linkMp4;
    private String cover;
    private String intro;
    private String tag;
    private int duration;
    private int playCount;
    private int playCountReal;
    private boolean hasFavor;
    private long uploadTime;
    private Object setName;
    private Object setNum;
    private boolean newest;
    private Object downloadable;
    private int isLock;
    private int previewDuration;
    private double unlockSeed;
    private Object unlockCount;

    @Override
    public String toString() {
        return "VideoBean{" +
                "avatar='" + avatar + '\'' +
                ", channelName='" + channelName + '\'' +
                ", linkMp4='" + linkMp4 + '\'' +
                ", tag='" + tag + '\'' +
                ", intro='" + intro + '\'' +
                ", channelIntro='" + channelIntro + '\'' +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getChannelIntro() {
        return channelIntro;
    }

    public void setChannelIntro(String channelIntro) {
        this.channelIntro = channelIntro;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkMp4() {
        return linkMp4;
    }

    public void setLinkMp4(String linkMp4) {
        this.linkMp4 = linkMp4;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getPlayCountReal() {
        return playCountReal;
    }

    public void setPlayCountReal(int playCountReal) {
        this.playCountReal = playCountReal;
    }

    public boolean isHasFavor() {
        return hasFavor;
    }

    public void setHasFavor(boolean hasFavor) {
        this.hasFavor = hasFavor;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Object getSetName() {
        return setName;
    }

    public void setSetName(Object setName) {
        this.setName = setName;
    }

    public Object getSetNum() {
        return setNum;
    }

    public void setSetNum(Object setNum) {
        this.setNum = setNum;
    }

    public boolean isNewest() {
        return newest;
    }

    public void setNewest(boolean newest) {
        this.newest = newest;
    }

    public Object getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(Object downloadable) {
        this.downloadable = downloadable;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public int getPreviewDuration() {
        return previewDuration;
    }

    public void setPreviewDuration(int previewDuration) {
        this.previewDuration = previewDuration;
    }

    public double getUnlockSeed() {
        return unlockSeed;
    }

    public void setUnlockSeed(double unlockSeed) {
        this.unlockSeed = unlockSeed;
    }

    public Object getUnlockCount() {
        return unlockCount;
    }

    public void setUnlockCount(Object unlockCount) {
        this.unlockCount = unlockCount;
    }
}
