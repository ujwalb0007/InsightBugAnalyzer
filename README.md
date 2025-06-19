
##   InsightBugAnalyzer   ##

**An Intelligent Bug Analytics and QA Automation Engine**  
Built with Spring Boot, REST APIs, ML Scoring, Log Pattern Analysis, and Static Code Analysis Tools.

---

##  Project Overview

**InsightBugAnalyzer** is a full-fledged QA and bug triage platform that analyzes logs, test failures, code, and bug reports to provide intelligent classification, scoring, and mapping. It combines rule-based logic, machine learning (Weka), static code analysis tools, and RESTful microservices to automate bug-related insights.

 Ideal for: QA Engineers, SDETs, DevOps teams, and automation testers seeking smarter testing and debugging workflows.

---

##  Project Structure

InsightBugSmart/
├── src/
│ ├── main/java/com/InsightBugAnalyzer/
│ │ ├── controller/ ← REST API Endpoints
│ │ ├── service/ ← Core logic for ML, heuristics, mapping
│ │ ├── rules/ ← Rule engine, pattern classifiers
│ │ ├── util/ ← Weka trainers, helpers
│ └── resources/
│ ├── impact_training.arff ← ML model training data
│ ├── testcase_failure_mapping.json ← Mapping data
│ └── application.properties
├── logs/ ← Sample log files for analysis
├── checkstyle.xml ← Static code analysis config
├── pom.xml ← Maven build + tool integrations
└── README.md



## Features Implemented

### Rule-Based Bug Classifier
- Matches keywords in logs/stack traces to predefined rules
- Easy to extend using `.json` or hardcoded logic
- Example: `NullPointerException → severity=High, cause=Null reference`

---

### Static Code Analysis
- Integrated with **Checkstyle**, **SpotBugs**, and **PMD**
- Auto-checks code for:
  - Dead code
  - Null pointer risks
  - Unused variables
  - Bad patterns
- Runs during Maven build: `mvn clean install`

---

### Log Pattern Mining
- Parses `.log` files using regex filters
- Extracts high-frequency failure patterns (e.g., timeouts, DB errors)
- Assigns priority scores based on frequency + timestamps

---

### Signature-Based Classification
- Maps exception classes to known bugs
- E.g., `BadCredentialsException → Login Failure`

---

### Heuristic Impact Estimator
- Applies scoring logic based on content:
  - Title contains "Login" → +5
  - Description contains "Payment" → +8
  - Reported by multiple users → +10
- Output: `impactScore (0–10)`

---

### ML-Based Bug Scoring
- Weka-based Linear Regression model
- Input features:
  - `titleLength`
  - `stackTraceLength`
  - `userReports`
- Trained with: `impact_training.arff`
- Exposed via API: `/api/ml/score`

---

### Test Case Failure Mapping
- Maps failing test cases to known bug IDs
- Uses:
  - Static name mappings
  - Keyword classifiers
  - Exception phrases
  - HTTP error codes

---

##  REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/ml/score` | Predicts bug impact using ML or heuristics |
| POST | `/api/testcase/map` | Maps test case failures to bug IDs |
| POST | `/api/logs/analyze` | Analyzes logs for common error patterns |
| POST | `/api/bugs/classify` | Signature-based exception classification |

---

##  Technologies Used

- **Spring Boot** (REST API backend)
- **Java 17+**
- **Weka** (ML model for scoring)
- **Checkstyle**, **PMD**, **SpotBugs** (Static code tools)
- **Swagger** (API documentation)
- **Maven** (Build + dependency management)



##  Run the Project

### Prerequisites:
- Java 17+
- Maven

### Run:

mvn clean install
mvn spring-boot:run
Access:
API Docs: http://localhost:8080/swagger-ui.html

Log Analyzer, Classifiers, Scorers → Available as REST endpoints

## Upcoming Features (WIP)

 MockMvc + JUnit Test Suite

 React-based QA Dashboard

 CI/CD Ready GitHub Actions

 Webhook Integration with Jira, Slack


**** Author ****
Built with by a passionate QA Automation Engineer
