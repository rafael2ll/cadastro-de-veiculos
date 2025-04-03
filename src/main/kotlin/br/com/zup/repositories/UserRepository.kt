package br.com.zup.repositories

import br.com.zup.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User?, String?> {
    fun findByEmail(email: String?): User?
}
