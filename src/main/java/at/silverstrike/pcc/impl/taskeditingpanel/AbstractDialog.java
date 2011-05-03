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

package at.silverstrike.pcc.impl.taskeditingpanel;

import java.io.IOException;

import com.vaadin.Application;
import com.vaadin.ui.Window;

import eu.livotov.tpt.gui.windows.TPTWindow;
import eu.livotov.tpt.i18n.Dictionary;
import eu.livotov.tpt.i18n.TM;

/**
 * Abstract dialog, that is used for all option dialogs in this package. Provides support for
 * translation files initialization as well as show/hide methods.
 */
public abstract class AbstractDialog extends TPTWindow
{
    protected Window parentWindow;
    protected volatile boolean finishDialogProcessStarted;

    public AbstractDialog ( Application app )
    {
        this ( app.getMainWindow () );
    }

    public AbstractDialog ( Window parent )
    {
        super ();
        this.parentWindow = parent;
        setModal ( true );
//        initTranslatoins ();
    }

    private void initTranslatoins ()
    {
        Dictionary dict = TM.getDictionary ();

        try
        {
            dict.loadWords ( "en", "us",
                    AbstractDialog.class.getResource ( "i18n/en_us.properties" ), false );
            dict.loadWords ( "ru", "ru",
                    AbstractDialog.class.getResource ( "i18n/ru_ru.properties" ), false );
        }
        catch ( IOException io )
        {
            //no-op
        }
    }

    public void showDialog ()
    {
        finishDialogProcessStarted = false;
        parentWindow.addWindow ( this );
        center ();
    }

    public void hideDialog ()
    {
        try
        {
            parentWindow.removeWindow ( this );
        } catch (Throwable err)
        {
            err.printStackTrace();
        }
    }

}
