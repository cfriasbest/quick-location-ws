package com.quick.location.service.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class MapperUtil {

    static DozerBeanMapper mapper = new DozerBeanMapper();

    private MapperUtil() {
        throw new IllegalAccessError("MapperUtil class");
    }

    public static <T, U> List<U> mapAsList(final List<T> source, final Class<U> destType) {

        final List<U> dest = new ArrayList<>();

        for (T element : source) {
            dest.add(mapper.map(element, destType));
        }

        return dest;
    }

    public static <T, U> U mapBean(final T source, final Class<U> destType) {

        return mapper.map(source, destType);
    }

}
