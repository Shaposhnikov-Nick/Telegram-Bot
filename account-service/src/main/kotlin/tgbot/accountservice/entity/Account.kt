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

    var name: String,

    var email: String?,

    var about: String?
)