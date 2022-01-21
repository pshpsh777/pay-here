package com.payhere.apiserver.controller;

import com.payhere.apiserver.domain.Expenditure;
import com.payhere.apiserver.service.ExpenditureService;
import com.payhere.apiserver.service.UserExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserExpenditureController {
    private UserExpenditureService userExpenditureService;

    @Autowired
    public UserExpenditureController(UserExpenditureService userExpenditureService) {
        this.userExpenditureService = userExpenditureService;
    }

    @RequestMapping(value = "/api/expenditure", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addExpenditure(@RequestBody Expenditure expenditure){
        // get userId from token
        Long userId = 1L;

        userExpenditureService.saveExpenditure(userId, expenditure);
    }

    @RequestMapping(value = "/api/expenditure/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateExpenditure(@RequestBody Expenditure expenditure, @PathVariable Long expenditureId) {
        // get userId from token
        Long userId = 1L;

        expenditure.setId(expenditureId);
        userExpenditureService.updateUserExpenditure(userId, expenditure);
    }

    @RequestMapping(value = "/api/expenditure/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteExpenditure(@PathVariable Long expenditureId) {
        // get userId from token
        Long userId = 1L;

        userExpenditureService.deleteExpenditure(userId, expenditureId);
    }

    @RequestMapping(value = "/api/expenditure/recover/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void recoverExpenditure(@PathVariable Long expenditureId) {
        // get userId from token
        Long userId = 1L;

        userExpenditureService.recoverExpenditure(userId, expenditureId);
    }

    @RequestMapping(value = "/api/expenditure", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Expenditure> getExpenditureList() {
        // get userId from token
        Long userId = 1L;

        List<Expenditure> expenditureList = userExpenditureService.getExpenditureListByUserId(userId, false);
        return expenditureList;
    }

    @RequestMapping(value = "/api/expenditure/deleted", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Expenditure> getDeletedExpenditureList() {
        // get userId from token
        Long userId = 1L;

        List<Expenditure> expenditureList = userExpenditureService.getExpenditureListByUserId(userId, true);
        return expenditureList;
    }
}
