FROM jenkins/jenkins:lts
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
USER root
RUN apt-get update \
    && apt-get -y install git maven \
    && chown jenkins:jenkins /var/jenkins_home
USER jenkins
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ADD jenkins-ssh/ /var/jenkins_home/.ssh
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
RUN eval `ssh-agent -s` \
    && ssh-add
