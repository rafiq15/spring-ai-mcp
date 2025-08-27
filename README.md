# Spring AI MCP (Model Context Protocol)

A Spring Boot application demonstrating a Medical Care Platform (MCP) with AI tool integration via the **Model Context Protocol**. This project manages patients and their medical reports, and exposes AI-enabled service methods for use in AI workflows and assistants like Claude Desktop.

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

## Usage in Claude Desktop or Other MCP Agents

Claude Desktop is an AI-powered desktop assistant that can interact with backend services via APIs or Model Context Protocol tool callbacks. To use this Spring AI MCP app with Claude Desktop or any MCP-compatible agent:

1. **Start the Spring AI MCP Application**
   - Build and run the app as described above.
   - Ensure the app is running and accessible on your local network or desired endpoint.

2. **Configure Your AI Agent**
   - In Claude Desktop (or another MCP agent), add a new tool or API integration.
   - Point the agent to the endpoint where your Spring AI MCP app is running.
   - If using MCP tool callbacks, ensure the agent is configured to recognize the tool methods exposed by your app (e.g., `list_patients`, `get_patient_info`, etc.).

3. **Interact with Medical Tools**
   - Use the agent’s interface to send requests to the app’s tool endpoints.
   - Example prompts: "List all patients", "Add a medical report for patient ID 1", etc.

4. **Extend Integration**
   - Add more annotated service methods in your app for new capabilities.
   - Register additional services in the main application as tool callbacks.

Refer to your AI agent’s documentation for details on adding and configuring external tools.

---

## License

MIT
