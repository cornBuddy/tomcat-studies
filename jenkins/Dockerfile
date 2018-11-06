FROM jenkins/jenkins:lts
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

USER root
RUN apt-get update \
    && apt-get -y install git python-pip apt-transport-https \
        ca-certificates curl gnupg2 software-properties-common sudo \
    && pip install 'ansible==2.6.3' \
    && curl -fsSL https://download.docker.com/linux/debian/gpg \
        | apt-key add - \
    && apt-key fingerprint 0EBFCD88 \
    && add-apt-repository \
        "deb [arch=amd64] https://download.docker.com/linux/debian stretch \
        stable" \
    && apt-get update \
    && apt-get -y install docker-ce \
    && chown jenkins:jenkins /var/jenkins_home \
    && usermod -aG docker jenkins
RUN mkdir -p /ets/sudoers.d/ \
    && echo "jenkins ALL=(ALL) NOPASSWD: /bin/chmod" > /etc/sudoers.d/jenkins \
    && /usr/sbin/visudo -cf /etc/sudoers.d/jenkins

USER jenkins
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV DEPLOYER deployer
ENV DEPLOYER_PASSWORD s3cret
ENV SONAR_URL http://sonarqube:9000
ADD jenkins-ssh/ /var/jenkins_home/.ssh
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
RUN eval `ssh-agent -s` \
    && ssh-add
WORKDIR /var/jenkins_home
