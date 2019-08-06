package net.vv2.util;

import java.math.BigDecimal;

public class FloatUtil {
	public static float add(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.add(b2).floatValue();
		}

		public static float sub(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.subtract(b2).floatValue();
		}
		
}
