FROM postgres:13-alpine
ENV POSTGRES_PASSWORD=admin
COPY init.sql /docker-entrypoint-initdb.d/
EXPOSE 5432