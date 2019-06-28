package com.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication

@SpringCloudApplication
public class Sp06RibbonApplication {

	//创建 RestTemplate 实例，并存入 spring 容器
//	@LoadBalanced   //生成一个动态代理对象，切入负载均衡代码
//	@Bean
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
		f.setConnectTimeout(1000);//这两个参数在yml中不生效，只能在这配置
		f.setReadTimeout(1000);   //接受响应数据超时
		return new RestTemplate(f);
		
		//RestTemplate 中默认的 Factory 实例中，两个超时属性默认是 -1，（-1就是不超时，不启用）
		//未启用超时，也不会触发重试
		//return new RestTemplate();
	}

	
	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}

}
