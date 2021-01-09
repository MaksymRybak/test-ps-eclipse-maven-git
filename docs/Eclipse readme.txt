link al corso https://app.pluralsight.com/library/courses/eclipse-java-developers
github https://github.com/JosePaumard

Impostazione dell'ambiente di sviluppo
	eclipse, quando parte, usa JRE del SO, se non la trova, la dobbiamo installare, se non possiamo installarla, nel file eclipse.ini specifichiamo il parametro -vm puntando
	cambiamo la codifica del file *.java in modo che viene impostata UTF-8
	cambiamo la formattazione del codice, per es. sostituendo identazione TAB con SPACE
	possiamo cambiare JRE del progetto se vogliamo, ricordiamoci di cambiare anche il Compiler
	possiamo referenziare un'altro progetto nel Build Path, se ci occorrono le sue dipendenze
	creiamo la cartella nel Build Path che puo' essere usata per i tests (es. src-test)
	riferenziato sempre da Build Path la libreria JUnit e scriviamo il test 
	referenziamo ArrertJ al posto di JUnit, come sostituiamo una lib, creazione di una User Lib AsserJ che referenzia il JAR esterno
	esportiamo il progetto in un archivio *.jar 
		selezioniamo solo i package che vogliamo esportare
Impostazione dell'ambiente Maven in Eclipse
	viene usato un plugin in eclipse x supportare maven
	ultima versione di eclipse contiene gia' maven integrato
		se vogliamo referenziare una versione specifica di maven, dobbiamo configurare opportunamente il plugin
	referenziamo maven locale sulla nostra macchina, puntiamo al suo file di settings.xml, controlliamo che e' stato aggiornato anche il path del repo maven locale
	creiamo un nuovo progetto maven (viene creato con la ver 1.5 di JRE)
	aggiungiamo tre props in pom.xml x impostare la versione di java del nostro codice, versione di java con la quale vogliamo compilare, codifica UTF-8
	eseguiamo Reindex del repo in modo che viene scaricato in locale indice di dipendenze del repo maven pubblico
		questo ci permette di fare la ricerca di una dipendenza
	aggiungiamo la dipendenza, tasto dx del mouse, maven, add dependency
	possiamo scaricare sia sorgenti che javadoc di una dipendenza con maven
	per lanciare il goal di maven non visibile dal menu, creiamo nuova Run Configurations 
	aggiungiamo plugin jacoco x eseguire la verifica della copertura del nostro codice con i tests
		va lanciato con mvn eseguendo in secuenza i goals clean, verify, jacoco:report
	creiamo 2 progetti, uno con interfaccia, uno con la sua implementazione
		implementazione dipende dall'interfaccia -> aggiungiamo la dipendenza nel pom.xml -> [NOTA: importante che la versione della dipendenza e' in linea in entrambi i pom.xml / 
		la versione giusta e' installata nel repo maven locale] -> implementando l'interfaccia NON abbiamo piu' l'errore
	creiamo parent project con pom.xml che contiene <packaging>pom</packaging> - in questo modo creiamo solo il progetto con pom.xml che sara' il padre per tutti altri
		in questo modo possiamo centralizzare la configurazione di maven comune a tutti i progetti (es. proprieta' comuni, dipendenze di librerie comuni)
	possiamo lanciare i test e creare il pacchetto partendo dal progetto parent (quello che contiene N moduli che sono poi N progetti maven)
	NOTA: i progetti figli sono creati nella cartella del pom.xml parent!!!!
Utilizzo di Git in Eclipse
	git viene supportato da eclipse con un plugin, gia' installato insieme ad un git embedded nell'ultima versione di eclipse
	modifichiamo la config di git in eclipse (es. nome e email)
	creiamo un progetto semplice e lo aggiungiamo al repo git
	aggiungiamo in .gitignore quello che non dobbiamo versionare (es. files .project, .classpath, ./bin/, ./settings/)
	commit del gitignore NOTA: "add to index" sposta il fine in staged area
	aggiungiamo e commitiamo codice java
	vediamo history (possiamo linkare history all'elemento selezionato in Package Explorer
	possiamo fare il checkout di un commit precedente - NOTA: fare il checkout senza creare branch SOLO x vedere la relativa versione del codice NON fare dei commit, per questo serve creare prima una
		branch dedicata -> possiamo tornare all'ultimo commit visualizzando tutte le branch del repo facendo il checkout dell'ultimo commit
	creiamo branch partendo da un commit nel passato facendo automaticamente anche il checkout -> facciamo modifiche su questo branch -> facciamo i commit necessari
		-> per fare il merge su master eseguiamo il checkout di master -> tasto dx sul develop -> merge -> se ci sono conflitti li risolviamo -> commit finale di merge 
	push sul repo remoto
		creiamo repo su github -> facciamo push di tutti branch e tags locali nel repo remoto -> NOTA: tale operazione viene eseguita dalla maschera Git Repositories, sezione Remotes 
		-> alla fine devono apparire le configurazioni PULL e PUSH
	per non salvare username e pwd in eclipse configuriamo la chiave e SSH -> eliminiamo la config PUSH -> configuriamo la chiave SSH in locale e nel profilo di GitHub seguendo istruzioni qui
	https://help.github.com/en/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent -> cambiamo la configurazione PUSH eliminando la vecchia e aggiungendo la nuova usando SSH url del 
	repo di github
	reimpostiamo anche la configurazione FETCH per poter fare pull usando la connessione SSH
	importiamo il progetto da Git -> il progetto viene importato come un progetto generico (NOTA: non abbiamo archiviamo su git i file di configurazione per es. file .project) ->
		-> una volta importato trasformiamo il progetto in un progetto java -> tasto dx, properties, project facets, convert to facet form, scegliamo java 
	importiamo progetto maven dal repo git -> dobbiamo installare il plugin per SCM di maven m2e-egit -> 
		NOTA: dobbiamo installare da Eclipse - Install New Software e incollando Url https://repo1.maven.org/maven2/.m2e/connectors/m2eclipse-egit/0.15.1/N/0.15.1.201806191431/
		come descritto qui https://stackoverflow.com/questions/51359823/m2e-egit-connector-for-scm-on-eclipse-photon-failure
		-> una volta importato fixiamo le cartelle che servono a maven per funzionare correttamente (es. src/main/resources, src/test/resources, src/test/java)
Usiamo Eclipse con il DB MySQL
	Eclipse EE contiene il plugin x SQL
	Eclipse serve JDBC driver x connettersi al DB server
		NOTA: JDBC di JDK principalmente contiene le interfacce, il fornitore di ogni driver specifico si occupa ad implementarle
	Il driver viene registrato a livello di workspace (cartella .metadata)
	Se ci serve la connessione al DB su ogni progetto, dobbiamo crearla separatamente per ogni progetto
	usiamo la view Data Source Explorer 
	creiamo la connessione a MySQL sul Docker
		docker run -p 3306:3306 --name docker-mysql -e MYSQL_ROOT_PASSWORD=pluralsight -d mysql:latest
		docker run -it --network bridge --rm mysql mysql -hdocker-mysql -upluralsight -ppluralsight
	in eclipse c'e' il plugin dbeaver che consente manipolare il DB abbastanza agevolmente
	ho creato utente e uno schema nuovo per i test da Eclipse
	export e import possiamo eseguire sia da eclipse che da workbench
Impostare progetto EE con Tomcat in Eclipse
	eclipse supporta la creazione di progetti EE
	le connessioni a app. server sono salvati nella cartella .metadata di eclipse
	referenziamo la cartella di tomcat server nelle impostazioni di eclipse (window - preferences - server - runtime env)
	creiamo il progetto Dynamic Web Project, runtime env impostato prima viene mostrato anche in questo wizard
	creiamo un progetto web usando maven -> il packaging impostiamo war, aggiungiamo il tag <failOnMissingWebXml>false</failOnMissingWebXml> nel pom.xml in modo che non contenga l'errore relativo
		al web.xml mancante (prima era' un file descriptor opzionale, e' diventato obbligatorio nelle versioni successive...) -> nelle proprieta' di progetto andiamo a Project Facets e scegliamo
		il runtime di Apache Tomcat (NON viene automaticamente impostato quando creiamo il progetto)
	lanciamo il nostro progetto sul Web Server scegliendo la versione da noi configurata prima (Tomcat 9.0)
	di default le impostazioni di tomcat e' ripubblicare il war se il suo contenuto e' stato aggiornato 
		possiamo disabilitare quessto comportamento -> doppio click sull'istanza di Tomcat Server, si apre la pagina di configurazione di Tomcat
	aggiungiamo un nuovo servlet che riceve una richiesta GET, costruisce una lista di oggetti, e la passa ad una pagina JSP (tramite attributi della request)
	la pagina JSP usa JSTL (Java Servlet Tag Library) per iterare la lista di oggetti passati
	NOTA: ogni volta che modifichiamo un servlet DOBBIAMO riavviare Tomcat (nella view Servers tra le [...] vediamo lo stato dia del server che dell'app
	lanciamo il server in Debug Mode x eseguire il debugging della nostra webapp
	per creare il pacchetto war aggiungiamo una nuova Run Configuration che esegue il goal war:war (NOTA: x sicurezza e' meglio eseguire clean package war:war)
	x eseguire il debugging remoto deployamo la nostra app su tomcat standalone (lanciato dalla linea di comando "catalina.bat start")
		-> lanciamo tomcat in debugging mode "catalina.bat jpda start", jpda sta per Java Platform Debugging Architecture
		-> configuriamo eclipse aggiungengo nuovo Debug Configuration selezionando il nostro progetto (localhost e la porta 8000 e' OK)
		-> mettendo un breakpoint e rieseguendo la richiesta al nostro servlet, eclipse si ferma nel punto breakpoint 
		x dettagli su JDPA vedi https://docs.oracle.com/javase/8/docs/technotes/guides/jpda/architecture.html
		
		
	
	
	