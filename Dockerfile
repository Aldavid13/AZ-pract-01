
FROM openjdk:16
COPY * /usr/src/myapp/
WORKDIR /usr/src/myapp
USER root
ENTRYPOINT ["sh","entry.sh"]
	



