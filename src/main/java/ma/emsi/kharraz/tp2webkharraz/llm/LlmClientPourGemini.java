package ma.emsi.kharraz.tp2webkharraz.llm;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

@Dependent
public class LlmClientPourGemini implements Serializable {

    public String systemRole;
    private final Assistant assistant;
    private final ChatMemory chatMemory;

    public LlmClientPourGemini() {
        String geminiKey = System.getenv("GEMINI_KEY");
        ChatLanguageModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(geminiKey)
                .modelName("gemini-2.5-flash")
                .build();
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemory(chatMemory)
                .build();
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
        this.chatMemory.clear();
        this.chatMemory.add(SystemMessage.from(systemRole));
    }

    public String generate(String userMessage) {
        return assistant.chat(userMessage);
    }
}