package webchat.unichat.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import webchat.unichat.repository.ChatRoomRepository;
import webchat.unichat.repository.MemberRepository;
import webchat.unichat.repository.MessageRepository;
import webchat.unichat.repository.ParticipationChatRoomRepository;
import webchat.unichat.service.ChatService;
import webchat.unichat.service.MemberService;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final ParticipationChatRoomRepository participationChatRoomRepository;
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public SpringConfig(MemberRepository memberRepository,
                        ParticipationChatRoomRepository participationChatRoomRepository,
                        MessageRepository messageRepository,
                        ChatRoomRepository chatRoomRepository) {
        this.memberRepository = memberRepository;
        this.participationChatRoomRepository = participationChatRoomRepository;
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    @Bean
    public ChatService chatService() {
        return new ChatService(participationChatRoomRepository, messageRepository, chatRoomRepository);
    }
}
