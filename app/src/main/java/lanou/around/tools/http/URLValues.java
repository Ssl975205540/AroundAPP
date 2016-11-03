package lanou.around.tools.http;

/**
 * Created by dllo on 16/10/20.
 */

public class URLValues {

    //首页页面
    //你的好友都在这 的网址接口
    public static final String HOME_FIRENE_SELLING = "http://zhuanzhuan.58.com/zz/transfer/getLatestFriendSelling";
    public static final String HOME_FRIEND_CREAME = "http://img.58cdn.com.cn/zhuanzhuan/friends/en1.png";
    public static final String HOME_FRIEND_PAKGE = "http://img.58cdn.com.cn/zhuanzhuan/friends/en2.png";
    //热门市场和轮播图 的网址接口
    public static final String HOME_HOT_MARKET = "http://zhuanzhuan.58.com/zz/transfer/gettopbanner";

    //推荐
    public static final String POST_RECOMMEND = "http://zhuanzhuan.58.com/zz/transfer/getRecommendInfoForIndex";

    public static final String PIN_RECOMMEND = "http://pic8.58cdn.com.cn/zhuanzh/";

    public static final String POSTBODY_RECOMMEND = "pageNum=1&lat=38.883479&lng=121.544684&pageSize=20&";

    public static final String POST_BODY_RECOMMEND = "pageNum=1&lat=38.883479&lng=121.544684&pageSize=20&";

    //附近
    public static final String POST_NEARBY = "http://zhuanzhuan.58.com/zz/transfer/getNearInfoList";

    //首页附近的TabLayout中的标题
    public static final String HOME_NEAR_TAB_MARKET = "http://zhuanzhuan.58.com/zz/transfer/getNearAndVillageCateList";

    //分类页
    //手机,数码等图标 的网址接口
    public static final String CLASSIFY_CHILD_CATES_LOGIC = "http://zhuanzhuan.58.com/zz/transfer/getChildCatesLogic";
    //手机,数码等图标点击之后的二级界面接口
    public static final String POST_CHILD_LOGIC = "http://zhuanzhuan.58.com/zz/transfer/getCateInfoList";
    //手机,数码等图标点击之后的二级界面的请求头
    public static final String REQUEST_HEADER_NAME = "Cookie";
    public static final String REQUEST_HEADER_BODY = "t=15;tk=8F903AB448D41C95C48EF932A3AFFD59;v=2.4.4;channelid=market_908;lat=38.883299;lon=121.544463;model=Redmi 3";
    //手机,数码等图标点击之后的二级界面的请求体
    public static final String REQUEST_BODY_BEFOR = "pagenum=1&lon=121.544463&cateid=";
    public static final String REQUEST_BODY_AFTER = "&sortpolicy=0&lat=38.883299&pagesize=20&";
    //手机,数码,电脑,母婴用品等 网址接口"
    public static final String CLASSIFY_WANT_BUY_MESSAGE = "http://zhuanzhuan.58.com/zz/transfer/getIWantBuyCateMessage";
    //EditText中的图和文字等接口
    public static final String CLASSIFY_EDITTEXT_TITLTE = "http://zhuanzhuan.58.com/zz/transfer/getTitleInformation";


    //手机按钮点击进入中iPhone SE的WebView接口
    public static final String CLASSIFY_PHONE_WEBVIEW = "http://youpin.m.58.com/bj/shouji/27769198399433x.shtml?channel=zz_youpin&webview=zzn&tt=E9E3D9A8EFFEF4A277C9C9D697C50FB21477299349933&zzv2.4.4";
    //

    //视频界面接口
    public static final String URL_VIDEO = "http://m.live.netease.com/bolo/api/rank/hotVideo.htm";
    // 服务协议接口
    public static final String URL_TREATY = "http://m.zhuanzhuan.58.com/Mzhuanzhuan/Mxieyi/xieyi.html";
}
