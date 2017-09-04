package cn.com.do1.component.common.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * Company (道一信息科技有限公司 DO1.ltd)
 * User: SARON
 * Date: 2007-6-11
 * Time: 15:25:53
 */
public class StringUtil {
    final private static char[] small = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    final private static char[] big = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /** 分割用ident号隔开的字符串 */
    public static String[] splitString(String str, String ident) {
        try {
            if (AssertUtil.isEmpty(str)) {
                return null;
            }
            String[] file = new String[countMatches(str, ident) + 1];
            for (int i = 0; i < file.length; i++) {
                int j = str.indexOf(ident);
                if (j == -1) {
                    file[i] = str;
                } else {
                    file[i] = str.substring(0, j);
                    //str = str.substring(j + 1, str.length());
                    str = subString(str, j + ident.length(), str.length());
                }
            }
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /** 判断字符串在另一字符串中出现的次数 */
    public static int countMatches(String str, String sub) {//sub在str中出现的次数
        if (str == null) {
            return 0;
        }
        int count = 0;
        for (int idx = 0; (idx = str.indexOf(sub, idx)) != -1; idx += sub.length()) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        String[] arr = {"aaa", "bbb", "ccc", "ddd", "eee"};
        String[] arr1 = {"fff", "ggg", "hhh", "iii", "jjj"};
        String str = "我是中国人dsfsflsksdfsdlfslfsldfsf";
        String sub = "aa";
        String[] arr2 = StringUtil.uniteArry(arr, arr1);
        for (int i = 0; i < arr2.length; i++) {
            //System.out.println(arr2[i]);
        }
        String aaa = StringUtil.uniteArry(arr, str);
        //System.out.println(aaa);
        String bbb = StringUtil.subString(str, 0, 3);
        //System.out.println(bbb);
        int a = StringUtil.countMatches(str, sub);
        //System.out.println(""+a);
        String[] ayy = StringUtil.splitString(sub, str);
        for (int i = 0; i < ayy.length; i++) {
            //System.out.println(ayy[i]);
        }
    }

    /** 截取一定长度字符串 */
    static public String subString(String str, int Start, int End) throws Exception {
        if (Start < 0 || End > str.length()) {
            throw new Exception("错误：输入的长度在字符串中不被认可");
        }
        char chrArry[] = str.toCharArray();
        char tempArry[] = new char[End - Start];
        int n = 0;
        for (int i = Start; i < End; i++) {
            tempArry[n] = chrArry[i];
            n++;
        }
        return new String(tempArry);
    }

    public static String[] splitString(String str, char chr) throws Exception {
        char[] chrAry = {chr};
        return splitString(str, new String(chrAry));
    }

    /** 将数组合并成一串，并用chr隔开 */
    public static String uniteArry(String[] ary, char chr) {
        return uniteArry(ary, Chr2Str(chr));
    }

    /** 将数组合并成一串，并用str隔开 */
    public static String uniteArry(String[] ary, String str) {
        if (ary == null || ary.length < 1) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ary.length; i++) {
            if (!AssertUtil.isEmpty(ary[i])) {
                if (i != 0) {
                    sb.append(str);
                }
                sb.append(ary[i]);
            }
        }
        return sb.toString();
    }

    public static String uniteArry(Object[] _obj, String str) {
        if (_obj == null || _obj.length < 1) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < _obj.length; i++) {
            if (!AssertUtil.isEmpty(_obj[i])) {
                if (i != 0) {
                    sb.append(str);
                }
                sb.append(_obj[i].toString());
            }
        }
        return sb.toString();
    }

    /** 将两个数组组合 */
    static public String[] uniteArry(String[] ary1, String[] ary2) {
        if (ary1 == null) {
            return ary2;
        }
        if (ary2 == null) {
            return ary1;
        }
        String rs[] = new String[ary1.length + ary2.length];
        int n = 0;
        for (int i = 0; i < ary1.length; i++) {
            if (ary1[i] != null) {
                rs[n] = ary1[i];
                n++;
            }
        }
        for (int i = 0; i < ary2.length; i++) {
            if (ary2[i] != null) {
                rs[n] = ary2[i];
                n++;
            }
        }
        return rs;
    }

