package com.lxtyp.tobe.hi;

import com.lxtyp.tobe.common.TobeConst;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TobeConst.REST_V1 + "/hi")
public class HiController {
    @GetMapping
    public String sayHi() {
        return "Hi!";
    }

    @GetMapping("/name")
    public String sayHiWithName(@RequestParam("userName") String userName) {
        return "Hi ~ " + userName;
    }

    @GetMapping("/say/{userName}")
    public String sayHiWithParamName(@PathVariable("userName") String userName) {
        return "Hi ~ " + userName;
    }
}
