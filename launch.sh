#/bin/bash
chmod 755 Bomberman\ project/ucigame.jar
javac -cp Bomberman\ project/src -sourcepath Bomberman\ project/ucigame.jar -d Bomberman\ project/bin Bomberman\ project/src/Main.java
java -jar Bomberman\ project/ucigame.jar -cp Bomberman\ project/bin Main
