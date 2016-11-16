package lanou.around.readphoto.region.listview_Indexes;

import java.util.Comparator;

/**
 * @author xiaanming
 *
 * Comparator<T> 泛型 T 指的是数据类
 */

public class PinyinComparator implements Comparator<RegionBean> {

    public int compare(RegionBean o1, RegionBean o2) {
        if (o1.getFristLetters().equals("@")
                || o2.getFristLetters().equals("#")) {
            return -1;
        } else if (o1.getFristLetters().equals("#")
                || o2.getFristLetters().equals("@")) {
            return 1;
        } else {
            return o1.getFristLetters().compareTo(o2.getFristLetters());
        }
    }

}
