package tgbot.telegramservice.repository

import org.springframework.data.repository.CrudRepository
import tgbot.telegramservice.entity.User

interface UserRepository : CrudRepository<User, String>