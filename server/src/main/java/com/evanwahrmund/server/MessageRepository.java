package com.evanwahrmund.server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface MessageRepository extends JpaRepository<Message, Long>{
}
