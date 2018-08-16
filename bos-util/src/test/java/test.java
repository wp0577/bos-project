import com.wp.utils.crm.Customer;
import com.wp.utils.crm.ICustomerService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Scanner;

public class test {
   /* public static void main(String[] args) {
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

    }*/
    public static void main(String[] args) {
        //open scan
        Scanner scan= new Scanner(System.in);
        //get keyboard input and store it into x
        while (scan.hasNext()) {
            String x = scan.nextLine();
            if (Character.isDigit(x.charAt(0)) && Integer.parseInt(x) > 0) {
                int temp = Integer.parseInt(x);
                int fi = temp;
                while (temp-- > 0) {
                    int res = 0;
                    for(int i=1; i<=fi; i++) {
                        res = i + res;
                    }
                    System.out.println("x > 0 and call " + x + " times and result is " + res);
                }
            } else if (Character.isDigit(x.charAt(0)) && Integer.parseInt(x) <= 0) {
                System.out.println("x is less than or equal to 0");
            } else if (x.toCharArray()[0] == 'q') {
                System.out.println("x is equal to q and program quit");
                break;
            }
        }

    }
    @Test
    public void test() {
        int x = findSecondLar(new int[]{23,4,56,156,235,678,65,567,23,21,435,68,34,123,2,4,4234,7,1,424,545,3,5,6,9,12,54,2,6,7,34,67});
        System.out.println(x);

    }
    public int findSecondLar(int[] arr) {
        int len = arr.length;
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for(int i=0; i < len; i++) {
            if(arr[i] > max2) {
                max2 = arr[i];
            }
            if(arr[i] > max) {
                max2 = max;
                max = arr[i];
            }
        }
        return max2;
    }




}
