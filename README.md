# Social Network Project

1. About Project
2. Installation

### 1. About Project
This project is a simple Social network. User can add/remove friends, send messages, etc.  
It is based on Oracle database and WebLogic server. Written on java ee 7.

### 2. Installation
	(for OS Windows. Almost the same for other OS)
1. First of all, go to oracle.com and download jdk 7,  
	oracle database 11g Enterprise Edition, WebLogic server.
2. Install jdk 7. Do not forget to set system variables:  
	JAVA_HOME, (optional) JAVA_VENDOR and add %JAVA_HOME%\bin to the Path
3. Install Oracle database.
4. Open SQL*Plus. 
	* Sign in like SYSTEM, use password you set during installation Oracle from the previous item.  
	* Run dcl_script.sql, then sign out.  
	* Now, sign in like Social. Use password "socpass".  
	* Run ddl_script.sql.
5. Install WebLogic server.
	Read readme.txt file inside WebLogic install folder.  
	(You need to create at least one domain. Do not forget to set system variable MW_HOME)
6. Now, you need to configure JDBC Data Source.  
	* You need to start WebLogic server. To start admin server, launch  
	user_projects\domains\\\<domain_name>\bin\startWeblogic.cmd
	* Open your browser and go to http://localhost:7001/console, use name and password you set in the previous item.
	* Go to Services => Data Sources. Press New => Generic Data Source  
	* Give name to your JDBC Data Source (e.g., JDBC Data Source Social)	
		**JNDI Name**:	jdbc/Social  
		**Database Type**:	Oracle  
	* Click on Next.  
		**Database Driver**:	*Oracle's Driver (Thin XA) for Instance Connections  
	* Click on Next.  
		You are in Transaction Options.  
	* Click on Next. You are in Connection Properties.  
		**Database Name**:	orcl  
		**Host Name**:		localhost  
		**Port**:		1521 (could be other. Type "netstat -a" in command prompt to see all ports)  
		**Database User Name**:	Social  
		**Password**:		socpass  
	* Click on Next.  
		You are in Test Database Connection. Check your configuration by clicking on "Test Configuration".  
	* Click on Next. You are in Select Targets.
		Select admin server. You can additionally select other servers.  
	* Click on Finish.
7. Now, environment is ready.  
	The easiest way to deploy project is to copy SocialEAR.ear to \user_projects\domains\\\<domain_name>\autodeploy  
	Or, open IDE and deploy project, using src files.  
	"Social" - is *Dynamic Web Project* with activated JPA 2.0 facet.  
8. To open homepage of the project, go to http://localhost:7001/Social
