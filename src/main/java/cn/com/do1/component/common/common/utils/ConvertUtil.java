package cn.com.do1.component.common.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * Company (道一信息科技有限公司 DO1.ltd)
 * User: SARON
 * Date: 2007-6-11
 * Time: 15:26:58
 */
public class ConvertUtil {
    private transient final static Logger log = LoggerFactory.getLogger(ConvertUtil.class);
    public ConvertUtil() {
    }

    public static BigDecimal cvStBD(String st) {
        BigDecimal rb = null;
        if (st != null) {
            if (st.trim().equals("")) {
                rb = null;
            } else {
                rb = new BigDecimal(st);
            }
        }
        return rb;
    }

    public static String cvBDtS(BigDecimal bd) {
        String rs = null;
        if (bd != null) {
            rs = bd.toString();
        }
        return rs;
    }

    public static java.sql.Date cvStDate(String st) {
        try {
            java.sql.Date rd = null;
            if (st != null) {
                if (st.trim().equals("")) {
                    rd = null;
                } else {
                    st = StringUtil.cutSpace(st);
                    if (st.length() > 10) {
                        st = st.substring(0, 10);
                    }
                    String[] s = StringUtil.splitString(st, st.substring(4, 5));
                    //下面被注释的代码用于判断有无写错月份及其天数
//                    Calendar c= Calendar.getInstance();
//                    c.clear();
//                    c.set(getInt(s[0]),getInt(s[1])-1,1);
//                    if (getInt(s[1]) > 12 || getInt(s[2]) > c.getActualMaximum(Calendar.DAY_OF_MONTH))
//                        return null;
//                    else
                    return new java.sql.Date(Integer.parseInt(s[0]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
                }
            }
            return rd;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static Date cvStUtildate(String st) {
        try {
            int nam = 0;
            Date rd = null;
            String[] timeSt = new String[2];
            if (st != null) {
                if ((st = st.trim()).equals("")) {
                    rd = null;
                } else {
                    if (st.length() <= 10) {
                        return new Timestamp(cvStDate(st).getTime());
                    }
                    timeSt = StringUtil.splitString(st, " ");
                    String[] d = StringUtil.splitString(timeSt[0], timeSt[0].substring(4, 5));
                    if (timeSt[1].length() > 8) {
                        nam = getInt(timeSt[1].substring(timeSt[1].indexOf(".") + 1, timeSt[1].length()));
                        timeSt[1] = timeSt[1].substring(0, timeSt[1].indexOf("."));
                    }
                    String[] s = StringUtil.splitString(timeSt[1], timeSt[1].substring(2, 3));
                    return new Date(Integer.parseInt(d[0]) - 1900, Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2]), Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
                }
            }
            return rd;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }


    public static Timestamp cvStTims(String st) {
        try {
            int nam = 0;
            Timestamp rd = null;
            String[] timeSt = new String[2];
            if (st != null) {
                if ((st = st.trim()).equals("")) {
                    rd = null;
                } else {
                    if (st.length() <= 10) {
                        return new Timestamp(cvStDate(st).getTime());
                    }
                    timeSt = StringUtil.splitString(st, " ");
                    String[] d = StringUtil.splitString(timeSt[0], timeSt[0].substring(4, 5));
                    if (timeSt[1].length() > 8) {
                        nam = getInt(timeSt[1].substring(timeSt[1].indexOf(".") + 1, timeSt[1].length()));
                        timeSt[1] = timeSt[1].substring(0, timeSt[1].indexOf("."));
                    }
                    String[] s = StringUtil.splitString(timeSt[1], timeSt[1].substring(2, 3));
                    return new Timestamp(Integer.parseInt(d[0]) - 1900, Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2]), Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), nam);
                }
            }
            return rd;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static String cvTimstS(Timestamp tims) {
        return tims.toString();
    }

    public static String cvDatetS(java.sql.Date da) {
        String rs = null;
        if (da != null) {
            rs = da.toString();
        }
        return rs;
    }

    public static String cvUtilDatetStr(Date da) {
        String rs = null;
        if (da != null) {
            rs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(da);
        }
        return rs;
    }

    public static Integer cvStIntg(String st) {
        Integer intg = null;
        if (AssertUtil.isEmpty(st)) {
            intg = null;
        } else {
            try {
                intg = new Integer(st);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                intg = null;
            }
        }
        return intg;
    }

    public static String cvItS(Integer intg) {
        String rs = null;
        if (intg != null) {
            rs = intg.toString();
        }
        return rs;
    }


    /**
     * 方法名：  Obj2Form
     * 参数:     Object Obj 值对象
     * Object Form Form对象
     * 返回参数： Object 接收之后应将其转换成相应的Form类型
     * 作用：将一个成员类型不定的OBJ对象转换成一个成员类型皆为String类型的Form
     *
     * @author lianbo_yang
     * @date 2003/4/30
     */
    public static Object Obj2Form(Object Obj, Object Form) throws Exception {
        Field objFields[] = ClassUtil.getFields(Obj.getClass());//objCls.getDeclaredFields();
        Field formFields[] = ClassUtil.getFields(Form.getClass());//formCls.getDeclaredFields();
        for (int i = 0; i < formFields.length; i++) {
            String formFieldName = formFields[i].getName();
            if (!Modifier.isFinal(formFields[i].getModifiers())) {
                for (int j = 0; j < objFields.length; j++) {
                    if (objFields[j].getName().equals(formFieldName)) {
                        Object tempObj = objFields[j].get(Obj);
                        if (!AssertUtil.isEmpty(tempObj)) {
                            formFields[i].set(Form, tempObj.toString());
                        }
                    }
                }
            }
        }
        return Form;
    }

    /**
     * 方法名：  Form2Obj
     * 参数:     Object Obj 值对象
     * Object Form Form对象
     * 返回参数： Object 接收之后应将其转换成相应的Obj类型
     * 作用：将一个成员类型皆为String类型的Form转换成一个成员类型不定的OBJ对象
     * 说明：本方法目前不是很完善，如果Obj的成员不是类，或成员不能经由String类型构造，则本方法无效。（java.sql.Date由于使用率较高
     * 我已经处理过了，在本方法中仍然有效）
     *
     * @author lianbo_yang
     * @date 2003/4/30
     */
    public static Object Form2Obj(Object Obj, Object Form) throws Exception {
        Object value = new Object();
        Field objFields[] = ClassUtil.getFields(Obj.getClass());//objCls.getDeclaredFields();
        Field formFields[] = ClassUtil.getFields(Form.getClass());//formCls.getDeclaredFields();
        for (int i = 0; i < objFields.length; i++) {
            Class objType = objFields[i].getType();
            String objFieldName = objFields[i].getName();
            if (!Modifier.isFinal(objFields[i].getModifiers())) {
                for (int j = 0; j < formFields.length; j++) {
                    if (formFields[j].getName().equals(objFieldName)) {
                        Object tempForm = formFields[j].get(Form);
                        if (!AssertUtil.isEmpty(tempForm)) {
                            Class paraCls[] = {ClassType.stringType};
                            Object paraObj[] = {tempForm.toString()};
                            if (objType.equals(ClassType.dateType)) {
                                value = cvStDate(tempForm.toString());
                            } else {
                                if (objType.equals(ClassType.sqlTimeType)) {
                                    value = cvStTims(tempForm.toString());
                                } else {
                                    Constructor objConst = objType.getConstructor(paraCls);
                                    value = objConst.newInstance(paraObj);
                                }
                            }
                            if (!AssertUtil.isEmpty(value)) {
                                objFields[i].set(Obj, value);
                            }
                        }
                    }
                }
            }
        }
        return Obj;
    }

    /**
     * 获取超类中的数据
     *
     * @param _super
     * @param _obj
     *
     * @return
     * @throws Exception
     */
    public static Object copySuperValue(Object _super, Object _obj) throws Exception {
//    String objCls = _obj.getClass().getName();
        Field superFiled[] = ClassUtil.getFields(_super.getClass());//formCls.getDeclaredFields();
        for (int i = 0; i < superFiled.length; i++) {
            String fieldName = superFiled[i].getName();
            Field tf = null;
            try {
                tf = ClassUtil.getField(_obj.getClass().getSuperclass(), fieldName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object temp = superFiled[i].get(_super);
            if (temp != null && tf != null && !Modifier.isFinal(tf.getModifiers())) {
                tf.set(_obj, temp);
            }
        }
        return _obj;
    }

    /**
     * 同类型值对象转换
     * 注意：转换后的对象要做为第一个参数
     */
    public static Object Obj2Obj(Object Obj, Object Form) throws Exception {
        Object value = new Object();
        Field objFields[] = ClassUtil.getFields(Obj.getClass());//objCls.getDeclaredFields();
        Field formFields[] = ClassUtil.getFields(Form.getClass());//formCls.getDeclaredFields();
        for (int i = 0; i < objFields.length; i++) {
            String objFieldName = objFields[i].getName();
            if (!Modifier.isFinal(objFields[i].getModifiers())) {
                for (int j = 0; j < formFields.length; j++) {
                    if (formFields[j].isAccessible() && formFields[j].getName().equals(objFieldName)) {
                        Object tempForm = formFields[j].get(Form);
                        if (!AssertUtil.isEmpty(tempForm)) {
                            value = tempForm;
                            objFields[i].set(Obj, value);
                        }
                    }
                }
            }
        }
        return Obj;
    }

    /** 比较两个对象及对象内的元素值是否一致 */
    public static boolean objCobj(Object obj_1, Object obj_2) throws Exception {
        int n = 0;
        boolean flag = false;
        if (obj_1 == null) {
            if (obj_2 == null) {
                return true;
            } else {
                return false;
            }
        } else if (obj_2 == null) {
            return false;
        }
        Class obj1 = obj_1.getClass();
        Class obj2 = obj_2.getClass();
        if ((obj1.equals(ClassType.stringType) && obj2.equals(ClassType.stringType))) {
            return obj_1.equals(obj_2);
        }

        Field obj1Fields[] = ClassUtil.getFields(obj1);//obj1.getDeclaredFields();
        Field obj2Fields[] = ClassUtil.getFields(obj2);//obj2.getDeclaredFields();
        if (obj1Fields.length != obj2Fields.length) {
            return false;
        } else if ((obj1Fields.length == 0) && obj1.equals(obj2)) {
            return true;
        }
        for (int i = 0; i < obj1Fields.length; i++) {
//            obj1Fields[i].setAccessible(true);
            Class obj1Type = obj1Fields[i].getType();
            String obj1FieldName = obj1Fields[i].getName();
            for (int j = 0; j < obj2Fields.length; j++) {
//                obj2Fields[j].setAccessible(true);
                Class obj2Type = obj2Fields[j].getType();
                if (obj2Fields[j].getName().equals(obj1FieldName)) {
                    if (!obj1Type.equals(obj2Type)) {
                        return false;
                    }
                    n++;
                    Object tempobj2 = obj2Fields[j].get(obj_2);
                    Object tempobj1 = obj1Fields[i].get(obj_1);
                    if (!AssertUtil.isEmpty(tempobj1)) {
                        if (!AssertUtil.isEmpty(tempobj2)) {
                            if (obj1Type.equals(ClassType.dateType)) {
                                flag = tempobj1.toString().substring(0, 10).equals(tempobj2.toString().substring(0, 10));
                            } else if (obj1Type.equals(ClassType.sqlTimeType)) {
                                flag = tempobj1.toString().substring(0, 19).equals(tempobj2.toString().substring(0, 19));
                            } else {
                                flag = tempobj1.equals(tempobj2);
                            }
                            if (!flag) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        if (AssertUtil.isEmpty(tempobj2)) {
                            flag = true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        if (n != obj1Fields.length) {
            return false;
        }
        return flag;
    }


    /** 将字符转换成字符串 */
    public static String Chr2Str(char chr) {
        char[] chrAry = new char[1];
        chrAry[0] = chr;
        return new String(chrAry);
    }


    public static String Bool2Str(Boolean bl) {
        if (bl == null) {
            return null;
        }
        return bl.toString();
    }

    public static Boolean Str2Bool(String str) {
        if (str == null) {
            return null;
        }
        return Boolean.valueOf(str);
    }

    static public int getInt(String str) {
        if (AssertUtil.isEmpty(str)) {
            return 0;
        }
        String s = "0";
        int i = 0;
        if ((i = str.indexOf(".")) > -1) {
            s = str.substring(i + 1, i + 2);
            str = str.substring(0, i);

        }
        if (new Integer(s) >= 5) {
            return new Integer(str) + 1;
        } else {
            return new Integer(str);
        }
    }

    static public long getLong(String str) {
        if (AssertUtil.isEmpty(str)) {
            return 0;
        }
        String s = "0";
        int i = 0;
        if ((i = str.indexOf(".")) > -1) {
            s = str.substring(i + 1, i + 2);
            str = str.substring(0, i);

        }
        if (new Integer(s) >= 5) {
            return new Long(str) + 1;
        } else {
            return new Long(str).intValue();
        }
    }

    /** 从数字得到字符串 */
    public static String Int2Str(int i) {
        return (Integer.toString(i));
    }

    /** 从长整型得到字符串 */
    public static String Long2Str(long l) {
        return (Long.toString(l));
    }

    /** 将long型转换成int型 */
    public static int long2int(long l) {
        return Integer.parseInt(Long2Str(l));
    }

    public static Float Str2Float(String str) {
        if (AssertUtil.isEmpty(str)) {
            return null;
        }
        return new Float(str);
    }

    public static Double Str2Double(String str) {
        if (AssertUtil.isEmpty(str)) {
            return null;
        }
        return new Double(str);
    }

    public static String Float2Str(Float flt) {
        if (AssertUtil.isEmpty(flt)) {
            return null;
        }
        return flt.toString();
    }

    /**
     * 将out中的所有元素加入in中
     *
     * @param in
     * @param out
     */
    static public <T> List<T> List2List(List<T> in, List<T> out) throws Exception {
        if (in == null) {
            in = out.getClass().newInstance();
        }
        if (out == null) {
            return in;
        }
        in.addAll(out);
        return in;
    }

    /**
     * 替换值
     *
     * @param x
     * @param a
     * @param b
     */
    public static void swap(Object x[], int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    public static void swap(List list, int a, int b) {
        Object t = list.get(a);
        list.set(a, list.get(b));
        list.set(b, t);
    }

    /**
     * 得到RSS日期描述字符串的确切时间值
     *
     * @param _time
     *
     * @return
     * @throws java.text.ParseException
     */
    public static long getRSSTime(String _time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        long ts = 0;
        try {
            ts = sdf.parse(_time).getTime();
        } catch (Exception e) {
//             sdf = new java.text.SimpleDateFormat("yyyy-mm-dd  HH:mm:ss", Locale.US);
            ts = cvStTims(_time).getTime();
        }

        return ts;
    }

    public static Timestamp getRssTimestamp(String _time) throws ParseException {
//        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        return new Timestamp(getRSSTime(_time));
    }

    public static java.sql.Date getRssDate(String _time) throws ParseException {
//        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        return new java.sql.Date(getRSSTime(_time));
    }

    public static Long cvStLong(String s) {
        if (AssertUtil.isEmpty(s)) {
            return 0L;
        }
        return new Long(s);
    }

    public static Byte cvStByte(String str) {
        return new Byte(str);
    }

    public static String cvStream2String(final InputStream is) throws IOException {
        if (null == is) {
            return null;
        }
        BufferedReader reader = null;
        StringBuilder sb = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        if (null == sb) {
            return null;
        }
        return sb.toString();
    }

    public static String cvStream2String(final InputStream is, String charSet) throws IOException {
        if (null == is || StringUtil.isNullEmpty(charSet)) {
            return null;
        }
        BufferedReader reader = null;
        StringBuilder sb = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, charSet));
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        if (null == sb) {
            return null;
        }
        return sb.toString();
    }
}
