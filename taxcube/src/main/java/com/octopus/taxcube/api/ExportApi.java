package com.octopus.taxcube.api;


import com.octopus.taxcube.common.lang.DateUtils;
import com.octopus.taxcube.eds.pojo.CreditExchange;
import com.octopus.taxcube.eds.service.IAccountService;
import com.octopus.taxcube.eds.service.ICreditService;
import com.octopus.taxcube.util.excel.ExcelExport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = "导入导出",tags = "office导入导出接口")
public class ExportApi {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICreditService creditService;

//    @RequiresRoles(value = {CONST.ACCOUNT_TYPE_SYSADMIN, CONST.ACCOUNT_TYPE_ADMIN}, logical = Logical.OR)
    @RequestMapping(value = "/export/excel/credit/exchanges", method = {RequestMethod.GET})
    @ApiOperation("导出积分兑换数据导出到excel")
    public void listOfArea(
            @ApiParam(name = "status", value = "status(PENDING DONE REFUSE)", required = true) @RequestParam(value = "status") String status,
            HttpServletResponse response) {

//        List<User> list = accountService.findListOfAdminUsers();

        List<CreditExchange> creditExchanges = creditService.findAllCreditExchangeByStatus(status);

        String fileName = "积分兑换数" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
        try(
                ExcelExport ee = new ExcelExport("积分兑换数", CreditExchange.class)
                )
            {
                ee.setDataList(creditExchanges).write(response, fileName);
        }
    }
}
