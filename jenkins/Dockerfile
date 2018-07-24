FROM jenkins/jenkins:lts
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
USER root
RUN apt-get update \
    && apt-get -y install git \
    && chown jenkins:jenkins /var/jenkins_home
USER jenkins
ADD jenkins-ssh/ /var/jenkins_home/.ssh
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
RUN eval `ssh-agent -s` \
    && ssh-add \
    && git config --global user.email "scm@bot.com" \
    && git config --global user.name "scm-bot"
