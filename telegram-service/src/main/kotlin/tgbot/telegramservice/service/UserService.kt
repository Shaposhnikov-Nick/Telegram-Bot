package tgbot.telegramservice.service

import org.springframework.stereotype.Service
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.repository.UserRepository

interface UserService {
    fun saveUser(user: User): User
    fun getAllUsers(): List<User>
    fun getUserByChatId(chatId: String): User?
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

    override fun getUserByChatId(chatId: String): User? {
        return userRepository.findUserByChatId(chatId)
    }

    override fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }

}