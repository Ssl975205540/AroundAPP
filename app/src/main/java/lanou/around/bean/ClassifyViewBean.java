package lanou.around.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 16/10/25.
 */

public class ClassifyViewBean implements Serializable {

    /**
     * respCode : 0
     * respData : [{"cateId":"101","cateName":"手机","label":"全新未用|验货面付|快递包邮|一口价|配件齐全|保修期内|无拆无修|大陆行货","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/shouji3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/shouji2x.png","cateParentId":"0","cateDesc":"iPhone 小米 三星","murl":"","services":[{"serviceId":"1","serviceName":"优品验机服务"}],"cateGrandId":"0"},{"cateId":"102","cateName":"数码","label":"验货面交|一口价|有发票|配件齐全|保修期内|无拆无修","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/shumachanpin3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/shuma2x.png","cateParentId":"0","cateDesc":"相机 音像 移动电源","murl":"http://m.zhuanzhuan.58.com/Mzhuanzhuan/zzvtemplate/index.html?type=zzapp&webview=zzn&zhuanquid=133","cateGrandId":"0"},{"cateId":"107","cateName":"家用电器","label":"验货面交|一口价|有发票|保修期内|无拆无修|门店购入","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/jiayongdianqi3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/jiayongdianqi2x.png","cateParentId":"0","cateDesc":"电视 冰箱 微波炉 热水器","murl":"","cateGrandId":"0"},{"cateId":"105","cateName":"交通工具","label":"验货面交|一口价|有发票|门店购入|无泡水|无事故","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/daibugongju3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/daibugongju2x.png","cateParentId":"0","cateDesc":"自行车 电动车 摩托车","murl":"","cateGrandId":"0"},{"cateId":"109","cateName":"母婴用品","label":"验货面交|一口价|宝宝自用|专柜购入|海淘正品","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/muyinyongpin3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/muyin2x.png","cateParentId":"0","cateDesc":"玩具 婴儿床 早教玩具","murl":"","cateGrandId":"0"},{"cateId":"111","cateName":"服装鞋帽","label":"验货面交|一口价|有吊牌|专柜购入|海淘正品","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/fuzhuangxiemao3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/fuzhuangxiemao2x.png","cateParentId":"0","cateDesc":"女装 男装 箱包 鞋帽","murl":"","cateGrandId":"0"},{"cateId":"108","cateName":"家居家具","label":"全新未用|验货面付|快递包邮|一口价|上门送货|自家使用|打包转让","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/jiajujiaju3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/jiaju2x.png","cateParentId":"0","cateDesc":"沙发 床具 衣柜 家居装饰","murl":"","cateGrandId":"0"},{"cateId":"103","cateName":"电脑","label":"验货面交|一口价|有发票|配件齐全|保修期内|无拆无修","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/diannaopeijian3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/diannao2x.png","cateParentId":"0","cateDesc":"笔记本 平板 台式机 配件","murl":"","cateGrandId":"0"},{"cateId":"115","cateName":"玩具乐器","label":"验货面交|一口价|有发票","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/wanjuyueqi3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/wanjuyueqi2x.png","cateParentId":"0","cateDesc":"益智玩具 吉他 钢琴","murl":"","cateGrandId":"0"},{"cateId":"104","cateName":"办公用品","label":"验货面交|一口价|有发票|配件齐全|打包出售","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/bangongyongju3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/bangongyongpin2x.png","cateParentId":"0","cateDesc":"投影仪 打印机","murl":"","cateGrandId":"0"},{"cateId":"106","cateName":"车载设备","label":"验货面交|一口价|有发票|保修期内|无拆无修|行车必备","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/chezaidianqi3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/chezaidianqi2x.png","cateParentId":"0","cateDesc":"导航仪 倒车雷达 车载电器","murl":"","cateGrandId":"0"},{"cateId":"116","cateName":"票务卡券","label":"验货面交|一口价|全市通用|无需更名|可查余额|长期有效","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/piaowukajuan3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/piaowu2x.png","cateParentId":"0","cateDesc":"演唱会 健身卡 体育赛事","murl":"","cateGrandId":"0"},{"cateId":"113","cateName":"图书音像","label":"验货面交|一口价|正版保障|打包出售","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/tushuyinxiang3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/tushuyinxiang2x.png","cateParentId":"0","cateDesc":"教材 图书 CD","murl":"","cateGrandId":"0"},{"cateId":"118","cateName":"宠物用品","label":"验货面交|一口价|亲测好用","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/chongwuyongpin3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/chongwu2x.png","cateParentId":"0","cateDesc":"宠物栅栏 宠物窝 狗粮","murl":"","cateGrandId":"0"},{"cateId":"110","cateName":"运动户外","label":"验货面交|一口价|有发票|包装完整|专柜购入","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/yundonghuwai3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/huwai2x.png","cateParentId":"0","cateDesc":"帐篷 跑步机 滑轮","murl":"","cateGrandId":"0"},{"cateId":"114","cateName":"珠宝配饰","label":"验货面交|一口价|有发票|专柜验货","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/zhubaoshoubiao3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/zhubaoshoubiao2x.png","cateParentId":"0","cateDesc":"手表 珠宝","murl":"","cateGrandId":"0"},{"cateId":"117","cateName":"艺术古玩","label":"验货面交|一口价|鉴定证书","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/yishuguwan3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/gudong2x.png","cateParentId":"0","cateDesc":"珠宝玉器 古画","murl":"","cateGrandId":"0"},{"cateId":"112","cateName":"美容保健","label":"验货面交|一口价|有发票|专柜购入|海淘正品|亲测好用","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/meirongbaojian3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/meirongbaojian2x.png","cateParentId":"0","cateDesc":"护肤 美妆 洗发护发","murl":"","cateGrandId":"0"},{"cateId":"100","cateName":"其他分类","label":"验货面交|一口价|有发票","cateUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/qita3x.png","cateLogo":"http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/qita2x.png","cateParentId":"0","cateDesc":"其他","murl":"","cateGrandId":"0"}]
     */

