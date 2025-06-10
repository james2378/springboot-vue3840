package com.project.demo.controller;

import com.project.demo.entity.CampusPerimeter;
import com.project.demo.service.CampusPerimeterService;
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
 *校园周边：(CampusPerimeter)表控制层
 *
 */
@RestController
@RequestMapping("/campus_perimeter")
public class CampusPerimeterController extends BaseController<CampusPerimeter,CampusPerimeterService> {

    /**
     *校园周边对象
     */
    @Autowired
    public CampusPerimeterController(CampusPerimeterService service) {
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
