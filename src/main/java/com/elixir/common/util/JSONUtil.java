package com.elixir.common.util;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;


/**
* @ClassName: JSONUtil
* @Description:
* @author Jingyan
* @date 2016年3月21日 下午5:49:28
*/ 
public class JSONUtil {
    public static Logger logger = Logger.getLogger(JSONUtil.class);

    public static List getListFromPageJSON(String jsonStr) {
        if (jsonStr == null || "".equals(jsonStr)) {
            logger.debug("page 式的 json串为空");
            return null;
        }
        int begin = jsonStr.indexOf("rows\":[");
        if (begin == -1) {
            logger.debug("json串不是约定的page式");
            return null;
        }
        String str = jsonStr.substring(begin + "\"rows\":".length() - 1, jsonStr.length() - 1);
        try {
            return JSONUtil.getListFormJSON(str);
        } catch (Exception e) {
            logger.debug("json串不是约定的page式");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    /**
     * 将[{}]式的JSON串转换为List
     *
     * @param jsonStr
     * @return
     */
    public static List getListFormJSON(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }

    /**
     * 将普通对象转换为JSON串
     *
     * @param obj
     * @return
     */
    public static String getJSONFromObj(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 将对象集合转换为JSON串
     *
     * @param list
     * @return
     */
    public static String getJSONFromObjList(List list) {
        return JSON.toJSONString(list);
    }

    /**
     * 将PAGE对象转换为JSON
     *
     * @param page
     * @return
     */
    /*public static String getJSONFromPage(Page page) {
        StringBuilder builder = new StringBuilder("{\"total\":" + page.getTotalRow() + ",\"totalPage\":" + page.getTotalPage() + ",\n\"rows\":[");
        int index = 0;
        for (Object obj : page.getList()) {
            if (obj instanceof Model) {
                Model model = (Model) obj;
                builder.append(model.toJson());
            } else if (obj instanceof Record) {
                Record record = (Record) obj;
                builder.append(record.toJson());
            }
            index++;
            if (index < page.getList().size()) {
                builder.append(",");
            }
        }
        builder.append("]\n}");
        return builder.toString();
    }*/

    /**
     * 将LIST对象转换为JSON
     *
     * @param list
     * @return
     */
    /*public static String getJSONFromList(List list) {
        StringBuilder builder = new StringBuilder("[");
        int index = 0;
        for (Object obj : list) {
            if (obj instanceof Model) {
                Model model = (Model) obj;
                builder.append(model.toJson());
            } else if (obj instanceof Record) {
                Record record = (Record) obj;
                builder.append(record.toJson());
            }
            index++;
            if (index < list.size()) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }*/


    /**
     * 将MAP转换为JSON
     *
     * @param map
     * @return
     */
    public static String getJSONFromMap(Map map) {
        if (map == null) {
            return null;
        }
        return JSON.toJSONString(map);
    }

    public static Map getMapFromJSON(String str) {
        if (PubMethod.isEmpty(str)) {
            return null;
        }
        return JSON.parseObject(str);
    }

    private static String getJSON(Object obj, StringBuilder builder) {
        List list = (List) obj;
        int i = 0;
        for (Object listObj : list) {
            builder.append(JSON.toJSONString(listObj));
            if (i < list.size() - 1) {
                builder.append(",");
            }
            i++;
        }
        return builder.toString();
    }
}
