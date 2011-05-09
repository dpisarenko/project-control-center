// $ANTLR 3.2 Sep 23, 2009 14:05:07 src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g 2011-05-09 23:17:18

package at.silverstrike.pcc.impl.tj3bookingsparser.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class BookingsLexer extends Lexer {
    public static final int D=35;
    public static final int End=16;
    public static final int Scheduled=19;
    public static final int Start=15;
    public static final int A=36;
    public static final int Prj=5;
    public static final int CloseParen=10;
    public static final int IntegerNumber=23;
    public static final int Supplement=20;
    public static final int DayOfWeek=27;
    public static final int DateTimeWithTimeZone=7;
    public static final int Time=30;
    public static final int Colon=31;
    public static final int EOF=-1;
    public static final int FloatingPointNumber=25;
    public static final int Project=4;
    public static final int Identifier=13;
    public static final int Space=37;
    public static final int Resource=12;
    public static final int Hyphen=8;
    public static final int Plus=33;
    public static final int Off=28;
    public static final int Overtime=34;
    public static final int OpenParen=9;
    public static final int TaskIdentifier=21;
    public static final int T__38=38;
    public static final int Booking=32;
    public static final int Priority=22;
    public static final int Projectids=11;
    public static final int Scheduling=17;
    public static final int String=6;
    public static final int Task=14;
    public static final int Asap=18;
    public static final int Comma=29;
    public static final int Complete=24;
    public static final int Workinghours=26;

    // delegates
    // delegators

    public BookingsLexer() {;} 
    public BookingsLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public BookingsLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g"; }

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:7:7: ( 'h' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:7:9: 'h'
            {
            match('h'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "Workinghours"
    public final void mWorkinghours() throws RecognitionException {
        try {
            int _type = Workinghours;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:106:2: ( 'workinghours' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:106:4: 'workinghours'
            {
            match("workinghours"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Workinghours"

    // $ANTLR start "Time"
    public final void mTime() throws RecognitionException {
        try {
            int _type = Time;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:119:2: ( IntegerNumber Colon IntegerNumber )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:119:4: IntegerNumber Colon IntegerNumber
            {
            mIntegerNumber(); 
            mColon(); 
            mIntegerNumber(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Time"

    // $ANTLR start "Colon"
    public final void mColon() throws RecognitionException {
        try {
            int _type = Colon;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:123:2: ( ':' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:124:2: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Colon"

    // $ANTLR start "Comma"
    public final void mComma() throws RecognitionException {
        try {
            int _type = Comma;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:128:2: ( ',' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:129:2: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comma"

    // $ANTLR start "DayOfWeek"
    public final void mDayOfWeek() throws RecognitionException {
        try {
            int _type = DayOfWeek;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:133:2: ( 'mon' | 'tue' | 'wed' | 'thu' | 'fri' | 'sat' | 'sun' )
            int alt1=7;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:133:4: 'mon'
                    {
                    match("mon"); 


                    }
                    break;
                case 2 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:134:4: 'tue'
                    {
                    match("tue"); 


                    }
                    break;
                case 3 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:135:4: 'wed'
                    {
                    match("wed"); 


                    }
                    break;
                case 4 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:136:4: 'thu'
                    {
                    match("thu"); 


                    }
                    break;
                case 5 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:137:4: 'fri'
                    {
                    match("fri"); 


                    }
                    break;
                case 6 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:138:4: 'sat'
                    {
                    match("sat"); 


                    }
                    break;
                case 7 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:139:4: 'sun'
                    {
                    match("sun"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DayOfWeek"

    // $ANTLR start "Off"
    public final void mOff() throws RecognitionException {
        try {
            int _type = Off;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:143:2: ( 'off' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:144:2: 'off'
            {
            match("off"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Off"

    // $ANTLR start "Booking"
    public final void mBooking() throws RecognitionException {
        try {
            int _type = Booking;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:173:2: ( 'booking' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:174:2: 'booking'
            {
            match("booking"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Booking"

    // $ANTLR start "Plus"
    public final void mPlus() throws RecognitionException {
        try {
            int _type = Plus;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:178:2: ( '+' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:179:2: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Plus"

    // $ANTLR start "Overtime"
    public final void mOvertime() throws RecognitionException {
        try {
            int _type = Overtime;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:193:2: ( 'overtime' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:194:2: 'overtime'
            {
            match("overtime"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Overtime"

    // $ANTLR start "Supplement"
    public final void mSupplement() throws RecognitionException {
        try {
            int _type = Supplement;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:198:2: ( 'supplement' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:199:2: 'supplement'
            {
            match("supplement"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Supplement"

    // $ANTLR start "Priority"
    public final void mPriority() throws RecognitionException {
        try {
            int _type = Priority;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:203:2: ( 'priority' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:204:2: 'priority'
            {
            match("priority"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Priority"

    // $ANTLR start "Complete"
    public final void mComplete() throws RecognitionException {
        try {
            int _type = Complete;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:208:2: ( 'complete' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:209:2: 'complete'
            {
            match("complete"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Complete"

    // $ANTLR start "Start"
    public final void mStart() throws RecognitionException {
        try {
            int _type = Start;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:213:2: ( 'start' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:214:2: 'start'
            {
            match("start"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Start"

    // $ANTLR start "End"
    public final void mEnd() throws RecognitionException {
        try {
            int _type = End;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:218:2: ( 'end' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:219:2: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "End"

    // $ANTLR start "Scheduling"
    public final void mScheduling() throws RecognitionException {
        try {
            int _type = Scheduling;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:223:2: ( 'scheduling' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:224:2: 'scheduling'
            {
            match("scheduling"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Scheduling"

    // $ANTLR start "Asap"
    public final void mAsap() throws RecognitionException {
        try {
            int _type = Asap;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:228:2: ( 'asap' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:229:2: 'asap'
            {
            match("asap"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Asap"

    // $ANTLR start "Scheduled"
    public final void mScheduled() throws RecognitionException {
        try {
            int _type = Scheduled;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:233:2: ( 'scheduled' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:234:2: 'scheduled'
            {
            match("scheduled"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Scheduled"

    // $ANTLR start "Task"
    public final void mTask() throws RecognitionException {
        try {
            int _type = Task;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:238:2: ( 'task' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:239:2: 'task'
            {
            match("task"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Task"

    // $ANTLR start "Resource"
    public final void mResource() throws RecognitionException {
        try {
            int _type = Resource;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:243:2: ( 'resource' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:244:2: 'resource'
            {
            match("resource"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Resource"

    // $ANTLR start "Projectids"
    public final void mProjectids() throws RecognitionException {
        try {
            int _type = Projectids;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:248:2: ( 'projectids' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:249:2: 'projectids'
            {
            match("projectids"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Projectids"

    // $ANTLR start "Project"
    public final void mProject() throws RecognitionException {
        try {
            int _type = Project;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:253:3: ( 'project' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:253:6: 'project'
            {
            match("project"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Project"

    // $ANTLR start "Prj"
    public final void mPrj() throws RecognitionException {
        try {
            int _type = Prj;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:257:3: ( 'prj' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:257:6: 'prj'
            {
            match("prj"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Prj"

    // $ANTLR start "OpenParen"
    public final void mOpenParen() throws RecognitionException {
        try {
            int _type = OpenParen;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:261:3: ( '{' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:261:6: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OpenParen"

    // $ANTLR start "CloseParen"
    public final void mCloseParen() throws RecognitionException {
        try {
            int _type = CloseParen;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:265:3: ( '}' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:265:6: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CloseParen"

    // $ANTLR start "Hyphen"
    public final void mHyphen() throws RecognitionException {
        try {
            int _type = Hyphen;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:269:3: ( '-' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:269:6: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Hyphen"

    // $ANTLR start "FloatingPointNumber"
    public final void mFloatingPointNumber() throws RecognitionException {
        try {
            int _type = FloatingPointNumber;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:273:3: ( ( D )+ '.' ( D )+ )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:273:5: ( D )+ '.' ( D )+
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:273:5: ( D )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:273:5: D
            	    {
            	    mD(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            match('.'); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:273:10: ( D )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:273:10: D
            	    {
            	    mD(); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FloatingPointNumber"

    // $ANTLR start "IntegerNumber"
    public final void mIntegerNumber() throws RecognitionException {
        try {
            int _type = IntegerNumber;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:277:3: ( ( D )+ )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:277:5: ( D )+
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:277:5: ( D )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:277:5: D
            	    {
            	    mD(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IntegerNumber"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:281:3: ( ( D | A )+ )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:281:5: ( D | A )+
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:281:5: ( D | A )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "TaskIdentifier"
    public final void mTaskIdentifier() throws RecognitionException {
        try {
            int _type = TaskIdentifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:284:3: ( ( D | A ) ( D | A | '.' )+ )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:284:5: ( D | A ) ( D | A | '.' )+
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:284:10: ( D | A | '.' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='.'||(LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='Z')||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TaskIdentifier"

    // $ANTLR start "String"
    public final void mString() throws RecognitionException {
        try {
            int _type = String;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:288:3: ( '\"' (~ '\"' )* '\"' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:288:6: '\"' (~ '\"' )* '\"'
            {
            match('\"'); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:288:10: (~ '\"' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:288:10: ~ '\"'
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "String"

    // $ANTLR start "DateTimeWithTimeZone"
    public final void mDateTimeWithTimeZone() throws RecognitionException {
        try {
            int _type = DateTimeWithTimeZone;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:292:3: ( D D D D '-' D D '-' D D '-' D D ':' D D '-+' D D D D )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:292:6: D D D D '-' D D '-' D D '-' D D ':' D D '-+' D D D D
            {
            mD(); 
            mD(); 
            mD(); 
            mD(); 
            match('-'); 
            mD(); 
            mD(); 
            match('-'); 
            mD(); 
            mD(); 
            match('-'); 
            mD(); 
            mD(); 
            match(':'); 
            mD(); 
            mD(); 
            match("-+"); 

            mD(); 
            mD(); 
            mD(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DateTimeWithTimeZone"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:297:3: ( '0' .. '9' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:297:6: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:302:3: ( 'A' .. 'Z' | 'a' .. 'z' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "Space"
    public final void mSpace() throws RecognitionException {
        try {
            int _type = Space;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:3: ( ( ' ' | '\\t' | ( '\\r' )? '\\n' ) )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:6: ( ' ' | '\\t' | ( '\\r' )? '\\n' )
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:6: ( ' ' | '\\t' | ( '\\r' )? '\\n' )
            int alt9=3;
            switch ( input.LA(1) ) {
            case ' ':
                {
                alt9=1;
                }
                break;
            case '\t':
                {
                alt9=2;
                }
                break;
            case '\n':
            case '\r':
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:7: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 2 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:13: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 3 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:20: ( '\\r' )? '\\n'
                    {
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:20: ( '\\r' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='\r') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:307:20: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Space"

    public void mTokens() throws RecognitionException {
        // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:8: ( T__38 | Workinghours | Time | Colon | Comma | DayOfWeek | Off | Booking | Plus | Overtime | Supplement | Priority | Complete | Start | End | Scheduling | Asap | Scheduled | Task | Resource | Projectids | Project | Prj | OpenParen | CloseParen | Hyphen | FloatingPointNumber | IntegerNumber | Identifier | TaskIdentifier | String | DateTimeWithTimeZone | Space )
        int alt10=33;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:10: T__38
                {
                mT__38(); 

                }
                break;
            case 2 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:16: Workinghours
                {
                mWorkinghours(); 

                }
                break;
            case 3 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:29: Time
                {
                mTime(); 

                }
                break;
            case 4 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:34: Colon
                {
                mColon(); 

                }
                break;
            case 5 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:40: Comma
                {
                mComma(); 

                }
                break;
            case 6 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:46: DayOfWeek
                {
                mDayOfWeek(); 

                }
                break;
            case 7 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:56: Off
                {
                mOff(); 

                }
                break;
            case 8 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:60: Booking
                {
                mBooking(); 

                }
                break;
            case 9 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:68: Plus
                {
                mPlus(); 

                }
                break;
            case 10 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:73: Overtime
                {
                mOvertime(); 

                }
                break;
            case 11 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:82: Supplement
                {
                mSupplement(); 

                }
                break;
            case 12 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:93: Priority
                {
                mPriority(); 

                }
                break;
            case 13 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:102: Complete
                {
                mComplete(); 

                }
                break;
            case 14 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:111: Start
                {
                mStart(); 

                }
                break;
            case 15 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:117: End
                {
                mEnd(); 

                }
                break;
            case 16 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:121: Scheduling
                {
                mScheduling(); 

                }
                break;
            case 17 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:132: Asap
                {
                mAsap(); 

                }
                break;
            case 18 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:137: Scheduled
                {
                mScheduled(); 

                }
                break;
            case 19 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:147: Task
                {
                mTask(); 

                }
                break;
            case 20 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:152: Resource
                {
                mResource(); 

                }
                break;
            case 21 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:161: Projectids
                {
                mProjectids(); 

                }
                break;
            case 22 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:172: Project
                {
                mProject(); 

                }
                break;
            case 23 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:180: Prj
                {
                mPrj(); 

                }
                break;
            case 24 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:184: OpenParen
                {
                mOpenParen(); 

                }
                break;
            case 25 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:194: CloseParen
                {
                mCloseParen(); 

                }
                break;
            case 26 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:205: Hyphen
                {
                mHyphen(); 

                }
                break;
            case 27 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:212: FloatingPointNumber
                {
                mFloatingPointNumber(); 

                }
                break;
            case 28 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:232: IntegerNumber
                {
                mIntegerNumber(); 

                }
                break;
            case 29 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:246: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 30 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:257: TaskIdentifier
                {
                mTaskIdentifier(); 

                }
                break;
            case 31 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:272: String
                {
                mString(); 

                }
                break;
            case 32 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:279: DateTimeWithTimeZone
                {
                mDateTimeWithTimeZone(); 

                }
                break;
            case 33 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:300: Space
                {
                mSpace(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA1_eotS =
        "\12\uffff";
    static final String DFA1_eofS =
        "\12\uffff";
    static final String DFA1_minS =
        "\1\146\1\uffff\1\150\2\uffff\1\141\4\uffff";
    static final String DFA1_maxS =
        "\1\167\1\uffff\1\165\2\uffff\1\165\4\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\5\1\uffff\1\2\1\4\1\6\1\7";
    static final String DFA1_specialS =
        "\12\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\4\6\uffff\1\1\5\uffff\1\5\1\2\2\uffff\1\3",
            "",
            "\1\7\14\uffff\1\6",
            "",
            "",
            "\1\10\23\uffff\1\11",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "132:1: DayOfWeek : ( 'mon' | 'tue' | 'wed' | 'thu' | 'fri' | 'sat' | 'sun' );";
        }
    }
    static final String DFA10_eotS =
        "\1\uffff\1\31\1\35\1\37\2\uffff\6\35\1\uffff\5\35\3\uffff\1\35"+
        "\2\uffff\1\35\2\uffff\2\35\1\uffff\1\37\1\uffff\1\32\1\uffff\22"+
        "\35\1\114\1\37\1\116\3\114\1\35\3\114\3\35\1\123\4\35\1\130\1\35"+
        "\1\132\3\35\1\uffff\1\37\1\uffff\1\140\3\35\1\uffff\4\35\1\uffff"+
        "\1\35\1\uffff\1\151\2\35\1\37\2\uffff\1\35\1\155\6\35\1\uffff\3"+
        "\35\1\uffff\13\35\1\u0083\1\35\1\u0086\6\35\1\u008d\1\uffff\1\u008e"+
        "\1\35\1\uffff\1\u0090\1\u0091\3\35\1\u0095\2\uffff\1\35\2\uffff"+
        "\1\35\1\u0098\1\u0099\1\uffff\1\u009a\1\35\3\uffff\1\u009c\1\uffff";
    static final String DFA10_eofS =
        "\u009d\uffff";
    static final String DFA10_minS =
        "\1\11\3\56\2\uffff\6\56\1\uffff\5\56\3\uffff\1\56\2\uffff\1\56"+
        "\2\uffff\2\56\1\uffff\1\56\1\uffff\1\60\1\uffff\52\56\1\uffff\1"+
        "\55\1\uffff\4\56\1\uffff\4\56\1\uffff\1\56\1\uffff\4\56\2\uffff"+
        "\10\56\1\uffff\3\56\1\uffff\25\56\1\uffff\2\56\1\uffff\6\56\2\uffff"+
        "\1\56\2\uffff\3\56\1\uffff\2\56\3\uffff\1\56\1\uffff";
    static final String DFA10_maxS =
        "\1\175\3\172\2\uffff\6\172\1\uffff\5\172\3\uffff\1\172\2\uffff"+
        "\1\172\2\uffff\2\172\1\uffff\1\172\1\uffff\1\71\1\uffff\52\172\1"+
        "\uffff\1\172\1\uffff\4\172\1\uffff\4\172\1\uffff\1\172\1\uffff\4"+
        "\172\2\uffff\10\172\1\uffff\3\172\1\uffff\25\172\1\uffff\2\172\1"+
        "\uffff\6\172\2\uffff\1\172\2\uffff\3\172\1\uffff\2\172\3\uffff\1"+
        "\172\1\uffff";
    static final String DFA10_acceptS =
        "\4\uffff\1\4\1\5\6\uffff\1\11\5\uffff\1\30\1\31\1\32\1\uffff\1"+
        "\37\1\41\1\uffff\1\1\1\36\2\uffff\1\35\1\uffff\1\34\1\uffff\1\3"+
        "\52\uffff\1\6\1\uffff\1\33\4\uffff\1\7\4\uffff\1\27\1\uffff\1\17"+
        "\4\uffff\1\40\1\23\10\uffff\1\21\3\uffff\1\16\25\uffff\1\10\2\uffff"+
        "\1\26\6\uffff\1\12\1\14\1\uffff\1\15\1\24\3\uffff\1\22\2\uffff\1"+
        "\13\1\20\1\25\1\uffff\1\2";
    static final String DFA10_specialS =
        "\u009d\uffff}>";
    static final String[] DFA10_transitionS = {
            "\2\27\2\uffff\1\27\22\uffff\1\27\1\uffff\1\26\10\uffff\1\14"+
            "\1\5\1\24\2\uffff\12\3\1\4\6\uffff\32\25\6\uffff\1\20\1\13\1"+
            "\16\1\25\1\17\1\10\1\25\1\1\4\25\1\6\1\25\1\12\1\15\1\25\1\21"+
            "\1\11\1\7\2\25\1\2\3\25\1\22\1\uffff\1\23",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\34\11\30"+
            "\1\33\13\30",
            "\1\40\1\uffff\12\36\1\41\6\uffff\32\30\6\uffff\32\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\16\30\1\42\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\1\45\6\30\1\44\14"+
            "\30\1\43\5\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\46\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\1\47\1\30\1\52\20"+
            "\30\1\51\1\50\5\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\5\30\1\53\17\30"+
            "\1\54\4\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\16\30\1\55\13\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\56\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\16\30\1\57\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\15\30\1\60\14\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\22\30\1\61\7\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\62\25\30",
            "",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\63\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\3\30\1\64\26\30",
            "",
            "\1\40\1\uffff\12\65\1\41\6\uffff\32\30\6\uffff\32\30",
            "",
            "\12\66",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\15\30\1\67\14\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\70\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\24\30\1\71\5\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\22\30\1\72\7\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\10\30\1\73\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\23\30\1\74\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\15\30\1\75\1\30"+
            "\1\76\12\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\1\77\31\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\7\30\1\100\22\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\5\30\1\101\24\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\102\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\16\30\1\103\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\10\30\1\104\1\106"+
            "\4\30\1\105\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\14\30\1\107\15\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\3\30\1\110\26\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\1\111\31\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\22\30\1\112\7\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\12\30\1\113\17\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\40\1\uffff\12\115\1\41\6\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\66\7\uffff\32\32\6\uffff\32\32",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\12\30\1\117\17\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\17\30\1\120\12\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\121\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\122\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\124\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\12\30\1\125\17\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\16\30\1\126\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\11\30\1\127\20\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\17\30\1\131\12\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\17\30\1\133\12\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\16\30\1\134\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\10\30\1\135\21\30",
            "",
            "\1\137\1\40\1\uffff\12\136\1\41\6\uffff\32\30\6\uffff\32\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\13\30\1\141\16\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\23\30\1\142\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\3\30\1\143\26\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\23\30\1\144\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\10\30\1\145\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\146\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\147\25\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\13\30\1\150\16\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\24\30\1\152\5\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\15\30\1\153\14\30",
            "\1\40\1\uffff\12\136\1\41\6\uffff\32\30\6\uffff\32\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\154\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\24\30\1\156\5\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\10\30\1\157\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\15\30\1\160\14\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\10\30\1\161\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\2\30\1\162\27\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\163\25\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\164\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\6\30\1\165\23\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\14\30\1\166\15\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\13\30\1\167\16\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\14\30\1\170\15\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\6\30\1\171\23\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\23\30\1\172\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\23\30\1\173\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\23\30\1\174\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\2\30\1\175\27\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\7\30\1\176\22\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\177\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\u0081\3\30"+
            "\1\u0080\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\u0082\25"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\30\30\1\u0084\1"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\10\30\1\u0085\21"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\u0087\25"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\4\30\1\u0088\25"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\16\30\1\u0089\13"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\15\30\1\u008a\14"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\15\30\1\u008b\14"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\3\30\1\u008c\26"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\3\30\1\u008f\26"+
            "\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\24\30\1\u0092\5"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\23\30\1\u0093\6"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\6\30\1\u0094\23"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\22\30\1\u0096\7"+
            "\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\21\30\1\u0097\10"+
            "\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\22\30\1\u009b\7"+
            "\30",
            "",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\6\uffff\32\30",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__38 | Workinghours | Time | Colon | Comma | DayOfWeek | Off | Booking | Plus | Overtime | Supplement | Priority | Complete | Start | End | Scheduling | Asap | Scheduled | Task | Resource | Projectids | Project | Prj | OpenParen | CloseParen | Hyphen | FloatingPointNumber | IntegerNumber | Identifier | TaskIdentifier | String | DateTimeWithTimeZone | Space );";
        }
    }
 

}