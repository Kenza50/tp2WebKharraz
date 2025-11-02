package ma.emsi.kharraz.tp2webkharraz.llm;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.google.ai.GoogleAiChatModel;
import jakarta.enterprise.context.Dependent;

@Dependent
public class LlmClientPourGemini {

    private final ChatLanguageModel model;

    public LlmClientPourGemini() {
        String geminiKey = System.getenv("GEMINI_KEY");
        this.model = GoogleAiChatModel.builder()
                .apiKey(geminiKey)
                .modelName("gemini-1.5-flash")
                .build();
    }

    public String generate(String userMessage) {
        return model.generate(userMessage);
    }
}