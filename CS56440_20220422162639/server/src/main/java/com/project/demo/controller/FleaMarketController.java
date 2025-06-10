package com.project.demo.controller;

import com.project.demo.entity.FleaMarket;
import com.project.demo.service.FleaMarketService;
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
 *跳蚤市场：(FleaMarket)表控制层
 *
 */
@RestController
@RequestMapping("/flea_market")
public class FleaMarketController extends BaseController<FleaMarket,FleaMarketService> {

    /**
     *跳蚤市场对象
     */
    @Autowired
    public FleaMarketController(FleaMarketService service) {
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
