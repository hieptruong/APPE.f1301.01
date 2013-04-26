package ch.hslu.appe.fs1303.gui.datasource;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1303.gui.datasource.ComboDataSource;

public class DataSourceTest {
	
	private List<ComboDataSource> fTestee;
	
	@Test
	public void addsAllConstantsAsDataSource() {
		final int ExpectedSize = 4; //NONE, Customer, SysUser, Admin
		fTestee = ComboDataSource.getDataSourceForClass(UserRole.class);
		
		assertThat(fTestee.size()).isEqualTo(ExpectedSize);
	}
	
	@Test
	public void idAndValueAreSetCorrectly() {
		final int[] ids = new int[] { UserRole.NONE, UserRole.CUSTOMER, UserRole.SYSUSER, UserRole.ADMIN };
		final String[] names = new String[] { "NONE", "CUSTOMER", "SYSUSER", "ADMIN" };
		
		fTestee = ComboDataSource.getDataSourceForClass(UserRole.class);
		
		for(int i = 0; i < ids.length; i++) {
			for(ComboDataSource source : fTestee) {
				assertThat(ids).contains(source.getId());
				assertThat(names).contains(source.getValue());
			}
		}
	}
	
	@Test
	public void test() {
		fTestee = ComboDataSource.getDataSourceForClass(ExceptionTest.class);
		
		assertThat(fTestee.size()).isZero();
	}
	
	class ExceptionTest {
		@SuppressWarnings("unused")
		private final int Testvalue = 2;
	}
}
