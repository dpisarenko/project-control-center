/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/
 
grammar Bookings;

@members
{
	private DefaultBookingsFile bookingsFile;
	
	public DefaultBookingsFile getBookingsFile()
	{
		return this.bookingsFile;
	}

}

@header { 

package at.silverstrike.pcc.impl.tj3bookingsparser.grammar; 

import org.slf4j.Logger;

}

@lexer::header {
package at.silverstrike.pcc.impl.tj3bookingsparser.grammar;
}

bookingsFile
  : 
	{
		this.bookingsFile = new DefaultBookingsFile();
	}
  header
  projectIds
  resourceDeclaration
  task+
  (
  	suppTask=supplementTask { this.bookingsFile.addSupplementStatement( $suppTask.suppStmt ); }
  )*
  supplementResource*
  EOF
  ;

header
	:
	Project Prj String String DateTimeWithTimeZone Hyphen DateTimeWithTimeZone OpenParen CloseParen
	;

projectIds:
	Projectids Prj
	;

resourceDeclaration
	: Resource Identifier String
	;

task
	:
	Task Identifier String OpenParen 
	Start DateTimeWithTimeZone
	End DateTimeWithTimeZone
	Scheduling Asap
	Scheduled
	CloseParen
	;


supplementTask returns [DefaultSupplementStatement suppStmt]
	:
		{
			suppStmt = new DefaultSupplementStatement();
		}	
	Supplement Task taskId=Identifier {suppStmt.setTaskId($taskId.text); } 
	OpenParen
	(
	bStmt=booking {suppStmt.addBookingStatement( $bStmt.stmt ); }
	)*
	(Priority IntegerNumber)*
	(Complete FloatingPointNumber)*
	CloseParen
	;

supplementResource
	:
	Supplement Resource Identifier OpenParen
	workinghours+
	CloseParen
	;

workinghours
	:
	Workinghours DayOfWeek (Off|workingIntervals)
	;

Workinghours
	: 'workinghours'
	;

workingIntervals
	:
	workingInterval (Comma workingInterval)*
	; 

workingInterval
	: Time Hyphen Time
	;
	
Time
	: IntegerNumber Colon IntegerNumber
	; 

Colon
	:
	':'
	;

Comma
	:
	','
	;

DayOfWeek
	: 'mon'
	| 'tue'
	| 'wed'
	| 'thu'
	| 'fri'
	| 'sat'
	| 'sun'
	;

Off
	:
	'off'
	;

booking  returns [DefaultBookingStatement stmt]
	:
	{
		stmt = new DefaultBookingStatement();
	}
	Booking resource=Identifier { stmt.setResource($resource.text); }  
	bt1=bookingTime { stmt.addIndBooking($bt1.indBooking); } 
	(Comma 
	bt2=bookingTime  { stmt.addIndBooking($bt2.indBooking); } 
	)*
	OpenParen 
	overtime 
	CloseParen
	;

bookingTime returns [DefaultIndBooking indBooking]
	:
	startTime=DateTimeWithTimeZone 
	Plus 
	bookingDuration=duration
	{
		$indBooking = new DefaultIndBooking($startTime.text, $bookingDuration.text);
	}
	;
	
Booking
	:
	'booking'
	;

Plus
	:
	'+'
	;

duration
	:
	FloatingPointNumber 'h'
	;

overtime
	:
	Overtime IntegerNumber
	;

Overtime
	:
	'overtime'
	;

Supplement
	:
	'supplement'
	;
	
Priority
	:
	'priority'
	;

Complete
	:
	'complete'
	;

Start
	:
	'start'
	;

End
	:
	'end'
	;

Scheduling
	:
	'scheduling'
	;
	
Asap
	:
	'asap'
	;

Scheduled
	:
	'scheduled'
	;

Task
	:
	'task'
	;

Resource
	:
	'resource'
	;

Projectids
	:
	'projectids'
	;

Project
  :  'project'
  ;

Prj
  :  'prj'
  ;

OpenParen
  :  '{'
  ;

CloseParen
  :  '}'
  ;

Hyphen
  :  '-'
  ;

FloatingPointNumber
  : D+'.'D+
  ;

IntegerNumber
  : D+
  ;

Identifier
  : (D|A)+;

String
  :  '"' ~'"'* '"'
  ;

DateTimeWithTimeZone
  :  D D D D '-' D D '-' D D '-' D D ':' D D '-+' D D D D
  ;  

fragment    
D
  :  '0'..'9'
  ;

fragment
A
  : 'A'..'Z'
  | 'a'..'z'
  ;

Space   
  :  (' ' | '\t' | '\r'? '\n'){$channel=HIDDEN;}
  ;
