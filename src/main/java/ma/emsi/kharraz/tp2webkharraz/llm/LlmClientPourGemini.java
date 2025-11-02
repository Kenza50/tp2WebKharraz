package ma.emsi.kharraz.tp2webkharraz.llm;

import dev.langchain4j.service.AiServices;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.google.ai.GoogleAiChatModel;
import jakarta.enterprise.context.Dependent;

@Dependent
public class LlmClientPourGemini {

    private final Assistant assistant;

    public LlmClientPourGemini() {
        String geminiKey = System.getenv("GEMINI_KEY");
        ChatLanguageModel model = GoogleAiChatModel.builder()
                .apiKey(geminiKey)
                .modelName("gemini-1.5-flash")
                .build();
        this.assistant = AiServices.create(Assistant.class, model);
    }

    public String generate(String userMessage) {
        return assistant.chat(userMessage);
    }
}