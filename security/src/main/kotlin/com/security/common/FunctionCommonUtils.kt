package com.security.common

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Function
import java.util.function.Predicate
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.HashMap

@Component
class FunctionCommonUtils {
    @Autowired
    private val env: Environment? = null

    fun milisecondToLocaleDate(millis: Long): LocalDateTime? {
        val instant = Instant.ofEpochMilli(millis)
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    fun localDateToString(date: LocalDateTime, pattern: String?): String? {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return date.format(formatter)
    }

    fun <T> distinctByKey(keyExtractor: Function<in T, *>): Predicate<T>? {
        val seen: MutableSet<Any> = ConcurrentHashMap.newKeySet()
        return Predicate { t: T -> seen.add(keyExtractor.apply(t)) }
    }

    fun stringToDate(date: String?, format: String?): Date? {
        return try {
            SimpleDateFormat(format).parse(date)
        } catch (e: ParseException) {
            null
        }
    }

    fun dateToString(date: Date?, pattern: String?): String? {
        if (date == null) {
            return null
        }
        val format = SimpleDateFormat(pattern)
        return format.format(date)
    }

    @Throws(Exception::class)
    fun getPropertyValue(key: String): String {
        return env!!.getProperty(key) ?: throw Exception("Property $key null")
    }

    @Throws(Exception::class)
    fun getSppSignature(params: String, secretKey: String): Map<String, String>? {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secretKey.toByteArray(), "HmacSHA256"))
        val hash = mac.doFinal(params.toByteArray())
        val signature = Base64.getEncoder().encodeToString(hash)
        val result: MutableMap<String, String> = HashMap()
        result["params"] = params
        result["signature"] = signature
        return result
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