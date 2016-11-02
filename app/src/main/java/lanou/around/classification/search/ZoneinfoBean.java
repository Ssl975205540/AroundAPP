package lanou.around.classification.search;

import java.util.List;

/**
 * Created by dllo on 16/11/1.
 */

public class ZoneinfoBean {


    /**
     * serviceTagColor : #00b75a
     * zoneType : 1
     * serviceTags : ["专业质检","30天质保","顺丰包邮"]
     * zoneUrl : http://img.58cdn.com.cn/zhuanzhuan/zz/service/youpinzone/youpintouxiang@3x.png
     * detailIcon : http://img.58cdn.com.cn/zhuanzhuan/zz/service/righttagicon@2x.png
     * dataTags : [{"title":"全部宝贝","data":1974},{"title":"已售出","data":"25.47万"},{"title":"满意率","data":"97%"}]
     * checkMoreUrl : http://youpin.m.58.com/bj/shouji?channel=zz_list
     * checkMoreText : 查看全部优品手机
     * zoneMoreUrl : http://youpin.m.58.com/?channel=zz_index
     * zoneTitle : 优品自营
     * itemInfos : [{"zoneCateTitle":"iPhone SE","zoneItemType":1,"zoneCateDescription":"优品已售4019台","itemInfoUrl":"http://youpin.m.58.com/bj/iphonese/?&channel=zz_zzlist","itemInfoId":"789748588166283268","tagIconUrl":"http://img.58cdn.com.cn/zhuanzhuan/zz/service/youpinziying-green@2x.png","infoImage":"http://img.58cdn.com.cn/ui7/youpin/img/yp_iphonese.gif","zoneOtherInfo":"<body>最低<font color='#ff5647'>¥<\/font><font color='#ff5647'><big><big><big>2360<\/big><\/big><\/big><\/font>起<\/body>"},{"zoneCateTitle":"iPhone5s","zoneItemType":1,"zoneCateDescription":"优品已售66404台","itemInfoUrl":"http://youpin.m.58.com/bj/iphone5s/?&channel=zz_zzlist","itemInfoId":"793355052877381636","tagIconUrl":"http://img.58cdn.com.cn/zhuanzhuan/zz/service/youpinziying-green@2x.png","infoImage":"http://img.58cdn.com.cn/ui7/youpin/img/yp_iphone5s.gif","zoneOtherInfo":"<body>最低<font color='#ff5647'>¥<\/font><font color='#ff5647'><big><big><big>1100<\/big><\/big><\/big><\/font>起<\/body>"},{"zoneCateTitle":"iPhone6 Plus","zoneItemType":1,"zoneCateDescription":"优品已售39284台","itemInfoUrl":"http://youpin.m.58.com/bj/iphone6plus/?&channel=zz_zzlist","itemInfoId":"792917287136165892","tagIconUrl":"http://img.58cdn.com.cn/zhuanzhuan/zz/service/youpinziying-green@2x.png","infoImage":"http://img.58cdn.com.cn/ui7/youpin/img/yp_iphone6plus.gif","zoneOtherInfo":"<body>最低<font color='#ff5647'>¥<\/font><font color='#ff5647'><big><big><big>2690<\/big><\/big><\/big><\/font>起<\/body>"},{"zoneCateTitle":"iPhone6","zoneItemType":1,"zoneCateDescription":"优品已售58144台","itemInfoUrl":"http://youpin.m.58.com/bj/iphone6/?&channel=zz_zzlist","itemInfoId":"792913279596134404","tagIconUrl":"http://img.58cdn.com.cn/zhuanzhuan/zz/service/youpinziying-green@2x.png","infoImage":"http://img.58cdn.com.cn/ui7/youpin/img/yp_iphone6.gif","zoneOtherInfo":"<body>最低<font color='#ff5647'>¥<\/font><font color='#ff5647'><big><big><big>2260<\/big><\/big><\/big><\/font>起<\/body>"}]
     */

    private String serviceTagColor;
    private int zoneType;
    private String zoneUrl;
    private String detailIcon;
    private String checkMoreUrl;
    private String checkMoreText;
    private String zoneMoreUrl;
    private String zoneTitle;
    private List<String> serviceTags;
    /**
     * title : 全部宝贝
     * data : 1974
     */

