package com.ibm.demo.redis.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IpUtils {
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		
		if  (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		
		if  (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}		
		
		if  (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			
			if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:1".equals(ipAddress)) {
				InetAddress inet = null;
				
				try {
					inet = InetAddress.getLocalHost();
				} catch(UnknownHostException e) {
					e.printStackTrace();
				}
				
				ipAddress = inet.getHostAddress();
			}
		}
		
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0 ) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		
		return ipAddress;
	}
	
	public static String getRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		
		return checkIp(ip) ? ip : (
					checkIp(ip = request.getHeader("Proxy-Client-IP")) ? 
							ip : (checkIp(ip = request.getHeader("WL-Proxy-Client-IP")) ? ip : request.getRemoteAddr()));
	}
	
    
    public static String getHostByAddress() {
    	try {
    		return InetAddress.getLocalHost().getHostAddress();
    	} catch(UnknownHostException e) {
    		e.printStackTrace();
    	}
    	
    	return "127.0.0.1";
    }	
	
	private static boolean checkIp(String ip) {
		return !StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip);
	}
}
