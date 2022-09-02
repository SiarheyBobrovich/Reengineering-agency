FROM postgres:14.4-alpine
MAINTAINER ITAcademy

ADD /reengineering-agency/ddl/*.sql /docker-entrypoint-initdb.d
ADD /reengineering-agency/dml/*.sql /docker-entrypoint-initdb.d