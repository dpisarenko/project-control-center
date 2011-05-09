// $ANTLR 3.2 Sep 23, 2009 14:05:07 src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g 2011-05-10 01:40:52

package at.silverstrike.pcc.impl.tj3bookingsparser.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class BookingsLexer extends Lexer {
    public static final int D=33;
    public static final int Scheduled=19;
    public static final int End=16;
    public static final int Start=15;
    public static final int A=37;
    public static final int Prj=5;
    public static final int CloseParen=10;
    public static final int IntegerNumber=24;
    public static final int Supplement=20;
    public static final int H=35;
    public static final int DayOfWeek=26;
    public static final int DateTimeWithTimeZone=7;
    public static final int Time=29;
    public static final int Colon=30;
    public static final int P=34;
    public static final int EOF=-1;
    public static final int Project=4;
    public static final int FloatingPointNumber=22;
    public static final int Identifier=13;
    public static final int Space=38;
    public static final int Resource=12;
    public static final int Hyphen=8;
    public static final int Plus=32;
    public static final int Off=27;
    public static final int Overtime=36;
    public static final int OpenParen=9;
    public static final int Booking=31;
    public static final int Priority=23;
    public static final int Projectids=11;
    public static final int Scheduling=17;
    public static final int String=6;
    public static final int Task=14;
    public static final int Asap=18;
    public static final int Comma=28;
    public static final int Complete=21;
    public static final int Workinghours=25;

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

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:189:3: ( 'h' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:189:5: 'h'
            {
            match('h'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "Overtime"
    public final void mOvertime() throws RecognitionException {
        try {
            int _type = Overtime;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:198:2: ( 'overtime' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:199:2: 'overtime'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:203:2: ( 'supplement' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:204:2: 'supplement'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:208:2: ( 'priority' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:209:2: 'priority'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:213:2: ( 'complete' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:214:2: 'complete'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:218:2: ( 'start' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:219:2: 'start'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:223:2: ( 'end' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:224:2: 'end'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:228:2: ( 'scheduling' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:229:2: 'scheduling'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:233:2: ( 'asap' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:234:2: 'asap'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:238:2: ( 'scheduled' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:239:2: 'scheduled'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:243:2: ( 'task' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:244:2: 'task'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:248:2: ( 'resource' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:249:2: 'resource'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:253:2: ( 'projectids' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:254:2: 'projectids'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:258:3: ( 'project' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:258:6: 'project'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:262:3: ( 'prj' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:262:6: 'prj'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:266:3: ( '{' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:266:6: '{'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:270:3: ( '}' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:270:6: '}'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:274:3: ( '-' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:274:6: '-'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:278:3: ( ( D )+ '.' ( D )+ )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:278:5: ( D )+ '.' ( D )+
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:278:5: ( D )+
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
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:278:5: D
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:278:10: ( D )+
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
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:278:10: D
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:282:3: ( ( D )+ )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:282:5: ( D )+
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:282:5: ( D )+
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
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:282:5: D
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:286:3: ( ( D | A | P )+ )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:286:5: ( D | A | P )+
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:286:5: ( D | A | P )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='.'||(LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
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

    // $ANTLR start "String"
    public final void mString() throws RecognitionException {
        try {
            int _type = String;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:289:3: ( '\"' (~ '\"' )* '\"' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:289:6: '\"' (~ '\"' )* '\"'
            {
            match('\"'); 
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:289:10: (~ '\"' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:289:10: ~ '\"'
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
            	    break loop6;
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:293:3: ( D D D D '-' D D '-' D D '-' D D ':' D D '-+' D D D D )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:293:6: D D D D '-' D D '-' D D '-' D D ':' D D '-+' D D D D
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:298:3: ( '0' .. '9' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:298:6: '0' .. '9'
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
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:303:3: ( 'A' .. 'Z' | 'a' .. 'z' )
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

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:309:3: ( '.' )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:309:5: '.'
            {
            match('.'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Space"
    public final void mSpace() throws RecognitionException {
        try {
            int _type = Space;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:3: ( ( ' ' | '\\t' | ( '\\r' )? '\\n' ) )
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:6: ( ' ' | '\\t' | ( '\\r' )? '\\n' )
            {
            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:6: ( ' ' | '\\t' | ( '\\r' )? '\\n' )
            int alt8=3;
            switch ( input.LA(1) ) {
            case ' ':
                {
                alt8=1;
                }
                break;
            case '\t':
                {
                alt8=2;
                }
                break;
            case '\n':
            case '\r':
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:7: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 2 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:13: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 3 :
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:20: ( '\\r' )? '\\n'
                    {
                    // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:20: ( '\\r' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='\r') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:313:20: '\\r'
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
        // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:8: ( Workinghours | Time | Colon | Comma | DayOfWeek | Off | Booking | Plus | Overtime | Supplement | Priority | Complete | Start | End | Scheduling | Asap | Scheduled | Task | Resource | Projectids | Project | Prj | OpenParen | CloseParen | Hyphen | FloatingPointNumber | IntegerNumber | Identifier | String | DateTimeWithTimeZone | Space )
        int alt9=31;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:10: Workinghours
                {
                mWorkinghours(); 

                }
                break;
            case 2 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:23: Time
                {
                mTime(); 

                }
                break;
            case 3 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:28: Colon
                {
                mColon(); 

                }
                break;
            case 4 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:34: Comma
                {
                mComma(); 

                }
                break;
            case 5 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:40: DayOfWeek
                {
                mDayOfWeek(); 

                }
                break;
            case 6 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:50: Off
                {
                mOff(); 

                }
                break;
            case 7 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:54: Booking
                {
                mBooking(); 

                }
                break;
            case 8 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:62: Plus
                {
                mPlus(); 

                }
                break;
            case 9 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:67: Overtime
                {
                mOvertime(); 

                }
                break;
            case 10 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:76: Supplement
                {
                mSupplement(); 

                }
                break;
            case 11 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:87: Priority
                {
                mPriority(); 

                }
                break;
            case 12 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:96: Complete
                {
                mComplete(); 

                }
                break;
            case 13 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:105: Start
                {
                mStart(); 

                }
                break;
            case 14 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:111: End
                {
                mEnd(); 

                }
                break;
            case 15 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:115: Scheduling
                {
                mScheduling(); 

                }
                break;
            case 16 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:126: Asap
                {
                mAsap(); 

                }
                break;
            case 17 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:131: Scheduled
                {
                mScheduled(); 

                }
                break;
            case 18 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:141: Task
                {
                mTask(); 

                }
                break;
            case 19 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:146: Resource
                {
                mResource(); 

                }
                break;
            case 20 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:155: Projectids
                {
                mProjectids(); 

                }
                break;
            case 21 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:166: Project
                {
                mProject(); 

                }
                break;
            case 22 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:174: Prj
                {
                mPrj(); 

                }
                break;
            case 23 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:178: OpenParen
                {
                mOpenParen(); 

                }
                break;
            case 24 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:188: CloseParen
                {
                mCloseParen(); 

                }
                break;
            case 25 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:199: Hyphen
                {
                mHyphen(); 

                }
                break;
            case 26 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:206: FloatingPointNumber
                {
                mFloatingPointNumber(); 

                }
                break;
            case 27 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:226: IntegerNumber
                {
                mIntegerNumber(); 

                }
                break;
            case 28 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:240: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 29 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:251: String
                {
                mString(); 

                }
                break;
            case 30 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:258: DateTimeWithTimeZone
                {
                mDateTimeWithTimeZone(); 

                }
                break;
            case 31 :
                // src\\main\\java\\at\\silverstrike\\pcc\\impl\\tj3bookingsparser\\grammar\\Bookings.g:1:279: Space
                {
                mSpace(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA9 dfa9 = new DFA9(this);
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
    static final String DFA9_eotS =
        "\1\uffff\1\24\1\31\2\uffff\6\24\1\uffff\5\24\6\uffff\2\24\1\uffff"+
        "\1\31\1\24\1\uffff\22\24\1\107\1\31\1\111\3\107\1\24\3\107\3\24"+
        "\1\116\4\24\1\123\1\24\1\125\3\24\1\uffff\1\31\1\uffff\1\133\3\24"+
        "\1\uffff\4\24\1\uffff\1\24\1\uffff\1\144\2\24\1\uffff\1\31\1\uffff"+
        "\1\24\1\150\6\24\1\uffff\3\24\1\uffff\13\24\1\176\1\24\1\u0081\6"+
        "\24\1\u0088\1\uffff\1\u0089\1\24\1\uffff\1\u008b\1\u008c\3\24\1"+
        "\u0090\2\uffff\1\24\2\uffff\1\24\1\u0093\1\u0094\1\uffff\1\u0095"+
        "\1\24\3\uffff\1\u0097\1\uffff";
    static final String DFA9_eofS =
        "\u0098\uffff";
    static final String DFA9_minS =
        "\1\11\1\145\1\56\2\uffff\1\157\1\141\1\162\1\141\1\146\1\157\1"+
        "\uffff\1\162\1\157\1\156\1\163\1\145\6\uffff\1\162\1\144\1\uffff"+
        "\1\56\1\60\1\uffff\1\156\1\145\1\165\1\163\1\151\1\164\1\156\1\141"+
        "\1\150\1\146\1\145\1\157\1\151\1\155\1\144\1\141\1\163\1\153\6\56"+
        "\1\153\3\56\1\160\1\162\1\145\1\56\1\162\1\153\1\157\1\152\1\56"+
        "\1\160\1\56\1\160\1\157\1\151\1\uffff\1\55\1\uffff\1\56\1\154\1"+
        "\164\1\144\1\uffff\1\164\1\151\1\162\1\145\1\uffff\1\154\1\uffff"+
        "\1\56\1\165\1\156\1\uffff\1\56\1\uffff\1\145\1\56\1\165\1\151\1"+
        "\156\1\151\1\143\1\145\1\uffff\1\162\1\147\1\155\1\uffff\1\154\1"+
        "\155\1\147\3\164\1\143\1\150\3\145\1\56\1\171\1\56\2\145\1\157\2"+
        "\156\1\144\1\56\1\uffff\1\56\1\144\1\uffff\2\56\1\165\1\164\1\147"+
        "\1\56\2\uffff\1\163\2\uffff\1\162\2\56\1\uffff\1\56\1\163\3\uffff"+
        "\1\56\1\uffff";
    static final String DFA9_maxS =
        "\1\175\1\157\1\172\2\uffff\1\157\1\165\1\162\1\165\1\166\1\157"+
        "\1\uffff\1\162\1\157\1\156\1\163\1\145\6\uffff\1\162\1\144\1\uffff"+
        "\1\172\1\71\1\uffff\1\156\1\145\1\165\1\163\1\151\1\164\1\160\1"+
        "\141\1\150\1\146\1\145\2\157\1\155\1\144\1\141\1\163\1\153\6\172"+
        "\1\153\3\172\1\160\1\162\1\145\1\172\1\162\1\153\1\157\1\152\1\172"+
        "\1\160\1\172\1\160\1\157\1\151\1\uffff\1\172\1\uffff\1\172\1\154"+
        "\1\164\1\144\1\uffff\1\164\1\151\1\162\1\145\1\uffff\1\154\1\uffff"+
        "\1\172\1\165\1\156\1\uffff\1\172\1\uffff\1\145\1\172\1\165\1\151"+
        "\1\156\1\151\1\143\1\145\1\uffff\1\162\1\147\1\155\1\uffff\1\154"+
        "\1\155\1\147\3\164\1\143\1\150\1\145\1\151\1\145\1\172\1\171\1\172"+
        "\2\145\1\157\2\156\1\144\1\172\1\uffff\1\172\1\144\1\uffff\2\172"+
        "\1\165\1\164\1\147\1\172\2\uffff\1\163\2\uffff\1\162\2\172\1\uffff"+
        "\1\172\1\163\3\uffff\1\172\1\uffff";
    static final String DFA9_acceptS =
        "\3\uffff\1\3\1\4\6\uffff\1\10\5\uffff\1\27\1\30\1\31\1\34\1\35"+
        "\1\37\2\uffff\1\33\2\uffff\1\2\52\uffff\1\5\1\uffff\1\32\4\uffff"+
        "\1\6\4\uffff\1\26\1\uffff\1\16\3\uffff\1\36\1\uffff\1\22\10\uffff"+
        "\1\20\3\uffff\1\15\25\uffff\1\7\2\uffff\1\25\6\uffff\1\11\1\13\1"+
        "\uffff\1\14\1\23\3\uffff\1\21\2\uffff\1\12\1\17\1\24\1\uffff\1\1";
    static final String DFA9_specialS =
        "\u0098\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\26\2\uffff\1\26\22\uffff\1\26\1\uffff\1\25\10\uffff\1\13"+
            "\1\4\1\23\1\24\1\uffff\12\2\1\3\6\uffff\32\24\6\uffff\1\17\1"+
            "\12\1\15\1\24\1\16\1\7\6\24\1\5\1\24\1\11\1\14\1\24\1\20\1\10"+
            "\1\6\2\24\1\1\3\24\1\21\1\uffff\1\22",
            "\1\30\11\uffff\1\27",
            "\1\33\1\uffff\12\32\1\34\6\uffff\32\24\6\uffff\32\24",
            "",
            "",
            "\1\35",
            "\1\40\6\uffff\1\37\14\uffff\1\36",
            "\1\41",
            "\1\42\1\uffff\1\45\20\uffff\1\44\1\43",
            "\1\46\17\uffff\1\47",
            "\1\50",
            "",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\56",
            "\1\57",
            "",
            "\1\33\1\uffff\12\60\1\34\6\uffff\32\24\6\uffff\32\24",
            "\12\61",
            "",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70\1\uffff\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77\1\101\4\uffff\1\100",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\33\1\uffff\12\110\1\34\6\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\61\7\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\112",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\124",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\126",
            "\1\127",
            "\1\130",
            "",
            "\1\131\1\33\1\uffff\12\132\1\34\6\uffff\32\24\6\uffff\32\24",
            "",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\134",
            "\1\135",
            "\1\136",
            "",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "",
            "\1\143",
            "",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\145",
            "\1\146",
            "",
            "\1\33\1\uffff\12\132\1\34\6\uffff\32\24\6\uffff\32\24",
            "",
            "\1\147",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "",
            "\1\157",
            "\1\160",
            "\1\161",
            "",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\174\3\uffff\1\173",
            "\1\175",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\177",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\10\24\1\u0080\21"+
            "\24",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\u008a",
            "",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "",
            "",
            "\1\u0091",
            "",
            "",
            "\1\u0092",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            "\1\u0096",
            "",
            "",
            "",
            "\1\24\1\uffff\12\24\7\uffff\32\24\6\uffff\32\24",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( Workinghours | Time | Colon | Comma | DayOfWeek | Off | Booking | Plus | Overtime | Supplement | Priority | Complete | Start | End | Scheduling | Asap | Scheduled | Task | Resource | Projectids | Project | Prj | OpenParen | CloseParen | Hyphen | FloatingPointNumber | IntegerNumber | Identifier | String | DateTimeWithTimeZone | Space );";
        }
    }
 

}