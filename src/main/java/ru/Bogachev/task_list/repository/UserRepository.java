package ru.Bogachev.task_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.Bogachev.task_list.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = """
            SELECT EXISTS (
                            SELECT 1
                            FROM users_tasks
                            WHERE user_id = :userId
                            AND task_id = :taskId
                          )
            """, nativeQuery = true)
    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);
}
