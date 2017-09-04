package cn.com.do1.component.common.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Company (道一信息科技有限公司 DO1.ltd)
 * User: SARON
 * Date: 2007-6-11
 * Time: 15:29:24
 */
public class ClassUtil {
    static private Map<String, Field[]> FMP = new HashMap<String, Field[]>();//成员
    static private Map<String, Method[]> MMP = new HashMap<String, Method[]>();//方法
//    static public String ContainerList[] = {"javax.swing.JRootPane", "javax.swing.JPanel", "javax.swing.JScrollPane", "javax.swing.JLayeredPane"};

    private ClassUtil() { }

    static private void parseClass(String ClassName) throws Exception {
        Class cls = Class.forName(ClassName);
        parseClass(cls);
    }

    static private void parseClass(Class cls) throws Exception {
        Field[] field = cls.getDeclaredFields();
        Method[] method = cls.getDeclaredMethods();
        for (int i = 0; i < field.length; i++) {
            field[i].setAccessible(true);
        }
        for (int i = 0; i < method.length; i++) {
            method[i].setAccessible(true);
        }
        FMP.put(cls.getName(), field);
        MMP.put(cls.getName(), method);
    }

    /**
     * 返回类的字段
     *
     * @param ClassName
     * @return Field[]
     * @throws Exception
     */
    static public Field[] getFields(String ClassName) throws Exception {
        if (FMP.get(ClassName) == null) parseClass(ClassName);
        return FMP.get(ClassName);
    }

