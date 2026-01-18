all:
	rm -f ../WhereAmI.jar
	./gradlew clean jar
	mv build/libs/WhereAmI.jar ..
