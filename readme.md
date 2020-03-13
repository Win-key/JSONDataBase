Json Data Base
==============

This is an java library that can be used as NoSQL DataBase to store JSON Object

Get it in your system using <code>mvn install</code>

Maven dependency
================
<code>
<dependency>
	<groupId>com.winkey</groupId>
	<artifactId>JSONFileDB</artifactId>
	<version>1.0.0</version>
<dependency>
</code>

Actions you can do
==================

 * Creates Json entry in the file with Time to live
 
 * Creates Json entry in the file with Time to live = 0 (which means TimeToLive is not assigned)
 
 * Read data based on Key and returns null if not found
 
 * Removes the key (and its corresponding value) from this database. 
 
 
 T&c : Eventhough It's named as Json Data Base. 
		It's not disigned for Production purpose. It's created for Practice. Thanks for your patience to read this :P
