package com.common.utils.model;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CopyUtils {
    private static final ConcurrentHashMap<String, BeanCopier> CACHE_COPIER_MAP = new ConcurrentHashMap();

    public CopyUtils() {
    }

    public static <T> T copyObject(Object srcObj, T targetObj, Converter converter) {
        if (null == srcObj) {
            return null;
        } else if (null == targetObj) {
            return null;
        } else {
            BeanCopier bc = getBeanCopierInstance(srcObj.getClass(), targetObj.getClass(), converter);
            bc.copy(srcObj, targetObj, converter);
            return targetObj;
        }
    }

    public static <T> T copyObject(Object srcObj, T targetObj) {
        return copyObject(srcObj, targetObj, (Converter)null);
    }

    public static <T> T copyObject(Object srcObj, Class<T> targetElementClass) {
        if (srcObj != null && targetElementClass != null) {
            try {
                T targetObj = targetElementClass.newInstance();
                return copyObject(srcObj, targetObj, (Converter)null);
            } catch (Exception var3) {
                throw new IllegalArgumentException(String.format("Cannot instantiate an object of %s.", targetElementClass));
            }
        } else {
            return null;
        }
    }

    public static <S, T> List<T> copyList(List<S> srcList, Class<T> targetElementClass) {
        List<T> targetList = new ArrayList();
        if (CollectionUtils.isEmpty(srcList)) {
            return targetList;
        } else {
            Iterator var3 = srcList.iterator();

            while(var3.hasNext()) {
                Object src = var3.next();

                try {
                    T target = targetElementClass.newInstance();
                    target = copyObject(src, target);
                    targetList.add(target);
                } catch (Exception var6) {
                    throw new IllegalArgumentException(String.format("Cannot instantiate an object of %s.", targetElementClass));
                }
            }

            return targetList;
        }
    }

    private static <S, T> BeanCopier getBeanCopierInstance(Class<S> sourceClass, Class<T> targetClass, Converter converter) {
        String key = sourceClass.getName() + "#" + targetClass.getName();
        BeanCopier bc = (BeanCopier)CACHE_COPIER_MAP.get(key);
        if (null == bc) {
            bc = BeanCopier.create(sourceClass, targetClass, converter != null);
            CACHE_COPIER_MAP.put(key, bc);
        }

        return bc;
    }

    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List<T> dest = (List)in.readObject();
        return dest;
    }
}
