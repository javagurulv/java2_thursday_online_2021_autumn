package lv.javaguru.java2.hospital.integrationtests;


import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertNotNull;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {HospitalConfiguration.class})
public class SpringContextTest {

    @Autowired private ApplicationContext appContext;

    @Ignore
    public void start() {
        assertNotNull(appContext);
    }

}
