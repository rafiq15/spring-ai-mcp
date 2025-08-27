
# Spring AI MCP (Model Context Protocol)

A Spring Boot application demonstrating a Medical Care Platform (MCP) with AI tool integration via the **Model Context Protocol**. This project manages patients and their medical reports, and exposes AI-enabled service methods for use in AI workflows and assistants like Claude Desktop.

---

## Project Overview

This project is a well-structured Spring Boot application for a Medical Care Platform (MCP) with AI tool integration via MCP. It features:

- JPA entities for `Patient` and `MedicalReport`
- DTOs for clean data transfer
- Service layer with MCP tool annotations
- Spring Data JPA repositories
- Example data initialization
- Ready for extension and AI integration

---

## Features

- Patient and Medical Report management using JPA entities and repositories
- DTOs for clean data transfer
- Service layer with AI tool annotations for Model Context Protocol (MCP) integration
- RESTful structure, ready for extension
- Example data initialization for quick start

---

## Project Structure

```
src/
  main/
    java/
      com/springai_mcp/
        SpringAiMcpApplication.java
        dto/PatientDTO.java
        entity/Patient.java, MedicalReport.java
        repository/PatientRepository.java, MedicalReportRepository.java
        service/MedicalService.java
    resources/
      application.properties
  test/
    java/
      com/springai_mcp/SpringAiMcpApplicationTests.java
```

---

## How It Works

- **Entities**: `Patient` and `MedicalReport` are JPA entities with relationships.
- **DTOs**: `PatientDTO` is used for transferring patient data.
- **Repositories**: Standard Spring Data JPA repositories for CRUD operations.
- **Service**: `MedicalService` provides business logic and is annotated for AI tool integration using MCP.
- **Main Application**: Registers tool callbacks for AI workflows, making service methods available to AI agents.

---

## Getting Started

1. **Build**:  
   Run `./mvnw clean install` (or `mvnw.cmd` on Windows).

2. **Run**:  
   Execute `./mvnw spring-boot:run` or run the generated JAR in `target/`.

3. **Test**:  
   Run `./mvnw test` to execute unit tests.

---

## Extending

- Add more services and annotate methods with `@Tool` for MCP/AI integration.
- Register additional services in `SpringAiMcpApplication` as tool callbacks.

**Example Tool Registration:**
```java
@Bean
public List<ToolCallback> medicalTools(MedicalService medicalService, AnotherService anotherService) {
    return List.of(
        ToolCallbacks.from(medicalService),
        ToolCallbacks.from(anotherService)
    );
}
```

---

## Example Prompts for Claude Desktop

Use these prompts in Claude Desktop (or any MCP-compatible AI agent) to interact with the Spring AI MCP app:

- "List all patients."
- "Get information about patient named John Doe."
- "Show all medical reports for patient ID 1."
- "Add a new medical report for patient ID 2 with diagnosis 'Diabetes' and content 'Patient diagnosed with Type 2 Diabetes.'"
- "Update medical report ID 3 with diagnosis 'Recovered' and content 'Patient has fully recovered.'"
- "Who are the patients born after 1990?"
- "Add a female patient named Jane Smith, born on 1990-08-22."

You can customize these prompts based on the tool methods exposed by your services.

---


## Connecting MCP Server to Claude Desktop

To use your Spring AI MCP server with Claude Desktop, follow these steps:

1. **Build and Start the MCP Server**
    - Open a terminal in your project directory.
    - Run the following command to build and start the server:
       ```
       ./mvnw clean install
       ./mvnw spring-boot:run
       ```
       Or, run the JAR directly:
       ```
       java -jar target/spring-ai-mcp.jar
       ```

2. **Configure Claude Desktop**
    - Open Claude Desktop.
    - Go to the settings or integrations section.
    - Add a new MCP server/tool integration.
    - For the server command, use:
       ```
       java -jar D:/AI_ML/Spring-AI-MCP/spring-ai-mcp/target/spring-ai-mcp.jar
       ```
       (Adjust the path if your JAR is elsewhere.)

    - If Claude Desktop asks for a config file, use the provided `claude_desktop_config.json`:
       ```json
       {
          "mcpServers": {
             "spring-ai-mcp-server": {
                "command": "java",
                "args": [
                   "-jar",
                   "D:/AI_ML/Spring-AI-MCP/spring-ai-mcp/target/spring-ai-mcp.jar"
                ]
             }
          }
       }
       ```

3. **Test the Connection**
    - In Claude Desktop, select the Spring AI MCP tool.
    - Try example prompts such as:
       - "List all patients."
       - "Add a new medical report for patient ID 2 with diagnosis 'Diabetes'."

4. **Extend and Customize**
    - Add more annotated service methods in your Spring Boot app for new capabilities.
    - Register additional services in `SpringAiMcpApplication` as tool callbacks.

Refer to your AI agent’s documentation for details on adding and configuring external tools.

---

## License

MIT
