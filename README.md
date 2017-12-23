# BigBankKnightRoaster

Solution for [Dragons of Mugloar](http://www.dragonsofmugloar.com)

## Build and run
Battle repetitions can be set as a command line argument. It will only work if there is exactly one integer argument. If no argument is provided or argument is invalid, the program will default to **100** repetitions.
### Build
```./gradlew build```
### Run
```java -jar build/libs/KnightRoaster-0.0.1.jar```

or

```java -jar build/libs/KnightRoaster-0.0.1.jar <repetitions>```

## Technical notes
* Written in Kotlin.
* Gradle as build tool.
* Spring Boot framework was used for DI and to remove network request boilerplate.

## Solution notes
* In the case of network problems/no internet access, the application crashes. This is expected behaviour as there is no use case for network problems.
* The algorithm for creating dragons is ad hoc, because I thought creating a reinforcement learning algorithm for this test would be an overkill, so I did not pursue it.
* I’m pro-testing and TDD, but unfortunately no tests here.
