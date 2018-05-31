### Game Of Life
___________________

1. Requirements und Dependencies
2. Das Programm bauen
3. Funktionalität und Anleitung

#### 1. Requirements
Um das Game Of Life benutzen zu können muss mindestens Java der Version 8 oder 
höher installiert sein. Weitere Dependencies gibt es nicht

#### 2. Das Programm bauen

Um eine ausführbare GameOfLife.jar Datei zu erhalten müssen, je nach System, die Build-Skripte 
ausgeführt werden. Für Linux und Mac ist dies build.sh (ggf. executable flag setzen).<br>
In Windows muss die build.bat ausgeführt werden.<br>
<br>
Falls der Quellcode geändert wird sollten die .java Dateien erst wieder zu .class Dateien kompiliert werden, bevor
man das Spiel erneut baut.
<br>
Es kann natürlich auch die mitgelieferte GameOfLife.jar verwendet werden.
<br>
#### 3. Funktionalität und Anleitung

Um eine neue Instanz des Game of lifes zu erstellen müssen Zahlen im Intervall **[1,150]** bei den
beiden TextInputs, die unter "Anzahl der Zellen" stehen eingegeben werden. 
Danach reicht ein Klick auf den Start Button. Nun kann man 
den Zustand einer Zelle mittels Klick auf dieser ändern.
<br>
<br>
Generationen können nun auf verschiedene Weise berechnet werden: <br>
1. Durch das Generationen/Klick Interface. Dazu gibt man an wieviele Generationen auf einmal berechenet
werden sollen. Mit dem Pfeil nach rechts geht man diese Anzahl vorwärts. Mit dem nach Links rückwärts.

2. Mittels des Slider kann eine bestimtme Anzahl von Generationen/Minute berechnet werden.

Es können auch alle jemals gelebten Zellen angezeigt werden, indem man den Haken bei 
"Zeige besuchte Zellen" setzt.

<br>
<br>

Mittels des "Datei" Menüs können Spielkonfigurationen geladen oder gespeichert werden. Dazu müssen nur die 
entsprechenden Einträge benutzt werden. 
<br>
Es sind auch verschiedene voreingestellte Konfigurationen vorhanden, nämlich:
1. Oszillierend
2. Gleiter
3. Chaos

Unter diesen drei Menüpunkten gibt es einige bekannte Konfigurationen, sowie auch eine zufällige zu finden.





