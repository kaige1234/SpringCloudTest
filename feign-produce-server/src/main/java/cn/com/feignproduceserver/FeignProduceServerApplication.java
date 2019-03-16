package cn.com.feignproduceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FeignProduceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignProduceServerApplication.class, args);
	}

}
