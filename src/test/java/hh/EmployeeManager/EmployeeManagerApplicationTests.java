package hh.EmployeeManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.EmployeeManager.web.EmployeeController;

@SpringBootTest
class EmployeeManagerApplicationTests {

	@Autowired
	private EmployeeController employeeController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(employeeController).isNotNull();
    }	

}
