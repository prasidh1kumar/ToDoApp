# TodoApp

A simple and dynamic task management web application that allows users to efficiently organize their daily activities. The application supports real-time task updates and ensures data persistence using browser storage.

---

# TodoApp Backend

A comprehensive REST API backend for a task management application built with Spring Boot. This backend provides advanced todo management features including search, filtering, statistics, and bulk operations.

## 🚀 Features

### Core Functionality
- ✅ **CRUD Operations**: Create, Read, Update, Delete todos
- 🔍 **Advanced Search**: Search todos by title (case-insensitive)
- 🎯 **Smart Filtering**: Filter by completion status, priority, category, and due dates
- 📊 **Analytics Dashboard**: Comprehensive statistics and insights
- ⚡ **Bulk Operations**: Mark multiple todos as complete, delete completed todos
- ⏰ **Due Date Management**: Set deadlines and track overdue items
- 🏷️ **Categorization**: Organize todos with categories and priorities

### Enhanced Todo Model
- **Title & Description**: Detailed task descriptions
- **Priority Levels**: LOW, MEDIUM, HIGH priority classification
- **Categories**: Custom categorization for better organization
- **Due Dates**: Set and track deadlines
- **Timestamps**: Automatic creation and update tracking

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **Database**: H2 (in-memory) / Configurable for other databases
- **ORM**: Spring Data JPA
- **Build Tool**: Maven
- **Documentation**: SpringDoc OpenAPI (Swagger)
- **Testing**: JUnit 5, Spring Boot Test

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd todoapp/backend
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Access API Documentation
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/todos
```

### Authentication
Currently, no authentication is implemented. CORS is configured for `http://localhost:3000` (frontend).

---

## 🔗 API Endpoints

### 📝 Basic CRUD Operations

#### Get All Todos
```http
GET /api/todos
```
**Response**: Array of Todo objects

#### Get Todo by ID
```http
GET /api/todos/{id}
```
**Parameters**: `id` (Long) - Todo ID
**Response**: Todo object or 404 if not found

#### Create New Todo
```http
POST /api/todos
Content-Type: application/json

{
  "title": "Complete project report",
  "description": "Write comprehensive report for Q1",
  "priority": "HIGH",
  "category": "Work",
  "dueDate": "2026-04-25T17:00:00",
  "completed": false
}
```
**Response**: Created Todo object with ID

#### Update Todo
```http
PUT /api/todos/{id}
Content-Type: application/json

{
  "title": "Updated project report",
  "description": "Write comprehensive report for Q1",
  "priority": "HIGH",
  "category": "Work",
  "dueDate": "2026-04-25T17:00:00",
  "completed": true
}
```
**Response**: Updated Todo object or 404 if not found

#### Delete Todo
```http
DELETE /api/todos/{id}
```
**Response**: 204 No Content or 404 if not found

---

### 🔍 Search & Filter Operations

#### Search by Title
```http
GET /api/todos/search?title={query}
```
**Example**: `/api/todos/search?title=project`

#### Filter by Completion Status
```http
GET /api/todos/filter/completed/{completed}
```
**Example**: `/api/todos/filter/completed/true`

#### Filter by Priority
```http
GET /api/todos/filter/priority/{priority}
```
**Priority values**: `HIGH`, `MEDIUM`, `LOW`
**Example**: `/api/todos/filter/priority/HIGH`

#### Filter by Category
```http
GET /api/todos/filter/category/{category}
```
**Example**: `/api/todos/filter/category/Work`

#### Filter by Due Date (Before)
```http
GET /api/todos/filter/due-before?dateTime={iso-date}
```
**Example**: `/api/todos/filter/due-before?dateTime=2026-04-30T23:59:59`

#### Filter by Due Date (After)
```http
GET /api/todos/filter/due-after?dateTime={iso-date}
```
**Example**: `/api/todos/filter/due-after?dateTime=2026-04-01T00:00:00`

#### Get Overdue Todos
```http
GET /api/todos/overdue
```

