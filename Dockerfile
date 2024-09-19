# Usando a imagem base do Java
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado pela aplicação para o diretório /app dentro do container
COPY target/*.jar app.jar

# Informar ao Docker qual porta o aplicativo estará escutando
EXPOSE 8083

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
