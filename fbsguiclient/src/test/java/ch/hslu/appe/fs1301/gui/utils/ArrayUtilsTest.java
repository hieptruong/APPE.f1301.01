package ch.hslu.appe.fs1301.gui.utils;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ch.hslu.appe.fs1303.gui.utils.ArrayUtils;

public class ArrayUtilsTest {

	@Test
	public void ConvertToIntArray() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(4);
		
		int[] intArray = ArrayUtils.convertToIntArray(list);
		
		for(Integer i = 0; i < list.size(); i++)
		{
			assertThat(intArray[i]).isEqualTo(list.get(i));
		}
	}
}