    private List<DataTagsBean> dataTags;
    /**
     * zoneCateTitle : iPhone SE
     * zoneItemType : 1
     * zoneCateDescription : 优品已售4019台
     * itemInfoUrl : http://youpin.m.58.com/bj/iphonese/?&channel=zz_zzlist
     * itemInfoId : 789748588166283268
     * tagIconUrl : http://img.58cdn.com.cn/zhuanzhuan/zz/service/youpinziying-green@2x.png
     * infoImage : http://img.58cdn.com.cn/ui7/youpin/img/yp_iphonese.gif
     * zoneOtherInfo : <body>最低<font color='#ff5647'>¥</font><font color='#ff5647'><big><big><big>2360</big></big></big></font>起</body>
     */

    private List<ItemInfosBean> itemInfos;

    public String getServiceTagColor() {
        return serviceTagColor;
    }

    public void setServiceTagColor(String serviceTagColor) {
        this.serviceTagColor = serviceTagColor;
    }

    public int getZoneType() {
        return zoneType;
    }

    public void setZoneType(int zoneType) {
        this.zoneType = zoneType;
    }

    public String getZoneUrl() {
        return zoneUrl;
    }

    public void setZoneUrl(String zoneUrl) {
        this.zoneUrl = zoneUrl;
    }

    public String getDetailIcon() {
        return detailIcon;
    }

    public void setDetailIcon(String detailIcon) {
        this.detailIcon = detailIcon;
    }

    public String getCheckMoreUrl() {
        return checkMoreUrl;
    }

    public void setCheckMoreUrl(String checkMoreUrl) {
        this.checkMoreUrl = checkMoreUrl;
    }

    public String getCheckMoreText() {
        return checkMoreText;
    }

    public void setCheckMoreText(String checkMoreText) {
        this.checkMoreText = checkMoreText;
    }

    public String getZoneMoreUrl() {
        return zoneMoreUrl;
    }

    public void setZoneMoreUrl(String zoneMoreUrl) {
        this.zoneMoreUrl = zoneMoreUrl;
    }

    public String getZoneTitle() {
        return zoneTitle;
    }

    public void setZoneTitle(String zoneTitle) {
        this.zoneTitle = zoneTitle;
    }

    public List<String> getServiceTags() {
        return serviceTags;
    }

    public void setServiceTags(List<String> serviceTags) {
        this.serviceTags = serviceTags;
    }

    public List<DataTagsBean> getDataTags() {
        return dataTags;
    }

    public void setDataTags(List<DataTagsBean> dataTags) {
        this.dataTags = dataTags;
    }

    public List<ItemInfosBean> getItemInfos() {
        return itemInfos;
    }

    public void setItemInfos(List<ItemInfosBean> itemInfos) {
        this.itemInfos = itemInfos;
    }

    public static class DataTagsBean {
        private String title;
        private int data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public static class ItemInfosBean {
        private String zoneCateTitle;
        private int zoneItemType;
        private String zoneCateDescription;
        private String itemInfoUrl;
        private String itemInfoId;
        private String tagIconUrl;
        private String infoImage;
        private String zoneOtherInfo;

        public String getZoneCateTitle() {
            return zoneCateTitle;
        }

        public void setZoneCateTitle(String zoneCateTitle) {
            this.zoneCateTitle = zoneCateTitle;
        }

        public int getZoneItemType() {
            return zoneItemType;
        }

        public void setZoneItemType(int zoneItemType) {
            this.zoneItemType = zoneItemType;
        }

        public String getZoneCateDescription() {
            return zoneCateDescription;
        }

        public void setZoneCateDescription(String zoneCateDescription) {
            this.zoneCateDescription = zoneCateDescription;
        }

        public String getItemInfoUrl() {
            return itemInfoUrl;
        }

        public void setItemInfoUrl(String itemInfoUrl) {
            this.itemInfoUrl = itemInfoUrl;
        }

        public String getItemInfoId() {
            return itemInfoId;
        }

        public void setItemInfoId(String itemInfoId) {
            this.itemInfoId = itemInfoId;
        }

        public String getTagIconUrl() {
            return tagIconUrl;
        }

        public void setTagIconUrl(String tagIconUrl) {
            this.tagIconUrl = tagIconUrl;
        }

        public String getInfoImage() {
            return infoImage;
        }

        public void setInfoImage(String infoImage) {
            this.infoImage = infoImage;
        }

        public String getZoneOtherInfo() {
            return zoneOtherInfo;
        }

        public void setZoneOtherInfo(String zoneOtherInfo) {
            this.zoneOtherInfo = zoneOtherInfo;
        }
    }
}
