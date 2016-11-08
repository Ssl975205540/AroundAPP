package lanou.around.classification.seek;

import java.util.List;

/**
 * Created by dllo on 16/11/8.
 */

public class SeekBean {

    private int respCode;
    private RespDataBean respData;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public RespDataBean getRespData() {
        return respData;
    }

    public void setRespData(RespDataBean respData) {
        this.respData = respData;
    }

    public static class RespDataBean {

        private List<InfosBean> infos;

        public List<InfosBean> getInfos() {
            return infos;
        }

        public void setInfos(List<InfosBean> infos) {
            this.infos = infos;
        }

        public static class InfosBean {
            private long uid;
            private String headImg;
            private String nickName;
            private long infoId;
            private String title;
            private String desc;
            private int price;
            private int originalPrice;
            private long pubTime;
            private String infoImage;
            private int status;
            private String cityName;
            private String businessName;
            private int isNew;
            private String strInfoId;
            private int isCredited;
            private String relationship;
            private String metric;
            private String friendTime;
            private LabelsBean labels;
            private int itemType;
            private List<?> serviceIds;

            public long getUid() {
                return uid;
            }

            public void setUid(long uid) {
                this.uid = uid;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public long getInfoId() {
                return infoId;
            }

            public void setInfoId(long infoId) {
                this.infoId = infoId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(int originalPrice) {
                this.originalPrice = originalPrice;
            }

            public long getPubTime() {
                return pubTime;
            }

            public void setPubTime(long pubTime) {
                this.pubTime = pubTime;
            }

            public String getInfoImage() {
                return infoImage;
            }

            public void setInfoImage(String infoImage) {
                this.infoImage = infoImage;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public int getIsNew() {
                return isNew;
            }

            public void setIsNew(int isNew) {
                this.isNew = isNew;
            }

            public String getStrInfoId() {
                return strInfoId;
            }

            public void setStrInfoId(String strInfoId) {
                this.strInfoId = strInfoId;
            }

            public int getIsCredited() {
                return isCredited;
            }

            public void setIsCredited(int isCredited) {
                this.isCredited = isCredited;
            }

            public String getRelationship() {
                return relationship;
            }

            public void setRelationship(String relationship) {
                this.relationship = relationship;
            }

            public String getMetric() {
                return metric;
            }

            public void setMetric(String metric) {
                this.metric = metric;
            }

            public String getFriendTime() {
                return friendTime;
            }

            public void setFriendTime(String friendTime) {
                this.friendTime = friendTime;
            }

            public LabelsBean getLabels() {
                return labels;
            }

            public void setLabels(LabelsBean labels) {
                this.labels = labels;
            }

            public int getItemType() {
                return itemType;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            public List<?> getServiceIds() {
                return serviceIds;
            }

            public void setServiceIds(List<?> serviceIds) {
                this.serviceIds = serviceIds;
            }

            public static class LabelsBean {
                private List<?> userLabels;
                private List<?> infoLabels;

                public List<?> getUserLabels() {
                    return userLabels;
                }

                public void setUserLabels(List<?> userLabels) {
                    this.userLabels = userLabels;
                }

                public List<?> getInfoLabels() {
                    return infoLabels;
                }

                public void setInfoLabels(List<?> infoLabels) {
                    this.infoLabels = infoLabels;
                }
            }
        }
    }
}