---

### 📊 Statistics & Analytics

#### Get Todo Statistics
```http
GET /api/todos/statistics
```
**Response**:
```json
{
  "totalTodos": 15,
  "completedTodos": 8,
  "pendingTodos": 7,
  "overdueTodos": 2,
  "highPriorityTodos": 5,
  "mediumPriorityTodos": 6,
  "lowPriorityTodos": 4
}
```

---

### ⚡ Bulk Operations

#### Delete All Completed Todos
```http
DELETE /api/todos/completed
```
**Response**: 204 No Content

#### Mark Multiple Todos as Completed
```http
PUT /api/todos/bulk/complete
Content-Type: application/json

[1, 2, 3, 4, 5]
```
**Body**: Array of todo IDs to mark as completed
**Response**: Array of updated Todo objects

---

## 📋 Data Models

### Todo
```json
{
  "id": 1,
  "title": "Complete project report",
  "description": "Write comprehensive report for Q1",
  "completed": false,
  "priority": "HIGH",
  "category": "Work",
  "dueDate": "2026-04-25T17:00:00",
  "createdAt": "2026-04-19T10:30:00",
  "updatedAt": "2026-04-19T10:30:00"
}
```

### TodoRequest (for POST/PUT)
```json
{
  "title": "Complete project report",
  "description": "Write comprehensive report for Q1",
  "completed": false,
  "priority": "HIGH",
  "category": "Work",
  "dueDate": "2026-04-25T17:00:00"
}
```

### TodoStatistics
```json
{
  "totalTodos": 15,
  "completedTodos": 8,
  "pendingTodos": 7,
  "overdueTodos": 2,
  "highPriorityTodos": 5,
  "mediumPriorityTodos": 6,
  "lowPriorityTodos": 4
}
```

---

## 🏗️ Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/todo/app/
│   │   │       ├── AppApplication.java          # Main Spring Boot application
│   │   │       ├── controller/
│   │   │       │   └── TodoController.java      # REST API endpoints
│   │   │       ├── model/
│   │   │       │   ├── Todo.java                # Todo entity
│   │   │       │   ├── TodoRequest.java         # Request DTO
│   │   │       │   └── TodoStatistics.java      # Statistics DTO
│   │   │       ├── repository/
│   │   │       │   └── TodoRepository.java      # Data access layer
│   │   │       └── service/
│   │   │           ├── TodoService.java         # Service interface
│   │   │           └── TodoServiceImpl.java     # Service implementation
│   │   └── resources/
│   │       ├── application.properties          # Application configuration
│   │       └── data.sql                        # Initial data
│   └── test/
│       └── java/
│           └── com/todo/app/
│               └── AppApplicationTests.java    # Unit tests
├── pom.xml                                     # Maven configuration
└── README.md                                   # This file
```

---

## ⚙️ Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080

# Database Configuration (H2)
spring.datasource.url=jdbc:h2:mem:tododb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console (for development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# CORS Configuration
app.cors.allowed-origins=http://localhost:3000
```

---

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Test Coverage
```bash
mvn test jacoco:report
```

---

## 🚀 Deployment

### Build for Production
```bash
mvn clean package -DskipTests
```

### Run JAR File
```bash
java -jar target/todoapp-0.0.1-SNAPSHOT.jar
```

### Docker Support (Optional)
```dockerfile
FROM openjdk:17-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java naming conventions
- Write unit tests for new features
- Update documentation for API changes
- Use meaningful commit messages

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📞 Support

For questions or issues, please open an issue on GitHub or contact the development team.

---

## 🔄 Future Enhancements

- [ ] User authentication and authorization
- [ ] Email notifications for due dates
- [ ] File attachments for todos
- [ ] Todo sharing and collaboration
- [ ] Mobile app API support
- [ ] Advanced analytics and reporting
- [ ] Integration with calendar applications
- [ ] Backup and restore functionality

---

*Built with ❤️ using Spring Boot*
