package com.ducnd.chattfunn.common.utils;

import com.ducnd.chattfunn.common.config.time.LocalDateDeserializer;
import com.ducnd.chattfunn.common.config.time.LocalDateSerializer;
import com.ducnd.chattfunn.common.config.time.LocalDateTimeDeserializer;
import com.ducnd.chattfunn.common.config.time.LocalDateTimeSerializer;
import com.ducnd.chattfunn.security.authen.jwt.JwtAuthenticationToken;
import com.ducnd.chattfunn.security.user.UserContext;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    public static MappingJackson2HttpMessageConverter converterJack() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        updateTimeConvertObjectMapper(converter.getObjectMapper());
        return converter;
    }

    public static void updateTimeConvertObjectMapper(ObjectMapper objectMapper) {
        SimpleModule javaTimeModule = new SimpleModule();

        // date
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());

        // time
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
//        javaTimeModule.addDeserializer(Long.class, new LongDeserializer());


        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(javaTimeModule);
    }

    public static int getUserLogin() {
        return ((UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static <T> void stream(List<T> os) {
        for (int i = 0; i < os.size() - 1; i++) {
            for (int j = i + 1; j < os.size(); j++) {
                if ( os.get(i).equals(os.get(j))){
                    os.remove(j);
                    j--;
                }
            }
        }
    }

    public static <T> T findObject(List<T> list, Action1Result<T, Boolean> actionCheck) {
        for (T t : list) {
            if (actionCheck.call(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> List<T> findObjects(List<T> list, Action1Result<T, Boolean> actionCheck) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (actionCheck.call(t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static <T1, T2> List<T1> findObjects(List<T1> list1, List<T2> list2, Compare<T1, T2> compare) {
        List<T1> newList = new ArrayList<>();
        for (T1 t1 : list1) {
            for (T2 t2 : list2) {
                if (compare.compare(t1, t2) == 0) {
                    newList.add(t1);
                    break;
                }
            }
        }
        return newList;
    }

    public static <T> boolean contain(T[] ts, T t, Compare<T, T> compare) {
        for (T t1 : ts) {
            if (compare.compare(t1, t) == 0) {
                return true;
            }
        }
        return false;
    }


}
