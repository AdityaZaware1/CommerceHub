package com.ben.Access.Service.service;

import com.ben.Access.Service.entity.SubScription;

import java.util.List;

public interface SubScriptionService {

    SubScription createSubScription(SubScription subScription);

    SubScription getSubScription(Long id);

    List<SubScription> getAllSubScription();

}
