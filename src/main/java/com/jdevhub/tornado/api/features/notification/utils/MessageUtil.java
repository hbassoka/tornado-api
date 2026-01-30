package com.jdevhub.tornado.api.features.notification.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.helpers.MessageFormatter;

public class MessageUtil {

	private MessageUtil() {
	}

	public static String format(String pattern, Object... objects) {
		return MessageFormatter.arrayFormat(pattern, objects).getMessage();
	}
	
	public  static String getStacktraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
