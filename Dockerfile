FROM  ubuntu:18.04
RUN apt-get update
#update the current ubuntu
RUN apt install -y git
#install git on current image
RUN apt install -y openjdk-8-jdk
#install openjdk8
RUN DEBIAN_FRONTEND=noninteractive \
    TZ=America/Sao_Paulo
ADD gradle-5.0-bin.tar.xz /opt/gradle/
ENV GRADLE_HOME=/opt/gradle/
#add gradle environment variable
ENV PATH=${GRADLE_HOME}/bin:${PATH}
# finish prepare for git and gradle
#begin configure ssh for github
COPY ./github_id_rsa /root/.ssh/id_rsa
RUN chmod 700 /root/.ssh/id_rsa
RUN echo "Host github.com\n\tStrictHostKeyChecking no\n" >> /root/.ssh/config
RUN  touch /root/.ssh/known_hosts
RUN apt install -y ssh
RUN ssh-keyscan github.com >> /root/.ssh/known_hosts
# finish for github configuration
COPY startup.sh /guiabolso/
RUN chmod +x /guiabolso/startup.sh
EXPOSE 8080
#expose port outside access is 8080
ENTRYPOINT "/guiabolso/startup.sh"