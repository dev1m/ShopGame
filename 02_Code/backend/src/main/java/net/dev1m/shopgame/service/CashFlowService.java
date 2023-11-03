package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.CashFlowDTO;
import net.dev1m.shopgame.entity.Cash_flow;
import net.dev1m.shopgame.entity.Users;
import net.dev1m.shopgame.reponsitory.CashFlowReponsitory;
import net.dev1m.shopgame.service.imp.CashFlowServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CashFlowService implements CashFlowServiceImp {
    @Autowired
    CashFlowReponsitory cashFlowReponsitory;
    @Override
    public boolean addCashFlow(int cashOld, int cashChange, int cashNew, String cashNote, int userId) {
        Cash_flow cashFlow = new Cash_flow();
        cashFlow.setCash_old(cashOld);
        cashFlow.setCash_change(cashChange);
        cashFlow.setCash_new(cashNew);
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        cashFlow.setTime(timestamp);
        cashFlow.setCash_note(cashNote);
        Users users = new Users();
        users.setId(userId);
        cashFlow.setUsers_cash_flow(users);
        cashFlowReponsitory.save(cashFlow);
        return true;
    }

    @Override
    public List<CashFlowDTO> getCash_FLowByUserId(int userId) {
        List<Cash_flow> cashFlows = cashFlowReponsitory.findCash_FlowByUserId(userId);
        List<CashFlowDTO> cashFlowDTOS = new ArrayList<>();
        for (Cash_flow data:cashFlows) {
            CashFlowDTO cashFlowDTO = new CashFlowDTO();
            cashFlowDTO.setId(data.getId());
            cashFlowDTO.setCash_old(data.getCash_old());
            cashFlowDTO.setCash_change(data.getCash_change());
            cashFlowDTO.setCash_new(data.getCash_new());
            cashFlowDTO.setTime(data.getTime());
            cashFlowDTO.setCash_note(data.getCash_note());
            cashFlowDTO.setUser_id(data.getUsers_cash_flow().getId());
            cashFlowDTOS.add(cashFlowDTO);

        }
        return cashFlowDTOS;
    }

    @Override
    public List<CashFlowDTO> getAllCashFlows() {
        List<Cash_flow> cashFlows = cashFlowReponsitory.findAll();
        List<CashFlowDTO> cashFlowDTOS = new ArrayList<>();
        for (Cash_flow data: cashFlows
             ) {
            CashFlowDTO cashFlowDTO = new CashFlowDTO();
            cashFlowDTO.setId(data.getId());
            cashFlowDTO.setUser_id(data.getUsers_cash_flow().getId());
            cashFlowDTO.setCash_old(data.getCash_old());
            cashFlowDTO.setCash_change(data.getCash_change());
            cashFlowDTO.setCash_new(data.getCash_new());
            cashFlowDTO.setCash_note(data.getCash_note());
            cashFlowDTO.setTime(data.getTime());
            cashFlowDTOS.add(cashFlowDTO);
        }
        return cashFlowDTOS;
    }


}
