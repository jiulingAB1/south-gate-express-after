package vip.xiaonuo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author hsh
 * @date 2021-12-21 17:13:34
 */

@Configuration
@ComponentScan(basePackages = {"cn.south.express"})
@MapperScan(basePackages = {"cn.south.express.**.mapper"})
public class SubmoduleConfig {
}
