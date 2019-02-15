package com.octopus.taxcube.api;

import com.github.wxpay.sdk.WXPayUtil;
import com.octopus.taxcube.eds.service.IGlobalConfigService;
import com.octopus.taxcube.eds.service.IOrderService;
import com.octopus.taxcube.exception.SignException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
@Api(value = "通知",tags = "外部系统接口")
public class NotifyApi {

    @Autowired
    private IGlobalConfigService globalConfigService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/notify/wechatPay", method = {RequestMethod.POST})
    @ApiOperation("微信支付结果通知")
    @ResponseBody
    public Object notifyWechatPay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        BufferedReader reader;
        reader = request.getReader();

        StringBuffer inputString = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        String msg = inputString.toString();
        request.getReader().close();

        Map<String, String> returnMap = new HashMap<>();

        if (!WXPayUtil.isSignatureValid(msg, globalConfigService.getPartnerKey())) {
            throw new SignException("");
        }

        Map<String, String> resultMap = WXPayUtil.xmlToMap(msg);
        String orderId = resultMap.get("out_trade_no");

        if (resultMap.get("return_code").equals("SUCCESS")) {

            Integer fee = Integer.valueOf(resultMap.get("total_fee"));

            orderService.notifyPaySuccess(orderId, fee/100.00, "微信官方通知", null, true);

            returnMap.put("return_code", "SUCCESS");
            returnMap.put("return_msg", "OK");
            return WXPayUtil.mapToXml(returnMap);

//            if (MallServantsConst.ORDER_STATUS_SUCCESS == orderEntity.getStatus().intValue()) {
//                //已经处理过的订单  不再处理
//                returnMap.put("return_code", "SUCCESS");
//                returnMap.put("return_msg", "OK");
//                return WXPayUtil.mapToXml(returnMap);
//            }
//
//            List<String> goodsIdList = Arrays.asList(orderEntity.getGoodsId().split(","));
//            SpringUtils.getBean(PermissionService.class).openPermissionByGoodsIdList(orderEntity.getUserId(), goodsIdList);
//            SpringUtils.getBean(VipAgentService.class).updateAgentOrderState(orderNo);
//            WxEnrollTrainingEntity wxEnrollTrainingEntity = wxEnrollTrainingEntityMapper.selectByUserIdAndValid(orderEntity.getUserId());
//
//            if (null !=wxEnrollTrainingEntity){
//                // 更新微信支付的名单信息, 通过id找
//                wxEnrollTrainingEntity.setOrderId(orderNo);
//                wxEnrollTrainingEntity.setGoodsId(orderEntity.getGoodsId());
//                wxEnrollTrainingEntity.setPay(1);
//                wxEnrollTrainingEntityMapper.updateByPrimaryKey(wxEnrollTrainingEntity);
//            }
//            orderEntity.setStatus(MallServantsConst.ORDER_STATUS_SUCCESS);
//            orderEntity.setDescription(MetaDataHelper.getPayStatusDescriptionByCode(orderEntity.getStatus()));
//            orderEntityMapper.updateByPrimaryKey(orderEntity);
//
//            int orderType = getOrderType(orderEntity);
//            if (orderType == CONST.ORDER_TYPE_TRAIN_CAMP) {
//                saveTrainingCampInfo(orderEntity);
//            }
//
//            //MS通知行为记录付款成功
//            MallObserverManager.instance().notifyOrderSuccess(orderEntity);
//
//            returnMap.put("return_code", "SUCCESS");
//            returnMap.put("return_msg", "OK");
//            return WXPayUtil.mapToXml(returnMap);
        }

        returnMap.put("return_code", "FAILED");

        return WXPayUtil.mapToXml(returnMap);
    }

}
