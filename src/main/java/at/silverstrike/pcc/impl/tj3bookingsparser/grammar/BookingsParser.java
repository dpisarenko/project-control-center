// $ANTLR 3.2 Sep 23, 2009 14:05:07 src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g 2011-05-09 23:21:01
 

package at.silverstrike.pcc.impl.tj3bookingsparser.grammar; 

import org.slf4j.Logger;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/
public class BookingsParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Project", "Prj", "String", "DateTimeWithTimeZone", "Hyphen", "OpenParen", "CloseParen", "Projectids", "Resource", "Identifier", "Task", "Start", "End", "Scheduling", "Asap", "Scheduled", "Supplement", "Priority", "IntegerNumber", "Complete", "FloatingPointNumber", "Workinghours", "DayOfWeek", "Off", "Comma", "Time", "Colon", "Booking", "Plus", "Overtime", "D", "A", "Space", "'h'"
    };
    public static final int D=34;
    public static final int Scheduled=19;
    public static final int End=16;
    public static final int Start=15;
    public static final int A=35;
    public static final int Prj=5;
    public static final int CloseParen=10;
    public static final int IntegerNumber=22;
    public static final int Supplement=20;
    public static final int DayOfWeek=26;
    public static final int DateTimeWithTimeZone=7;
    public static final int Time=29;
    public static final int Colon=30;
    public static final int EOF=-1;
    public static final int FloatingPointNumber=24;
    public static final int Project=4;
    public static final int Identifier=13;
    public static final int Space=36;
    public static final int Plus=32;
    public static final int Hyphen=8;
    public static final int Resource=12;
    public static final int Off=27;
    public static final int Overtime=33;
    public static final int T__37=37;
    public static final int OpenParen=9;
    public static final int Booking=31;
    public static final int Priority=21;
    public static final int Projectids=11;
    public static final int Scheduling=17;
    public static final int String=6;
    public static final int Task=14;
    public static final int Asap=18;
    public static final int Comma=28;
    public static final int Workinghours=25;
    public static final int Complete=23;

    // delegates
    // delegators


        public BookingsParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public BookingsParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return BookingsParser.tokenNames; }
    public String getGrammarFileName() { return "src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g"; }


    	private DefaultBookingsFile bookingsFile;
    	
    	public DefaultBookingsFile getBookingsFile()
    	{
    		return this.bookingsFile;
    	}




    // $ANTLR start "bookingsFile"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:37:1: bookingsFile : header projectIds resourceDeclaration ( task )+ (suppTask= supplementTask )* ( supplementResource )* EOF ;
    public final void bookingsFile() throws RecognitionException {
        DefaultSupplementStatement suppTask = null;


        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:38:3: ( header projectIds resourceDeclaration ( task )+ (suppTask= supplementTask )* ( supplementResource )* EOF )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:39:2: header projectIds resourceDeclaration ( task )+ (suppTask= supplementTask )* ( supplementResource )* EOF
            {

            		this.bookingsFile = new DefaultBookingsFile();
            	
            pushFollow(FOLLOW_header_in_bookingsFile43);
            header();

            state._fsp--;

            pushFollow(FOLLOW_projectIds_in_bookingsFile47);
            projectIds();

            state._fsp--;

            pushFollow(FOLLOW_resourceDeclaration_in_bookingsFile51);
            resourceDeclaration();

            state._fsp--;

            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:45:3: ( task )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==Task) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:45:3: task
            	    {
            	    pushFollow(FOLLOW_task_in_bookingsFile55);
            	    task();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:46:3: (suppTask= supplementTask )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==Supplement) ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1==Task) ) {
                        alt2=1;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:47:4: suppTask= supplementTask
            	    {
            	    pushFollow(FOLLOW_supplementTask_in_bookingsFile67);
            	    suppTask=supplementTask();

            	    state._fsp--;

            	     this.bookingsFile.addSupplementStatement( suppTask ); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:49:3: ( supplementResource )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==Supplement) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:49:3: supplementResource
            	    {
            	    pushFollow(FOLLOW_supplementResource_in_bookingsFile78);
            	    supplementResource();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match(input,EOF,FOLLOW_EOF_in_bookingsFile83); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "bookingsFile"


    // $ANTLR start "header"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:53:1: header : Project Prj String String DateTimeWithTimeZone Hyphen DateTimeWithTimeZone OpenParen CloseParen ;
    public final void header() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:54:2: ( Project Prj String String DateTimeWithTimeZone Hyphen DateTimeWithTimeZone OpenParen CloseParen )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:55:2: Project Prj String String DateTimeWithTimeZone Hyphen DateTimeWithTimeZone OpenParen CloseParen
            {
            match(input,Project,FOLLOW_Project_in_header96); 
            match(input,Prj,FOLLOW_Prj_in_header98); 
            match(input,String,FOLLOW_String_in_header100); 
            match(input,String,FOLLOW_String_in_header102); 
            match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_header104); 
            match(input,Hyphen,FOLLOW_Hyphen_in_header106); 
            match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_header108); 
            match(input,OpenParen,FOLLOW_OpenParen_in_header110); 
            match(input,CloseParen,FOLLOW_CloseParen_in_header112); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "header"


    // $ANTLR start "projectIds"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:58:1: projectIds : Projectids Prj ;
    public final void projectIds() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:58:11: ( Projectids Prj )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:59:2: Projectids Prj
            {
            match(input,Projectids,FOLLOW_Projectids_in_projectIds122); 
            match(input,Prj,FOLLOW_Prj_in_projectIds124); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "projectIds"


    // $ANTLR start "resourceDeclaration"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:62:1: resourceDeclaration : Resource Identifier String ;
    public final void resourceDeclaration() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:63:2: ( Resource Identifier String )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:63:4: Resource Identifier String
            {
            match(input,Resource,FOLLOW_Resource_in_resourceDeclaration135); 
            match(input,Identifier,FOLLOW_Identifier_in_resourceDeclaration137); 
            match(input,String,FOLLOW_String_in_resourceDeclaration139); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "resourceDeclaration"


    // $ANTLR start "task"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:66:1: task : Task Identifier String OpenParen ( task )* Start DateTimeWithTimeZone End DateTimeWithTimeZone Scheduling Asap Scheduled CloseParen ;
    public final void task() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:67:2: ( Task Identifier String OpenParen ( task )* Start DateTimeWithTimeZone End DateTimeWithTimeZone Scheduling Asap Scheduled CloseParen )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:68:2: Task Identifier String OpenParen ( task )* Start DateTimeWithTimeZone End DateTimeWithTimeZone Scheduling Asap Scheduled CloseParen
            {
            match(input,Task,FOLLOW_Task_in_task151); 
            match(input,Identifier,FOLLOW_Identifier_in_task153); 
            match(input,String,FOLLOW_String_in_task155); 
            match(input,OpenParen,FOLLOW_OpenParen_in_task157); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:69:2: ( task )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==Task) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:69:2: task
            	    {
            	    pushFollow(FOLLOW_task_in_task160);
            	    task();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,Start,FOLLOW_Start_in_task165); 
            match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_task167); 
            match(input,End,FOLLOW_End_in_task170); 
            match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_task172); 
            match(input,Scheduling,FOLLOW_Scheduling_in_task175); 
            match(input,Asap,FOLLOW_Asap_in_task177); 
            match(input,Scheduled,FOLLOW_Scheduled_in_task180); 
            match(input,CloseParen,FOLLOW_CloseParen_in_task183); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "task"


    // $ANTLR start "supplementTask"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:78:1: supplementTask returns [DefaultSupplementStatement suppStmt] : Supplement Task taskId= Identifier OpenParen ( Priority IntegerNumber )* (bStmt= booking )* ( Complete FloatingPointNumber )* CloseParen ;
    public final DefaultSupplementStatement supplementTask() throws RecognitionException {
        DefaultSupplementStatement suppStmt = null;

        Token taskId=null;
        DefaultBookingStatement bStmt = null;


        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:79:2: ( Supplement Task taskId= Identifier OpenParen ( Priority IntegerNumber )* (bStmt= booking )* ( Complete FloatingPointNumber )* CloseParen )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:80:3: Supplement Task taskId= Identifier OpenParen ( Priority IntegerNumber )* (bStmt= booking )* ( Complete FloatingPointNumber )* CloseParen
            {

            			suppStmt = new DefaultSupplementStatement();
            		
            match(input,Supplement,FOLLOW_Supplement_in_supplementTask205); 
            match(input,Task,FOLLOW_Task_in_supplementTask207); 
            taskId=(Token)match(input,Identifier,FOLLOW_Identifier_in_supplementTask211); 
            suppStmt.setTaskId((taskId!=null?taskId.getText():null)); 
            match(input,OpenParen,FOLLOW_OpenParen_in_supplementTask217); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:85:2: ( Priority IntegerNumber )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==Priority) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:85:3: Priority IntegerNumber
            	    {
            	    match(input,Priority,FOLLOW_Priority_in_supplementTask221); 
            	    match(input,IntegerNumber,FOLLOW_IntegerNumber_in_supplementTask223); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:86:2: (bStmt= booking )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==Booking) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:87:2: bStmt= booking
            	    {
            	    pushFollow(FOLLOW_booking_in_supplementTask233);
            	    bStmt=booking();

            	    state._fsp--;

            	    suppStmt.addBookingStatement( bStmt ); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:89:2: ( Complete FloatingPointNumber )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==Complete) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:89:3: Complete FloatingPointNumber
            	    {
            	    match(input,Complete,FOLLOW_Complete_in_supplementTask243); 
            	    match(input,FloatingPointNumber,FOLLOW_FloatingPointNumber_in_supplementTask245); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match(input,CloseParen,FOLLOW_CloseParen_in_supplementTask250); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return suppStmt;
    }
    // $ANTLR end "supplementTask"


    // $ANTLR start "supplementResource"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:93:1: supplementResource : Supplement Resource Identifier OpenParen ( workinghours )+ CloseParen ;
    public final void supplementResource() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:94:2: ( Supplement Resource Identifier OpenParen ( workinghours )+ CloseParen )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:95:2: Supplement Resource Identifier OpenParen ( workinghours )+ CloseParen
            {
            match(input,Supplement,FOLLOW_Supplement_in_supplementResource262); 
            match(input,Resource,FOLLOW_Resource_in_supplementResource264); 
            match(input,Identifier,FOLLOW_Identifier_in_supplementResource266); 
            match(input,OpenParen,FOLLOW_OpenParen_in_supplementResource268); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:96:2: ( workinghours )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==Workinghours) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:96:2: workinghours
            	    {
            	    pushFollow(FOLLOW_workinghours_in_supplementResource271);
            	    workinghours();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            match(input,CloseParen,FOLLOW_CloseParen_in_supplementResource275); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "supplementResource"


    // $ANTLR start "workinghours"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:100:1: workinghours : Workinghours DayOfWeek ( Off | workingIntervals ) ;
    public final void workinghours() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:101:2: ( Workinghours DayOfWeek ( Off | workingIntervals ) )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:102:2: Workinghours DayOfWeek ( Off | workingIntervals )
            {
            match(input,Workinghours,FOLLOW_Workinghours_in_workinghours287); 
            match(input,DayOfWeek,FOLLOW_DayOfWeek_in_workinghours289); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:102:25: ( Off | workingIntervals )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==Off) ) {
                alt9=1;
            }
            else if ( (LA9_0==Time) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:102:26: Off
                    {
                    match(input,Off,FOLLOW_Off_in_workinghours292); 

                    }
                    break;
                case 2 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:102:30: workingIntervals
                    {
                    pushFollow(FOLLOW_workingIntervals_in_workinghours294);
                    workingIntervals();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "workinghours"


    // $ANTLR start "workingIntervals"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:109:1: workingIntervals : workingInterval ( Comma workingInterval )* ;
    public final void workingIntervals() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:110:2: ( workingInterval ( Comma workingInterval )* )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:111:2: workingInterval ( Comma workingInterval )*
            {
            pushFollow(FOLLOW_workingInterval_in_workingIntervals318);
            workingInterval();

            state._fsp--;

            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:111:18: ( Comma workingInterval )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==Comma) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:111:19: Comma workingInterval
            	    {
            	    match(input,Comma,FOLLOW_Comma_in_workingIntervals321); 
            	    pushFollow(FOLLOW_workingInterval_in_workingIntervals323);
            	    workingInterval();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "workingIntervals"


    // $ANTLR start "workingInterval"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:114:1: workingInterval : Time Hyphen Time ;
    public final void workingInterval() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:115:2: ( Time Hyphen Time )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:115:4: Time Hyphen Time
            {
            match(input,Time,FOLLOW_Time_in_workingInterval337); 
            match(input,Hyphen,FOLLOW_Hyphen_in_workingInterval339); 
            match(input,Time,FOLLOW_Time_in_workingInterval341); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "workingInterval"


    // $ANTLR start "booking"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:147:1: booking returns [DefaultBookingStatement stmt] : Booking resource= Identifier bt1= bookingTime ( Comma bt2= bookingTime )* OpenParen overtime CloseParen ;
    public final DefaultBookingStatement booking() throws RecognitionException {
        DefaultBookingStatement stmt = null;

        Token resource=null;
        DefaultIndBooking bt1 = null;

        DefaultIndBooking bt2 = null;


        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:148:2: ( Booking resource= Identifier bt1= bookingTime ( Comma bt2= bookingTime )* OpenParen overtime CloseParen )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:149:2: Booking resource= Identifier bt1= bookingTime ( Comma bt2= bookingTime )* OpenParen overtime CloseParen
            {

            		stmt = new DefaultBookingStatement();
            	
            match(input,Booking,FOLLOW_Booking_in_booking455); 
            resource=(Token)match(input,Identifier,FOLLOW_Identifier_in_booking459); 
             stmt.setResource((resource!=null?resource.getText():null)); 
            pushFollow(FOLLOW_bookingTime_in_booking468);
            bt1=bookingTime();

            state._fsp--;

             stmt.addIndBooking(bt1); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:154:2: ( Comma bt2= bookingTime )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==Comma) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:154:3: Comma bt2= bookingTime
            	    {
            	    match(input,Comma,FOLLOW_Comma_in_booking475); 
            	    pushFollow(FOLLOW_bookingTime_in_booking481);
            	    bt2=bookingTime();

            	    state._fsp--;

            	     stmt.addIndBooking(bt2); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            match(input,OpenParen,FOLLOW_OpenParen_in_booking492); 
            pushFollow(FOLLOW_overtime_in_booking496);
            overtime();

            state._fsp--;

            match(input,CloseParen,FOLLOW_CloseParen_in_booking500); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return stmt;
    }
    // $ANTLR end "booking"


    // $ANTLR start "bookingTime"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:162:1: bookingTime returns [DefaultIndBooking indBooking] : startTime= DateTimeWithTimeZone Plus bookingDuration= duration ;
    public final DefaultIndBooking bookingTime() throws RecognitionException {
        DefaultIndBooking indBooking = null;

        Token startTime=null;
        BookingsParser.duration_return bookingDuration = null;


        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:163:2: (startTime= DateTimeWithTimeZone Plus bookingDuration= duration )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:164:2: startTime= DateTimeWithTimeZone Plus bookingDuration= duration
            {
            startTime=(Token)match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_bookingTime518); 
            match(input,Plus,FOLLOW_Plus_in_bookingTime522); 
            pushFollow(FOLLOW_duration_in_bookingTime528);
            bookingDuration=duration();

            state._fsp--;


            		indBooking = new DefaultIndBooking((startTime!=null?startTime.getText():null), (bookingDuration!=null?input.toString(bookingDuration.start,bookingDuration.stop):null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return indBooking;
    }
    // $ANTLR end "bookingTime"

    public static class duration_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "duration"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:182:1: duration : FloatingPointNumber 'h' ;
    public final BookingsParser.duration_return duration() throws RecognitionException {
        BookingsParser.duration_return retval = new BookingsParser.duration_return();
        retval.start = input.LT(1);

        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:183:2: ( FloatingPointNumber 'h' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:184:2: FloatingPointNumber 'h'
            {
            match(input,FloatingPointNumber,FOLLOW_FloatingPointNumber_in_duration568); 
            match(input,37,FOLLOW_37_in_duration570); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "duration"


    // $ANTLR start "overtime"
    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:187:1: overtime : Overtime IntegerNumber ;
    public final void overtime() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:188:2: ( Overtime IntegerNumber )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:189:2: Overtime IntegerNumber
            {
            match(input,Overtime,FOLLOW_Overtime_in_overtime582); 
            match(input,IntegerNumber,FOLLOW_IntegerNumber_in_overtime584); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "overtime"

    // Delegated rules


 

    public static final BitSet FOLLOW_header_in_bookingsFile43 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_projectIds_in_bookingsFile47 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_resourceDeclaration_in_bookingsFile51 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_task_in_bookingsFile55 = new BitSet(new long[]{0x0000000000104000L});
    public static final BitSet FOLLOW_supplementTask_in_bookingsFile67 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_supplementResource_in_bookingsFile78 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_EOF_in_bookingsFile83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Project_in_header96 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Prj_in_header98 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_String_in_header100 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_String_in_header102 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_header104 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_Hyphen_in_header106 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_header108 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_OpenParen_in_header110 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CloseParen_in_header112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Projectids_in_projectIds122 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Prj_in_projectIds124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Resource_in_resourceDeclaration135 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_resourceDeclaration137 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_String_in_resourceDeclaration139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Task_in_task151 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_task153 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_String_in_task155 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_OpenParen_in_task157 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_task_in_task160 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_Start_in_task165 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_task167 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_End_in_task170 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_task172 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_Scheduling_in_task175 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_Asap_in_task177 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_Scheduled_in_task180 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CloseParen_in_task183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Supplement_in_supplementTask205 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_Task_in_supplementTask207 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_supplementTask211 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_OpenParen_in_supplementTask217 = new BitSet(new long[]{0x0000000080A00400L});
    public static final BitSet FOLLOW_Priority_in_supplementTask221 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IntegerNumber_in_supplementTask223 = new BitSet(new long[]{0x0000000080A00400L});
    public static final BitSet FOLLOW_booking_in_supplementTask233 = new BitSet(new long[]{0x0000000080800400L});
    public static final BitSet FOLLOW_Complete_in_supplementTask243 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FloatingPointNumber_in_supplementTask245 = new BitSet(new long[]{0x0000000000800400L});
    public static final BitSet FOLLOW_CloseParen_in_supplementTask250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Supplement_in_supplementResource262 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Resource_in_supplementResource264 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_supplementResource266 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_OpenParen_in_supplementResource268 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_workinghours_in_supplementResource271 = new BitSet(new long[]{0x0000000002000400L});
    public static final BitSet FOLLOW_CloseParen_in_supplementResource275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Workinghours_in_workinghours287 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_DayOfWeek_in_workinghours289 = new BitSet(new long[]{0x0000000028000000L});
    public static final BitSet FOLLOW_Off_in_workinghours292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_workingIntervals_in_workinghours294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_workingInterval_in_workingIntervals318 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_Comma_in_workingIntervals321 = new BitSet(new long[]{0x0000000028000000L});
    public static final BitSet FOLLOW_workingInterval_in_workingIntervals323 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_Time_in_workingInterval337 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_Hyphen_in_workingInterval339 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_Time_in_workingInterval341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Booking_in_booking455 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_booking459 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_bookingTime_in_booking468 = new BitSet(new long[]{0x0000000010000200L});
    public static final BitSet FOLLOW_Comma_in_booking475 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_bookingTime_in_booking481 = new BitSet(new long[]{0x0000000010000200L});
    public static final BitSet FOLLOW_OpenParen_in_booking492 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_overtime_in_booking496 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CloseParen_in_booking500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_bookingTime518 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_Plus_in_bookingTime522 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_duration_in_bookingTime528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FloatingPointNumber_in_duration568 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_duration570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Overtime_in_overtime582 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IntegerNumber_in_overtime584 = new BitSet(new long[]{0x0000000000000002L});

}