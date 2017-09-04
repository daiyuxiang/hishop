package cn.com.do1.component.common.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD) 
public @interface TitleAnnotation {

	//属性对应列名，如果没有设置Annotation属性，将不会被导出和导入  
    public String titleName(); 
}
