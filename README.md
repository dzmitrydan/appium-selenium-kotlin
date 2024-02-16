# Selenium Kotlin Gradle project
## Description
- Selenium
- Kotlin
- Gradle
- TestNG

#### Test launch options:
- Browser (comment in the file:  src/main/kotlin/driver/DriverFactory.kt)
    - Google Chrome
    - Firefox
- Browser mode (comment in the file:  src/main/kotlin/driver/DriverFactory.kt)
    - Headless
    - Not headless

#### Run tests
```
./gradlew clean test -PsimpleTest
```

```
./gradlew clean test
```