package tgbot.telegramservice.extensions

fun String.bold() = "<b>$this</b>"

fun String.italic() = "<i>$this</i>"

fun String.underline() = "<u>$this</u>"

fun String.strikethrough() = "<s>$this</s>"

fun String.spoiler() = "<tg-spoiler>$this</tg-spoiler>"

fun String.url(linkText: String) = if (linkText.isEmpty()) "<a href='$this'>$this</a>" else "<a href='$this'>$linkText</a>"

fun String.code() = "<code>$this</code>"

fun String.pre() = "<pre>$this</pre>"
