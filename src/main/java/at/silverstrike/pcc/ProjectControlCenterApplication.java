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

package at.silverstrike.pcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

import com.google.inject.Injector;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

public class ProjectControlCenterApplication extends TPTApplication {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProjectControlCenterApplication.class);
    private static final String THEME = "pcc";

    private static final long serialVersionUID = 1L;

    private transient Persistence persistence;

    @Override
    public void close() {
        super.close();
        closeSession();
    }

    protected void closeSession() {
        if (this.persistence != null) {
            this.persistence.closeSession();
        }
    }

    @Override
    public void applicationInit() {
        LOGGER.info("PCC application starts");

        setTheme(THEME);
        TM.getDictionary().setDefaultLanguage("en");

        this.setUser("DP");

        InjectorFactory injectorFactory = new DefaultInjectorFactory();
        Injector injector = injectorFactory.createInjector();

        persistence = injector.getInstance(Persistence.class);

        persistence.openSession();

        final MainWindowFactory mainWindowFactory = injector
                .getInstance(MainWindowFactory.class);
        final MainWindow mainWindow = mainWindowFactory.create();

        mainWindow.setInjector(injector);
        mainWindow.initGui();

        setMainWindow(mainWindow.getWindow());
    }

    @Override
    public void firstApplicationStartup() {
    }
}
