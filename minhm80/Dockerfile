# ================================
# Stage 1: Build
# ================================
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy Maven wrapper & pom.xml trước (tận dụng Docker layer cache)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Download dependencies trước (cache layer khi code chưa thay đổi)
RUN ./mvnw dependency:go-offline -B

# Copy toàn bộ source code
COPY src ./src

# Build JAR (bỏ qua tests để nhanh hơn)
RUN ./mvnw package -DskipTests -B

# ================================
# Stage 2: Runtime (image nhỏ gọn)
# ================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Tạo user không phải root (bảo mật)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copy JAR từ stage build
COPY --from=builder /app/target/*.jar app.jar

# Expose port theo application.properties
EXPOSE 5000

# Khởi động ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
