package tgbot.telegramservice.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import tgbot.telegramservice.entity.User
import tgbot.telegramservice.handler.util.*
import tgbot.telegramservice.keyboard.mainMenuKeyboard
import tgbot.telegramservice.model.TranslateRequestEvent
import tgbot.telegramservice.producer.Producer


@Component
class MessageHandler(
    val commandUtil: CommandHandlerUtil,
    val producers: Map<String, Producer>
) {
    fun handle(msg: Message, user: User): SendMessage? {
        if (!commandUtil.isChatStarted(user)) return commandUtil.chatNotStartedMsg(user)

        val producer = when (commandUtil.getLastServiceCommand(user)) {
            ServiceCommand.TRANSLATE -> producers["translate"]!!
            else -> null
        }

        return if (producer != null) {
            producer.send(
                TranslateRequestEvent(user.chatId, msg.text, commandUtil.getLastTranslateCommand(user)?.direction!!)
            )
        } else mainMenuKeyboard(user)

    }

}