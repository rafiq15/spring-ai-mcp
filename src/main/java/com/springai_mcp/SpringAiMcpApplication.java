package com.springai_mcp;

import com.springai_mcp.service.MedicalService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAiMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiMcpApplication.class, args);
    }

    @Bean
    public List<ToolCallback> medicalTools(MedicalService medicalService) {
        return List.of(
                ToolCallbacks.from(medicalService)
               // ToolCallbacks.from(anotherService)
        );
    }
}
