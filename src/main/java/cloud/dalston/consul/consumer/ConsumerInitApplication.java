package cloud.dalston.consul.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient /** 使用服务注册发现 */
@SpringBootApplication
public class ConsumerInitApplication {
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
//		SpringApplication.run(ConsumerInitApplication.class, args);
		new SpringApplicationBuilder(ConsumerInitApplication.class).web(true).run(args);
	}
}