    /** 将字符转换成字符串 */
    public static String Chr2Str(char chr) {
        char[] chrAry = new char[1];
        chrAry[0] = chr;
        return new String(chrAry);
    }

    /** 判断两个字符串是否相等 */
    public static boolean strCstr(String str1, String str2) {
        if (str1 == null || str2 == null) {
            if (str1 == null && str2 == null) {
                return true;
            }
            return false;
        }
        return str1.equals(str2);
    }

    /** 将字符串中所有的空格去掉 */
    public static String cutSpace(String str) {
        if (str == null) {
            return null;
        }
        char[] chr = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chr.length; i++) {
            if (chr[i] != ' ' && chr[i] != '　' && chr[i] != ' ') {
                sb.append(chr[i]);
            }
        }
        return sb.toString();
    }

    /** 从字符集里找到字符的位置 */
    public static int findChar(char[] arry, char chr) {
        for (int i = 0; i < arry.length; i++) {
            if (arry[i] == chr) {
                return i;
            }
        }
        return -1;
    }

    /** 得到大/小写对应的字符 */
    public static char getRefChar(char chr) throws Exception {
        int i = 0;
        if ((i = findChar(small, chr)) > -1) {
            return big[i];
        } else if ((i = findChar(big, chr)) > -1) {
            return small[i];
        }
        return chr;
    }

    /** 将一串字符转化成大写 */
    public static String CnvBigChr(String str) {
        int n = 0;
        char chrArry[] = str.toCharArray();
        for (int i = 0; i < chrArry.length; i++) {
            if ((n = findChar(small, chrArry[i])) > -1) {
                chrArry[i] = big[n];
            }
        }
        return new String(chrArry);
    }

    /** 将一串字符转换成小写 */
    public static String CnvSmallChr(String str) {
        int n = 0;
        char chrArry[] = str.toCharArray();
        for (int i = 0; i < chrArry.length; i++) {
            if ((n = findChar(big, chrArry[i])) > -1) {
                chrArry[i] = small[n];
            }
        }
        return new String(chrArry);
    }

    /**
     * 判断是否是大写
     *
     * @param chr
     *
     * @return
     */
    public static boolean isBigChr(char chr) {
        return (findChar(big, chr) > -1);
    }

    /**
     * 判断是否是小写
     *
     * @param chr
     *
     * @return
     */
    public static boolean isSmallChr(char chr) {
        return (findChar(small, chr) > -1);
    }

    /** 判断一个字符串是否是数组的元素(数组已合并成一个字符串,以chr隔开) */
    public static boolean isInArry(String strArry, String str, char chr) throws Exception {
        return isInContainer(splitString(strArry, chr), str);
    }

    /** 判断一个字符串是否是数组的元素(数组已合并成一个字符串,以chr隔开) */
    public static boolean isInArry(String strArry, String str, String chr) throws Exception {
        return isInContainer(splitString(strArry, chr), str);
    }

    /** 判断元素是否在数组内 */
    public static boolean isInContainer(Object[] objArry, Object obj) throws Exception {
        if (objArry == null || objArry.length < 1) {
            if (obj != null) {
                return false;
            } else {
                return true;
            }
        }
        for (int i = 0; i < objArry.length; i++) {
            if (ConvertUtil.objCobj(objArry[i], obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为：null或者长度是否为：0
     *
     * @param text
     *
     * @return
     * @author 施华 Anders See
     * 生成日期：2008-3-24
     */
    public static boolean isNullEmpty(String text) {
        if (null == text) {
            return true;
        } else if (0 == text.length()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否有内容
     *
     * @param text
     *
     * @return
     * @author 施华 Anders See
     * 生成日期：2008-3-24
     */
    public static boolean hasText(String text) {
        if (null != text) {
            if (0 < text.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断元素在数组中第一次出现的位置
     * 需要注意的是返回的数值比实际数值要小1，比如第一个元素即为要查找的元素，但这时返回的值就不是1而是0
     */
    public static int IndexInArry(Object[] objArry, Object obj) throws Exception {
        if (objArry == null || objArry.length < 1) {
            if (obj != null) {
                return -1;
            } else {
                return -1;
            }
        }
        for (int i = 0; i < objArry.length; i++) {
            if (ConvertUtil.objCobj(objArry[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    /** 判断元素在数组中最后一次出现的位置 */
    public static int LastInArry(Object[] objArry, Object obj) throws Exception {
        if (objArry == null || objArry.length < 1) {
            if (obj != null) {
                return -1;
            } else {
                return -1;
            }
        }
        for (int i = objArry.length - 1; i >= 0; i--) {
            if (ConvertUtil.objCobj(objArry[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    /** 将一个数组转化成Collection */
    @SuppressWarnings("unchecked")
    public static Collection Arry2Coll(Object[] obj) {
        Collection rc = new Vector();
        for (int i = 0; i < obj.length; i++) {
            rc.add(obj[i]);
        }
        return rc;
    }

    /** 将一个字符转化成字符串 */
    public static String char2Str(char chr) {
        char[] chaArr = new char[1];
        chaArr[0] = chr;
        return new String(chaArr);
    }

    /** 将字符串中的一部分或全部替换成指定字符串 */
    static public String replace(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        } else {
            return line;
        }
    }

    /** 将第一个字母替换成大写 */
    static public String bigFirstChar(String str) throws Exception {
        if (AssertUtil.isEmpty(str)) {
            return str;
        }
        return StringUtil.CnvBigChr(str.substring(0, 1)) + str.substring(1, str.length());
    }

    /** 将第一个字母换成小写 */
    static public String smallFirstChar(String str) throws Exception {
        if (AssertUtil.isEmpty(str)) {
            return str;
        }
        return StringUtil.CnvSmallChr(str.substring(0, 1)) + str.substring(1, str.length());
    }

    /** 将原字符串中从index开始的的地方由一串新的字符串替换 */
    static public String replaceAt(String oldString, int index, String newString) throws Exception {
        if (oldString != null && newString != null) {
            char[] oc = oldString.toCharArray();
            char[] nc = newString.toCharArray();
            int j = 0;
            for (int i = 0; i < oc.length; i++) {
                if (i >= index) {
                    if (j < nc.length) {
                        oc[i] = nc[j];
                        j++;
                    }
                }
            }
            return new String(oc);
        } else {
            throw new Exception("错误，不能将空符串做为此方法参数");
        }
    }

    @SuppressWarnings("unchecked")
    static public Iterator Coll2Iter(Collection c) {
        if (!AssertUtil.isEmpty(c)) {
            return c.iterator();
        } else {
            return null;
        }
    }

    /**
     * 将集合类的字符串组成数组
     *
     * @param _list
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    static public String[] getStrings(List _list) {
        String[] rs = new String[_list.size()];
        for (int i = 0; i < _list.size(); i++) {
            rs[i] = (String) _list.get(i);
        }
        return rs;
    }

    /**
     * byte数组转16进制字符串
     *
     * @param b
     *
     * @return
     */
    static public String byteArrayToHexString(byte bytes[]) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(byteToHexString(b));
        }
        return result.toString();
    }

    static public String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HexCode[d1] + HexCode[d2];
    }

    static public String HexCode[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * 把字节数组转换成字符串(UTF-8)
     *
     * @param byteArray
     *
     * @return
     */
    public static String byteArrayToString(byte[] byteArray) {
        if (null == byteArray) {
            return null;
        } else if (0 == byteArray.length) {
            return null;
        }
        try {
            return new String(byteArray, "UTF8");
        } catch (final UnsupportedEncodingException e) {
            return new String(byteArray);
        }
    }

    /**
     * 将字符串转换为Base64
     *
     * @param text
     *
     * @return
     * @author 施华 Anders See
     * 生成日期：2007-10-25
     */
    public static String toBase64Encode(String text) {
        try {
            return byteArrayToString(Base64.encodeBase64(text.getBytes("UTF8")));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 将Base64转换为字符串
     *
     * @param base64
     *
     * @return
     * @author 施华 Anders See
     * 生成日期：2007-10-25
     */
    public static String toBase64Decode(String base64) {
        try {
            return byteArrayToString(Base64.decodeBase64(base64.getBytes("UTF8")));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 将两个ASCII字符合成一个字节；
     * 如："EF"--> 0xEF
     *
     * @param src0 byte
     * @param src1 byte
     *
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0}));
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1}));
        return (byte) (_b0 ^ _b1);
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式
     * 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
     *
     * @param src String
     *
     * @return byte[]
     */
    public static byte[] HexString2Bytes(String src) {
        byte[] ret = new byte[src.length() / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < src.length() / 2; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 将字符串转换为Base64
     * <p/>
     * <p/>
     * 生成日期：2007-10-25
     *
     * @param text
     *
     * @return
     */
    public static String toBase64EncodeDefault(String text) {
        try {
            return byteArrayToString(Base64.encodeBase64(text.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把字节数组转换成字符串(UTF-8)
     *
     * @param byteArray
     *
     * @return
     */
    public static String byteArrayToStringDefault(byte[] byteArray) {
        if (null == byteArray) {
            return null;
        } else if (0 == byteArray.length) {
            return null;
        }
        try {
            return new String(byteArray);
        } catch (final Exception e) {
            return new String(byteArray);
        }
    }

    public static int compareToCurrentDate(Date date) {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String currentDateStr = formatter.format(currentDate);
        String dateStr = formatter.format(date);
        return dateStr.compareTo(currentDateStr);
    }

    public static int compareToCurrentDate(String dateStr, String pattern) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(dateStr);
        return compareToCurrentDate(date);
    }

    public static String getWebPic(String picUrl, String ext) {
        if (StringUtils.isBlank(picUrl)) {
            return picUrl;
        }
        return ImageTools.newImageName(picUrl, ext);
    }

    public static String encode(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(URLEncoder.encode(str, "utf-8"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public static String decode(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public static String getSecretPhone(String phone) {
        if (StringUtils.isNotBlank(phone) && phone.trim().length() == 11) {
            phone = phone.trim();
            return phone.substring(0, 3) + "****" + phone.substring(7);
        }
        return "";
    }


    public static String floatFormat(float f) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(f);
    }

    public static String doubleFormat(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }

    private static int length(String source) {
        if (StringUtil.isNullEmpty(source)) {
            return 0;
        }
        try {
            source = new String(source.getBytes(), "ISO8859_1");
        } catch (java.io.IOException e) {
            source = "";
        }
        return source.length();
    }
    
    public static String substring(String content, int length, String fixString){
    	if(content == null || "".equals(content)){
    		return "";
    	}
    	if(content.length() > length){
    		content = content.substring(0, length) + fixString;
    	}
    	return content;
    }

    public static String substring(String content, int startIdx, int bytesLength, String fixString) {
        int strLength = length(content);
        if (strLength <= 0) {
            return "";
        }
        if (startIdx < 0) {
            startIdx = 0;
        }
        if (bytesLength < 0) {
            bytesLength = 0;
        }
        if (fixString == null) {
            fixString = "...";
        }
        int posEnd = startIdx + bytesLength;
        if (posEnd > strLength) {
            posEnd = strLength;
        }
        byte[] bs = content.getBytes();
        int i = 0;
        for (i = startIdx - 1; i >= 0; i--) {
            if (bs[i] > 0) {
                break;
            }
        }
        if ((startIdx - 1 - i) % 2 == 1) {
            startIdx = startIdx + 1;
        }
        for (i = posEnd - 1; i >= 0; i--) {
            if (bs[i] > 0) {
                break;
            }
        }
        if ((posEnd - 1 - i) % 2 == 1) {
            posEnd = posEnd + 1;
        }
        content = new String(bs, startIdx, posEnd - startIdx);
        bytesLength = length(content) + startIdx;
        if (bytesLength < strLength) {
            content = content + fixString;
        }
        return content;
    }

    /**
     * 判断参数c是否是中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断参数c是否是中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(byte c) {
        return isChinese((char) c);
    }

    public static boolean hasChinese(String str) {
        if (StringUtil.hasText(str)) {
            byte[] bytes = str.getBytes();
            for (byte b : bytes) {
                if (isChinese(b)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 转成html
     * daiyuxiang
     * **/
    public static String toHtmlInput(String str) {
        if (str == null)
            return null;
        String html = new String(str);
        html = replace(html, "&amp;","&");
        html = replace(html,  "&lt;","<");
        html = replace(html,  "&gt;",">");
        html = replace(html,  "&quot;","\"");

        return html;
    }
}

