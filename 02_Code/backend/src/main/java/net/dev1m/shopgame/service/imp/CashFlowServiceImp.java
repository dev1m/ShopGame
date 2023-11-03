package net.dev1m.shopgame.service.imp;


import net.dev1m.shopgame.dto.CashFlowDTO;
import net.dev1m.shopgame.entity.Cash_flow;

import java.util.Date;
import java.util.List;

public interface CashFlowServiceImp {
    boolean addCashFlow(int cashOld, int cashChange, int cashNew, String cashNote, int userId);
    List<CashFlowDTO> getCash_FLowByUserId(int userId);
    List<CashFlowDTO> getAllCashFlows();
}
