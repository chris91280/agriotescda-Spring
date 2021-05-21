package fr.greta91.cda.agriotes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta91.cda.agriotes.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	User findByUsername(String username);

}
