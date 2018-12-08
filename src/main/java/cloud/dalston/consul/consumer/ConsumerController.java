package cloud.dalston.consul.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    LoadBalancerClient loadBalancerClient;
    
    /**
     * 进行服务调用
     * @param a
     * @param b
     * @return
     */
    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String add(@RequestParam("a")Integer a,@RequestParam("b")Integer b) {
    	ServiceInstance serviceInstance = loadBalancerClient.choose("provider1");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/discovery/calculate?a="+a+"&b="+b;
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
