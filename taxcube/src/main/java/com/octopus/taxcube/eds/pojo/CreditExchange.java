package com.octopus.taxcube.eds.pojo;

import com.octopus.taxcube.util.excel.annotation.ExcelField;
import lombok.Data;

@Data
public class CreditExchange {
    private Long applyId;

    @ExcelField(title="用户ID", attrName="accountId", align= ExcelField.Align.CENTER, sort=60)
    private Long accountId;

    @ExcelField(title="用户名称", attrName="accountName", align= ExcelField.Align.CENTER, sort=60)
    private String accountName;
    private String accountType;

    @ExcelField(title="积分兑换值", attrName="creditVal", align= ExcelField.Align.CENTER, sort=60)
    private Integer creditVal;
    private String exchangeType;
    private Double exchangeVal;

    @ExcelField(title="申请状态", attrName="status", align= ExcelField.Align.CENTER, sort=60)
    private String status;

    @ExcelField(title="申请时间", attrName="date", align= ExcelField.Align.CENTER, sort=60)
    private String date;

    private String remark;
}
