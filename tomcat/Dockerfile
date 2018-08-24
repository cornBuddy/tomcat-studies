FROM tomcat:8-jre8

RUN apt update \
    && apt install -y python

RUN useradd tomcat \
    && groupadd tomcat \
    && usermod -a -G tomcat tomcat \
    && chown -R tomcat:tomcat $CATALINA_HOME

USER tomcat
