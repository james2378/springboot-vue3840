package com.project.demo.controller;

import com.project.demo.entity.WithRunningService;
import com.project.demo.service.WithRunningServiceService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *带跑服务：(WithRunningService)表控制层
 *
 */
@RestController
@RequestMapping("/with_running_service")
public class WithRunningServiceController extends BaseController<WithRunningService,WithRunningServiceService> {

    /**
     *带跑服务对象
     */
    @Autowired
    public WithRunningServiceController(WithRunningServiceService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
