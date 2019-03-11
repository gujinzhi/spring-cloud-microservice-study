package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provider.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}