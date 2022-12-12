package com.multithread.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class FunctionCommonUtils {
    @Autowired
    private Environment env;

    public LocalDateTime milisecondToLocaleDate(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }
    public String localDateToString(LocalDateTime date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    public Date stringToDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    public String dateToString(Date date, String pattern) {
        if(date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    public String getPropertyValue(String key) throws Exception {
        String result = env.getProperty(key);
        if(result == null) {
            throw new Exception("Property " + key + " null");
        }
        return result;
    }
    public Map<String, String> getSppSignature(String params, String secretKey) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"));
        byte[] hash = mac.doFinal(params.getBytes());
        String signature = Base64.getEncoder().encodeToString(hash);
        Map<String, String> result = new HashMap<>();
        result.put("params", params);
        result.put("signature", signature);
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        String params = "{\"amount\":37400000,\"payment_reference_id\":\"NEW202108250000\"}";
//        String secretKey = "1912010b01904df08e47dc6e2907df2f";
//        System.out.println(getSppSignature(params, secretKey));
//    }

//	public static <T> Map<String, String> getSppSignature(T t, String secretKey) throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setSerializationInclusion(Include.NON_NULL);
//		String paramsJson = mapper.writeValueAsString(t);
//		Mac mac = Mac.getInstance("HmacSHA256");
//		mac.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"));
//		byte[] hash = mac.doFinal(paramsJson.getBytes());
//		String signature = Base64.getEncoder().encodeToString(hash);
//		Map<String, String> result = new HashMap<>();
//		result.put("params", paramsJson);
//		result.put("signature", signature);
//		return result;
//	}
}
