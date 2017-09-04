package cn.com.do1.component.common.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringUtil implements BeanFactoryAware{
	
	private static BeanFactory beanFactory;  
    
    public static Object getBean(String beanName) {  
         return beanFactory.getBean(beanName);  
    }  
      
    public static <T> T getBean(String beanName, Class<T> clazs) {  
         return clazs.cast(getBean(beanName));  
    }  
      
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {  
    	SpringUtil.beanFactory = beanFactory;  
    }
    public static void main(String[] args) {
    	String pol = "4331270002015102800016";
    	String startTime = pol.substring(9,17);
        String str = startTime.substring(0,4)+"/"+startTime.substring(4,6)+"/"+startTime.substring(6,8);
        System.out.println(str);
	}
}
