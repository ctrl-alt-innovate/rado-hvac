package com.evanwahrmund.server;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MailService mailService;

    public MessageService(MessageRepository messageRepository, MailService mailService) {
        this.messageRepository = messageRepository;
        this.mailService = mailService;
    }

    public Message saveMessage(Message message) {

        Message saved = messageRepository.save(message);

        String subject = "Thank you for contacting Rado HVAC!";
        String body = String.format("Hi %s,\n\nThanks for your message:\n\"%s\"\n\nWe'll get back to you shortly!\n\n— Rado HVAC Team",
                message.getName(), message.getMessage());
        mailService.sendConfirmation(message.getEmail(), subject, body);
        mailService.sendConfirmation(
                "ewahrmund1@gmail.com",
                "New Contact Form Message",
                String.format(
                        "Name: %s%nEmail: %s%nPhone: %s%nMessage:%n%s",
                        message.getName(), message.getEmail(), message.getPhone(), message.getMessage()
                )
        );


        return saved;
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    public void deleteMessageById(Long id ) {
        messageRepository.deleteById(id);
    }
}
