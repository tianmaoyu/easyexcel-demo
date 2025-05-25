package org.example.demomybtis.feign;

import org.example.demomybtis.entity.Account;
import org.example.demomybtis.entity.AccountStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "demo-service", path = "account")
public interface AccountFeignClient {

    @GetMapping("/getById")
    Account getById();

    @GetMapping("/addUserType")
    AccountStatus addUserType(@RequestParam("userType") AccountStatus userType);
}
