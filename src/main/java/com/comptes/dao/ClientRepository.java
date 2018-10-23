package com.comptes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comptes.entities.Client;

public interface ClientRepository  extends JpaRepository<Client, Long>{

}
