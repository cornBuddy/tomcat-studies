create user sonar;
alter role sonar with createdb;
alter user sonar with encrypted password 'sonar';
create database sonar owner sonar;
grant all privileges on database sonar to sonar;
