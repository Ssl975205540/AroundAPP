package lanou.around.bean;

/**
 * Created by dllo on 16/10/25.
 */

public class ClassifyTabBean {

    /**
     * respCode : 0
     * respData : {"photoUrl2x":"http://img.58cdn.com.cn/zhuanzhuan/images/iwantBuyTitle3x.png","showName":"发现，生活的精彩","userName":"输入你正在找的用户","inputName":"输入你正在找的宝贝","tapId":"0","photoUrl":"http://img.58cdn.com.cn/zhuanzhuan/images/iwantBuyTitle2x.png"}
     */

    private int respCode;
    /**
     * photoUrl2x : http://img.58cdn.com.cn/zhuanzhuan/images/iwantBuyTitle3x.png
     * showName : 发现，生活的精彩
     * userName : 输入你正在找的用户
     * inputName : 输入你正在找的宝贝
     * tapId : 0
     * photoUrl : http://img.58cdn.com.cn/zhuanzhuan/images/iwantBuyTitle2x.png
     */

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
        private String photoUrl2x;
        private String showName;
        private String userName;
        private String inputName;
        private String tapId;
        private String photoUrl;

        public String getPhotoUrl2x() {
            return photoUrl2x;
        }

        public void setPhotoUrl2x(String photoUrl2x) {
            this.photoUrl2x = photoUrl2x;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getInputName() {
            return inputName;
        }

        public void setInputName(String inputName) {
            this.inputName = inputName;
        }

        public String getTapId() {
            return tapId;
        }

        public void setTapId(String tapId) {
            this.tapId = tapId;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }
    }
}
