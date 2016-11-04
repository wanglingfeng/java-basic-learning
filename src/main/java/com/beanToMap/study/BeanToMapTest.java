package com.beanToMap.study;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Lingfeng on 2016/6/23.
 */
public class BeanToMapTest {

    public static void main(String[] args) {

    }

    public static MultiValueMap<String, String> convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        MultiValueMap<String, String> returnMap = new LinkedMultiValueMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    if (result instanceof Date) {
                        returnMap.add(propertyName, String.valueOf(((Date) result).getTime()));
                    }
                    returnMap.add(propertyName, result.toString());
                } else {
                    returnMap.add(propertyName, "");
                }
            }
        }
        return returnMap;
    }
}
