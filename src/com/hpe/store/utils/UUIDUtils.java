/**
 * 
 */
package com.hpe.store.utils;

import java.util.UUID;

/**
 * @author kyrie liu
 * @date Nov 7, 2017
 * @time 10:01:45 PM
 * @version 1.0
 */
public class UUIDUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
