package com.chanct.ThreeOperators.dao.impl;

import com.chanct.ThreeOperators.dao.OperatorsDao;
import com.chanct.ThreeOperators.entity.*;
import com.chanct.core.dao.BaseDAO;
import com.chanct.netsecur.constants.DBConstant;

/**
 * Created by Administrator on 2017/12/21.
 */
public class OperatorsDaoImpl extends BaseDAO implements OperatorsDao{
    @Override
    public int insertAbroad(AbroadPhoneNumber abroad) {
        return this.insertObject("evilOracle.InsertThreeOperators.InsertAbroadPhoneNumber",abroad, DBConstant.tj_db);
    }

    @Override
    public int insertBadCall(BadcallNumber bad) {
        return this.insertObject("evilOracle.InsertThreeOperators.InsertBadcallNumber",bad,DBConstant.tj_db);
    }

    @Override
    public int insertFraudClass(FraudClass fraud) {
        return this.insertObject("evilOracle.InsertThreeOperators.InsertFraudClass",fraud,DBConstant.tj_db);
    }

    @Override
    public int insertIrr(IrregularPhone irr) {
        return this.insertObject("evilOracle.InsertThreeOperators.InsertIrregularPhone",irr,DBConstant.tj_db);
    }

    @Override
    public int insertEnterprise(EnterpriseManagement ent) {
        return this.insertObject("evilOracle.InsertThreeOperators.Insertenterprise",ent,DBConstant.tj_db);
    }

    @Override
    public int insertTel(TelecomRectify tel) {
        return this.insertObject("evilOracle.InsertThreeOperators.InsertTelecom",tel,DBConstant.tj_db);
    }

    @Override
    public int insertBlackCard(BlackCardVo black) {
        return this.insertObject("evilOracle.InsertThreeOperators.InsertBlackCard",black,DBConstant.dispose_web);
    }

    @Override
    public int insertBlacPhone(BlackphoneResult black) {
        return this.insertObject("evilOracle.InsertThreeOperators.InsertBlacPhone",black,DBConstant.tj_db);
    }


}