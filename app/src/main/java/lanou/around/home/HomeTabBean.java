package lanou.around.home;

import java.util.List;

/**
 * Created by dllo on 16/10/29.
 */

public class HomeTabBean {

    /**
     * respCode : 0
     * respData : [{"cateName":"数码控","subName":"手机/电脑/数码","cateId":"101|102|103"},{"cateName":"居家达人","subName":"家具/家居/家电","cateId":"107|108"},{"cateName":"运动男生","subName":"运动/户外","cateId":"110"},{"cateName":"有车一族","subName":"代步工具/车载","cateId":"105|106"},{"cateName":"爱美女生","subName":"美容/鞋服/珠宝","cateId":"111|112|114"},{"cateName":"母婴孩童","subName":"母婴/婴幼玩具","cateId":"109"},{"cateName":"文艺青年","subName":"图书/票务/乐器","cateId":"113|115|116"},{"cateName":"办公文具","subName":"办公用品/文具","cateId":"104"},{"cateName":"杂七杂八","subName":"宠物/古玩/其他","cateId":"117|118|100"}]
     */

    private int respCode;
    /**
     * cateName : 数码控
     * subName : 手机/电脑/数码
     * cateId : 101|102|103
     */

    private List<RespDataBean> respData;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public List<RespDataBean> getRespData() {
        return respData;
    }

    public void setRespData(List<RespDataBean> respData) {
        this.respData = respData;
    }

    public static class RespDataBean {
        private String cateName;
        private String subName;
        private String cateId;

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }
    }
}
