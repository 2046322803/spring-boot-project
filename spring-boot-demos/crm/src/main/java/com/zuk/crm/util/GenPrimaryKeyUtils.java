package com.zuk.crm.util;

import java.util.UUID;

public class GenPrimaryKeyUtils {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
}
