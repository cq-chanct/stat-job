package com.chanct.ThreeOperators.dao;

import com.chanct.ThreeOperators.entity.*;

/**
 * Created by Administrator on 2017/12/21.
 */
public interface OperatorsDao {

    int insertAbroad(AbroadPhoneNumber abroad);

    int insertBadCall(BadcallNumber bad);

    int insertFraudClass(FraudClass fraud);

    int insertIrr(IrregularPhone irr);

    int insertEnterprise(EnterpriseManagement ent);

    int insertTel(TelecomRectify tel);
    //插入数据到黑卡库
    int insertBlackCard(BlackCardVo black);
    //插入数据到黑卡库
    int insertBlacPhone(BlackphoneResult black);
}