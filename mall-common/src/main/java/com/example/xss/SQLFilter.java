package com.example.xss;

import com.example.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * @Author ninan
 * @Description 去掉SQL注入问题  1.判空 2.过滤符号 3.转换成小写 4.过滤关键字
 * @Date 2021/4/2
 **/
public class SQLFilter {

    public static String sqlInject(String str) {

        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        if (StringUtils.isEmpty(str)) {
            return null;
        }

        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        str = str.toLowerCase();

        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new RRException("包含非法字符");
            }
        }
        return str;
    }
}
