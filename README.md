# gpsd.client
Java implementation of a Linux GPSD Client through TCP connection
## Dependencies
- For some tests with timezone, I've used the code from: https://github.com/agap/llttz

## Compiling
- Just clone this repository and run the following maven command `mvn clean install -DskipTests`. If you want to generate a executable jar package use this command: `mvn clean install -DskipTests package`.
