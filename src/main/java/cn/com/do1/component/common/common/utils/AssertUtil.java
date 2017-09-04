package cn.com.do1.component.common.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Company (道一信息科技有限公司 DO1.ltd)
 * User: SARON
 * Date: 2007-6-11
 * Time: 15:24:17
 */
public class AssertUtil {
    private transient final static Logger log = LoggerFactory.getLogger(AssertUtil.class);
    private AssertUtil() { }

    /** 判断数组是否为空 */
    public static <T> boolean isEmpty(T[] obj) {
        return null == obj || 0 == obj.length;
    }

    /** 判断字符串是否为空 */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        } else if (obj instanceof Number) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param obj
     *
     * @return
     */
    public static boolean isEmpty(Collection obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 判断Map是否为空
     *
     * @param obj
     *
     * @return
     */
    public static boolean isEmpty(Map obj) {
        return null == obj || obj.isEmpty();
    }

}
