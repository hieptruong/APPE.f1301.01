package ch.hslu.appe.fs1303.gui.utils;

import java.util.List;

public class ArrayUtils {
	public static int[] convertToIntArray(List<Integer> list) {
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i) ;
		}
		
		return array;
	}
}
