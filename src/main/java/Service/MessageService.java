package Service;

import DAO.MessageDAO;
import Model.Message;
import Model.Account;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private MessageDAO messageDao;

    public MessageService() {
        messageDao = new MessageDAO();
    }

    public MessageService(MessageDAO messageDao) {
        this.messageDao = messageDao;
    }

    public Message getMessageById(int id) {
        return messageDao.getMessageId(id);
    }

    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        List<Message> messages = messageDao.getMessagesByAccountId(accountId);
        if (messages == null) return new ArrayList<>();
        return messages;
    }

    // Message needs to be within 1-255 characters.
    public Message createMessage(Message message) {
        int messageLength = message.getMessage_text().length();
        if (messageLength == 0 || messageLength > 255) return null;
        return messageDao.newMessage(message);
    }

    // When updating a message, the message needs to still follow the rules of 
    // a regular message. Has to not be empty as well as be within 1-255 characters.
    public Message updateMessage(Message message) {
        Message messageExist = this.getMessageById(message.getMessage_id());
        int messageLength = message.getMessage_text().length();
        if (messageLength == 0 || messageLength > 255) return null;
        if (messageExist == null) return null;
        messageExist.setMessage_text(message.getMessage_text());
        return messageDao.update(messageExist);
    }

    public Message deleteMessage(Message message) {
        return messageDao.deleteMessage(message);
    }
}
