package com.octopus.taxcube.eds.pojo;

import com.octopus.taxcube.common.constenum.CONST;
import lombok.Data;

import java.util.List;

@Data
public class CreditSummary {
    private Long credit;
    private List<CreditItem> creditItems;

    public void setCreditItems(List<CreditItem> creditItems) {
        this.credit = 0l;
        if(null != creditItems && !creditItems.isEmpty()){
            for(CreditItem creditItem : creditItems){
                if(creditItem.getStatus().equals(CONST.STATUS_DONE)){
                    //处于结算状态的积分
                    credit+=creditItem.getCredit();
                }
            }
        }

        this.creditItems = creditItems;
    }
}
