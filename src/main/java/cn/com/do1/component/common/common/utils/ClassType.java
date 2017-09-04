package cn.com.do1.component.common.common.utils;

/**
 * Created by IntelliJ IDEA.
 * Company (道一信息科技有限公司 DO1.ltd)
 * User: SARON
 * Date: 2007-6-11
 * Time: 15:30:32
 */
public class ClassType {
   final static public Class stringType = String.class;
   final static public Class dateType = java.sql.Date.class;
//   final static public Class sybTimeType = com.sybase.jdbc2.tds.SybTimestamp.class;
   final static public Class booleanType = boolean.class;
   final static public Class ObjectType = Object.class;
   final static public Class sqlTimeType = java.sql.Timestamp.class;
   final static public Class bigDecimalType = java.math.BigDecimal.class;
   final static public Class integerType = Integer.class;
   private ClassType(){ }

}
