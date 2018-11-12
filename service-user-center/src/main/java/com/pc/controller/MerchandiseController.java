package com.pc.controller;

import com.common.base.controller.BaseController;
import com.pc.model.ao.MerchandiseAo;
import com.pc.model.dto.MerchandiseDto;
import com.pc.service.MerchandiseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchandises")
public class MerchandiseController extends BaseController<MerchandiseAo, MerchandiseDto, MerchandiseService> {
}
