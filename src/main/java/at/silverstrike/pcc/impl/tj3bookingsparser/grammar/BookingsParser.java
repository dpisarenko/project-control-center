// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g 2010-11-05 07:17:38
 

package at.silverstrike.pcc.impl.tj3bookingsparser.grammar; 

import org.slf4j.Logger;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
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
    public String getGrammarFileName() { return "src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g"; }


    	private DefaultBookingsFile bookingsFile;
    	
    	public DefaultBookingsFile getBookingsFile()
    	{
    		return this.bookingsFile;
    	}




    // $ANTLR start "bookingsFile"
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:45:1: bookingsFile : header projectIds resourceDeclaration ( task )+ (suppTask= supplementTask )* ( supplementResource )* EOF ;
    public final void bookingsFile() throws RecognitionException {
        DefaultSupplementStatement suppTask = null;


        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:46:3: ( header projectIds resourceDeclaration ( task )+ (suppTask= supplementTask )* ( supplementResource )* EOF )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:47:2: header projectIds resourceDeclaration ( task )+ (suppTask= supplementTask )* ( supplementResource )* EOF
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

            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:53:3: ( task )+
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
            	    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:53:3: task
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

            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:54:3: (suppTask= supplementTask )*
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
            	    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:55:4: suppTask= supplementTask
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

            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:57:3: ( supplementResource )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==Supplement) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:57:3: supplementResource
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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:61:1: header : Project Prj String String DateTimeWithTimeZone Hyphen DateTimeWithTimeZone OpenParen CloseParen ;
    public final void header() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:62:2: ( Project Prj String String DateTimeWithTimeZone Hyphen DateTimeWithTimeZone OpenParen CloseParen )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:63:2: Project Prj String String DateTimeWithTimeZone Hyphen DateTimeWithTimeZone OpenParen CloseParen
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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:66:1: projectIds : Projectids Prj ;
    public final void projectIds() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:66:11: ( Projectids Prj )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:67:2: Projectids Prj
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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:70:1: resourceDeclaration : Resource Identifier String ;
    public final void resourceDeclaration() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:71:2: ( Resource Identifier String )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:71:4: Resource Identifier String
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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:74:1: task : Task Identifier String OpenParen Start DateTimeWithTimeZone End DateTimeWithTimeZone Scheduling Asap Scheduled CloseParen ;
    public final void task() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:75:2: ( Task Identifier String OpenParen Start DateTimeWithTimeZone End DateTimeWithTimeZone Scheduling Asap Scheduled CloseParen )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:76:2: Task Identifier String OpenParen Start DateTimeWithTimeZone End DateTimeWithTimeZone Scheduling Asap Scheduled CloseParen
            {
            match(input,Task,FOLLOW_Task_in_task151); 
            match(input,Identifier,FOLLOW_Identifier_in_task153); 
            match(input,String,FOLLOW_String_in_task155); 
            match(input,OpenParen,FOLLOW_OpenParen_in_task157); 
            match(input,Start,FOLLOW_Start_in_task161); 
            match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_task163); 
            match(input,End,FOLLOW_End_in_task166); 
            match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_task168); 
            match(input,Scheduling,FOLLOW_Scheduling_in_task171); 
            match(input,Asap,FOLLOW_Asap_in_task173); 
            match(input,Scheduled,FOLLOW_Scheduled_in_task176); 
            match(input,CloseParen,FOLLOW_CloseParen_in_task179); 

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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:85:1: supplementTask returns [DefaultSupplementStatement suppStmt] : Supplement Task taskId= Identifier OpenParen Priority IntegerNumber (bStmt= booking )* Complete FloatingPointNumber CloseParen ;
    public final DefaultSupplementStatement supplementTask() throws RecognitionException {
        DefaultSupplementStatement suppStmt = null;

        Token taskId=null;
        DefaultBookingStatement bStmt = null;


        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:86:2: ( Supplement Task taskId= Identifier OpenParen Priority IntegerNumber (bStmt= booking )* Complete FloatingPointNumber CloseParen )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:87:3: Supplement Task taskId= Identifier OpenParen Priority IntegerNumber (bStmt= booking )* Complete FloatingPointNumber CloseParen
            {

            			suppStmt = new DefaultSupplementStatement();
            		
            match(input,Supplement,FOLLOW_Supplement_in_supplementTask201); 
            match(input,Task,FOLLOW_Task_in_supplementTask203); 
            taskId=(Token)match(input,Identifier,FOLLOW_Identifier_in_supplementTask207); 
            suppStmt.setTaskId((taskId!=null?taskId.getText():null)); 
            match(input,OpenParen,FOLLOW_OpenParen_in_supplementTask213); 
            match(input,Priority,FOLLOW_Priority_in_supplementTask216); 
            match(input,IntegerNumber,FOLLOW_IntegerNumber_in_supplementTask218); 
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:93:2: (bStmt= booking )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==Booking) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:94:2: bStmt= booking
            	    {
            	    pushFollow(FOLLOW_booking_in_supplementTask226);
            	    bStmt=booking();

            	    state._fsp--;

            	    suppStmt.addBookingStatement( bStmt ); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,Complete,FOLLOW_Complete_in_supplementTask235); 
            match(input,FloatingPointNumber,FOLLOW_FloatingPointNumber_in_supplementTask237); 
            match(input,CloseParen,FOLLOW_CloseParen_in_supplementTask240); 

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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:100:1: supplementResource : Supplement Resource Identifier OpenParen ( workinghours )+ CloseParen ;
    public final void supplementResource() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:101:2: ( Supplement Resource Identifier OpenParen ( workinghours )+ CloseParen )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:102:2: Supplement Resource Identifier OpenParen ( workinghours )+ CloseParen
            {
            match(input,Supplement,FOLLOW_Supplement_in_supplementResource252); 
            match(input,Resource,FOLLOW_Resource_in_supplementResource254); 
            match(input,Identifier,FOLLOW_Identifier_in_supplementResource256); 
            match(input,OpenParen,FOLLOW_OpenParen_in_supplementResource258); 
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:103:2: ( workinghours )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==Workinghours) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:103:2: workinghours
            	    {
            	    pushFollow(FOLLOW_workinghours_in_supplementResource261);
            	    workinghours();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            match(input,CloseParen,FOLLOW_CloseParen_in_supplementResource265); 

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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:107:1: workinghours : Workinghours DayOfWeek ( Off | workingIntervals ) ;
    public final void workinghours() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:108:2: ( Workinghours DayOfWeek ( Off | workingIntervals ) )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:109:2: Workinghours DayOfWeek ( Off | workingIntervals )
            {
            match(input,Workinghours,FOLLOW_Workinghours_in_workinghours277); 
            match(input,DayOfWeek,FOLLOW_DayOfWeek_in_workinghours279); 
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:109:25: ( Off | workingIntervals )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==Off) ) {
                alt6=1;
            }
            else if ( (LA6_0==Time) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:109:26: Off
                    {
                    match(input,Off,FOLLOW_Off_in_workinghours282); 

                    }
                    break;
                case 2 :
                    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:109:30: workingIntervals
                    {
                    pushFollow(FOLLOW_workingIntervals_in_workinghours284);
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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:116:1: workingIntervals : workingInterval ( Comma workingInterval )* ;
    public final void workingIntervals() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:117:2: ( workingInterval ( Comma workingInterval )* )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:118:2: workingInterval ( Comma workingInterval )*
            {
            pushFollow(FOLLOW_workingInterval_in_workingIntervals308);
            workingInterval();

            state._fsp--;

            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:118:18: ( Comma workingInterval )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==Comma) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:118:19: Comma workingInterval
            	    {
            	    match(input,Comma,FOLLOW_Comma_in_workingIntervals311); 
            	    pushFollow(FOLLOW_workingInterval_in_workingIntervals313);
            	    workingInterval();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:121:1: workingInterval : Time Hyphen Time ;
    public final void workingInterval() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:122:2: ( Time Hyphen Time )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:122:4: Time Hyphen Time
            {
            match(input,Time,FOLLOW_Time_in_workingInterval327); 
            match(input,Hyphen,FOLLOW_Hyphen_in_workingInterval329); 
            match(input,Time,FOLLOW_Time_in_workingInterval331); 

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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:154:1: booking returns [DefaultBookingStatement stmt] : Booking resource= Identifier bt1= bookingTime ( Comma bt2= bookingTime )* OpenParen overtime CloseParen ;
    public final DefaultBookingStatement booking() throws RecognitionException {
        DefaultBookingStatement stmt = null;

        Token resource=null;
        DefaultIndBooking bt1 = null;

        DefaultIndBooking bt2 = null;


        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:155:2: ( Booking resource= Identifier bt1= bookingTime ( Comma bt2= bookingTime )* OpenParen overtime CloseParen )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:156:2: Booking resource= Identifier bt1= bookingTime ( Comma bt2= bookingTime )* OpenParen overtime CloseParen
            {

            		stmt = new DefaultBookingStatement();
            	
            match(input,Booking,FOLLOW_Booking_in_booking445); 
            resource=(Token)match(input,Identifier,FOLLOW_Identifier_in_booking449); 
             stmt.setResource((resource!=null?resource.getText():null)); 
            pushFollow(FOLLOW_bookingTime_in_booking458);
            bt1=bookingTime();

            state._fsp--;

             stmt.addIndBooking(bt1); 
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:161:2: ( Comma bt2= bookingTime )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==Comma) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:161:3: Comma bt2= bookingTime
            	    {
            	    match(input,Comma,FOLLOW_Comma_in_booking465); 
            	    pushFollow(FOLLOW_bookingTime_in_booking471);
            	    bt2=bookingTime();

            	    state._fsp--;

            	     stmt.addIndBooking(bt2); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match(input,OpenParen,FOLLOW_OpenParen_in_booking482); 
            pushFollow(FOLLOW_overtime_in_booking486);
            overtime();

            state._fsp--;

            match(input,CloseParen,FOLLOW_CloseParen_in_booking490); 

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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:169:1: bookingTime returns [DefaultIndBooking indBooking] : startTime= DateTimeWithTimeZone Plus bookingDuration= duration ;
    public final DefaultIndBooking bookingTime() throws RecognitionException {
        DefaultIndBooking indBooking = null;

        Token startTime=null;
        BookingsParser.duration_return bookingDuration = null;


        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:170:2: (startTime= DateTimeWithTimeZone Plus bookingDuration= duration )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:171:2: startTime= DateTimeWithTimeZone Plus bookingDuration= duration
            {
            startTime=(Token)match(input,DateTimeWithTimeZone,FOLLOW_DateTimeWithTimeZone_in_bookingTime508); 
            match(input,Plus,FOLLOW_Plus_in_bookingTime512); 
            pushFollow(FOLLOW_duration_in_bookingTime518);
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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:189:1: duration : FloatingPointNumber 'h' ;
    public final BookingsParser.duration_return duration() throws RecognitionException {
        BookingsParser.duration_return retval = new BookingsParser.duration_return();
        retval.start = input.LT(1);

        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:190:2: ( FloatingPointNumber 'h' )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:191:2: FloatingPointNumber 'h'
            {
            match(input,FloatingPointNumber,FOLLOW_FloatingPointNumber_in_duration558); 
            match(input,37,FOLLOW_37_in_duration560); 

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
    // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:194:1: overtime : Overtime IntegerNumber ;
    public final void overtime() throws RecognitionException {
        try {
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:195:2: ( Overtime IntegerNumber )
            // src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g:196:2: Overtime IntegerNumber
            {
            match(input,Overtime,FOLLOW_Overtime_in_overtime572); 
            match(input,IntegerNumber,FOLLOW_IntegerNumber_in_overtime574); 

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
    public static final BitSet FOLLOW_OpenParen_in_task157 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_Start_in_task161 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_task163 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_End_in_task166 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_task168 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_Scheduling_in_task171 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_Asap_in_task173 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_Scheduled_in_task176 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CloseParen_in_task179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Supplement_in_supplementTask201 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_Task_in_supplementTask203 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_supplementTask207 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_OpenParen_in_supplementTask213 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_Priority_in_supplementTask216 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IntegerNumber_in_supplementTask218 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_booking_in_supplementTask226 = new BitSet(new long[]{0x0000000080800000L});
    public static final BitSet FOLLOW_Complete_in_supplementTask235 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_FloatingPointNumber_in_supplementTask237 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CloseParen_in_supplementTask240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Supplement_in_supplementResource252 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Resource_in_supplementResource254 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_supplementResource256 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_OpenParen_in_supplementResource258 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_workinghours_in_supplementResource261 = new BitSet(new long[]{0x0000000002000400L});
    public static final BitSet FOLLOW_CloseParen_in_supplementResource265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Workinghours_in_workinghours277 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_DayOfWeek_in_workinghours279 = new BitSet(new long[]{0x0000000028000000L});
    public static final BitSet FOLLOW_Off_in_workinghours282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_workingIntervals_in_workinghours284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_workingInterval_in_workingIntervals308 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_Comma_in_workingIntervals311 = new BitSet(new long[]{0x0000000028000000L});
    public static final BitSet FOLLOW_workingInterval_in_workingIntervals313 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_Time_in_workingInterval327 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_Hyphen_in_workingInterval329 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_Time_in_workingInterval331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Booking_in_booking445 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_Identifier_in_booking449 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_bookingTime_in_booking458 = new BitSet(new long[]{0x0000000010000200L});
    public static final BitSet FOLLOW_Comma_in_booking465 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_bookingTime_in_booking471 = new BitSet(new long[]{0x0000000010000200L});
    public static final BitSet FOLLOW_OpenParen_in_booking482 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_overtime_in_booking486 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CloseParen_in_booking490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DateTimeWithTimeZone_in_bookingTime508 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_Plus_in_bookingTime512 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_duration_in_bookingTime518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FloatingPointNumber_in_duration558 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_duration560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Overtime_in_overtime572 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_IntegerNumber_in_overtime574 = new BitSet(new long[]{0x0000000000000002L});

}