package com.logictech.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.logictech.App.logger;

/**
 * CRM系统签名工具类（备用）
 */

public final class SignUtils {

    

    private static final String MD5 = "MD5";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static final String SIGN_KEY = "_sign";
    private static final List<String> SKIP_SIGN_PARAMS = com.google.common.collect.Lists.newArrayList(SIGN_KEY);

    private SignUtils() {
    }

    /**
     * 根据 AppKeyUtils、参数生成签名字符串。
     *
     * @param appKey    AppKeyUtils，与 AppId 对应，接口提供者提供。
     * @param sortedMap 参与签名的参数
     * @return 签名字符串
     */
    public static String sign(String appKey, SortedSetMultimap<String, String> sortedMap) {
        return doSign(appKey, sortedMap).getSignature();
    }

    /**
     * GET / POST FORM 请求签名验证
     *
     * @param appKey  AppKeyUtils，与 AppId 对应，接口提供者提供。
     * @param request {@link HttpServletRequest}
     * @return 签名是否有效
     */
    public static boolean checkSign(String appKey, HttpServletRequest request) {
        return checkSign(appKey, request, null);
    }

    /**
     * POST (JSON, XML etc.) 请求签名验证
     *
     * @param appKey  AppKeyUtils，与 AppId 对应，接口提供者提供。
     * @param request {@link HttpServletRequest}
     * @param body    请求 Body 体
     * @return 签名是否有效
     */
    public static boolean checkSign(String appKey, HttpServletRequest request, String body) {
        String sign = request.getParameter(SIGN_KEY);

        if (sign == null || sign.trim().length() == 0) {
            return false;
        }

        SortedSetMultimap<String, String> sortedMap = TreeMultimap.create();
        Map<String, String[]> map = request.getParameterMap();

        if (map != null && map.size() > 0) {
            map.entrySet().stream()
                .filter(entry -> !SKIP_SIGN_PARAMS.contains(entry.getKey()))
                .forEach(entry -> sortedMap.putAll(entry.getKey(), Arrays.asList(entry.getValue())));
        }

        if (body != null) {
            sortedMap.put("_body", body);
        }

        return checkSign(appKey, sign, sortedMap);
    }

    public static boolean checkSign(String appKey, String signature, SortedSetMultimap<String, String> sortedMap) {
        if (signature == null || signature.trim().length() == 0) {
            return false;
        }

        SignPair signPair = doSign(appKey, sortedMap);
        String expected = signPair.getSignature();

        boolean same = signature.equalsIgnoreCase(expected);

        if (!same) {
        	logger.info("checkSign, expected: [{}], actual: [{}], stringToSign: {}",
                expected,
                signature,
                signPair.getStringToSign());
        }

        return same;
    }

    private static SignPair doSign(String appKey, SortedSetMultimap<String, String> sortedMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(appKey);

        if (sortedMap != null && sortedMap.size() > 0) {
            String params = Joiner.on("")
                .withKeyValueSeparator("")
                .join(sortedMap.entries());

            sb.append(params);
        }

        sb.append(appKey);

        String str = sb.toString();
        logger.info("doSign, stringToSign: {}", str);

        return SignPair.make(md5Hex(str), str);
    }

    // region MD5

    private static String md5Hex(final String data) {
        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance(MD5);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }

        byte[] bytes = digest.digest(getBytesUtf8(data));
        return encodeHexString(bytes, true);
    }

    private static byte[] getBytesUtf8(final String string) {
        if (string == null) {
            return null;
        }
        return string.getBytes(DEFAULT_CHARSET);
    }

    // endregion

    // region Hex

    @SuppressWarnings("SameParameterValue")
    private static String encodeHexString(final byte[] data, final boolean toLowerCase) {
        return new String(encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER));
    }

    private static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }

    // endregion

    // region Class

    static final class SignPair {

        private String signature;
        private String stringToSign;

        private SignPair(String signature, String stringToSign) {
            this.signature = signature;
            this.stringToSign = stringToSign;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getStringToSign() {
            return stringToSign;
        }

        public void setStringToSign(String stringToSign) {
            this.stringToSign = stringToSign;
        }

        public static SignPair make(String signature, String stringToSign) {
            return new SignPair(signature, stringToSign);
        }
    }

    // endregion
}
