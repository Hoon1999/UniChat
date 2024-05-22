package webchat.knung.springConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import webchat.knung.controller.LoginController;
import webchat.knung.domain.Member;
import webchat.knung.repository.ChatRoomRepository;
import webchat.knung.repository.MemberRepository;
import webchat.knung.repository.MessageRepository;
import webchat.knung.repository.ParticipationChatRoomRepository;
import webchat.knung.service.ChatService;
import webchat.knung.service.MemberService;

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
