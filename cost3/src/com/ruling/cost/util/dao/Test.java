package com.ruling.cost.util.dao;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ruling.cost.department.domain.Department;
import com.ruling.cost.department.service.DepartmentManager;
import com.ruling.cost.user.domain.User;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testFind();
	}

	public static void testFind() {
		// String[] configFile
		// ={"applicationContext.xml","com/ruling/cost/config/userContext.xml"};
		// ApplicationContext bean = new
		// ClassPathXmlApplicationContext(configFile);
		Resource res = new ClassPathResource("applicationContext.xml");
		XmlBeanFactory bean = new XmlBeanFactory(res);
		DepartmentManager dao = (DepartmentManager) bean
				.getBean("departmentservice");
		Department d = new Department();
		// dao.saveDepartment(d);
		User u = new User();
		u.setAddress("12345678444");
		// dao.saveUserManager(u);
	}
}
