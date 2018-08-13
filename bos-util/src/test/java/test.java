import com.wp.utils.crm.Customer;
import com.wp.utils.crm.ICustomerService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {
    public static void main(String[] args) {
        //使用JaxWsProxyFactoryBean调用soap服务，但是这种方式需要需要生成接口文件
        System.out.println("***********JaxWsProxyFactoryBean***********");
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ICustomerService.class);
        factory.setAddress("http://localhost:8080/service/customer");
        ICustomerService userServiceJaxBean = (ICustomerService)factory.create();
        List<Customer> userJaxBean = userServiceJaxBean.getAllNotAssociation();
        System.out.println(userJaxBean);

        //测试jaxws:client，soap服务client调用
        System.out.println("******************soap jaxws:client**************");
        ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext(
                new String[]{"applicationConetext.xml"});
        ICustomerService us = (ICustomerService)cxt.getBean("client");

    }
}
