## Example Prompts for Claude Desktop

Use these prompts in Claude Desktop to interact with the Spring AI MCP app:

- "List all patients."
- "Get information about patient named John Doe."
- "Show all medical reports for patient ID 1."
- "Add a new medical report for patient ID 2 with diagnosis 'Diabetes' and content 'Patient diagnosed with Type 2 Diabetes.'"
- "Update medical report ID 3 with diagnosis 'Recovered' and content 'Patient has fully recovered.'"
- "Who are the patients born after 1990?"
- "Add a female patient named Jane Smith, born on 1990-08-22."

You can customize these prompts based on the tool methods exposed by your services.
# Spring AI MCP

A Spring Boot application demonstrating Medical Care Platform (MCP) features with AI tool integration. It manages patients and their medical reports, and exposes AI-enabled service methods for use in AI workflows.

## Features
- Patient and Medical Report management using JPA entities and repositories
- DTOs for clean data transfer
- Service layer with AI tool annotations for integration
- RESTful structure, ready for extension
- Example data initialization

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

## How It Works
- **Entities**: `Patient` and `MedicalReport` are JPA entities with relationships.
- **DTOs**: `PatientDTO` is used for transferring patient data.
- **Repositories**: Standard Spring Data JPA repositories for CRUD operations.
- **Service**: `MedicalService` provides business logic and is annotated for AI tool integration.
- **Main Application**: Registers tool callbacks for AI workflows.

## Getting Started
1. **Build**: Run `./mvnw clean install` (or `mvnw.cmd` on Windows).
2. **Run**: Execute `./mvnw spring-boot:run` or run the generated JAR in `target/`.
3. **Test**: Run `./mvnw test` to execute unit tests.

## Extending
- Add more services and annotate methods with `@Tool` for AI integration.
- Register additional services in `SpringAiMcpApplication` as tool callbacks.

## Example Tool Registration
```java
@Bean
public List<ToolCallback> medicalTools(MedicalService medicalService, AnotherService anotherService) {
    return List.of(
        ToolCallbacks.from(medicalService),
        ToolCallbacks.from(anotherService)
    );
}
```

## Usage in Claude Desktop

Claude Desktop is an AI-powered desktop assistant that can interact with backend services via APIs or tool callbacks. To use this Spring AI MCP app with Claude Desktop:

1. **Start the Spring AI MCP Application**
  - Build and run the app as described above.
  - Ensure the app is running and accessible on your local network or desired endpoint.

2. **Configure Claude Desktop**
  - In Claude Desktop, add a new tool or API integration.
  - Point Claude Desktop to the endpoint where your Spring AI MCP app is running.
  - If using AI tool callbacks, ensure Claude Desktop is configured to recognize the tool methods exposed by your app (e.g., `list_patients`, `get_patient_info`, etc.).

3. **Interact with Medical Tools**
  - Use Claude Desktop's interface to send requests to the app's tool endpoints.
  - Example prompts: "List all patients", "Add a medical report for patient ID 1", etc.

4. **Extend Integration**
  - Add more annotated service methods in your app for new capabilities.
  - Register additional services in the main application as tool callbacks.

Refer to Claude Desktop's documentation for details on adding and configuring external tools.

## License
MIT
