package lanou.around.classification.search;

import java.util.List;

/**
 * Created by dllo on 16/11/1.
 */

public class BannerImageBean {

    /**
     * respCode : 0
     * respData : [{"bannerImage":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/ypiebiao3x.png","bannerUrl":"http://m.zhuanzhuan.58.com/Mzhuanzhuan/zzYoupin/introduction.html"}]
     */

    private String respCode;
    /**
     * bannerImage : http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/ypiebiao3x.png
     * bannerUrl : http://m.zhuanzhuan.58.com/Mzhuanzhuan/zzYoupin/introduction.html
     */

    private List<RespDataBean> respData;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public List<RespDataBean> getRespData() {
        return respData;
    }

    public void setRespData(List<RespDataBean> respData) {
        this.respData = respData;
    }

    public static class RespDataBean {
        private String bannerImage;
        private String bannerUrl;

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }
    }
}
