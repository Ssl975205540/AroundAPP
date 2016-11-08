package lanou.around.classification.seek;

import java.util.List;

/**
 * Created by dllo on 16/11/8.
 */

public class SeekSuggestBean {

    /**
     * respCode : 0
     * respData : [{"k":"女装","t":0},{"k":"男装","t":0},{"k":"服装","t":0},{"k":"童装","t":0},{"k":"大码女装","t":0},{"k":"套装","t":0},{"k":"西装","t":0},{"k":"苹果原装配件","t":0},{"k":"女装外套","t":0},{"k":"服装鞋帽","t":0}]
     */

    private int respCode;
    /**
     * k : 女装
     * t : 0
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
        private String k;
        private int t;

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }
    }
}
