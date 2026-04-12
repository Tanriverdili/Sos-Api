package com.example.api.repository;
import com.example.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
<<<<<<< HEAD
    List<UserEntity> findByEmail(String userEmail);
}


=======
    Optional<UserEntity> findByEmail(String email); 
}
>>>>>>> 3659dd1fa1086b761448b70b37797dc0465ac165
