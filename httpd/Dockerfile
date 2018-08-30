FROM httpd:2

RUN apt-get -y update \
    && apt-get -y install python libapache2-mod-jk \
    && mkdir -p /var/log/apache2 \
    && chmod 700 /var/log/apache2/ \
    && touch /var/log/apache2/mod_jk.log
