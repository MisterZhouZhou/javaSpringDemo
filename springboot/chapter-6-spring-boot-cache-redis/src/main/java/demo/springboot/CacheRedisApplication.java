package demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动程序
 *
 * Created by bysocket on 18/09/2017.
 */
@SpringBootApplication
public class CacheRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheRedisApplication.class, args);
	}
}
