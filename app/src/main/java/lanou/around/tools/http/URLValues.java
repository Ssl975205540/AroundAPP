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
    public static final String WELCOME = "http://pic1.58cdn.com.cn/p1/big/n_v1bl2lwtiygervqlrsh4qq_6c7ea76f39c78102.jpg";
    //转转优品
    public static final String HOME_ZHUAN_MOST_THING = "http://youpin.m.58.com/?channel=zz_index&prefectureId=7";
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
    //搜索接口
    public static final String SEARCH = "http://zhuanzhuan.58.com/zz/transfer/search";
    public static final String SEARCH_BODY = "pagenum=1&lon=121.544195&sortpolicy=0&lat=38.883377&pagesize=20&keyword=";
    public static final String SEARCH_HERDER_KEY = "Cookie";
    public static final String SEARCH_HERDER_VALUE = "t=15;tk=ED7454894632EB3106DE5F63CA35708A;v=2.5.1;channelid=market_908;lat=38.883384;lon=121.544266;model=MI 4C;uid=42880272487959;PPU=\"UID=42880272487959&PPK=20b31553&PPT=2998614e&SK=7A8AA9BF524FE0547BF140E06081581FD176D5ACCF821269C&LT=1478564765135&UN=%E9%9D%88%E9%AD%82%E6%B7%B1%E8%99%95%E6%9C%89%E5%80%8B%E5%A5%B9&LV=e5d60885&PBODY=Hj7js0XR0FxTL1SpmIdTgcrk7iBT5Jn6R-DM-CdRX78ymNYKwnLA6xOds6EsTEat0OzBjhUI3aJwWO-I5j8fWthlt4-TZiGM_8XcjIWBnoCbuydvc_UHz9QzypOTKaDopN_d8vnU_qefZ7t2TWNkPmvyd8W18sFTmJsfo7srIe4&VER=1\"; Version=1; Domain=58.com; Path=/;";

    //搜索页面
    public static final String SEARCH_SUGGEST = "http://zhuanzhuan.58.com/zz/transfer/searchSuggest";
    public static final String SEARCH_SUGGEST_BODY = "inputText=";
    //视频界面接口
    public static final String URL_VIDEO = "http://m.live.netease.com/bolo/api/rank/hotVideo.htm";
    // 服务协议接口
    public static final String URL_TREATY = "http://m.zhuanzhuan.58.com/Mzhuanzhuan/Mxieyi/xieyi.html";
}
