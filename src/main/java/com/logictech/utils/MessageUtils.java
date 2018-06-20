package com.logictech.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.*;

import static com.logictech.App.logger;

/**
 * @Author xp-zhao@logictech.cn
 * @CreateOn 2016/12/28^ 13:09 in dwSecurities
 * @Remark language工具类
 */
public class MessageUtils {

    // Message 格式化工具
    public final static MessageFormat FORMAT = new MessageFormat("");
    // Message cache 中文
    final static Map<String, String> messageCacheCnMap = new HashMap<>();

    static {
        //Load Business Message
        Locale.setDefault(new Locale("zh","CN"));
        ResourceBundle rb = ResourceBundle.getBundle("message/messages");
        Set<String> keySet = rb.keySet();
        for (String keyStr : keySet) {
            messageCacheCnMap.put(keyStr, rb.getString(keyStr));
        }
    }

    /**
     * @param key message.properties 存在的key
     * @Author xp-zhao@logictech.cn
     * @CreateOn 2016/12/28 13:40
     * @Parameter message.properties 存在的key
     * @Remark 获取il8n下存在的value
     */
    public static String get(String key) {
        return get(key, null);
    }

    /**
     * @param key  message.properties 存在的key
     * @param arg0 填充参数
     * @Author xp-zhao@logictech.cn
     * @CreateOn 2016/12/28 13:40
     * @Remark 获取il8n下存在的携带参数的value
     */
    public static String get(String key, String... arg0) {
        String msgCh = messageCacheCnMap.get(key);
        logger.info("####| 获取配置文件：{}，值：{}", key, msgCh);
        if (StringUtils.isEmpty(msgCh)) {
            return "{}";
        }
        FORMAT.applyPattern(msgCh);
        final String formatCh = FORMAT.format(arg0);
        if (arg0 != null) {
            logger.info("####| 填充数据：{}", formatCh);
        }
        return String.valueOf(JSON.toJSON(formatCh));
    }

}