    /**
     * 返回类的字段名
     *
     * @param ClassName
     * @return String[]
     * @throws Exception
     */
    static public String[] getFieldName(String ClassName) throws Exception {
        if (FMP.get(ClassName) == null) parseClass(ClassName);
        Field field[] = FMP.get(ClassName);
        String[] rs = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            rs[i] = field[i].getName();
        }
        return rs;
    }

    /**
     * 返回类的字段
     *
     * @param cls
     * @return Field[]
     * @throws Exception
     */
    static public Field[] getFields(Class cls) throws Exception {
        if (FMP.get(cls.getName()) == null) parseClass(cls);
        return FMP.get(cls.getName());
    }

    /**
     * 返回类的字段名
     *
     * @param cls
     * @return String[]
     * @throws Exception
     */
    static public String[] getFieldName(Class cls) throws Exception {
        if (FMP.get(cls.getName()) == null) parseClass(cls);
        Field field[] = FMP.get(cls.getName());
        String[] rs = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            rs[i] = field[i].getName();
        }
        return rs;
    }

    /**
     * 返回类的方法
     *
     * @param ClassName
     * @return Method[]
     * @throws Exception
     */
    static public Method[] getMethods(String ClassName) throws Exception {
        if (MMP.get(ClassName) == null) parseClass(ClassName);
        return MMP.get(ClassName);
    }

    /**
     * 返回类的方法名
     *
     * @param ClassName
     * @return String[]
     * @throws Exception
     */
    static public String[] getMethodName(String ClassName) throws Exception {
        if (MMP.get(ClassName) == null) parseClass(ClassName);
        Method[] field = MMP.get(ClassName);
        String[] rs = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            rs[i] = field[i].getName();
        }
        return rs;
    }

    /**
     * 返回类的方法
     *
     * @param cls
     * @return Method[]
     * @throws Exception
     */
    static public Method[] getMethods(Class cls) throws Exception {
        if (MMP.get(cls.getName()) == null) parseClass(cls);
        return MMP.get(cls.getName());
    }

    /**
     * 根据类得到类里方法名的集合
     *
     * @param cls
     * @return String[]
     * @throws Exception
     */
    static public String[] getMethodName(Class cls) throws Exception {
        if (MMP.get(cls.getName()) == null) parseClass(cls);
        Method[] field = MMP.get(cls.getName());
        String[] rs = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            rs[i] = field[i].getName();
        }
        return rs;
    }

    /**
     * 根据类名和字段名得到类里的字段
     *
     * @param ClassName
     * @param FieldName
     * @return Field
     * @throws Exception
     */
    static public Field getField(String ClassName, String FieldName) throws Exception {
        Field[] field = getFields(ClassName);
        for (int i = 0; i < field.length; i++) {
            if (field[i].getName().equals(FieldName)) return field[i];
        }
        return null;
    }

    /**
     * 根据类和字段名得到类里的字段
     *
     * @param cls
     * @param FieldName
     * @return Field
     * @throws Exception
     */
    static public Field getField(Class cls, String FieldName) throws Exception {
        Field[] field = getFields(cls);
        for (int i = 0; i < field.length; i++) {
            if (field[i].getName().equals(FieldName)) return field[i];
        }
        return null;
    }

    /**
     * 根据类和字段名得到类里的字段,
     *
     * @param cls
     * @param fieldName
     * @param searchInSuper 是否在超类中查找
     * @return
     * @throws Exception
     */
    static public Field getField(Class cls, String fieldName, boolean searchInSuper) throws Exception {
        Field beanField = getField(cls, fieldName);
        if (beanField == null && searchInSuper && cls.getSuperclass() != null)
            beanField = getField(cls.getSuperclass(), fieldName);
        return beanField;
    }

    /**
     * 根据类名、方法名、方法参数类型名得到类里的方法
     *
     * @param ClassName
     * @param MethodName
     * @param ParTypes
     * @return Method
     * @throws Exception
     */
    static public Method getMethod(String ClassName, String MethodName, String[] ParTypes) throws Exception {
        Method[] method = getMethods(ClassName);
        boolean find = true;
        for (int i = 0; i < method.length; i++) {
            if (method[i].getName().equals(MethodName)) {
                Class[] parType = method[i].getParameterTypes();
                if (parType.length == ParTypes.length) {
                    for (int j = 0; j < parType.length; j++) {
                        if (parType[j].getName().equals(ParTypes[j]))
                            find = true;
                        else
                            find = false;
                    }
                    if (find)
                        return method[i];
                }
            }
        }
        return null;
    }

    /**
     * 根据类、方法名、方法参数类型名得到类里的方法
     *
     * @param cls
     * @param MethodName
     * @param ParTypes
     * @return Method
     * @throws Exception
     */
    static public Method getMethod(Class cls, String MethodName, String[] ParTypes) throws Exception {
        Method[] method = getMethods(cls);
        boolean find = true;
        for (int i = 0; i < method.length; i++) {
            if (method[i].getName().equals(MethodName)) {
                Class[] parType = method[i].getParameterTypes();
                if (parType.length == ParTypes.length) {
                    for (int j = 0; j < parType.length; j++) {
                        if (parType[j].getName().equals(ParTypes[j]))
                            find = true;
                        else
                            find = false;
                    }
                    if (find)
                        return method[i];
                }
            }
        }
        return null;
    }

    /**
     * 根据类名、方法名、方法参数类得到类里的方法
     *
     * @param ClassName
     * @param MethodName
     * @param ParTypes
     * @return Method
     * @throws Exception
     */
    static public Method getMethod(String ClassName, String MethodName, Class[] ParTypes) throws Exception {
        Method[] method = getMethods(ClassName);
        boolean find = true;
        for (int i = 0; i < method.length; i++) {
            if (method[i].getName().equals(MethodName)) {
                Class[] parType = method[i].getParameterTypes();
                if (parType.length == ParTypes.length) {
                    for (int j = 0; j < parType.length; j++) {
                        if (parType[j].getName().equals(ParTypes[j].getName()))
                            find = true;
                        else
                            find = false;
                    }
                    if (find)
                        return method[i];
                }
            }
        }
        return null;
    }

    /**
     * 根据类、方法名、方法参数类型得到类里方法
     *
     * @param cls
     * @param MethodName
     * @param ParTypes
     * @return Methods
     * @throws Exception
     */
    static public Method getMethod(Class cls, String MethodName, Class[] ParTypes) throws Exception {
        Method[] method = getMethods(cls);
        boolean find = true;
        for (int i = 0; i < method.length; i++) {
            if (method[i].getName().equals(MethodName)) {
                Class[] parType = method[i].getParameterTypes();
                if (parType.length == ParTypes.length) {
                    for (int j = 0; j < parType.length; j++) {
                        if (parType[j].getName().equals(ParTypes[j].getName()))
                            find = true;
                        else
                            find = false;
                    }
                    if (find)
                        return method[i];
                }
            }
        }
        return null;
    }
}

