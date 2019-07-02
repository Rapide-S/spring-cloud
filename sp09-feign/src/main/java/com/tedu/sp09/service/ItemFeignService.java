package com.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;
//指定一个服务的ID，去调用哪个微服务，指定一个降级类
@FeignClient(name="item-service", fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
	/*
	 * fegin利用springmvc  注解，来反向生成访问路径
	 * 
	 * 根据Mapping 中指定的路径，在这户籍地址后面拼接路径，
	 * http://localhost:8001/{orderId}
	 * 
	 * 根据@PathVariable配置，把参数 数据，拼接到路径当中，
	 * 假设 参数是  “123”
	 * http://localhost:8001/123
	 * 
	 * 向拼接的路径，来转发调用
	 */
	
	
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);
//具体代码不用写，都是动态生成
	
	/*
	 * 根据配置，拼接下面的路径，并向下面路径转发请求
	 * 在协议体重，携带参数
	 * http://localhost:8001/decreaseNumber
	 */
	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}















