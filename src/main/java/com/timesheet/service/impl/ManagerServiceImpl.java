package com.timesheet.service.impl;

import com.google.common.collect.Lists;
import com.timesheet.domain.Manager;
import com.timesheet.domain.Task;
import com.timesheet.repository.ManagerRepository;
import com.timesheet.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Vitaliy, Yan on 10.04.15.
 */
@Service("managerService")
@Repository
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @PersistenceContext
    EntityManager entityManager;
    @Transactional(readOnly = true)
    public List<Manager> findAll() {
        return Lists.newArrayList(managerRepository.findAll());
    }

    public Page<Manager> findAll(Pageable page) {
        return managerRepository.findAll(page);
    }

    public Manager findById(Long id) {
        return managerRepository.findOne(id);
    }

    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    public void delete(Manager manager) {
        managerRepository.delete(manager);
    }

    public boolean deleteManager(Manager manager) {
        TypedQuery managerQuery = entityManager.createQuery(
                "select t from Task t where t.manager.id = :id ", Task.class
        );
        managerQuery.setParameter("id", manager.getId());

        if(!managerQuery.getResultList().isEmpty()) {
            return false;
        }

        delete(manager);
        return true;
    }
}
