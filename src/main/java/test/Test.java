package test;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jialu.yxl
 * @date 19/04/2023 18:58
 */
public class Test {

    public static void main(String[] args) {
        Map<Long, Integer> consignOrderIds = new HashMap<>();
        consignOrderIds.put(323L, 2);
        consignOrderIds.put(2223L,3);
        System.out.println(JSON.toJSONString(consignOrderIds));

        String s = "{\"sortTypeList\":[{\"secondTypeName\":null,\"name\":\"付款时间由近到远\",\"choose\":false,\"canModify\":false,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"PAY_TIME_NEAR_TO_FAR\",\"group\":null},{\"secondTypeName\":null,\"name\":\"付款时间由远到近\",\"choose\":false,\"canModify\":false,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"PAY_TIME_FAR_TO_NEAR\",\"group\":null},{\"secondTypeName\":null,\"name\":\"下单时间由近到远\",\"choose\":false,\"canModify\":false,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"BUY_TIME_NEAR_TO_FAR\",\"group\":null},{\"secondTypeName\":null,\"name\":\"下单时间由远到近\",\"choose\":true,\"canModify\":false,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"BUY_TIME_FAR_TO_NEAR\",\"group\":null},{\"secondTypeName\":null,\"name\":\"非锁定订单\",\"choose\":false,\"canModify\":false,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"UN_LOCK\",\"group\":null}],\"queryConditionList\":[\"ORDER_ID\",\"BUYER_NICK\",\"CP_NAME\",\"RECEIVER_NAME\",\"RECEIVER_MOBILE\",\"EXPRESS_ORDER\",\"CONSIGN_ORDER\",\"ORDER_STATUS_WAIT_CONSIGN\",\"SELLER_FLAG\",\"QUICK_SEARCH\",\"AREA_FILTER\"],\"quickQueryList\":[\"ALL\",\"PRINT_EXPRESS\",\"WAIT_EXPRESS\",\"PRINT_SHIPPING\"],\"source\":\"WAIT_CONSIGN\",\"displayItemList\":[{\"secondTypeName\":null,\"name\":\"买家旺旺\",\"choose\":true,\"canModify\":false,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"BUYER_NICK\",\"group\":null},{\"secondTypeName\":null,\"name\":\"订单编号\",\"choose\":true,\"canModify\":false,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"ORDER_ID\",\"group\":null},{\"secondTypeName\":null,\"name\":\"收件信息\",\"choose\":true,\"canModify\":true,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"RECEIVER_INFO\",\"group\":null},{\"secondTypeName\":null,\"name\":\"快递信息\",\"choose\":true,\"canModify\":true,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"EXPRESS_INFO\",\"group\":null},{\"secondTypeName\":null,\"name\":\"备注留言\",\"choose\":true,\"canModify\":true,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"MEMO_OR_MESSAGE\",\"group\":null},{\"secondTypeName\":null,\"name\":\"数量\",\"choose\":true,\"canModify\":true,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"BUY_AMOUNT\",\"group\":null},{\"secondTypeName\":null,\"name\":\"发货剩余时效\",\"choose\":true,\"canModify\":true,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"REMAIN_CONSIGN_TIME\",\"group\":null},{\"secondTypeName\":null,\"name\":\"商品信息\",\"choose\":true,\"canModify\":true,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"ITEM_INFO\",\"group\":null},{\"secondTypeName\":null,\"name\":\"付款时间\",\"choose\":true,\"canModify\":true,\"class\":\"com.taobao.fulfillment.model.base.BaseItemDto\",\"value\":null,\"key\":\"PAY_TIME\",\"group\":null}],\"class\":\"com.taobao.fulfillment.model.dto.PcFormDisplayDto\",\"mergeBySameAddress\":true,\"showDetails\":true}";
    }
}
