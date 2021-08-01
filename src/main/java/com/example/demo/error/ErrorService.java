package com.example.demo.error;

import com.example.noSuchMethodErrorDemo.FirstDemo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author luobo.cs@raycloud.com
 * @since 2021/7/31 10:32 下午
 */
@Service
public class ErrorService {

    @PostConstruct
    public void init(){
        FirstDemo firstDemo = new FirstDemo();
        firstDemo.second();
    }


}
