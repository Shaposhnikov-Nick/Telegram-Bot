package tgbot.telegramservice.service

import org.springframework.stereotype.Service
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.repository.UserRepository

interface UserService {
    fun saveUser(user: User): User
    fun getAllUsers(): List<User>
    fun getUserById(id: String): User?
    fun deleteUser(id: String)
}

@Service
class UserServiceImpl(
    val userRepository: UserRepository
) : UserService {

    override fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll().toList()
    }

    override fun getUserById(id: String): User? {
        return userRepository.findById(id).orElse(null)
    }

    override fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }

}