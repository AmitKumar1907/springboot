package com.exam.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
