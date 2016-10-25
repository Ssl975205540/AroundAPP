package lanou.around.bean;

/**
 * Created by dllo on 16/10/25.
 */

public class HomeBeanHot {
   private String postName;
    private String imageUrl;
    private HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean.GoOperationBean goOperation;
    private String postId;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean.GoOperationBean getGoOperation() {
        return goOperation;
    }

    public void setGoOperation(HomeBean.RespDataBean.ActBannersBean.MiddleBannerBean.BannersBean.GoOperationBean goOperation) {
        this.goOperation = goOperation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private class GoOperation {

        private Params params;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Params getParams() {
            return params;
        }

        public void setParams(Params params) {
            this.params = params;
        }

        private class Params {

            private String goUrl;

            public String getGoUrl() {
                return goUrl;
            }

            public void setGoUrl(String goUrl) {
                this.goUrl = goUrl;
            }
        }
    }
}
