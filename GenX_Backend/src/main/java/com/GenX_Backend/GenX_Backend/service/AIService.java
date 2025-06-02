package com.GenX_Backend.GenX_Backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;

@Service
public class AIService {


    @Value("${GEMINI_API_URL}")
    private String geminiApiUrl;

    @Value("${GEMINI_API_KEY}")
    private String geminiApiKey;


    private final WebClient webClient;
    public AIService(WebClient.Builder webClient) {
       this.webClient = webClient.build();
   }



    public String getSummarizedResponse(String userInput) {

           StringBuilder res=new StringBuilder("Summarize the following text while maintaining technical accuracy:"+userInput);
            Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                       Map.of("parts", new Object[] {
                               Map.of("text", res.toString())
                       } )
               }
               );

        // Make API Call
       String response = webClient.post()
               .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
               .block();


        return response;
    }


    public String getConvertedResponse(String inputCode,String outputCode) {

        StringBuilder res=new StringBuilder("Convert the following "+inputCode+" code to "+outputCode);
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", res.toString())
                        } )
                }
        );

        // Make API Call
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String getLetterResponse(String companyName,String jobTitle,String jobDescription) {

        StringBuilder res=new StringBuilder("Please generate a professional cover letter for a job application with the following details:\n" +
                "Company Name:"+companyName+"\n" +
                "Job Title:"+jobTitle+"\n" +
                "Job Description: "+jobDescription+"\n" +
                "\n" +
                "Ensure the cover letter is well-structured, grammatically correct, and tailored to the provided information.");
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", res.toString())
                        } )
                }
        );

        // Make API Call
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String getRoadmapResponse(String text) {


        StringBuilder req=new StringBuilder("Create a forward-thinking, ultra-detailed 6-month roadmap to achieve [specific goal] using world-class, cutting-edge strategies." +
                "Provide an hour-by-hour daily schedule balancing learning, hands-on practice, revision, mindset development, and rejuvenation ." +
                "Define clear weekly and monthly milestones to track progress and ensure continuous improvement." +
                "Specify precise learning objectives, practical applications, and project-based tasks aligned with advanced industry standards." +
                "Include self-assessment techniques, mock tests, and peer or mentor discussions for ongoing feedback." +
                "Embed mindset growth activities, reflection prompts, and stress-management intervals to foster resilience." +
                "Outline a dynamic revision plan with daily and weekly self-testing and adaptation strategies." +
                "Incorporate problem-solving challenges that push boundaries and develop innovative thinking." +
                "Set up a feedback loop for reflecting on weaknesses, adapting tactics, and optimizing performance." +
                "Ensure the plan guarantees success if followed rigorously, with concise actionable steps and accountability checkpoints.");
        req.append(text);
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", req.toString())
                        } )
                }
        );

        // Make API Call
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String getImprovedResponse(String text) {

        StringBuilder req = new StringBuilder(
                "Rewrite the following text to enhance its grammar and clarity while preserving the original meaning. "
                        + "Correct any punctuation, sentence structure, and word usage errors. "
                        + "Respond with a single sentence and no extra words; if I supply one line, return only that line."
        );

        req.append(text);
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", req.toString())
                        } )
                }
        );

        // Make API Call
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String getEmailResponse(String text,String email,String recipient) {

        StringBuilder res=new StringBuilder("Please generate a professional email with the following details:\n" +
                " To:" +email+"\n"+
                "Recipient Name:" + recipient +"\n"+
                "Subject:" + text+"\n"+
                "The email should be well-structured, clear, and grammatically correct. Make sure to include a polite greeting and a proper closing.");

        res.append(text);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", res.toString())
                        } )
                }
        );
        // Make API Call
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }
}
