package tgbot.accountservice.entity

import jakarta.persistence.*


@Entity
@Table(name = "account")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "chat_id")
    val chatId: String,

    val name: String,

    val email: String?,

    val about: String?
)