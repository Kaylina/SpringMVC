package com.elixir.common.until;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
* @ClassName: ReadPropertiesUtil
* @Description:
* @author Jingyan
* @date 2016年3月21日 下午5:50:35
*/ 
public class ReadPropertiesUtil {
	
	private static String propFilePath = "";
    private static Map<String, Object> configMap = new HashMap<String, Object>();
    
    public static void loadConfig(String propFilePath) throws Exception {
    	if(PubMethod.isEmpty(configMap) || !configMap.containsKey(propFilePath)){
            Properties config = new Properties();
            config.load(ReadPropertiesUtil.class.getResourceAsStream(propFilePath));
            configMap.put(propFilePath, config);
    	}
    }
    
    /**
     * 通过配置文件key值，获取对应配置值
     * @param key
     * @return
     * @throws Exception
     */
    public static String getValue(String filename,String key) throws Exception {
        Properties config;
        if (!PubMethod.isEmpty(configMap) && configMap.containsKey("/" + filename)) {
            config = (Properties) configMap.get("/" + filename);
            return config.getProperty(key).trim();
        }else {
        	loadConfig("/" + filename);
            config = (Properties) configMap.get("/" + filename);
        	return config.getProperty(key).trim();
        }
    }
	
}