    private String respCode;
    /**
     * cateId : 101
     * cateName : 手机
     * label : 全新未用|验货面付|快递包邮|一口价|配件齐全|保修期内|无拆无修|大陆行货
     * cateUrl : http://img.58cdn.com.cn/zhuanzhuan/images/firstcate/shouji3x.png
     * cateLogo : http://img.58cdn.com.cn/zhuanzhuanftp/images/firstcatelie/shouji2x.png
     * cateParentId : 0
     * cateDesc : iPhone 小米 三星
     * murl :
     * services : [{"serviceId":"1","serviceName":"优品验机服务"}]
     * cateGrandId : 0
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
        private String cateId;
        private String cateName;
        private String label;
        private String cateUrl;
        private String cateLogo;
        private String cateParentId;
        private String cateDesc;
        private String murl;
        private String cateGrandId;
        /**
         * serviceId : 1
         * serviceName : 优品验机服务
         */

        private List<ServicesBean> services;

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getCateUrl() {
            return cateUrl;
        }

        public void setCateUrl(String cateUrl) {
            this.cateUrl = cateUrl;
        }

        public String getCateLogo() {
            return cateLogo;
        }

        public void setCateLogo(String cateLogo) {
            this.cateLogo = cateLogo;
        }

        public String getCateParentId() {
            return cateParentId;
        }

        public void setCateParentId(String cateParentId) {
            this.cateParentId = cateParentId;
        }

        public String getCateDesc() {
            return cateDesc;
        }

        public void setCateDesc(String cateDesc) {
            this.cateDesc = cateDesc;
        }

        public String getMurl() {
            return murl;
        }

        public void setMurl(String murl) {
            this.murl = murl;
        }

        public String getCateGrandId() {
            return cateGrandId;
        }

        public void setCateGrandId(String cateGrandId) {
            this.cateGrandId = cateGrandId;
        }

        public List<ServicesBean> getServices() {
            return services;
        }

        public void setServices(List<ServicesBean> services) {
            this.services = services;
        }

        public static class ServicesBean {
            private String serviceId;
            private String serviceName;

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
                this.serviceId = serviceId;
            }

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }
        }
    }
}
